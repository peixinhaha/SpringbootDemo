package cn.phyworks.bp.exception;

import cn.phyworks.bp.support.ExceptionEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Jackson 格式化
 *
 * @author shyasiny@gmail.com
 * @date 2018.08.14
 */
@Data
@EqualsAndHashCode(callSuper=true)
public class BusinessException extends Exception {

    /**
     * 错误码
     */
    private String customCode;

    /**
     * 消息
     */
    private String customMessage;

    /**
     * 业务异常
     */
    public BusinessException(ExceptionEnum exceptionEnum) {
        this.customCode    = exceptionEnum.getCode();
        this.customMessage = exceptionEnum.getMessage();
    }
}
