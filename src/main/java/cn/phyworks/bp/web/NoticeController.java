package cn.phyworks.bp.web;

import cn.phyworks.bp.dao.NoticeMapper;
import cn.phyworks.bp.domain.NoticeDo;
import cn.phyworks.bp.pojo.bo.NoticeServiceSaveBo;
import cn.phyworks.bp.pojo.dto.NoticeServiceSaveDto;
import cn.phyworks.bp.pojo.dto.NoticeServiceUpdateDto;
import cn.phyworks.bp.pojo.form.NoticeControllerSaveForm;
import cn.phyworks.bp.pojo.form.NoticeControllerUpdateForm;
import cn.phyworks.bp.pojo.vo.NoticeControllerSaveVo;
import cn.phyworks.bp.service.NoticeService;
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
 * 公告控制器
 *
 * @author shouxueyun@163.com
 * @date 2018.09.10
 */
@Slf4j
@RestController
@CrossOrigin
public class NoticeController {

    @Autowired
    private NoticeService noticeService;

    @Autowired
    private NoticeMapper noticeMapper;

    /**
     * 删除
     */
    @RequestMapping(value = "/notices/{id}", method = RequestMethod.DELETE)
    public Output remove(@PathVariable Long id) throws Exception {
        noticeService.remove(id);
        return WebUtil.outputSuccess();
    }

    /**
     * 更新
     */
    @RequestMapping(value = "/notices/{id}", method = RequestMethod.PATCH)
    public Output update(@PathVariable Long id, @Validated NoticeControllerUpdateForm noticeControllerUpdateForm) throws Exception {
        NoticeServiceUpdateDto noticeServiceUpdateDto = new NoticeServiceUpdateDto();
        noticeServiceUpdateDto.setId(id);
        noticeServiceUpdateDto.setContent(noticeControllerUpdateForm.getContent());

        return WebUtil.outputSuccess(noticeService.update(noticeServiceUpdateDto));
    }

    /**
     * 列表
     */
    @RequestMapping(value = "/notices", method = RequestMethod.GET)
    public Output listPagination(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                 @RequestParam(value = "page_size", defaultValue = "20") Integer pageSize,
                                 @RequestParam(value = "page_type", defaultValue = "") String page_type) throws Exception {

        if (page_type.equals(String.valueOf("none"))) {
            return WebUtil.outputSuccess(noticeService.listPagination());
        }
        PageHelper.startPage(page, pageSize);
        Page<NoticeDo> notices = noticeService.listPagination();
        PageInfo<NoticeDo> pageInfo = new PageInfo<>(notices);
        return WebUtil.outputSuccess(notices, pageInfo);
    }

    /**
     * 新增
     */
    @RequestMapping(value = "/notices", method = RequestMethod.POST)
    public Output save(@Validated NoticeControllerSaveForm noticeControllerSaveForm) throws Exception {
        NoticeServiceSaveDto noticeServiceSaveDto = new NoticeServiceSaveDto();
        noticeServiceSaveDto.setContent(noticeControllerSaveForm.getContent());

        NoticeServiceSaveBo noticeServiceSaveBo = noticeService.save(noticeServiceSaveDto);

        NoticeControllerSaveVo noticeControllerSaveVo = new NoticeControllerSaveVo();
        noticeControllerSaveVo.setId(noticeServiceSaveBo.getId());
        noticeControllerSaveVo.setContent(noticeServiceSaveBo.getContent());

        return WebUtil.outputSuccess(noticeControllerSaveVo);
    }
}
