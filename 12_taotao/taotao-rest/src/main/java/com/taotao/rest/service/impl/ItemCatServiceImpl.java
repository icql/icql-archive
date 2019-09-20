package com.taotao.rest.service.impl;

import com.taotao.mapper.TbItemCatDOMapper;
import com.taotao.pojo.TbItemCatDO;
import com.taotao.pojo.TbItemCatDOExample;
import com.taotao.rest.pojo.CatNode;
import com.taotao.rest.pojo.ItemCatResult;
import com.taotao.rest.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemCatServiceImpl implements ItemCatService {

    @Autowired
    private TbItemCatDOMapper itemCatDOMapper;

    @Override
    public ItemCatResult getItemCatList() {
        //调用递归方法查询商品分类列表
        List catList = getItemCatList(0l);
        //返回结果
        ItemCatResult result = new ItemCatResult();
        result.setData(catList);
        return result;
    }

    private List getItemCatList(Long parentId) {
        //根据parentId查询列表
        TbItemCatDOExample example = new TbItemCatDOExample();
        TbItemCatDOExample.Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(parentId);
        //执行查询
        List<TbItemCatDO> list = itemCatDOMapper.selectByExample(example);
        List resultList = new ArrayList<>();

        for(TbItemCatDO item :list){
            if(item.getIsParent()){
                CatNode node = new CatNode();
                node.setUrl("/products/"+item.getId()+".html");

                if (item.getParentId() == 0) {
                    node.setName("<a href='/products/"+item.getId()+".html'>"+item.getName()+"</a>");
                } else {
                    node.setName(item.getName());
                }
                node.setItems(getItemCatList(item.getId()));
                //把node添加到列表
                resultList.add(node);

            }else {
                String link = "/products/"+item.getId()+".html|" + item.getName();
                resultList.add(link);
            }
        }

        return resultList;

    }
}
