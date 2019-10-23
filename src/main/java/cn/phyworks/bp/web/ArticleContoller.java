package cn.phyworks.bp.web;

import cn.phyworks.bp.pojo.bo.ArticleServiceGetBo;
import cn.phyworks.bp.pojo.bo.ArticleServiceSaveBo;
import cn.phyworks.bp.pojo.bo.ArticleServiceUpdateBo;
import cn.phyworks.bp.pojo.dto.ArticleServiceSaveDto;
import cn.phyworks.bp.pojo.dto.ArticleServiceUpdateDto;
import cn.phyworks.bp.pojo.form.ArticleControllerSaveForm;
import cn.phyworks.bp.pojo.form.ArticleControllerUpdateForm;
import cn.phyworks.bp.service.ArticleService;
import cn.phyworks.bp.support.Output;
import cn.phyworks.bp.util.WebUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ArticleController class
 *
 * @author peixin
 * @date 2018/09/11
 */
@RestController
@CrossOrigin
@Slf4j
@Api(tags = {"文章"})
public class ArticleContoller {
    @Autowired
    private ArticleService articleService;

    /**
     * 新增
     *
     * @param articleControllerSaveForm ArticleControllerSaveForm
     * @return Output
     */
    @ApiOperation("新增")
    @RequestMapping(value = "/articles", method = RequestMethod.POST)
    public Output save(@Validated ArticleControllerSaveForm articleControllerSaveForm) throws Exception {
        ArticleServiceSaveDto articleServiceSaveDto = new ArticleServiceSaveDto();

        articleServiceSaveDto.setContent(articleControllerSaveForm.getContent());
        articleServiceSaveDto.setIntroduction(articleControllerSaveForm.getIntroduction());
        articleServiceSaveDto.setMonth(articleControllerSaveForm.getMonth());
        articleServiceSaveDto.setYear(articleControllerSaveForm.getYear());
        articleServiceSaveDto.setTitle(articleControllerSaveForm.getTitle());
        articleServiceSaveDto.setTypeId(articleControllerSaveForm.getTypeId());
        articleServiceSaveDto.setTypeName(articleControllerSaveForm.getTypeName());
        articleServiceSaveDto.setTagIds(articleControllerSaveForm.getTagIds());

        ArticleServiceSaveBo articleServiceSaveBo = articleService.save(articleServiceSaveDto);

        return WebUtil.outputSuccess(articleServiceSaveBo);
    }

    /**
     * 更新
     *
     * @param articleControllerUpdateForm ArticleControllerUpdateForm
     * @return Output
     */
    @ApiOperation("更新")
    @RequestMapping(value = "/articles/{id}", method = RequestMethod.PATCH)
    public Output update(@PathVariable Long id, @Validated ArticleControllerUpdateForm articleControllerUpdateForm) throws Exception {
        ArticleServiceUpdateDto articleServiceUpdateDto = new ArticleServiceUpdateDto();

        articleServiceUpdateDto.setContent(articleControllerUpdateForm.getContent());
        articleServiceUpdateDto.setIntroduction(articleControllerUpdateForm.getIntroduction());
        articleServiceUpdateDto.setTitle(articleControllerUpdateForm.getTitle());
        articleServiceUpdateDto.setTypeId(articleControllerUpdateForm.getTypeId());
        articleServiceUpdateDto.setTypeName(articleControllerUpdateForm.getTypeName());
        articleServiceUpdateDto.setTagIds(articleControllerUpdateForm.getTagIds());
        articleServiceUpdateDto.setId(id);

        ArticleServiceUpdateBo articleServiceUpdateBo = articleService.update(articleServiceUpdateDto);

        return WebUtil.outputSuccess(articleServiceUpdateBo);
    }

    /**
     * 删除
     *
     * @param id Long
     * @return Output
     */
    @ApiOperation("删除")
    @RequestMapping(value = "/articles/{id}", method = RequestMethod.DELETE)
    public Output remove(@PathVariable Long id) throws Exception {
        articleService.remove(id);
        return WebUtil.outputSuccess();
    }

    /**
     * 根据id查询
     *
     * @param id Long
     * @return Output
     */
    @ApiOperation("根据id查询")
    @RequestMapping(value = "/articles/{id}", method = RequestMethod.GET)
    public Output get(@PathVariable Long id) throws Exception {
        return WebUtil.outputSuccess(articleService.getById(id));
    }

    /**
     * 查询分页
     *
     * @param type String, id Long
     * @return Output
     */
    @ApiOperation("查询分页")
    @RequestMapping(value = "articles/{type}/{param}", method = RequestMethod.GET)
    public Output listPagination(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                 @RequestParam(value = "page_size", defaultValue = "20") Integer pageSize,
                                 @PathVariable String param,
                                 @PathVariable String type) throws Exception {
        PageHelper.startPage(page, pageSize);
        List<ArticleServiceGetBo> articleServiceGetBos = articleService.listPaginationByType(type, param);
        PageInfo<ArticleServiceGetBo> pageInfo = new PageInfo<ArticleServiceGetBo>(articleServiceGetBos);
        return WebUtil.outputSuccess(articleServiceGetBos, pageInfo);
    }
}
