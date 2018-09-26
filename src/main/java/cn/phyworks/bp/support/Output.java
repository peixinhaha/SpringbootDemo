package cn.phyworks.bp.support;

import lombok.Data;

/**
 * 统一输出
 *
 * @author shyasiny@gmail.com
 * @date 2018.08.14
 */
@Data
public class  Output<T> {
    /**
     * 状态码
     */
    private String code;

    /**
     * 消息
     */
    private String message;

    /**
     * 数据
     */
    private T payload;

    /**
     * 分页数据
     */
    private T pagination;

    /**
     * 验证数据
     */
    private T validation;

    /**
     * 自定义数据
     */
    private T custom;

    /**
     * 默认未知错误
     */
    public Output() {
        this.code    = ExceptionEnum.UNKNOWN.getCode();
        this.message = ExceptionEnum.UNKNOWN.getMessage();
    }
}
