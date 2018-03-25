package com.taotao.content.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.content.service.ContentCategoryService;
import com.taotao.mapper.TbContentCategoryMapper;
import com.taotao.pojo.TbContentCategory;
import com.taotao.pojo.TbContentCategoryExample;
import com.taotao.pojo.TbContentCategoryExample.Criteria;
import com.taotao.result.EasyUITreeNode;
import com.taotao.result.TaotaoResult;

@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {

	@Autowired
	private TbContentCategoryMapper contentCategoryMapper;
	
	@Override
	public List<EasyUITreeNode> getContentCategoryList(long parentId) {
		
		TbContentCategoryExample contentCategoryExample = new TbContentCategoryExample();
		Criteria criteria = contentCategoryExample.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		
		List<TbContentCategory> list = this.contentCategoryMapper.selectByExample(contentCategoryExample);
		System.out.println("hello"+list+parentId);
		List<EasyUITreeNode> result = new ArrayList<>();
		for(TbContentCategory item : list) {
			EasyUITreeNode node = new EasyUITreeNode();
			node.setId(item.getId());
			node.setText(item.getName());
			node.setState(item.getIsParent()?"closed":"open");
			result.add(node);
		}
		
		return result;
	}

	@Override
	public TaotaoResult addContentCategory(long parentId, String name) {
		TbContentCategory contentCategory = new TbContentCategory();
		contentCategory.setParentId(parentId);
		contentCategory.setName(name);
		contentCategory.setIsParent(false);
		contentCategory.setSortOrder(1);
		//可选值:1(正常),2(删除) 
		contentCategory.setStatus(1);
		contentCategory.setCreated(new Date());
		contentCategory.setUpdated(new Date());
		
		this.contentCategoryMapper.insert(contentCategory);
		
		TbContentCategory parent = this.contentCategoryMapper.selectByPrimaryKey(parentId);
		if(parent != null && parent.getIsParent() == false) {
			parent.setIsParent(true);
		}

		return TaotaoResult.ok(contentCategory);
	}

}
