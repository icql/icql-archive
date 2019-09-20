package work.icql.springcloud.common.entity;

import java.util.List;

/**
 * @author icql
 * @version 1.0
 * @date 2019/1/24 10:17
 * @Title PageResult
 * @Description PageResult
 */
public class PageResult<T> {
    private Long total;
    private List<T> rows;

    public PageResult(Long total, List<T> rows) {
        this.total = total;
        this.rows = rows;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }
}
