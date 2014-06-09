package com.web4j.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.web4j.exception.DoErrorException;

/**
 * 文件读写类
 * @author xixi
 * @date 2012-10-31
 *
 */
public class FileUtil {
	
	private static String WEBSERVER_DIR=null;
	
	static {
		WEBSERVER_DIR = new File(ServletActionContext.getServletContext()
				.getRealPath("/")).getParentFile().getPath()
				.replaceAll("\\\\", "/");
	}

	/**
	 * 写文件
	 * @param file 源文件
	 * @param fileName 文件名
	 * @param realPath 保存路径
	 * @throws IOException
	 */
	public static void writeFile (File file,String fileName,String realPath) throws IOException{
        File savefile = new File(new File(realPath), fileName);
        if (!savefile.getParentFile().exists()){
        	savefile.getParentFile().mkdirs();
        }
        FileUtils.copyFile(file, savefile);
	}
	
	/**
	 * 删除目录以及目录下的所有文件
	 * @param realPath
	 * @throws IOException
	 */
	public static void removeFile(String realPath) throws IOException{
		File dir=new File(realPath);
		FileUtils.deleteDirectory(dir);
	}
	
	/**
	 * 验证文件，文件类型，文件大小有有效性
	 * @param file
	 * @param contentType
	 * @param fileName
	 * @param contentTypes
	 * @param maxSize
	 * @throws Exception
	 */
	public static void validate(File file,String contentType,String fileName,String []contentTypes,int maxSize)throws Exception{
		if(file==null||contentType==null||fileName==null){
			throw new DoErrorException("上传失败，文件损坏。");
		}
		if (!checkContentType(contentType,contentTypes)) {
			throw new DoErrorException("文件类型错误。");
		}
		FileInputStream fs=new FileInputStream(file);
		if(fs.available()>maxSize){
			throw new DoErrorException("文件超出规定大小。");
		}
	}
	
	/**
	 * 检查文件类型是否在指定的文件类型数组中
	 * @param contentType
	 * @param types
	 * @return
	 */
	public static boolean checkContentType(String contentType,String [] types){
		boolean isFlag=false;
		for(String t:types){
			if(contentType.equals(t)){
				isFlag=true;
				break;
			}
		}
		return isFlag;
	}
	
	/**
	 * 获得文件流 
	 * @param relaPath
	 * @return
	 * @throws Exception
	 */
	public static InputStream getFileInputStream(String relaPath)throws Exception{
		String filePath=getTomcatWebAppPath()+relaPath;
		File file=new File(filePath);
		if(!file.exists()){
			throw new DoErrorException("文件不存在！");
		}else{
			return new FileInputStream(file);
		}
	}
	
	/**
	 * 获得tomcat webapp 目录
	 * @return
	 */
	public static String getTomcatWebAppPath() {
		return WEBSERVER_DIR;
	}
	
	/**
	 * 把文件大小转换为带两位小数点的字符
	 * @param size
	 * @return
	 */
	public static String formatFileSizeToStr(long size){
		DecimalFormat df=new DecimalFormat("#.00");
		return(df.format((double)size/1024/1024))+"M";
	}
}
