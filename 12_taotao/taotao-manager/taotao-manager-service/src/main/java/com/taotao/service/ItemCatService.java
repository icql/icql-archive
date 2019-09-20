package com.taotao.service;


import com.taotao.common.pojo.EasyUITreeNodeBO;

import java.util.List;

public interface ItemCatService {
    List<EasyUITreeNodeBO> getItemCatList(long parentId);
}
