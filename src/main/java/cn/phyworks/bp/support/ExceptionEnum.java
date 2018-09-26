package cn.phyworks.bp.support;

import lombok.Getter;

/**
 * 异常信息
 *
 * @author shyasiny@gmail.com
 * @date 2018.08.14
 */
@Getter
public enum ExceptionEnum {

    /**
     * UNKNOWN
     */
    UNKNOWN("UNKNOWN", "未知错误"),

    /**
     * SUCCESS
     */
    SUCCESS("SUCCESS", "成功"),

    /**
     * VALIDATION_ABNORMAL
     */
    VALIDATION_ABNORMAL("VALIDATION_ABNORMAL", "验证异常"),

    /**
     * VALIDATION_ABNORMAL
     */
    USER_ALREADY_EXISTS("USER_ALREADY_EXISTS", "用户已存在"),

    /**
     * VALIDATION_ABNORMAL
     */
    ARRANGEMENT_ALREADY_EXISTS("ARRANGEMENT_ALREADY_EXISTS", "整理表已存在"),

    /**
     * USER_NOT_FOUND
     */
    USER_NOT_FOUND("USER_NOT_FOUND", "用户不存在"),

    /**
     * USER_DISABLED
     */
    USER_DISABLED("USER_DISABLED", "账号被停用"),

    /**
     * VALIDATION_ABNORMAL
     */
    DEMO_EXCEPTION("DEMO_EXCEPTION", "示例异常"),

    /**
     * VALIDATION_ABNORMAL
     */
    TAG_ALREADY_EXISTS("TAG_ALREADY_EXISTS", "标签已存在"),

    /**
     * VALIDATION_ABNORMAL
     */
    NOTICE_ALREADY_EXISTS("NOTICE_ALREADY_EXISTS", "公告已存在"),

    /**
     * TAG_NOT_FOUND
     */
    TAG_NOT_FOUND("TAG_NOT_FOUND", "标签不存在"),

    /**
     * TYPE_NOT_FOUND
     */
    TYPE_NOT_FOUND("TYPE_NOT_FOUND", "分类不存在"),

    /**
     * NOTICE_NOT_FOUND
     */
    NOTICE_NOT_FOUND("NOTICE_NOT_FOUND", "公告不存在"),

    /**
     * ARTICLE_NOT_FOUND
     */
    ARTICLE_NOT_FOUND("NOTICE_NOT_FOUND", "文章不存在"),

    ;

    private String code;
    private String message;

    ExceptionEnum(String code, String message) {
        this.code = code;
        this.message  = message;
    }
}
