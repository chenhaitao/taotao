package com.tatao.util;

import java.io.IOException;

import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;

public class FastDfsUtil {
	
	private TrackerClient trackerClient;
	private TrackerServer trackerServer;
	private StorageClient storageClient;
	private StorageServer storageServer;
	
	public FastDfsUtil(String config) throws Exception{
		if (config.contains("classpath:")) {
			config = config.replace("classpath:", this.getClass().getResource("/").getPath());
		}
		
		ClientGlobal.init(config);
		this.trackerClient = new TrackerClient();
		this.trackerServer = this.trackerClient.getConnection();
		this.storageServer = null;
		this.storageClient = new StorageClient(this.trackerServer, this.storageServer);
	}
	
	public String[] updateFile(String fileName,String extName,NameValuePair[] meta) throws IOException, MyException {
		String[] filePaths = this.storageClient.upload_file(fileName, extName, meta);
		return filePaths;
	}
	
	
	public String[] updateFile(byte[] fileContent,String extName,NameValuePair[] meta) throws IOException, MyException {
		String[] filePaths = this.storageClient.upload_file(fileContent, extName, meta);
		return filePaths;
	}

}
