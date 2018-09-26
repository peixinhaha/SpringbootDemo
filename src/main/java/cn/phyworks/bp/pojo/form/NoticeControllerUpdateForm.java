package cn.phyworks.bp.pojo.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 公告更新 From
 *
 * @author shouxueyun@163.com
 * @date 2018.09.10
 */
@Data
public class NoticeControllerUpdateForm {

    /**
     * 内容
     */
    @NotBlank(message = "内容不能为空")
    private String content;
}
