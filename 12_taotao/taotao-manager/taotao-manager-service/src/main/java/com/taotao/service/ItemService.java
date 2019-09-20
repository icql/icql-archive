package com.taotao.service;

import com.taotao.common.pojo.EasyUIDataGridResultBO;
import com.taotao.common.pojo.TaotaoResultBO;
import com.taotao.pojo.TbItemDO;

public interface ItemService {
    TbItemDO getItemById(Long id);

    EasyUIDataGridResultBO getItemList(int page, int rows);

    TaotaoResultBO createItem(TbItemDO item,String desc,String itemParam);

    String getItemParamHtml(Long itemId);
}
