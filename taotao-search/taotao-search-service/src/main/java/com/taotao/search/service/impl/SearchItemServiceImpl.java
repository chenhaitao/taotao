package com.taotao.search.service.impl;

import java.util.List;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.pojo.SearchItem;
import com.taotao.result.TaotaoResult;
import com.taotao.search.mapper.SearchMapper;
import com.taotao.search.service.SearchItemService;

@Service
public class SearchItemServiceImpl implements SearchItemService {
	@Autowired
	private SearchMapper searchMapper;

	@Autowired
	private SolrServer SolrServer;
	
	@Override
	public TaotaoResult importItemsToIndex() {
		
		
		List<SearchItem> data = this.searchMapper.getSearchItemList();
		try {
			for(SearchItem item : data) {
				SolrInputDocument document = new SolrInputDocument();
				document.addField("id", item.getId());  
		        document.addField("item_title",item.getTitle());  
		        document.addField("item_sell_point", item.getSell_point());  
		        document.addField("item_price", item.getPrice() * 100);  
		        document.addField("item_image", item.getImage());  
		        document.addField("item_category_name",item.getName());  
		        document.addField("item_desc", item.getItem_desc());  
		        SolrServer.add(document);
			}
			SolrServer.commit();
			return TaotaoResult.ok();
		} catch (Exception e) {
			e.printStackTrace();
			return TaotaoResult.build(500, "导入搜索数据失败！");
		}
		
		
	}

}
