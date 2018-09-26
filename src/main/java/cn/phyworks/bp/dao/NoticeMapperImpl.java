package cn.phyworks.bp.dao;

import cn.phyworks.bp.domain.NoticeDo;
import cn.phyworks.bp.support.Constant;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.stereotype.Repository;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * NoticeMapper 实现
 *
 * @author shouxueyun@163.com
 * @date 2018.09.10
 */
@Slf4j
@Repository
public class NoticeMapperImpl {

    private final String TABLE = "notice";
    private String NowTime = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now());

    /**
     * 查询所有
     *
     * @param
     * @return sql
     */
    public String listPagination() {
        return "select * from " + TABLE + " where gmt_disabled='" + Constant.DISABLE_DATETIME + "' order by id desc";
    }

    /**
     * 添加操作，返回新增元素的 ID
     *
     * @param noticeDo NoticeDo
     * @return sql
     */
    public String save(@Validated NoticeDo noticeDo) {
        return "insert into " + TABLE + "(content, gmt_create, gmt_modified, gmt_disabled) values(#{content}, '" + NowTime + "', '" + NowTime + "', '" + Constant.DISABLE_DATETIME +"')";
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
     * 删除操作
     *
     * @param id ID
     * @return sql
     */
    public String remove(@Validated Long id) {
        SQL sql = new SQL()
                .UPDATE(TABLE)
                .SET("gmt_disabled = '" + NowTime + "'")
                .WHERE("id = #{id}")
                .WHERE("gmt_disabled = '" + Constant.DISABLE_DATETIME + "'");

        return sql.toString();
    }

    /**
     * 更新操作
     *
     * @param noticeDo NoticeDo
     * @return sql
     */
    public String update(@Validated NoticeDo noticeDo) {
        Boolean flag = false;

        SQL sql = new SQL()
                .UPDATE(TABLE)
                .WHERE("id = #{id}")
                .WHERE("gmt_disabled = '" + Constant.DISABLE_DATETIME + "'");
        if (noticeDo.getContent() != null) {
            flag = true;
            sql.SET("content = #{content}");
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
     * 根据名查询单个
     *
     * @return sql
     */
    public String getByContent(String content) {
        SQL sql = new SQL()
                .SELECT("*")
                .FROM(TABLE)
                .WHERE("content = #{content}")
                .WHERE("gmt_disabled = '" + Constant.DISABLE_DATETIME + "'");

        return sql.toString();
    }


}
