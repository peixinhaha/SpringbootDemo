package cn.phyworks.bp.web;

import cn.phyworks.bp.dao.TagMapper;
import cn.phyworks.bp.domain.TagDo;
import cn.phyworks.bp.pojo.bo.TagServiceSaveBo;
import cn.phyworks.bp.pojo.dto.TagServiceSaveDto;
import cn.phyworks.bp.pojo.dto.TagServiceUpdateDto;
import cn.phyworks.bp.pojo.form.TagControllerSaveForm;
import cn.phyworks.bp.pojo.form.TagControllerUpdateForm;
import cn.phyworks.bp.pojo.vo.TagControllerSaveVo;
import cn.phyworks.bp.service.TagService;
import cn.phyworks.bp.support.Output;
import cn.phyworks.bp.util.WebUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 标签控制器
 *
 * @author shouxueyun@163.com
 * @date 2018.09.08
 */
@Slf4j
@RestController
@CrossOrigin
public class TagController {

    @Autowired
    private TagMapper tagMapper;

    @Autowired
    private TagService tagService;

    /**
     * 新增
     */
    @RequestMapping(value = "/tags", method = RequestMethod.POST)
    public Output save(@Validated TagControllerSaveForm tagControllerSaveForm) throws Exception {
        TagServiceSaveDto tagServiceSaveDto = new TagServiceSaveDto();
        log.info(tagControllerSaveForm.getName());
        tagServiceSaveDto.setName(tagControllerSaveForm.getName());

        TagServiceSaveBo tagServiceSaveBo = tagService.save(tagServiceSaveDto);

        TagControllerSaveVo tagControllerSaveVo = new TagControllerSaveVo();
        tagControllerSaveVo.setId(tagServiceSaveBo.getId());
        tagControllerSaveVo.setName(tagServiceSaveBo.getName());

        return WebUtil.outputSuccess(tagControllerSaveVo);
    }

    /**
     * 删除
     */
    @RequestMapping(value = "/tags/{id}", method = RequestMethod.DELETE)
    public Output remove(@PathVariable Long id) throws Exception {
        tagService.remove(id);
        return WebUtil.outputSuccess();
    }

    /**
     * 更新
     */
    @RequestMapping(value = "/tags/{id}", method = RequestMethod.PATCH)
    public Output update(@PathVariable Long id, @Validated TagControllerUpdateForm tagControllerUpdateForm) throws Exception {
        TagServiceUpdateDto tagServiceUpdateDto = new TagServiceUpdateDto();
        tagServiceUpdateDto.setId(id);
        tagServiceUpdateDto.setName(tagControllerUpdateForm.getName());

        return WebUtil.outputSuccess(tagService.update(tagServiceUpdateDto));
    }

    /**
     * 列表
     */
    @RequestMapping(value = "/tags", method = RequestMethod.GET)
    public Output listPagination(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                 @RequestParam(value = "page_size", defaultValue = "20") Integer pageSize,
                                 @RequestParam(value = "page_type", defaultValue = "") String page_type) throws Exception {

        if (page_type.equals(String.valueOf("none"))) {
            return WebUtil.outputSuccess(tagService.listPagination());
        }
        PageHelper.startPage(page, pageSize);
        Page<TagDo> tags = tagService.listPagination();
        PageInfo<TagDo> pageInfo = new PageInfo<>(tags);

        return WebUtil.outputSuccess(tags, pageInfo);
    }
}
