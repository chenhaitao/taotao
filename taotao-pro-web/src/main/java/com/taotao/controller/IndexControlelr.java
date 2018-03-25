package com.taotao.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.taotao.content.service.ContentService;
import com.taotao.pojo.TbContent;
import com.taotao.pro.pojo.AD1Node;

@Controller
public class IndexControlelr {
	
	//#大广告分类ID  
	@Value("${AD1_CATEGORY_ID}")
	private Long AD1_CATEGORY_ID ;
	//#设置图片的宽高  
	@Value("${AD1_WIDTH}")
	private Integer AD1_WIDTH;
	@Value("${AD1_WIDTH_B}")
	private Integer AD1_WIDTH_B;
	@Value("${AD1_HEIGHT}")
	private Integer AD1_HEIGHT;
	@Value("${AD1_HEIGHT_B}")
	private Integer AD1_HEIGHT_B=240 ;
	
	@Autowired
	private ContentService contentService;
	
	
	@RequestMapping("/index")
	public String showIndex(Model model) {
		
		List<TbContent> list = this.contentService.getContentListByCid(this.AD1_CATEGORY_ID);
		List<AD1Node> result = new ArrayList<>();
		for(TbContent item : list) {
			AD1Node node = new AD1Node();
			result.add(node);
			node.setAlt(item.getTitle());
			node.setHref(item.getUrl());
			node.setSrc(item.getPic());
			node.setSrcB(item.getPic2());
			node.setHeight(this.AD1_HEIGHT);
			node.setHeightB(this.AD1_HEIGHT_B);
			node.setWidth(this.AD1_WIDTH);
			node.setWidthB(this.AD1_WIDTH_B);
		}
		
		String ad1 = JSON.toJSONString(result);
		model.addAttribute("ad1", ad1);
		
		return "index";
	}
}
