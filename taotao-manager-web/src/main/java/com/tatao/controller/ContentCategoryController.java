package com.tatao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.content.service.ContentCategoryService;
import com.taotao.result.EasyUITreeNode;
import com.taotao.result.TaotaoResult;

@Controller
public class ContentCategoryController {
	
	@Autowired
	private ContentCategoryService contentCategoryService;
	
	@RequestMapping("/content/category/list")
	@ResponseBody
	public List<EasyUITreeNode> getContentCategoryList(@RequestParam(value="id",defaultValue="0") long parentId){
		List<EasyUITreeNode> list = this.contentCategoryService.getContentCategoryList(parentId);
		return list;
	}
	
	@RequestMapping("/content/category/create")
	@ResponseBody
	public TaotaoResult createContentCategory(long parentId,String name){
		TaotaoResult result = this.contentCategoryService.addContentCategory(parentId, name);
		return result;
	}
}
