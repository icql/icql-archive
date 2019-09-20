package com.taotao.service.impl;

import com.taotao.common.pojo.TaotaoResultBO;
import com.taotao.mapper.TbItemParamDOMapper;
import com.taotao.pojo.TbItemParamDO;
import com.taotao.pojo.TbItemParamDOExample;
import com.taotao.service.ItemParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ItemParamServiceImpl implements ItemParamService {

    @Autowired
    private TbItemParamDOMapper itemParamDOMapper;

    @Override
    public TaotaoResultBO getItemParamByCid(Long cid) {
        TbItemParamDOExample example =new TbItemParamDOExample();
        TbItemParamDOExample.Criteria criteria = example.createCriteria();
        criteria.andItemCatIdEqualTo(cid);

        List<TbItemParamDO> list = itemParamDOMapper.selectByExampleWithBLOBs(example);
        if(list!=null && list.size()>0){
            TbItemParamDO itemParamDO = list.get(0);
            return TaotaoResultBO.ok(itemParamDO);
        }
        return TaotaoResultBO.ok();
    }

    @Override
    public TaotaoResultBO insertItemParam(Long cid, String paramData) {
        //创建一个pojo
        TbItemParamDO itemParam = new TbItemParamDO();
        itemParam.setItemCatId(cid);
        itemParam.setParamData(paramData);
        itemParam.setCreated(new Date());
        itemParam.setUpdated(new Date());
        //插入记录
        itemParamDOMapper.insert(itemParam);
        return TaotaoResultBO.ok();

    }
}
