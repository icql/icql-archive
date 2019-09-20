package work.icql.ssm.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import work.icql.javatutil.HttpUtils;
import work.icql.javatutil.PoiUtils;
import work.icql.ssm.mapper.TbEasyuiGridMapper;
import work.icql.ssm.mapper.TbEasyuiTreeMapper;
import work.icql.ssm.pojo.*;
import work.icql.ssm.service.EasyuiService;

import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * @author icql
 * @version 1.0
 * @date 2018/10/31 17:17
 * @Title EasyUIServiceImpl
 * @Description EasyUIServiceImpl
 */
@Service
public class EasyuiServiceImpl implements EasyuiService {
    @Autowired
    private TbEasyuiTreeMapper easyuiTreeMapper;

    @Autowired
    private TbEasyuiGridMapper easyuiGridMapper;

    @Override
    public List<EasyuiTreeNode> getEasyuiTreeNodeList() {
        //List<EasyuiTreeNode> list = getEasyuiTreeNodeList(0l); //递归查询数据库

        TbEasyuiTreeExample example = new TbEasyuiTreeExample();
        example.setOrderByClause("sort asc");//排序
        TbEasyuiTreeExample.Criteria criteria = example.createCriteria();
        List<TbEasyuiTree> easyuiTrees = easyuiTreeMapper.selectByExample(example);

        List<TbEasyuiTree> easyuiTrees1 = easyuiTreeMapper.selectByExample(example);

        List<EasyuiTreeNode> list = getEasyuiTreeNodeList(0l, easyuiTrees);
        return list;
    }

    @Override
    public EasyuiGrid getEasyuiGridDataList(Integer page, Integer rows, String name) {
        //分页处理
        PageHelper.startPage(page, rows);
        TbEasyuiGridExample example = new TbEasyuiGridExample();
        TbEasyuiGridExample.Criteria criteria = example.createCriteria();

        if (!StringUtils.isEmpty(name)) {
            criteria.andNameLike("%" + name.trim() + "%");
        }

        List<TbEasyuiGrid> easyuiGrids = easyuiGridMapper.selectByExample(example);

        PageInfo pageInfo = new PageInfo(easyuiGrids);

        //返回处理结果
        EasyuiGrid easyuiGrid = new EasyuiGrid();
        easyuiGrid.setTotal(pageInfo.getTotal());
        easyuiGrid.setRows(pageInfo.getList());
        return easyuiGrid;
    }

    @Override
    public IcqlResult saveEasyuiGridData(TbEasyuiGrid data) {
        int count;
        if (StringUtils.isEmpty(data.getId())) {
            Date now = new Date();
            data.setCreated(now);
            data.setUpdated(now);
            count = easyuiGridMapper.insert(data);
        } else {
            TbEasyuiGrid tbEasyuiGrid = easyuiGridMapper.selectByPrimaryKey(data.getId());
            if (tbEasyuiGrid.getVersion() != data.getVersion()) {
                return IcqlResult.build(204, "数据已被更新，请重新编辑");
            }

            data.setCreated(tbEasyuiGrid.getCreated());
            data.setUpdated(new Date());

            count = easyuiGridMapper.updateByPrimaryKey(data);
        }
        if (count > 0) {
            return IcqlResult.ok();
        } else {
            return IcqlResult.build(204, "未保存任何数据");
        }
    }

    @Override
    public IcqlResult deleteEasyuiGridData(String ids) {
        String[] arrIds = ids.split(",");
        for (String id : arrIds) {
            easyuiGridMapper.deleteByPrimaryKey(id);
        }
        return IcqlResult.ok();
    }

    @Override
    public IcqlResult importEasyuiGridData(MultipartFile file) {
        if (file.isEmpty()) {
            return IcqlResult.build(204, "上传的文件为空！");
        }

        String originalFilename = file.getOriginalFilename();
        String extName = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
        Map<String, String> mapHeaderField = new HashMap<>();
        mapHeaderField.put("name", "name");
        mapHeaderField.put("price", "price");
        mapHeaderField.put("status", "status");

        List<TbEasyuiGrid> tbEasyuiGrids = null;
        try {
            tbEasyuiGrids = PoiUtils.excel2list(TbEasyuiGrid.class, file.getBytes(), mapHeaderField, "xls".equals(extName) ? 0 : 1);
        } catch (Exception ex) {
            return IcqlResult.build(500, ex.getMessage());
        }

        for (TbEasyuiGrid item : tbEasyuiGrids) {
            saveEasyuiGridData(item);
        }
        return IcqlResult.ok();
    }

    @Override
    public void downloadEasyuiGridData(HttpServletResponse response, String name) {
        TbEasyuiGridExample example = new TbEasyuiGridExample();
        TbEasyuiGridExample.Criteria criteria = example.createCriteria();
        if (!StringUtils.isEmpty(name)) {
            criteria.andNameLike("%" + name.trim() + "%");
        }
        List<TbEasyuiGrid> tbEasyuiGrids = easyuiGridMapper.selectByExample(example);
        byte[] bytes = PoiUtils.list2excel(TbEasyuiGrid.class, tbEasyuiGrids);
        String fileName = "测试" + UUID.randomUUID() + ".xls";
        HttpUtils.download(response, fileName, bytes);
    }

    //代码递归
    private List<EasyuiTreeNode> getEasyuiTreeNodeList(Long id, List<TbEasyuiTree> easyuiTrees) {
        List<EasyuiTreeNode> list = new ArrayList<EasyuiTreeNode>();
        for (TbEasyuiTree item : easyuiTrees) {
            if (item.getPid() != id) {
                continue;
            }
            EasyuiTreeNode node = new EasyuiTreeNode();
            node.setId(item.getId());
            node.setText(item.getName());

            EasyuiTreeNodeAttributes easyuiTreeNodeAttributes = new EasyuiTreeNodeAttributes();
            easyuiTreeNodeAttributes.setUrl(item.getUrl());

            node.setAttributes(easyuiTreeNodeAttributes);

            List<EasyuiTreeNode> easyuiTreeNodeList = getEasyuiTreeNodeList(item.getId(), easyuiTrees);
            if (null != easyuiTreeNodeList && easyuiTreeNodeList.size() > 0) {
                node.setChildren(easyuiTreeNodeList);
                node.setState("closed");
            }

            //easyui tree 是否为叶子节点标志：state为undefined，若state为open或closed则不是叶子节点
            list.add(node);
        }
        return list;
    }

    //递归查询数据库
    private List<EasyuiTreeNode> getEasyuiTreeNodeList(Long pid) {
        //根据pid查询列表
        TbEasyuiTreeExample example = new TbEasyuiTreeExample();
        example.setOrderByClause("sort asc");//排序

        TbEasyuiTreeExample.Criteria criteria = example.createCriteria();
        criteria.andPidEqualTo(pid);

        List<TbEasyuiTree> easyuiTrees = easyuiTreeMapper.selectByExample(example);

        List<EasyuiTreeNode> list = new ArrayList<EasyuiTreeNode>();
        for (TbEasyuiTree item : easyuiTrees) {
            EasyuiTreeNode node = new EasyuiTreeNode();
            node.setId(item.getId());
            node.setText(item.getName());

            EasyuiTreeNodeAttributes easyuiTreeNodeAttributes = new EasyuiTreeNodeAttributes();
            easyuiTreeNodeAttributes.setUrl(item.getUrl());

            node.setAttributes(easyuiTreeNodeAttributes);

            List<EasyuiTreeNode> easyuiTreeNodeList = getEasyuiTreeNodeList(item.getId());
            if (null != easyuiTreeNodeList && easyuiTreeNodeList.size() > 0) {
                node.setChildren(easyuiTreeNodeList);
                node.setState("closed");
            }

            //easyui tree 是否为叶子节点标志：state为undefined，若state为open或closed则不是叶子节点
            list.add(node);
        }

        return list;
    }
}
