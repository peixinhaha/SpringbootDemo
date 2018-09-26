package cn.phyworks.bp.pojo.dto;

import lombok.Data;

import javax.validation.constraints.*;

@Data
public class TagServiceSaveDto {

    /**
     * 标签名
     */
    @NotBlank(message = "标签名不能为空")
    private String name;
}
