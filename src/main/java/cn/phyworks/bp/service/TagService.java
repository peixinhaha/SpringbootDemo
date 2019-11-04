package cn.phyworks.bp.service;

import cn.phyworks.bp.annotation.TransactionalForException;
import cn.phyworks.bp.dao.TagMapper;
import cn.phyworks.bp.domain.TagDo;
import cn.phyworks.bp.pojo.bo.TagServiceSaveBo;
import cn.phyworks.bp.pojo.bo.TagServiceUpdateBo;
import cn.phyworks.bp.pojo.dto.TagServiceSaveDto;
import cn.phyworks.bp.pojo.dto.TagServiceUpdateDto;
import cn.phyworks.bp.support.ExceptionEnum;
import cn.phyworks.bp.util.WebUtil;
import com.github.pagehelper.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

/**
 * 标签服务层
 *
 * @author shouxueyun@163.com
 * @date 2018.08.14
 */
@Slf4j
@Service
@CacheConfig(cacheNames = "tagCache")
@TransactionalForException
public class TagService {

    @Autowired
    private TagMapper tagMapper;

    /**
     * 标签新增
     */
    @CachePut(key = "'all'")
    public TagServiceSaveBo save(@Validated TagServiceSaveDto tagServiceSaveDto) throws Exception{
        // 判断重复
        if (tagMapper.getByName(tagServiceSaveDto.getName()) != null) {
            WebUtil.throwBusinessException(ExceptionEnum.TAG_ALREADY_EXISTS);
        }

        // 赋值
        TagDo saveTagDo = new TagDo();
        saveTagDo.setName(tagServiceSaveDto.getName());

        // 标签新增
        tagMapper.save(saveTagDo);

        // 赋值
        TagServiceSaveBo tagServiceSaveBo = new TagServiceSaveBo();
        // 获取新增的id
        tagServiceSaveBo.setId(saveTagDo.getId());
        tagServiceSaveBo.setName(saveTagDo.getName());
        tagServiceSaveBo.setGmtCreate(saveTagDo.getGmtCreate());
        tagServiceSaveBo.setGmtModified(saveTagDo.getGmtModified());
        tagServiceSaveBo.setGmtDisabled(saveTagDo.getGmtDisabled());

        return tagServiceSaveBo;
    }

    /**
     * 删除
     */
    @CacheEvict(key = "'all'")
    public void remove(Long id) throws Exception {
        // 判断是否存在
        if (tagMapper.get(id) == null) {
            WebUtil.throwBusinessException(ExceptionEnum.TAG_NOT_FOUND);
        }

        tagMapper.remove(id);
    }

    /**
     * 更新
     */
    @CachePut(key = "'all'")
    public TagServiceUpdateBo update(@Validated TagServiceUpdateDto tagServiceUpdateDto) throws Exception {
        // 判断是否存在
        if (tagMapper.get(tagServiceUpdateDto.getId()) == null) {
            WebUtil.throwBusinessException(ExceptionEnum.TAG_NOT_FOUND);
        }

        // 赋值
        TagDo updateTagDo = new TagDo();
        updateTagDo.setId(tagServiceUpdateDto.getId());
        updateTagDo.setName(tagServiceUpdateDto.getName());

        // 更新
        tagMapper.update(updateTagDo);
        TagDo returnTagDo = tagMapper.get(tagServiceUpdateDto.getId());

        // 赋值
        TagServiceUpdateBo tagServiceUpdateBo = new TagServiceUpdateBo();
        tagServiceUpdateBo.setId(returnTagDo.getId());
        tagServiceUpdateBo.setName(returnTagDo.getName());
        tagServiceUpdateBo.setGmtCreate(returnTagDo.getGmtCreate());
        tagServiceUpdateBo.setGmtModified(returnTagDo.getGmtModified());
        tagServiceUpdateBo.setGmtDisabled(returnTagDo.getGmtDisabled());

        return tagServiceUpdateBo;
    }

    @Cacheable(key = "'all'")
    public Page<TagDo> listPagination() throws Exception {
        return tagMapper.listPagination();
    }
}
