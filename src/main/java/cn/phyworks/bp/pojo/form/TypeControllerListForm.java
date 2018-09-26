package cn.phyworks.bp.pojo.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 分类列表 From
 *
 * @author shouxueyun@163.com
 * @date 2018.09.10
 */
@Data
public class TypeControllerListForm {

    /**
     * 标签名
     */
    @NotBlank(message = "分类名不能为空")
    private String name;
}
