package cn.phyworks.bp.support;

import lombok.Data;

@Data
public class Pagination {

    /**
     * 总数
     */
    private Long total;

    /**
     * 总页数
     */
    private Integer totalPage;

    /**
     * 当前页数
     */
    private Integer page;

    /**
     * 每页数据数
     */
    private Integer size;
}
