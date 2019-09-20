package work.icql.ssm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import work.icql.ssm.pojo.TbEasyuiGrid;
import work.icql.ssm.service.EasyuiService;

import javax.servlet.http.HttpServletResponse;

/**
 * @author icql
 * @version 1.0
 * @date 2018/10/31 14:53
 * @Title EasyUIController
 * @Description EasyUIController
 */
@Controller
@RequestMapping("/easyui")
public class EasyuiController {
    @Autowired
    private EasyuiService easyuiService;

    @RequestMapping("/{page}")
    public String page(@PathVariable String page) {
        return ("easyui/" + page);
    }


    @RequestMapping(value = "/api/tree", method = RequestMethod.GET)
    @ResponseBody
    public Object getEasyuiTreeNodeList() {
        return easyuiService.getEasyuiTreeNodeList();
    }

    @RequestMapping(value = "/api/grid", method = RequestMethod.GET)
    @ResponseBody
    public Object getEasyuiGridDataList(Integer page, Integer rows, String name) {
        return easyuiService.getEasyuiGridDataList(page, rows, name);
    }

    @RequestMapping(value = "/api/grid", method = {RequestMethod.POST, RequestMethod.PUT})
    @ResponseBody
    public Object saveEasyuiGridData(TbEasyuiGrid data) {
        return easyuiService.saveEasyuiGridData(data);
    }

    @RequestMapping(value = "/api/grid", method = RequestMethod.DELETE)
    @ResponseBody
    public Object deleteEasyuiGridData(String ids) {
        return easyuiService.deleteEasyuiGridData(ids);
    }

    @RequestMapping(value = "/api/grid/excel", method = {RequestMethod.POST, RequestMethod.PUT})
    @ResponseBody
    public Object importEasyuiGridData(MultipartFile file) {
        return easyuiService.importEasyuiGridData(file);
    }

    @RequestMapping(value = "/api/grid/excel", method = RequestMethod.GET)
    public void exportEasyuiGridData(HttpServletResponse response, String name) {
        easyuiService.downloadEasyuiGridData(response, name);
    }
}
