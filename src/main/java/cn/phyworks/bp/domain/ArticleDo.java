package cn.phyworks.bp.domain;

import lombok.Data;

import javax.validation.constraints.Size;
import java.util.Date;

/**
 * 文章表
 *
 * @author peixin
 * @date 2018.09.06
 */
@Data
public class ArticleDo {

    /**
     * 自增 ID
     */
    private Long id;

    /**
     * 内容
     */
    private String content;

    /**
     * 简介
     */
    private String introduction;

    /**
     * 标题
     */
    private String title;

    /**
     * 类别名称
     */
    private String typeName;

    /**
     * 类别id
     */
    private Long typeId;

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
     * 禁用时间
     */
    private Date gmtDisabled;

    /**
     * 更新时间
     */
    private Date gmtModified;
}