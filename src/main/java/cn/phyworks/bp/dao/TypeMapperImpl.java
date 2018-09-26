package cn.phyworks.bp.dao;

import cn.phyworks.bp.domain.TypeDo;
import cn.phyworks.bp.support.Constant;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.stereotype.Repository;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * TypeMapper 实现
 *
 * @author shouxueyun@163.com
 * @date 2018.09.10
 */
@Slf4j
@Repository
public class TypeMapperImpl {

    private final String TABLE = "type";

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
     * 更新操作
     *
     * @param typeDo TypeDo
     * @return sql
     */
    public String update(@Validated TypeDo typeDo) {
        Boolean flag = false;

        SQL sql = new SQL()
                .UPDATE(TABLE)
                .WHERE("id = #{id}")
                .WHERE("gmt_disabled = '" + Constant.DISABLE_DATETIME + "'");
        if (typeDo.getName() != null) {
            flag = true;
            sql.SET("name = #{name}");
        }
        if (typeDo.getUsedTotal() != null) {
            flag = true;
            sql.SET("used_total += 1");
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
     * 更新操作
     *
     * @return sql
     */
    public String updateUsedTotal(Long id, String type) {
        String setText = "";
        if (type.equals(String.valueOf('A'))) {
            setText = "used_total = used_total + 1,";
        } else if (type.equals(String.valueOf('I'))) {
            setText = "used_total = used_total - 1,";
        }
        SQL sql = new SQL()
                .UPDATE(TABLE)
                .WHERE("id =" + id)
                .WHERE("gmt_disabled = '" + Constant.DISABLE_DATETIME + "'")
                .SET(setText + "gmt_modified = '" + DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now()) + "'");

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

}
