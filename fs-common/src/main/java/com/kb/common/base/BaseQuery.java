package com.kb.common.base;

/**
 * @author syg
 * @version 1.0
 */
public class BaseQuery {
    /**
     * 当前页,默认1
     */
    private Integer page=1;
    /**
     * 每页显示的条数,默认10
     */
    private Integer limit=10;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }
}
