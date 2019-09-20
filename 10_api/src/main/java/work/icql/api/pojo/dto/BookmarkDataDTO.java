package work.icql.api.pojo.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

/**
 * @author icql
 * @version 1.0
 * @date 2018/12/8 10:13
 * @Title BookmarksData
 * @Description BookmarksData
 */
@Data
public class BookmarkDataDTO implements Serializable {

    private static final long serialVersionUID = 5959769669190461980L;

    private String id;
    private String parentId;
    private Integer index;
    private String url;
    private String title;
    private BookmarkDataDTO[] children;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BookmarkDataDTO bookmarks = (BookmarkDataDTO) o;
        return Objects.equals(index, bookmarks.index) &&
                Objects.equals(url, bookmarks.url) &&
                Objects.equals(title, bookmarks.title) &&
                Arrays.equals(children, bookmarks.children);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(index, url, title);
        result = 31 * result + Arrays.hashCode(children);
        return result;
    }
}
