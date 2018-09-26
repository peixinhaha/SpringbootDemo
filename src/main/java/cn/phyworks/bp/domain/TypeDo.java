package cn.phyworks.bp.domain;

import lombok.Data;
import java.util.Date;

/**
 * 分类表
 *
 * @author peixin
 * @date 2018.09.06
 */
@Data
public class TypeDo {

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
    private Integer usedTotal;

    /**
     * 禁用时间
     */
    private Date gmtDisabled;

    /**
     * 创建时间
     */
    private Date gmtCreate;

    /**
     * 更新时间
     */
    private Date gmtModified;
}
