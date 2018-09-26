package cn.phyworks.bp.dao;

import cn.phyworks.bp.domain.ArticleTagDo;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.stereotype.Repository;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * ArticleMapperImpl class
 *
 * @author peixin
 * @date 2018/09/11
 */
@Repository
public class ArticleTagMapperImpl {
    private final String TABLE = "article_tag";

    private String NowTime = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now());

    /**
     * 新增
     *
     * @param articleTagDo ArticleTagDo
     * @return sql
     */
    public String save(@Validated ArticleTagDo articleTagDo) {
        SQL sql = new SQL()
                .INSERT_INTO(TABLE)
                .VALUES("article_id", "#{articleId}")
                .VALUES("tag_id", "#{tagId}")
                .VALUES("gmt_modified", "'" + NowTime + "'")
                .VALUES("gmt_create", "'" + NowTime + "'");
        return sql.toString();
    }

    /**
     * 删除
     *
     * @param articleId Long, tagId Long
     * @return sql
     */
    public String remove(Long articleId) {
        SQL sql = new SQL()
                .DELETE_FROM(TABLE)
                .WHERE("article_id = #{articleId}");
        return sql.toString();
    }

    /**
     * 查询
     *
     * @param articleId Long
     * @return sql
     */
    public String list(Long articleId) {
        SQL sql = new SQL()
                .SELECT("tag_id")
                .FROM(TABLE)
                .WHERE("article_id = #{articleId}");
        return sql.toString();
    }

    /**
     * 查询
     *
     * @param tagId Long
     * @return sql
     */
    public String listById(Long tagId) {
        SQL sql = new SQL()
                .SELECT("article_id")
                .FROM(TABLE)
                .WHERE("tag_id = #{tagId}");
        return sql.toString();
    }
}
