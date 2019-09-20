package work.icql.ssm.pojo;

import java.util.List;

/**
 * @author icql
 * @version 1.0
 * @date 2018/10/31 17:06
 * @Title EasyuiGrid
 * @Description EasyuiGrid
 */
public class EasyuiGrid {
    private long total;
    private List rows;

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List getRows() {
        return rows;
    }

    public void setRows(List rows) {
        this.rows = rows;
    }
}
