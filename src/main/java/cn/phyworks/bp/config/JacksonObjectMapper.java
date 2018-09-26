package cn.phyworks.bp.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;

/**
 * Jackson 格式化
 *
 * @author shyasiny
 * @date 2018.08.14
 */
public class JacksonObjectMapper extends ObjectMapper {

    private static final long serialVersionUID = 1L;

    public JacksonObjectMapper() {
        // 调用父类构造方法
        super();

        // Null 值不做处理（移除）
        this.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);

        // 字段和值都加引号
        this.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);

        // 数字也加引号
        this.configure(JsonGenerator.Feature.WRITE_NUMBERS_AS_STRINGS, true);
        this.configure(JsonGenerator.Feature.QUOTE_NON_NUMERIC_NUMBERS, true);

        // 转换使用蛇形命名法
        this.setPropertyNamingStrategy(
                PropertyNamingStrategy.SNAKE_CASE
        );
    }
}
