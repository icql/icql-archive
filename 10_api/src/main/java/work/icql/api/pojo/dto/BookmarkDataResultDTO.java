package work.icql.api.pojo.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author icql
 * @version 1.0
 * @date 2018/12/6 21:04
 * @Title BookmarkDataResultDTO
 * @Description BookmarkDataResultDTO
 */
@Data
public class BookmarkDataResultDTO implements Serializable {
    private static final long serialVersionUID = -6714212177294919575L;
    private Integer version;
    private List<BookmarkDataTreeNodeDTO> treeNodeDTOS;
}
