package cn.phyworks.bp.pojo.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class TagServiceUpdateDto {

    /**
     * 自增 ID
     */
    @NotNull(message = "id 不能为空")
    private Long id;

    /**
     * 标签名
     */
    @NotBlank(message = "标签名不能为空")
    private String name;
}
