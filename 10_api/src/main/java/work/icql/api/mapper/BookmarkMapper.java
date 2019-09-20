package work.icql.api.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import work.icql.api.pojo.entity.BookmarkDO;
import work.icql.api.pojo.entity.BookmarkExample;

/**
 * @author icql
 * @version 1.0
 * @date 2018/12/23 14:10
 * @Title BookmarkMapper
 * @Description BookmarkMapper
 */
@Repository
public interface BookmarkMapper {
    /**
     * 根据example计数
     * @param example
     * @return
     */
    long countByExample(BookmarkExample example);

    /**
     * 根据example删除
     * @param example
     * @return
     */
    int deleteByExample(BookmarkExample example);

    /**
     * 根据主键删除
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 新增
     * @param record
     * @return
     */
    int insert(BookmarkDO record);

    /**
     * 有选择的新增
     * @param record
     * @return
     */
    int insertSelective(BookmarkDO record);

    /**
     * 根据用户id查询最新的数据
     * @param userid
     * @return
     */
    BookmarkDO selectByUseridWithLatest(Long userid);

    /**
     * 根据example查询数据（包含大文本）
     * @param example
     * @return
     */
    List<BookmarkDO> selectByExampleWithBLOBs(BookmarkExample example);

    /**
     * 根据example查询数据
     * @param example
     * @return
     */
    List<BookmarkDO> selectByExample(BookmarkExample example);

    /**
     * 根据主键查询
     * @param id
     * @return
     */
    BookmarkDO selectByPrimaryKey(Long id);

    /**
     * 根据example有选择的更新
     * @param record
     * @param example
     * @return
     */
    int updateByExampleSelective(@Param("record") BookmarkDO record, @Param("example") BookmarkExample example);

    /**
     * 根据example有选择的更新（包含大文本）
     * @param record
     * @param example
     * @return
     */
    int updateByExampleWithBLOBs(@Param("record") BookmarkDO record, @Param("example") BookmarkExample example);

    /**
     * 根据example更新
     * @param record
     * @param example
     * @return
     */
    int updateByExample(@Param("record") BookmarkDO record, @Param("example") BookmarkExample example);

    /**
     * 根据主键有选择的更新
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(BookmarkDO record);

    /**
     * 根据主键更新（包含大文本）
     * @param record
     * @return
     */
    int updateByPrimaryKeyWithBLOBs(BookmarkDO record);

    /**
     * 根据主键更新
     * @param record
     * @return
     */
    int updateByPrimaryKey(BookmarkDO record);
}