package com.tatao.search.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.result.TaotaoResult;
import com.taotao.search.service.SearchItemService;

@Controller
public class ImportIndexController {

	@Autowired
	private SearchItemService searchItemService;
	
	
	@RequestMapping("/index/import")
	@ResponseBody
	public TaotaoResult importIndex() {
		return this.searchItemService.importItemsToIndex();
	}
	
}
