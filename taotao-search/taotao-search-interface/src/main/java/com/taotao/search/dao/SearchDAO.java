package com.taotao.search.dao;

import org.apache.solr.client.solrj.SolrQuery;

import com.taotao.pojo.SearchResult;

public interface SearchDAO {
	public  SearchResult search(SolrQuery query) throws Exception;
}
