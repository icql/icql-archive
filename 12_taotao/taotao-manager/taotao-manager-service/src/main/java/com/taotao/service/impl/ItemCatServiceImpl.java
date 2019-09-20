package com.taotao.service.impl;

import com.taotao.mapper.TbItemCatDOMapper;
import com.taotao.common.pojo.EasyUITreeNodeBO;
import com.taotao.pojo.TbItemCatDO;
import com.taotao.pojo.TbItemCatDOExample;
import com.taotao.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemCatServiceImpl implements ItemCatService {

    @Autowired
    private TbItemCatDOMapper itemCatMapper;

    @Override
    public List<EasyUITreeNodeBO> getItemCatList(long parentId) {
        TbItemCatDOExample example = new TbItemCatDOExample();
        TbItemCatDOExample.Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(parentId);

        List<TbItemCatDO> itemCats = itemCatMapper.selectByExample(example);
        List<EasyUITreeNodeBO> easyUITreeNodes = new ArrayList<>();
        for (TbItemCatDO itemCat : itemCats) {
            EasyUITreeNodeBO node = new EasyUITreeNodeBO();
            node.setId(itemCat.getId());
            node.setText(itemCat.getName());
            node.setState(itemCat.getStatus() != 0 ? "closed" : "open");
            easyUITreeNodes.add(node);
        }
        return easyUITreeNodes;
    }
}
