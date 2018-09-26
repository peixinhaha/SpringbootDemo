package cn.phyworks.bp.domain;

import lombok.Data;
import java.util.Date;

/**
 * 公告表
 *
 * @author peixin
 * @date 2018.09.06
 */
@Data
public class NoticeDo {

    /**
     * 自增 ID
     */
    private Long id;

    /**
     * 公告内容
     */
    private String content;

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
