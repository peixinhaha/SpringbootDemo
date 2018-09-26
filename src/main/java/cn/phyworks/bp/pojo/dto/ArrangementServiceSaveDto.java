package cn.phyworks.bp.pojo.dto;

import lombok.Data;

import javax.validation.constraints.Size;

/**
 * @ Author     ：peixin
 * @ Date       ：Created in 2018/9/9 下午9:18
 * @ Description：ArrangementServiceSaveDto
 * @ Modified By：
 * @Version: 0.0.1
 */
@Data
public class ArrangementServiceSaveDto {
    /**
     * 年份
     */
    private Integer year;

    /**
     * 月份
     */
    @Size(max = 2, message = "最大两位数")
    private String month;

}
