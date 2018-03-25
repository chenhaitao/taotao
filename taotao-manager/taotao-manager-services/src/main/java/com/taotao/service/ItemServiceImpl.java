package com.taotao.service;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.mapper.TbItemDescMapper;
import com.taotao.mapper.TbItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.pojo.TbItemExample;
import com.taotao.result.EasyUIDataResult;
import com.taotao.result.TaotaoResult;
import com.taotao.service.ItemService;
import com.taotao.util.IDUtils;


@Service
public class ItemServiceImpl implements ItemService{

	@Autowired
	private TbItemMapper itemMapper;
	
	@Autowired
	private TbItemDescMapper itemDescMapper;
	
	@Override
	public TbItem getTbItem(long id) {
		TbItem item = this.itemMapper.selectByPrimaryKey(id);
		return item;
	}

	@Override
	public EasyUIDataResult getItemList(int page, int rows) {
		
		PageHelper.startPage(page, rows);
		
		TbItemExample itemExample = new TbItemExample();
		List<TbItem> list = this.itemMapper.selectByExample(itemExample);
		
		PageInfo<TbItem> pageInfo = new PageInfo<>(list);
		
		EasyUIDataResult result = new EasyUIDataResult();
		result.setTotal(String.valueOf(pageInfo.getTotal()));
		result.setRows(list);
		
		return result;
	}

	@Override
	public TaotaoResult createItem(TbItem item, String desc) throws Exception {
		
		long itemId = IDUtils.getItemId();
		item.setId(itemId);
		//商品状态，1-正常，2-下架，3-删除
		item.setStatus((byte) 1);
		item.setUpdated(new Date());
		item.setCreated(new Date());
		
		TbItemDesc itemDesc = new TbItemDesc();
		itemDesc.setItemId(itemId);
		itemDesc.setItemDesc(desc);
		itemDesc.setUpdated(new Date());
		itemDesc.setCreated(new Date());
		
		this.itemMapper.insert(item);
		this.itemDescMapper.insert(itemDesc);
		
		return TaotaoResult.ok();
	}

}
