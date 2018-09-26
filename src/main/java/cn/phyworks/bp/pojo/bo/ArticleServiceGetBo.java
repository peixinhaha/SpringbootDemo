package cn.phyworks.bp.pojo.bo;

import cn.phyworks.bp.domain.TagDo;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

/**
 * ArticleServiceGetBo class
 *
 * @author peixin
 * @date 2018/09/12
 */
@Data
public class ArticleServiceGetBo {
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
     * 年份
     */
    private Integer year;

    /**
     * 月份
     */
    @Size(min = 2, max = 2, message = "月份必须是2位数")
    private String month;

    /**
     * 标签名
     */
    private List<TagDo> tagDos;

    /**
     * 创建时间
     */
    private Date gmtCreate;

    /**
     * 更新时间
     */
    private Date gmtModified;
}
