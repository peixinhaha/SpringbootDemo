package cn.phyworks.bp.dao;

import cn.phyworks.bp.domain.TagDo;
import cn.phyworks.bp.support.Constant;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.stereotype.Repository;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * TagMapper 实现
 *
 * @author shouxueyun@163.com
 * @date 2018.09.08
 */
@Slf4j
@Repository
public class TagMapperImpl {

    private final String TABLE = "tag";
    private String NowTime = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now());

    /**
     * 添加操作，返回新增元素的 ID
     *
     * @param tagDo TagDo
     * @return sql
     */
    public String save(@Validated TagDo tagDo) {
        return "insert ignore into " + TABLE + "(name, gmt_create, gmt_modified, gmt_disabled) values(#{name}, '" + NowTime + "', '" + NowTime + "', '" + Constant.DISABLE_DATETIME +"')";
    }

    /**
     * 删除操作
     *
     * @param id ID
     * @return sql
     */
    public String remove(@Validated Long id) {
        SQL sql = new SQL()
                .UPDATE(TABLE)
                .SET("gmt_disabled = '" + DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now()) + "'")
                .WHERE("id = #{id}")
                .WHERE("gmt_disabled = '" + Constant.DISABLE_DATETIME + "'");

        return sql.toString();
    }

    /**
     * 更新操作
     *
     * @param tagDo TagDo
     * @return sql
     */
    public String update(@Validated TagDo tagDo) {
        Boolean flag = false;

        SQL sql = new SQL()
                .UPDATE(TABLE)
                .WHERE("id = #{id}")
                .WHERE("gmt_disabled = '" + Constant.DISABLE_DATETIME + "'");
        if (tagDo.getName() != null) {
            flag = true;
            sql.SET("name = #{name}");
        }

        if (! flag) {
            return new SQL()
                    .UPDATE(TABLE)
                    .SET("gmt_modified = '" + DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now()) + "'")
                    .WHERE("false")
                    .toString();
        }

        return sql.toString();
    }

    /**
     * 根据标签名查询单个
     *
     * @return sql
     */
    public String getByName(String name) {
        SQL sql = new SQL()
                .SELECT("*")
                .FROM(TABLE)
                .WHERE("name = #{name}")
                .WHERE("gmt_disabled = '" + Constant.DISABLE_DATETIME + "'");

        return sql.toString();
    }

    /**
     * 根据 ID 查询单个
     *
     * @return sql
     */
    public String get(Long id) {
        SQL sql = new SQL()
                .SELECT("*")
                .FROM(TABLE)
                .WHERE("id = #{id}")
                .WHERE("gmt_disabled = '" + Constant.DISABLE_DATETIME + "'");

        return sql.toString();
    }

    /**
     * 查询
     *
     * @return sql
     */
    public String listByIds(String articleId) {
        SQL sql = new SQL()
                .SELECT("*")
                .FROM(TABLE)
                .LEFT_OUTER_JOIN("article_tag on tag.id = article_tag.tag_id where article_tag.article_id = " + articleId);

        log.info("{}", sql.toString());
        return sql.toString();
    }

    /**
     * 查询所有
     *
     * @return sql
     */
    public String list() {
        SQL sql = new SQL()
                .SELECT("*")
                .FROM(TABLE)
                .WHERE("gmt_disabled = '" + Constant.DISABLE_DATETIME + "'");

        return sql.toString();
    }

    /**
     * 返回新插入数据的自增id
     *
     * @return id
     */
    public String getLastInsertId() {
        return "select last_insert_id()";
    }

    /**
     * 查询分页
     *
     * @param
     * @return sql
     */
    public String listPagination() {
        return "select * from " + TABLE + " where gmt_disabled='" + Constant.DISABLE_DATETIME + "' order by gmt_create desc,id desc";
    }
}
