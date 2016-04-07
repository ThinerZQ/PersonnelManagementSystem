package com.zq.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.zq.bean.User;
import com.zq.service.UserService;
import com.zq.service.impl.UserServiceImpl;

public class FileUpload {

	private String filename1;

	public void dealFileUpload(HttpServletRequest request,String filenames){
		DiskFileItemFactory factory = new DiskFileItemFactory();
		
		//获取文件需要上传到的路径
		String path = "E:/Jackie/Documents/WorkSpace/Eclipse-jee/softwareassignment/WebContent"+"/upload";
		
		System.out.println("文件上传路径为： "+path);
		
		//如果没以下两行设置的话，上传大的 文件 会占用 很多内存，
		//设置暂时存放的 存储室 , 这个存储室，可以和 最终存储文件 的目录不同
		/**
		 * 原理 它是先存到 暂时存储室，然后在真正写到 对应目录的硬盘上， 
		 * 按理来说 当上传一个文件时，其实是上传了两份，第一个是以 .tem 格式的 
		 * 然后再将其真正写到 对应目录的硬盘上
		 */
		factory.setRepository(new File(path));
		//设置 缓存的大小，当上传文件的容量超过该缓存时，直接放到 暂时存储室
		factory.setSizeThreshold(1024*1024) ;
				
		//高水平的API文件上传处理
		ServletFileUpload upload = new ServletFileUpload(factory);
		
		User user = (User) request.getSession().getAttribute("user");
		
		
		try {
			//可以上传多个文件
			List<FileItem> list = (List<FileItem>)upload.parseRequest(request);
			
			for(FileItem item : list)
			{
				//获取表单的属性名字
				String name = item.getFieldName();
				
				//如果获取的 表单信息是普通的 文本 信息
				if(item.isFormField())
				{					
					//获取用户具体输入的字符串 ，名字起得挺好，因为表单提交过来的是 字符串类型的
					String value = item.getString() ;
					
					request.setAttribute(name, value);
				}
				//对传入的非 简单的字符串进行处理 ，比如说二进制的 图片，电影这些
				else
				{
					/**
					 * 以下三步，主要获取 上传文件的名字
					 */
					//获取路径名
					String value = item.getName() ;
					System.out.println("value=  "+value);
					//索引到最后一个.
					int start = value.lastIndexOf(".");
					System.out.println("start == " +start);
					//截取 上传文件的后缀名
					String filenametail = value.substring(start);
					System.out.println("上传的是以  "+filenametail+"  为后缀的文件");
					
					String filename = filenames+filenametail;
					System.out.println("最终的文件名为： "+filename);
					//真正写到磁盘上
					//它抛出的异常 用exception 捕捉
					
					//item.write( new File(path,filename) );//第三方提供的
					
					//手动写的
					OutputStream out = new FileOutputStream(new File(path,filename));
					
					InputStream in = item.getInputStream() ;
					
					int length = 0 ;
					byte [] buf = new byte[1024] ;
					
					System.out.println("获取上传文件的总共的容量："+item.getSize());

					// in.read(buf) 每次读到的数据存放在   buf 数组中
					while( (length = in.read(buf) ) != -1)
					{
						//在   buf 数组中 取出数据 写到 （输出流）磁盘上
						out.write(buf, 0, length);	    
					}
					
					
					//调用service来将变化存储到数据库
					UserService userService = new UserServiceImpl();
					int result = userService.savePhoto(user.getId()+"", "upload/"+filename);
					if(result==-1){
						
						request.setAttribute("info", "修改失败，再来一次");
					}else{
						request.setAttribute("info", "修改成功");
					}
					user.setPhoto("upload/"+filename);
					//将改变的user对象，重新放入session
					request.getSession().setAttribute("user", user);
					
					in.close();
					out.close();
				}
			}
			
		} catch (FileUploadException e) {
			e.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
