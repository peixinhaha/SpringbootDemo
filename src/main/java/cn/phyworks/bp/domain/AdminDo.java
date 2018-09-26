package cn.phyworks.bp.domain;

import lombok.Data;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * 用户
 *
 * @author peixin
 * @date 2018.09.06
 */
@Data
public class AdminDo {

    /**
     * 自增 ID
     */
    private Long id;

    /**
     * 用户名
     */
    @Size(min = 2, max = 20, message="姓名长度必须介于 2 - 20 之间")
    private String username;

    /**
     * 密码
     */
    @Pattern(regexp = "^.*[a-zA-Z]+.*$", message = "必须包含数字，字母，特殊字符")
    @Size(min = 8, message = "长度至少8位")
    private String password;

    /**
     * token
     */
    private String token;

    /**
     * 创建时间
     */
    private Date gmtCreate;

    /**
     * salt
     */
    private String salt;

    /**
     * 禁用时间
     */
    private Date gmtDisabled;

    /**
     * 更新时间
     */
    private Date gmtModified;
}