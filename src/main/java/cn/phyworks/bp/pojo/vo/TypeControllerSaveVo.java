package cn.phyworks.bp.pojo.vo;

import lombok.Data;

@Data
public class TypeControllerSaveVo {
    /**
     * 自增 ID
     */
    private Long id;

    /**
     * 分类名
     */
    private String name;

    /**
     * 使用总量
     */
    private Integer usedTotal;
}
