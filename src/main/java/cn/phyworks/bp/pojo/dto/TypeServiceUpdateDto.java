package cn.phyworks.bp.pojo.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class TypeServiceUpdateDto {

    /**
     * 自增 ID
     */
    @NotNull(message = "id 不能为空")
    private Long id;

    /**
     * 分类名
     */
    private String name;
}
