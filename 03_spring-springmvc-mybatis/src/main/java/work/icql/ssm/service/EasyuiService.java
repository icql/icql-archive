package work.icql.ssm.service;

import org.springframework.web.multipart.MultipartFile;
import work.icql.ssm.pojo.EasyuiGrid;
import work.icql.ssm.pojo.EasyuiTreeNode;
import work.icql.ssm.pojo.IcqlResult;
import work.icql.ssm.pojo.TbEasyuiGrid;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author icql
 * @version 1.0
 * @date 2018/10/31 17:17
 * @Title EasyuiService
 * @Description EasyuiService
 */
public interface EasyuiService {

    /**
     * 获取所有 EasyuiTreeNode
     *
     * @return
     */
    List<EasyuiTreeNode> getEasyuiTreeNodeList();

    /**
     * 根据 name 分页获取 EasyuiGridData
     *
     * @param page
     * @param rows
     * @param name
     * @return
     */
    EasyuiGrid getEasyuiGridDataList(Integer page, Integer rows, String name);

    /**
     * 保存 TbEasyuiGrid
     *
     * @param data
     * @return
     */
    IcqlResult saveEasyuiGridData(TbEasyuiGrid data);

    /**
     * 根据Ids（id用,分割）删除 TbEasyuiGrid
     *
     * @param ids
     * @return
     */
    IcqlResult deleteEasyuiGridData(String ids);

    /**
     * 导入Excel数据
     *
     * @param file
     * @return
     */
    IcqlResult importEasyuiGridData(MultipartFile file);

    /**
     * 导出Excel数据
     *
     * @param response
     * @param name
     */
    void downloadEasyuiGridData(HttpServletResponse response, String name);
}
