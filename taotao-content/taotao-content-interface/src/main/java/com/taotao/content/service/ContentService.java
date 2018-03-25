package com.taotao.content.service;

import java.util.List;

import com.taotao.pojo.TbContent;

public interface ContentService {
	public List<TbContent> getContentListByCid(long cid);
}
