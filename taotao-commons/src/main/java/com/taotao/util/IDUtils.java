package com.taotao.util;

import java.util.Random;

public class IDUtils {
	public static long getItemId() {
		
		long mills = System.currentTimeMillis();
		int random = new Random().nextInt(99);
		String randomStr = String.format("%02d", random);
		String idStr = mills + randomStr;
		
		
		return new Long(idStr);
	}
	
	
}
