package cn.phyworks.bp.domain;

import lombok.Data;

import java.util.Date;

/**
 * 文章标签表
 *
 * @author peixin
 * @date 2018.09.06
 */
@Data
public class ArticleTagDo {

    /**
     * 自增 ID
     */
    private Long id;

    /**
     * 文章ID
     */
    private Long articleId;

    /**
     * 标签ID
     */
    private Long tagId;

    /**
     * 创建时间
     */
    private Date gmtCreate;

    /**
     * 更新时间
     */
    private Date gmtModified;
}