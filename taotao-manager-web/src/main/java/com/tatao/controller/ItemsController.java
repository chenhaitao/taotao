package com.tatao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.taotao.pojo.TbItem;
import com.taotao.result.TaotaoResult;
import com.taotao.service.ItemService;

@Controller
public class ItemsController {
	
	@Autowired
	@Qualifier("itemService")
	private  ItemService itemService;
	
	@RequestMapping(value="/item/{itemId}")
	@ResponseBody
	public TbItem getITemById(@PathVariable long itemId) {
		TbItem item = this.itemService.getTbItem(itemId);
		System.out.println(item);
		return item;
	}
	
	@RequestMapping("/item/save")
	@ResponseBody
	public TaotaoResult addItem(TbItem item,String desc) {
		
		try {
			TaotaoResult taotaoResult = this.itemService.createItem(item, desc);
			return taotaoResult;
		} catch (Exception e) {
			e.printStackTrace();
			TaotaoResult taotaoResult = TaotaoResult.build(500, "添加商品失败");
			return taotaoResult;
		}
		
		
	}
}
