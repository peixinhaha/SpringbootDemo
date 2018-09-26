package cn.phyworks.bp.service;

import cn.phyworks.bp.dao.*;
import cn.phyworks.bp.domain.ArrangementDo;
import cn.phyworks.bp.domain.ArticleDo;
import cn.phyworks.bp.domain.ArticleTagDo;
import cn.phyworks.bp.domain.TagDo;
import cn.phyworks.bp.pojo.bo.ArticleServiceGetBo;
import cn.phyworks.bp.pojo.bo.ArticleServiceSaveBo;
import cn.phyworks.bp.pojo.bo.ArticleServiceUpdateBo;
import cn.phyworks.bp.pojo.dto.ArticleServiceSaveDto;
import cn.phyworks.bp.pojo.dto.ArticleServiceUpdateDto;
import cn.phyworks.bp.support.ExceptionEnum;
import cn.phyworks.bp.util.WebUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;
import java.util.List;

/**
 * ArticleService class
 *
 * @author peixin
 * @date 2018/09/11
 */
@Service
@Slf4j
public class ArticleService {
    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private ArrangementMapper arrangementMapper;

    @Autowired
    private ArticleTagMapper articleTagMapper;

    @Autowired
    private TypeMapper typeMapper;

    @Autowired
    private TagMapper tagMapper;

    /**
     * 新增
     */
    @Transactional(rollbackFor = Exception.class)
    public ArticleServiceSaveBo save(@Validated ArticleServiceSaveDto articleServiceSaveDto) throws Exception {
        ArticleDo articleDo = new ArticleDo();
        articleDo.setContent(articleServiceSaveDto.getContent());
        articleDo.setIntroduction(articleServiceSaveDto.getIntroduction());
        articleDo.setMonth(articleServiceSaveDto.getMonth());
        articleDo.setYear(articleServiceSaveDto.getYear());
        articleDo.setTitle(articleServiceSaveDto.getTitle());
        articleDo.setTypeId(articleServiceSaveDto.getTypeId());
        articleDo.setTypeName(articleServiceSaveDto.getTypeName());
        // 新增文章
        articleMapper.save(articleDo);

        log.info("{}", articleDo.getId());
        // 分类数量 + 1
        typeMapper.updateUsedTotal(articleServiceSaveDto.getTypeId(), "A");

        // 归档
        ArrangementDo arrangementDo = arrangementMapper.getByYearAndMonth(articleServiceSaveDto.getYear(), articleServiceSaveDto.getMonth());
        if (arrangementDo != null) {
            // 存在则 + 1
            arrangementMapper.update(arrangementDo.getId(), "A");
        } else {
            // 不存在则 - 1
            ArrangementDo arrangementNewDo = new ArrangementDo();
            arrangementNewDo.setYear(articleServiceSaveDto.getYear());
            arrangementNewDo.setMonth(articleServiceSaveDto.getMonth());
            arrangementMapper.save(arrangementNewDo);
        }

        // 文章标签表 + 1
        String tagIds = articleServiceSaveDto.getTagIds();
        String[] tagsArr = tagIds.split(",");
        for (int i = 0; i < tagsArr.length; i++) {
            ArticleTagDo articleTagDo = new ArticleTagDo();
            articleTagDo.setArticleId(articleDo.getId());
            articleTagDo.setTagId(Long.valueOf(tagsArr[i]));
            articleTagMapper.save(articleTagDo);
        }

        ArticleServiceSaveBo articleServiceSaveBo = new ArticleServiceSaveBo();
        articleServiceSaveBo.setId(articleDo.getId());
        articleServiceSaveBo.setContent(articleDo.getContent());
        articleServiceSaveBo.setIntroduction(articleDo.getIntroduction());
        articleServiceSaveBo.setMonth(articleDo.getMonth());
        articleServiceSaveBo.setYear(articleDo.getYear());
        articleServiceSaveBo.setTitle(articleDo.getTitle());
        articleServiceSaveBo.setTypeId(articleDo.getTypeId());
        articleServiceSaveBo.setTypeName(articleDo.getTypeName());

        return articleServiceSaveBo;
    }

    /**
     * 更新
     */
    @Transactional(rollbackFor = Exception.class)
    public ArticleServiceUpdateBo update(@Validated ArticleServiceUpdateDto articleServiceUpdateDto) throws Exception {
        ArticleDo articleDo = new ArticleDo();
        articleDo.setId(articleServiceUpdateDto.getId());
        articleDo.setContent(articleServiceUpdateDto.getContent());
        articleDo.setIntroduction(articleServiceUpdateDto.getIntroduction());
        articleDo.setTitle(articleServiceUpdateDto.getTitle());
        articleDo.setTypeId(articleServiceUpdateDto.getTypeId());
        articleDo.setTypeName(articleServiceUpdateDto.getTypeName());

        // 获取文章原始数据
        Long articleId = articleDo.getId();
        ArticleDo articleDo1 = articleMapper.getById(articleId);

        if (articleDo1 == null) {
            WebUtil.throwBusinessException(ExceptionEnum.ARTICLE_NOT_FOUND);
        }

        // 分类数量更新
        Long typeId = articleDo1.getTypeId();
        if (!typeId.equals(articleDo.getTypeId())) {
            // 如分类修改，则原始分类-1，新增分类+1
            typeMapper.updateUsedTotal(articleDo.getTypeId(), "A");
            typeMapper.updateUsedTotal(typeId, "I");
        }

        // 标签更新
        articleTagMapper.remove(articleId);
        String tagIds = articleServiceUpdateDto.getTagIds();
        String[] tagsArr = tagIds.split(",");
        for (int i = 0; i < tagsArr.length; i++) {
            ArticleTagDo articleTagDo = new ArticleTagDo();
            articleTagDo.setArticleId(articleId);
            articleTagDo.setTagId(Long.valueOf(tagsArr[i]));
            articleTagMapper.save(articleTagDo);
        }

        // 更新文章信息
        articleMapper.update(articleDo);

        ArticleServiceUpdateBo articleServiceUpdateBo = new ArticleServiceUpdateBo();
        articleServiceUpdateBo.setId(articleDo.getId());
        articleServiceUpdateBo.setContent(articleDo.getContent());
        articleServiceUpdateBo.setIntroduction(articleDo.getIntroduction());
        articleServiceUpdateBo.setTitle(articleDo.getTitle());
        articleServiceUpdateBo.setTypeId(articleDo.getTypeId());
        articleServiceUpdateBo.setTypeName(articleDo.getTypeName());

        return articleServiceUpdateBo;
    }

    /**
     * 删除
     */
    @Transactional(rollbackFor = Exception.class)
    public void remove(Long id) throws Exception {
        // 获取原始数据
        ArticleDo articleDo = articleMapper.getById(id);

        if (articleDo == null) {
            WebUtil.throwBusinessException(ExceptionEnum.ARTICLE_NOT_FOUND);
        }

        // 对应分类数量 - 1
        Long typeId = articleDo.getTypeId();
        typeMapper.updateUsedTotal(typeId, "I");

        // 归档表 -1
        ArrangementDo arrangementDo = arrangementMapper.getByYearAndMonth(articleDo.getYear(), articleDo.getMonth());

        if (arrangementDo != null) {
            // 存在则 - 1
            arrangementMapper.update(arrangementDo.getId(), "I");
        }

        // 文章标签表删除
        articleTagMapper.remove(id);

        // 文章删除
        articleMapper.remove(id);
    }

    /**
     * 根据id查询
     */
    @Transactional(rollbackFor = Exception.class)
    public ArticleServiceGetBo getById(Long id) throws Exception {
        // 获取原始数据
        ArticleDo articleDo = articleMapper.getById(id);

        if (articleDo == null) {
            WebUtil.throwBusinessException(ExceptionEnum.ARTICLE_NOT_FOUND);
        }

        List<TagDo> tagDos = tagMapper.listByIds(String.valueOf(id));

        ArticleServiceGetBo articleServiceGetBo = new ArticleServiceGetBo();
        articleServiceGetBo.setId(articleDo.getId());
        articleServiceGetBo.setIntroduction(articleDo.getIntroduction());
        articleServiceGetBo.setContent(articleDo.getContent());
        articleServiceGetBo.setTitle(articleDo.getTitle());
        articleServiceGetBo.setTypeName(articleDo.getTypeName());
        articleServiceGetBo.setTypeId(articleDo.getTypeId());
        articleServiceGetBo.setGmtCreate(articleDo.getGmtCreate());
        articleServiceGetBo.setGmtModified(articleDo.getGmtModified());
        articleServiceGetBo.setTagDos(tagDos);
        articleServiceGetBo.setYear(articleDo.getYear());
        articleServiceGetBo.setMonth(articleDo.getMonth());

        return articleServiceGetBo;
    }

    @Transactional(rollbackFor = Exception.class)
    public List<ArticleServiceGetBo> listPaginationByType(String type, String param) throws Exception {
        log.info("{}", param);
        List<ArticleDo> articleDos = new ArrayList<>();
        if (type.equals("tag")) {
            articleDos = articleMapper.listPaginationByTag(param);
        } else if (type.equals("time") && !param.equals("all")) {
            Integer year = Integer.valueOf(param.substring(0, 4));
            String month = param.substring(4, 6);
            articleDos = articleMapper.listPaginationByTime(year, month);
        } else if (type.equals("type")) {
            Long id = Long.valueOf(param);
            articleDos = articleMapper.listPaginationByType(id);
        } else {
            articleDos = articleMapper.listPagination();
        }

        List<ArticleServiceGetBo> articleServiceGetBos = new ArrayList<>();
        for (int i = 0; i < articleDos.size(); i++) {
            ArticleServiceGetBo articleServiceGetBo = new ArticleServiceGetBo();
            ArticleDo articleDo = articleDos.get(i);
            List<TagDo> tagDos = tagMapper.listByIds(String.valueOf(articleDo.getId()));
            articleServiceGetBo.setId(articleDo.getId());
            articleServiceGetBo.setIntroduction(articleDo.getIntroduction());
            articleServiceGetBo.setContent(articleDo.getContent());
            articleServiceGetBo.setTitle(articleDo.getTitle());
            articleServiceGetBo.setTypeName(articleDo.getTypeName());
            articleServiceGetBo.setTypeId(articleDo.getTypeId());
            articleServiceGetBo.setGmtCreate(articleDo.getGmtCreate());
            articleServiceGetBo.setGmtModified(articleDo.getGmtModified());
            articleServiceGetBo.setTagDos(tagDos);
            articleServiceGetBo.setYear(articleDo.getYear());
            articleServiceGetBo.setMonth(articleDo.getMonth());
            articleServiceGetBos.add(articleServiceGetBo);
        }
        return articleServiceGetBos;
    }

}
