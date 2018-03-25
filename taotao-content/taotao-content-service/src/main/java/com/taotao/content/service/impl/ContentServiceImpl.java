package com.taotao.content.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSON;
import com.taotao.content.service.ContentService;
import com.taotao.jedis.service.impl.JedisClientPool;
import com.taotao.mapper.TbContentMapper;
import com.taotao.pojo.TbContent;
import com.taotao.pojo.TbContentExample;
import com.taotao.pojo.TbContentExample.Criteria;


@Service
public class ContentServiceImpl implements ContentService {

	@Autowired
	private TbContentMapper contentMapper;
	
	@Autowired
	private JedisClientPool jedisClientPool;
	
	@Override
	public List<TbContent> getContentListByCid(long cid) {
		
		try {
			String json = jedisClientPool.hget("index_content", cid+"");
			if (json.trim().length() > 0) {
				List<TbContent> list = JSON.parseArray(json, TbContent.class);
				return list;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		TbContentExample example = new TbContentExample();
		Criteria criteria = example.createCriteria();
		criteria.andCategoryIdEqualTo(cid);
		List<TbContent> list = this.contentMapper.selectByExample(example);
		
		try {
			String json = JSON.toJSONString(list);
			this.jedisClientPool.hset("index_content", cid+"", json);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}

}
