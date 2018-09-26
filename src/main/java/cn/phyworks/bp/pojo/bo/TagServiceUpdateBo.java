package cn.phyworks.bp.pojo.bo;

import lombok.Data;

import java.util.Date;

@Data
public class TagServiceUpdateBo {

    /**
     * 自增 ID
     */
    private Long id;

    /**
     * 标签名
     */
    private String name;

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
