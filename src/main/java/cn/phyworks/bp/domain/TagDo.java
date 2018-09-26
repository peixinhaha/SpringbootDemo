package cn.phyworks.bp.domain;

import lombok.Data;
import java.util.Date;

/**
 * 标签表
 *
 * @author peixin
 * @date 2018.09.06
 */
@Data
public class TagDo {

    /**
     * 自增 ID
     */
    private Long id;

    /**
     * 标签名
     */
    private String name;

    /**
     * 禁用时间
     */
    private Date gmtDisabled;

    /**
     * 创建时间
     */
    private Date gmtCreate;

    /**
     * 更新时间
     */
    private Date gmtModified;
}
