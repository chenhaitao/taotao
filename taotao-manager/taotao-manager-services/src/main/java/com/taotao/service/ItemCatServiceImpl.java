package com.taotao.service;

import java.util.ArrayList;
import java.util.List;

import org.aspectj.weaver.ast.Literal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mysql.fabric.xmlrpc.base.Array;
import com.taotao.mapper.TbItemCatMapper;
import com.taotao.mapper.TbItemMapper;
import com.taotao.pojo.TbItemCat;
import com.taotao.pojo.TbItemCatExample;
import com.taotao.pojo.TbItemCatExample.Criteria;
import com.taotao.result.EasyUITreeNode;

@Service
public class ItemCatServiceImpl implements ItemCatService{
	@Autowired
	private TbItemCatMapper itemCatMapper;

	@Override
	public List<EasyUITreeNode> getItemCatList(long parentId) {
		TbItemCatExample catExample = new TbItemCatExample();
		Criteria criteria = catExample.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		List<TbItemCat> data = this.itemCatMapper.selectByExample(catExample);
		List<EasyUITreeNode> result = new ArrayList<EasyUITreeNode>();
		for(TbItemCat cat : data) {
			System.out.println(cat.getName()+" "+cat.getIsParent());
			EasyUITreeNode node = new EasyUITreeNode();
			node.setId(cat.getId());
			node.setText(cat.getName());
			node.setState(cat.getIsParent()?"closed":"open");
			result.add(node);
		}
		return result;
	}

}
