package work.icql.api.pojo.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author icql
 * @version 1.0
 * @date 2018/12/23 14:10
 * @Title BookmarkDO
 * @Description BookmarkDO
 */
@Data
public class BookmarkDO {
    private Long id;

    private Long userid;

    private Integer version;

    private Date gmtCreate;

    private Date gmtModified;

    private Byte isDeleted;

    private String data;
}