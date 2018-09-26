package cn.phyworks.bp.pojo.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * ArticleDto class
 *
 * @author peixin
 * @date 2018/09/11
 */
@Data
public class ArticleServiceSaveDto {

    /**
     * 内容
     */
    @NotBlank(message = "id不能为空")
    private String content;

    /**
     * 简介
     */
    @NotBlank(message = "id不能为空")
    private String introduction;

    /**
     * 标题
     */
    @NotBlank(message = "id不能为空")
    private String title;

    /**
     * 类别名称
     */
    @NotBlank(message = "id不能为空")
    private String typeName;

    /**
     * 类别id
     */
    @NotNull(message = "id不能为空")
    private Long typeId;

    /**
     * 年份
     */
    @NotNull(message = "id不能为空")
    private Integer year;

    /**
     * 月份
     */
    @NotBlank(message = "id不能为空")
    @Size(min = 2, max = 2, message = "月份必须是2位数")
    private String month;

    /**
     * 标签id
     */
    private String tagIds;
}
