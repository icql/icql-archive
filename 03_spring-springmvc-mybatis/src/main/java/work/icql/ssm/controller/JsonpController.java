package work.icql.ssm.controller;

import com.alibaba.fastjson.JSONPObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import work.icql.javatutil.FastjsonUtils;
import work.icql.ssm.pojo.EasyuiTreeNode;
import work.icql.ssm.service.EasyuiService;

import java.util.List;

/**
 * @author icql
 * @version 1.0
 * @date 2018/11/5 13:35
 * @Title JsonpController
 * @Description JsonpController
 */
@Controller
@RequestMapping("/jsonp")
public class JsonpController {

    @Autowired
    private EasyuiService easyuiService;

    /**
     * 使用 fastjson 有效
     *
     * @param callback
     * @return
     */
    @RequestMapping(value = "/api/list1")
    @ResponseBody
    public Object getList1(String callback) {
        List<EasyuiTreeNode> data = easyuiService.getEasyuiTreeNodeList();
        if (StringUtils.isEmpty(callback)) {
            return data;
        }
        JSONPObject jsonpObject = new JSONPObject(callback);
        jsonpObject.addParameter(data);
        return jsonpObject;
    }

    /**
     * 使用 jackson 有效
     *
     * @param callback
     * @return
     */
    @RequestMapping(value = "/api/list2", produces = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
    @ResponseBody
    public String getList2(String callback) {
        List<EasyuiTreeNode> data = easyuiService.getEasyuiTreeNodeList();
        if (StringUtils.isEmpty(callback)) {
            return FastjsonUtils.object2json(data);
        }
        String json = FastjsonUtils.object2json(data);
        return callback + "(" + json + ");";
    }

    ///**
    // * 使用 jackson 有效
    // *
    // * @param callback
    // * @return
    // */
    //@RequestMapping(value = "/api/list3")
    //@ResponseBody
    //public Object getList3(String callback) {
    //    List<EasyuiTreeNode> data = easyuiService.getEasyuiTreeNodeList();
    //    if (StringUtils.isEmpty(callback)) {
    //        return data;
    //    }
    //    MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(data);
    //    mappingJacksonValue.setJsonpFunction(callback);
    //    return mappingJacksonValue;
    //}
}
