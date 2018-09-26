package cn.phyworks.bp.dao;

import cn.phyworks.bp.domain.ArrangementDo;
import cn.phyworks.bp.support.Constant;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.stereotype.Repository;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * ArrangementMapperImpl
 *
 * @author peiixn
 * @date 2018.09.09
 */
@Slf4j
@Repository
public class ArrangementMapperImpl {
    private final String TABLE = "arrangement";
    private String NowTime = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now());

    /**
     * 添加操作，返回新增元素的 ID
     *
     * @param arrangementDo ArrangementDo
     * @return sql
     */
    public String save(@Validated ArrangementDo arrangementDo) {
        SQL sql = new SQL()
                .INSERT_INTO(TABLE)
                .VALUES("year", "#{year}")
                .VALUES("month", "#{month}")
                .VALUES("used_total", "1")
                .VALUES("gmt_disabled", "'" + Constant.DISABLE_DATETIME + "'")
                .VALUES("gmt_modified", "'" + NowTime + "'")
                .VALUES("gmt_create", "'" + NowTime + "'");
        return sql.toString();
    }

    /**
     * 更新操作
     *
     * @param id Long
     * @return sql
     */
    public String update(Long id, String type) {
        log.info("id3 {}", id);
        SQL sql = new SQL()
                .UPDATE(TABLE)
                .WHERE("id = " + id)
                .WHERE("gmt_disabled = '" + Constant.DISABLE_DATETIME + "'");
        if (type.equals("A")) {
            sql.SET("used_total = used_total + 1");
        } else {
            sql.SET("used_total = used_total - 1");
        }

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
                .WHERE("gmt_disabled = '" + Constant.DISABLE_DATETIME + "'")
                .ORDER_BY("id");

        return sql.toString();
    }

    /**
     * 根据年份月份查是否存在
     *
     * @param year month
     * @return sql
     */
    public String getByYearAndMonth(Integer year, String month) {
        SQL sql = new SQL()
                .SELECT("*")
                .FROM(TABLE)
                .WHERE("year = " + year)
                .WHERE("month = " + month)
                .WHERE("gmt_disabled = '" + Constant.DISABLE_DATETIME + "'");

        return sql.toString();
    }
}
