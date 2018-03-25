package com.tatao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.result.EasyUIDataResult;
import com.taotao.service.ItemService;

@Controller
public class PageController {
	
	@Autowired
	private ItemService itemService;
	
	@RequestMapping(value="/")
	public String index() {
		return "index";
	}
	
	@RequestMapping("/{page}")
	public String page(@PathVariable String page) {
		return page;
	}
	
	@RequestMapping("/item/list")
	@ResponseBody
	public EasyUIDataResult itemList(int page,int rows) {
		return this.itemService.getItemList(page, rows);
	}
}
