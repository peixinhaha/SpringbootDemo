package cn.phyworks.bp.domain;

import lombok.Data;

import javax.validation.constraints.Size;
import java.util.Date;

/**
 * 归档表
 *
 * @author peixin
 * @date 2018.09.06
 */
@Data
public class ArrangementDo {

    /**
     * 自增 ID
     */
    private Long id;

    /**
     * 使用总量
     */
    private Integer usedTotal;

    /**
     * 年份
     */
    private Integer year;

    /**
     * 月份
     */
    @Size(min = 2, max = 2, message = "月份必须是2位数")
    private String month;

    /**
     * 创建时间
     */
    private Date gmtCreate;

    /**
     * 更新时间
     */
    private Date gmtModified;
}