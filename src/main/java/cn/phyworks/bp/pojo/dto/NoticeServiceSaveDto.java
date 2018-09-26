package cn.phyworks.bp.pojo.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class NoticeServiceSaveDto {

    /**
     * 内容
     */
    @NotBlank(message = "内容不能为空")
    private String content;
}
