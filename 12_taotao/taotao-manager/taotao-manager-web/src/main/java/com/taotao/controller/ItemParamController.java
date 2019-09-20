package com.taotao.controller;

import com.taotao.common.pojo.TaotaoResultBO;
import com.taotao.service.ItemParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/item/param")
public class ItemParamController {
    @Autowired
    private ItemParamService itemParamService;

    @RequestMapping("/query/itemcatid/{cid}")
    @ResponseBody
    public TaotaoResultBO getItemCatByCid(@PathVariable Long cid) {
        TaotaoResultBO result = itemParamService.getItemParamByCid(cid);
        return result;
    }

    @RequestMapping("/save/{cid}")
    @ResponseBody
    public TaotaoResultBO insertItemParam(@PathVariable Long cid, String paramData) {
        TaotaoResultBO result = itemParamService.insertItemParam(cid, paramData);
        return result;
    }

}
