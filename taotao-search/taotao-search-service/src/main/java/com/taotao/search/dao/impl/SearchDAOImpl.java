package com.taotao.search.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.taotao.pojo.SearchItem;
import com.taotao.pojo.SearchResult;
import com.taotao.search.dao.SearchDAO;

@Component
public class SearchDAOImpl implements SearchDAO{
	@Autowired
	private SolrServer solrServer;

	@Override
	public SearchResult search(SolrQuery query) throws Exception {
		System.out.println("hello search");
		System.out.println(solrServer);
		//根据query对象进行查询  
        QueryResponse response = solrServer.query(query);  
        //取查询结果  
        SolrDocumentList solrDocumentList = response.getResults();  
        //取查询结果总记录数  
        long numFound = solrDocumentList.getNumFound();  
        //初始化一个SolrResult对象并把总数属性进行赋值  
        SearchResult searchResult = new SearchResult();  
        searchResult.setTotalNumber(numFound);  
        List<SearchItem> itemList = new ArrayList<>();  
        //把查询结果封装到SearchItem当中  
        for (SolrDocument solrDocument : solrDocumentList) {  
            SearchItem searchItem = new SearchItem();  
            searchItem.setId((String)solrDocument.get("id"));  
            searchItem.setImage((String)solrDocument.get("item_image"));  
            searchItem.setName((String)solrDocument.get("item_category_name"));  
            searchItem.setItem_desc((String)solrDocument.get("item_desc"));  
            searchItem.setPrice((long)solrDocument.get("item_price"));  
            searchItem.setSell_point((String)solrDocument.get("item_sell_point"));  
            //取高亮显示  
            Map<String,Map<String,List<String>>> highlighting = response.getHighlighting();  
            List<String> list = highlighting.get(solrDocument.get("id")).get("item_title");  
            String title = "";  
            if(list != null && list.size() > 0){  
                title = list.get(0);  
            } else {  
                title = (String)solrDocument.get("item_title");  
            }  
            searchItem.setTitle(title);  
            itemList.add(searchItem);  
        }  
        //把结果添加到SearchResult当中  
        searchResult.setItemList(itemList);  
        //返回  
        return searchResult;  
	}

}
