package cn.phyworks.bp.pojo.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 标签新增 From
 *
 * @author shouxueyun@163.com
 * @date 2018.09.10
 */
@Data
public class TagControllerSaveForm {

    /**
     * 标签名
     */
    @NotBlank(message = "标签名不能为空")
    private String name;
}
