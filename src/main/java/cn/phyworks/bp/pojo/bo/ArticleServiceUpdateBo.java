package cn.phyworks.bp.pojo.bo;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * ArticleServiceUpdateBo class
 *
 * @author peixin
 * @date 2018/09/11
 */
@Data
public class ArticleServiceUpdateBo {

    /**
     * 自增 ID
     */
    @NotNull(message = "id不能为空")
    private Long id;

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
     * 标签id
     */
    private String tagIds;
}
