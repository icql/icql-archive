package com.taotao.controller;

import com.taotao.common.pojo.EasyUIDataGridResultBO;
import com.taotao.common.pojo.TaotaoResultBO;
import com.taotao.pojo.TbItemDO;
import com.taotao.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @RequestMapping("/{itemId}")
    @ResponseBody
    public TbItemDO getItemById(@PathVariable Long itemId) {
        return itemService.getItemById(itemId);
    }

    @RequestMapping("/list")
    @ResponseBody
    public EasyUIDataGridResultBO getItemList(Integer page, Integer rows) {
        return itemService.getItemList(page, rows);
    }

    @RequestMapping("/save")
    @ResponseBody
    public TaotaoResultBO CreateItem(TbItemDO item, String desc,String itemParams){
        TaotaoResultBO resultBO = itemService.createItem(item, desc,itemParams);
        return resultBO;
    }

    @RequestMapping("/item/{itemId}")
    public String showItemParam(@PathVariable Long itemId, Model model) {
        String html = itemService.getItemParamHtml(itemId);
        model.addAttribute("html", html);

        return "itemparam";
    }

}
