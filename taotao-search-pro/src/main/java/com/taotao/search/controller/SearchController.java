package com.taotao.search.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.taotao.pojo.SearchResult;
import com.taotao.result.TaotaoResult;
import com.taotao.search.service.SearchService;

@Controller
public class SearchController {
	@Autowired
	private SearchService searchService;
	@Value("${NumberRowsOfPage}")
	private Integer numberRowsOfPage;
	
	@RequestMapping("/search")
	public String search(@RequestParam(value="q") String queryString,
		@RequestParam(defaultValue="1")Integer page,Model model) throws Exception{
		
		int data = 1/0;
		queryString = new String(queryString.getBytes("iso8859-1"), "utf8");
		System.out.println("hello world");
		SearchResult searchResult = searchService.search(queryString, page, numberRowsOfPage);
		System.out.println("hello"+searchResult);
		 //把结果传递给页面  
        model.addAttribute("query", queryString);  
        model.addAttribute("totalPages", searchResult.getTotalPages());  
        model.addAttribute("itemList", searchResult.getItemList());  
        model.addAttribute("page", page);  	
		
		return "search";
	}
}
