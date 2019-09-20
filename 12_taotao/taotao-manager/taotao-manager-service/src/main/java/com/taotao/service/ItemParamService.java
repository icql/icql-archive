package com.taotao.service;

import com.taotao.common.pojo.TaotaoResultBO;

public interface ItemParamService {

    TaotaoResultBO getItemParamByCid(Long cid);
    TaotaoResultBO insertItemParam(Long cid, String paramData);
}
