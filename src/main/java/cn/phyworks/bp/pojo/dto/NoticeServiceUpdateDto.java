package cn.phyworks.bp.pojo.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class NoticeServiceUpdateDto {

    /**
     * 自增 ID
     */
    @NotNull(message = "id 不能为空")
    private Long id;

    /**
     * 内容
     */
    @NotBlank(message = "内容不能为空")
    private String content;
}
