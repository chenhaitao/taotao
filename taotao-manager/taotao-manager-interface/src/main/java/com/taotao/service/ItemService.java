package com.taotao.service;

import com.taotao.pojo.TbItem;
import com.taotao.result.EasyUIDataResult;
import com.taotao.result.TaotaoResult;

public interface ItemService {
	//根据商品id获取商品信息
	public TbItem getTbItem(long id);
	
	//按照页获取商品信息
	public EasyUIDataResult getItemList(int page,int rows) ;

	public TaotaoResult createItem(TbItem item,String desc) throws Exception;
	
}
