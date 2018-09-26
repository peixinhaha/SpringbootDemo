package cn.phyworks.bp.pojo.bo;

import lombok.Data;

import javax.validation.constraints.Size;

/**
 * @ Author     ：peixin
 * @ Date       ：Created in 2018/9/9 下午9:16
 * @ Description：ArrangementServiceSaveBo
 * @ Modified By：
 * @Version: 0.0.1
 */
@Data
public class ArrangementServiceSaveBo {
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
