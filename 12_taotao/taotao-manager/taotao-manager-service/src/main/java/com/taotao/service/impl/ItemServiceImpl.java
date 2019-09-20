package com.taotao.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.TaotaoResultBO;
import com.taotao.common.utils.IDUtils;
import com.taotao.common.utils.JsonUtils;
import com.taotao.mapper.TbItemDOMapper;
import com.taotao.common.pojo.EasyUIDataGridResultBO;
import com.taotao.mapper.TbItemDescDOMapper;
import com.taotao.mapper.TbItemParamItemDOMapper;
import com.taotao.pojo.*;
import com.taotao.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private TbItemDOMapper itemDOMapper;

    @Autowired
    private TbItemDescDOMapper itemDescDOMapper;

    @Autowired
    private TbItemParamItemDOMapper itemParamItemDOMapper;

    @Override
    public TbItemDO getItemById(Long id) {
//        TbItemExample itemExample = new TbItemExample();
//        TbItemExample.Criteria criteria = itemExample.createCriteria();
//        criteria.andIdEqualTo(id);
//        List<TbItem> items = itemMapper.selectByExample(itemExample);
//        if(items != null && items.size()>0){
//            return items.get(0);
//        }

        return itemDOMapper.selectByPrimaryKey(id);
    }

    @Override
    public EasyUIDataGridResultBO getItemList(int page, int rows) {
        //分页处理
        PageHelper.startPage(page,rows);

        //执行查询
        TbItemDOExample example = new TbItemDOExample();
        List<TbItemDO> items = itemDOMapper.selectByExample(example);

        PageInfo pageInfo = new PageInfo(items);

        //返回处理结果

        EasyUIDataGridResultBO easyUIDataGridResult =new EasyUIDataGridResultBO();
        easyUIDataGridResult.setTotal(pageInfo.getTotal());
        easyUIDataGridResult.setRows(pageInfo.getList());

        return easyUIDataGridResult;
    }

    @Override
    public TaotaoResultBO createItem(TbItemDO item, String desc,String itemParam) {
        // 生成商品id
        long itemId = IDUtils.genItemId();
        // 补全TbItem属性
        item.setId(itemId);
        // '商品状态，1-正常，2-下架，3-删除'
        item.setStatus((byte) 1);
        // 创建时间和更新时间
        Date date = new Date();
        item.setCreated(date);
        item.setUpdated(date);
        // 插入商品表
        itemDOMapper.insert(item);

        // 商品描述
        TbItemDescDO itemDesc = new TbItemDescDO();
        itemDesc.setItemId(itemId);
        itemDesc.setItemDesc(desc);
        itemDesc.setCreated(date);
        itemDesc.setUpdated(date);
        // 插入商品描述数据
        itemDescDOMapper.insert(itemDesc);

        //商品参数
        TbItemParamItemDO itemParamItemDO = new TbItemParamItemDO();
        itemParamItemDO.setItemId(itemId);
        itemParamItemDO.setParamData(itemParam);
        itemParamItemDO.setCreated(date);
        itemParamItemDO.setUpdated(date);
        itemParamItemDOMapper.insert(itemParamItemDO);

        return TaotaoResultBO.ok();
    }

    @Override
    public String getItemParamHtml(Long itemId) {
        // 根据商品id查询规格参数
        TbItemParamItemDOExample example = new TbItemParamItemDOExample();
        com.taotao.pojo.TbItemParamItemDOExample.Criteria criteria = example.createCriteria();
        criteria.andItemIdEqualTo(itemId);

        //执行查询
        List<TbItemParamItemDO> list = itemParamItemDOMapper.selectByExample(example);
        if (list == null || list.isEmpty()) {
            return"";
        }
        //取规格参数
        TbItemParamItemDO itemParamItem = list.get(0);
        //取json数据
        String paramData = itemParamItem.getParamData();
        //转换成java对象
        List<Map> mapList = JsonUtils.jsonToList(paramData, Map.class);

        //遍历list生成html
        StringBuffer sb = new StringBuffer();

        sb.append("<table cellpadding=\"0\" cellspacing=\"1\" width=\"100%\" border=\"0\" class=\"Ptable\">\n");
        sb.append("	<tbody>\n");
        for (Map map : mapList) {
            sb.append("		<tr>\n");
            sb.append("			<th class=\"tdTitle\" colspan=\"2\">"+map.get("group")+"</th>\n");
            sb.append("		</tr>\n");
            //取规格项
            List<Map>mapList2 = (List<Map>) map.get("params");
            for (Map map2 : mapList2) {
                sb.append("		<tr>\n");
                sb.append("			<td class=\"tdTitle\">"+map2.get("k")+"</td>\n");
                sb.append("			<td>"+map2.get("v")+"</td>\n");
                sb.append("		</tr>\n");
            }
        }
        sb.append("	</tbody>\n");
        sb.append("</table>");

        return sb.toString();
    }
}
