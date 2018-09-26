package cn.phyworks.bp.pojo.bo;

import lombok.Data;

import java.util.Date;

@Data
public class NoticeServiceUpdateBo {

    /**
     * 自增 ID
     */
    private Long id;

    /**
     * 内容
     */
    private String content;

    /**
     * 创建时间
     */
    private Date gmtCreate;

    /**
     * 修改时间
     */
    private Date gmtModified;

    /**
     * 禁用时间
     */
    private Date gmtDisabled;
}
