package cn.phyworks.bp.pojo.bo;

import lombok.Data;

import java.util.Date;

@Data
public class TypeServiceUpdateBo {

    /**
     * 自增 ID
     */
    private Long id;

    /**
     * 分类名
     */
    private String name;

    /**
     * 使用总量
     */
    private Number usedTotal;

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
