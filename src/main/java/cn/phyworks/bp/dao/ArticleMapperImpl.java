package cn.phyworks.bp.dao;

import cn.phyworks.bp.domain.ArticleDo;
import cn.phyworks.bp.support.Constant;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.stereotype.Repository;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * ArticleMapperImpl class
 *
 * @author peixin
 * @date 2018/09/10
 */
@Repository
@Slf4j
public class ArticleMapperImpl {

    private final String TABLE = "article";

    private String NowTime = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now());

    /**
     * 添加操作，返回新增元素的 ID
     *
     * @param articleDo ArticleDo
     * @return sql
     */
    public String save(@Validated ArticleDo articleDo) {
        SQL sql = new SQL()
                .INSERT_INTO(TABLE)
                .VALUES("year", "#{year}")
                .VALUES("month", "#{month}")
                .VALUES("content", "#{content}")
                .VALUES("introduction", "#{introduction}")
                .VALUES("title", "#{title}")
                .VALUES("type_name", "#{typeName}")
                .VALUES("type_id", "#{typeId}")
                .VALUES("gmt_disabled", "'" + Constant.DISABLE_DATETIME + "'")
                .VALUES("gmt_modified", "'" + NowTime + "'")
                .VALUES("gmt_create", "'" + NowTime + "'");
        return sql.toString();
    }

    /**
     * 删除
     *
     * @param id Long
     * @return sql
     */
    public String remove(Long id) {
        SQL sql = new SQL()
                .UPDATE(TABLE)
                .SET("gmt_disabled = '" + NowTime + "'")
                .WHERE("id = " + id);
        return sql.toString();
    }

    /**
     * 更新
     *
     * @param articleDo ArticleDo
     * @return sql
     */
    public String update(ArticleDo articleDo) {
        Boolean flag = false;
        SQL sql = new SQL()
                .UPDATE(TABLE)
                .WHERE("gmt_disabled = '" + Constant.DISABLE_DATETIME + "'")
                .WHERE("id = #{id}");
        if (articleDo.getContent() != null) {
            flag = true;
            sql.SET("content = #{content}");
        }

        if (articleDo.getIntroduction() != null) {
            flag = true;
            sql.SET("introduction = #{introduction}");
        }

        if (articleDo.getTitle() != null) {
            flag = true;
            sql.SET("title = #{title}");
        }

        if (articleDo.getTypeId() != null) {
            flag = true;
            sql.SET("type_name = #{typeName}");
            sql.SET("type_id = #{typeId}");
        }

        if (!flag) {
            return new SQL()
                    .UPDATE(TABLE)
                    .SET("gmt_modified = '" + NowTime + "'")
                    .WHERE("false")
                    .toString();
        }
        sql.SET("gmt_modified = '" + NowTime + "'");
        return sql.toString();
    }

    /**
     * 根据id查询
     *
     * @param id Long
     * @return sql
     */
    public String getById(Long id) {
        SQL sql = new SQL()
                .SELECT("*")
                .FROM(TABLE)
                .WHERE("id = #{id}");
        return sql.toString();
    }

    /**
     * 查询分页
     *
     * @param
     * @return sql
     */
    public String listPagination() {
        SQL sql = new SQL()
                .SELECT("id, title, year, month, introduction, type_id, type_name, gmt_create")
                .FROM(TABLE)
                .WHERE("article.gmt_disabled = '" + Constant.DISABLE_DATETIME + "'")
                .ORDER_BY("article.id");
        return sql.toString();
    }

    /**
     * 根据分类查询分页
     *
     * @param id Long
     * @return sql
     */
    public String listPaginationByType(Long id) {
        SQL sql = new SQL()
                .SELECT("id, title, year, month, introduction, type_name, gmt_create")
                .FROM(TABLE)
                .WHERE("type_id = #{id}")
                .WHERE("gmt_disabled = '" + Constant.DISABLE_DATETIME + "'")
                .ORDER_BY("id");

        return sql.toString();
    }

    /**
     * 根据标签查询分页
     *
     * @param tagId String
     * @return sql
     */
    public String listPaginationByTag(String tagId) {
        SQL sql = new SQL()
                .SELECT("*")
                .FROM(TABLE)
                .LEFT_OUTER_JOIN("article_tag on article.id = article_tag.article_id where article_tag.tag_id = " + tagId)
                .ORDER_BY("article.id");
        return sql.toString();
    }

    /**
     * 根据归档查询分页
     *
     * @param year String, month Long
     * @return sql
     */
    public String listPaginationByTime(Integer year, String month) {
        SQL sql = new SQL()
                .SELECT("id, year, month, title")
                .FROM(TABLE)
                .WHERE("year = " + year)
                .WHERE("month = " + month)
                .WHERE("gmt_disabled = '" + Constant.DISABLE_DATETIME + "'")
                .ORDER_BY("id");

        return sql.toString();
    }
}
