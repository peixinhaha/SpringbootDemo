package cn.phyworks.bp.service;

import cn.phyworks.bp.dao.NoticeMapper;
import cn.phyworks.bp.domain.NoticeDo;
import cn.phyworks.bp.pojo.bo.NoticeServiceSaveBo;
import cn.phyworks.bp.pojo.bo.NoticeServiceUpdateBo;
import cn.phyworks.bp.pojo.dto.NoticeServiceSaveDto;
import cn.phyworks.bp.pojo.dto.NoticeServiceUpdateDto;
import cn.phyworks.bp.support.ExceptionEnum;
import cn.phyworks.bp.util.WebUtil;
import com.github.pagehelper.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

/**
 * 公告服务层
 *
 * @author shouxueyun@163.com
 * @date 2018.08.14
 */
@Slf4j
@Service
public class NoticeService {

    @Autowired
    private NoticeMapper noticeMapper;
    
    /**
     * 新增
     */
    @Transactional(rollbackFor = Exception.class)
    public NoticeServiceSaveBo save(@Validated NoticeServiceSaveDto noticeServiceSaveDto) throws Exception{

        // 赋值
        NoticeDo saveNoticeDo = new NoticeDo();
        saveNoticeDo.setContent(noticeServiceSaveDto.getContent());

        // 新增
        noticeMapper.save(saveNoticeDo);

        // 赋值
        NoticeServiceSaveBo noticeServiceSaveBo = new NoticeServiceSaveBo();
        // 获取新增的id
        noticeServiceSaveBo.setId(saveNoticeDo.getId());
        noticeServiceSaveBo.setContent(saveNoticeDo.getContent());
        noticeServiceSaveBo.setGmtCreate(saveNoticeDo.getGmtCreate());
        noticeServiceSaveBo.setGmtModified(saveNoticeDo.getGmtModified());
        noticeServiceSaveBo.setGmtDisabled(saveNoticeDo.getGmtDisabled());

        return noticeServiceSaveBo;
    }


    /**
     * 删除
     */
    public void remove(Long id) throws Exception {
        // 判断是否存在
        if (noticeMapper.get(id) == null) {
            WebUtil.throwBusinessException(ExceptionEnum.NOTICE_NOT_FOUND);
        }

        noticeMapper.remove(id);
    }

    /**
     * 更新
     */
    public NoticeServiceUpdateBo update(@Validated NoticeServiceUpdateDto noticeServiceUpdateDto) throws Exception {
        // 判断是否存在
        if (noticeMapper.get(noticeServiceUpdateDto.getId()) == null) {
            WebUtil.throwBusinessException(ExceptionEnum.NOTICE_NOT_FOUND);
        }

        // 赋值
        NoticeDo updateNoticeDo = new NoticeDo();
        updateNoticeDo.setId(noticeServiceUpdateDto.getId());
        updateNoticeDo.setContent(noticeServiceUpdateDto.getContent());

        // 更新
        noticeMapper.update(updateNoticeDo);
        NoticeDo returnNoticeDo = noticeMapper.get(noticeServiceUpdateDto.getId());

        // 赋值
        NoticeServiceUpdateBo noticeServiceUpdateBo = new NoticeServiceUpdateBo();
        noticeServiceUpdateBo.setId(returnNoticeDo.getId());
        noticeServiceUpdateBo.setContent(returnNoticeDo.getContent());
        noticeServiceUpdateBo.setGmtCreate(returnNoticeDo.getGmtCreate());
        noticeServiceUpdateBo.setGmtModified(returnNoticeDo.getGmtModified());
        noticeServiceUpdateBo.setGmtDisabled(returnNoticeDo.getGmtDisabled());

        return noticeServiceUpdateBo;
    }

    @Transactional(rollbackFor = Exception.class)
    public Page<NoticeDo> listPagination() throws Exception {
        return noticeMapper.listPagination();
    }
}
