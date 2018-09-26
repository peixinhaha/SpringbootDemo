package cn.phyworks.bp.pojo.form;

import lombok.Data;

import javax.validation.constraints.Size;

/**
 * @ Author     ：peixin
 * @ Date       ：Created in 2018/9/9 下午9:43
 * @ Description：整理表表单
 * @ Modified By：
 * @Version: 0.0.1
 */
@Data
public class ArrangementControllerSaveForm {

    /**
     * 年份
     */
    @Size(min = 4, max = 4, message = "年份必须是4位数")
    private String year;

    /**
     * 月份
     */
    @Size(min = 2, max = 2, message = "月份必须是2位数")
    private String month;

}
