package work.icql.api.pojo.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author icql
 * @version 1.0
 * @date 2018/12/9 18:00
 * @Title BookmarkTreeNode
 * @Description BookmarkTreeNode
 */
@Data
public class BookmarkDataTreeNodeDTO implements Serializable {
    private static final long serialVersionUID = 9119501509587740516L;
    private String id;
    private String parentId;
    private Integer index;
    private String title;
    private String url;
}
