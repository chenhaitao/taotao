package com.tatao.controller;

import java.awt.image.RescaleOp;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.dubbo.common.URL;
import com.alibaba.fastjson.JSON;
import com.tatao.util.FastDfsUtil;

@Controller
public class PictrueController {

	@Value("${IMAGE_SERVER_URL}")
	private String IMAGE_SERVER_URL;
	
	@RequestMapping("/pic/upload")
	@ResponseBody
	public String uploadFile(MultipartFile uploadFile) {
		
		String fileName = uploadFile.getOriginalFilename();
		
		
		String extName = fileName.substring(fileName.lastIndexOf(".")+1);
		
		FastDfsUtil fastDfsUtil = null;
		Map result = new HashMap<>();
		try {
			System.out.println("hello");
			System.out.println(uploadFile.getBytes());
			System.out.println(extName);
			fastDfsUtil = new FastDfsUtil("classpath:resource/client.conf");
			String[] uploadFileNames = fastDfsUtil.updateFile(uploadFile.getBytes(), extName, null);
			System.out.println(uploadFileNames.length);
			result.put("error", 0);
			result.put("url", this.IMAGE_SERVER_URL+"/"+uploadFileNames[0]+"/"+uploadFileNames[1]);
		} catch (Exception e) {
			e.printStackTrace();
			result.put("error", 1);
			result.put("message", "上传图片失败~");
		}
		
		return JSON.toJSONString(result);
	}
}
