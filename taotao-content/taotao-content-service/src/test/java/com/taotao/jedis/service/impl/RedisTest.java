package com.taotao.jedis.service.impl;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class RedisTest {


//	@Test
//	public  void  test1() {
//		Jedis jedis = new Jedis("192.168.131.107", 6379);
//		jedis.set("hello", "ni hao!");
//		String result = jedis.get("hello");
//		System.out.println(result);
//		jedis.close();
//	}
//	
//	@Test
//	public void test2() {
//		JedisPool pool = new JedisPool("192.168.131.107",6379);
//		Jedis jedis = pool.getResource();
//		jedis.set("hello", "world");
//		System.out.println(jedis.get("hello"));
//		jedis.close();
//		pool.close();
//	}
	
//	@Test
//	public void test3() {
//		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-jedis.xml");
//		JedisClientPool pool = (JedisClientPool) applicationContext.getBean(JedisClientPool.class);
//		pool.set("name", "tom");
//		System.out.println(pool.get("name"));
//		
//	}
	
	
}
