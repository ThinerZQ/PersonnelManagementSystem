package com.zq.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Hyperlink;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Picture;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.zq.bean.Department;
import com.zq.bean.User;

public class ExcelBean {

	private XSSFWorkbook wb =null;
	public ExcelBean(){
		wb = new XSSFWorkbook();
	}
	public void createFixationSheet(Map<String ,List> allInfoMap,OutputStream os)throws IOException{
		
		//创建sheet
		XSSFSheet sheet = wb.createSheet("人事信息");
		wb.setSheetName(0, "人事信息");
		//创建第一行
		Row  row = sheet.createRow((short)0);
		//设置冻结窗口
		sheet.createFreezePane(0, 2);
		sheet.addMergedRegion(new CellRangeAddress(
				0,0,3,17
				));
		//调用自定义函数，生成头部信息
		this.createUrl(wb,row,(short)3,"10软件工程1班人事信息");
		
		//创建第二行
		row  = sheet.createRow((short)1);
		createCell(wb, row, (short)3, "员工号");
		createCell(wb, row, (short)4, "员工姓名");
		createCell(wb, row, (short)5, "别名");
		createCell(wb, row, (short)6, "密码");
		createCell(wb, row, (short)7, "年龄");
		createCell(wb, row, (short)8, "性别");
		createCell(wb, row, (short)9, "邮件");
		createCell(wb, row, (short)10, "入职时间");
		createCell(wb, row, (short)11, "部门名称");
		
		//创建图片
		String pictureString = "E:\\Jackie\\Documents\\WorkSpace\\Eclipse-jee\\POI\\WebContent\\me.jpg";
		createPicture(wb, sheet,pictureString,0,2,0,3);
		
		int i=0;
		
		
		//得到参数传过来的消息
		List<User> userList = allInfoMap.get("userInfoList");
		List<Department> departmentList = allInfoMap.get("departmentinfoList");
		List<String> aboutNumInfoList = allInfoMap.get("aboutNumInfoList");
		
		
		
		//根据得到的信息，输出到excel
		try{
			int j=1;
			i=userList.size();
			System.out.println("下载的xlsx文件共包含 ："+i+"  用户的信息");
			Iterator<User> it = userList.iterator();
			//循环输出,用户信息
			while(it.hasNext()){
				j++;
				Row row2 = sheet.createRow((short)j);
				User user = it.next();
				//输出用户信息
				this.createCell(wb, row2, (short)(3), user.getId()+"");
				this.createCell(wb, row2, (short)(4), user.getRealname());
				this.createCell(wb, row2, (short)(5), user.getUsername());
				this.createCell(wb, row2, (short)(6), user.getPassword());
				this.createCell(wb, row2, (short)(7), user.getAge()+"");
				this.createCell(wb, row2, (short)(8), user.getSex());
				this.createEmail(wb, row2, (short)(9), user.getEmail());
				this.createCell(wb, row2, (short)(10), user.getRegisterDate()+"");
				this.createCell(wb, row2, (short)(11), user.getDepartmentName());
				
			}
			//迭代，准备遍历部门信息
			Iterator<Department> it1 = departmentList.iterator();
			i=departmentList.size();
			System.out.println("下载的xlsx文件共包含 ："+i+"  个部门的信息");
			j=j+3;
			this.createCell(wb, sheet.createRow((short)j), (short)2, "小计");
			while(it1.hasNext()){
				j++;
				Row row2 = sheet.createRow((short)j);
				Department department = it1.next();
				
				this.createCell(wb, row2, (short)(3), department.getName());
				this.createCell(wb, row2, (short)(4), department.getMember_num()+"人");
			}
			
			j=j+1;
			Row row3 =  sheet.createRow((short)j);
			//输出总计信息
			this.createCell(wb,row3, (short)10, "总计");
			this.createCell(wb, row3, (short)11, aboutNumInfoList.get(0));
			Row row4 =sheet.createRow((short)(j+1));
			this.createCell(wb, row4, (short)10,"日期");
			this.createCell(wb, row4, (short)11, new Date().toLocaleString());
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		sheet.autoSizeColumn(5);
		sheet.autoSizeColumn(6);
		
		sheet.autoSizeColumn(9);
		sheet.autoSizeColumn(10);
		sheet.autoSizeColumn(11);
		//关闭流
		wb.write(os);
		os.flush();
		os.close();
		
		
	}
	//创建普通cell
	private void createCell(XSSFWorkbook wb ,Row row,short col,String val){
		Cell cell = row.createCell(col);
		if(val==null){
			cell.setCellValue("空");
		}else{
			cell.setCellValue(val);
		}
		
		CellStyle cellstyle = wb.createCellStyle();
		cellstyle.setAlignment(CellStyle.ALIGN_CENTER_SELECTION);
		cell.setCellStyle(cellstyle);
	}
	//创建头部cell
	private  void createCellHeader(XSSFWorkbook wb,Row row,short col,String val){
		
			Cell cell = row.createCell(col);
			cell.setCellValue(val);
			CellStyle cellstyle = wb.createCellStyle();
			Font font = wb.createFont();
			font.setFontHeightInPoints((short)24);
			font.setFontName("Courier New");
			font.setItalic(true);
			//font.setStrikeout(true);
			cellstyle.setFont(font);
			cellstyle.setAlignment(CellStyle.ALIGN_CENTER_SELECTION);
			cell.setCellStyle(cellstyle);
			
		}
		//创建图片
	private  void createPicture(XSSFWorkbook wb, XSSFSheet sheet,String fileString,int row1,int row2,int col1,int col2){
			int pictureIdx=0;
			
			try {
				InputStream is = new FileInputStream(fileString);
				byte[] bytes = IOUtils.toByteArray(is);
				pictureIdx = wb.addPicture(bytes, Workbook.PICTURE_TYPE_JPEG);
				is.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			CreationHelper helper = wb.getCreationHelper();
			
			
			Drawing drawing = sheet.createDrawingPatriarch();
			//添加一个图片图形
			ClientAnchor anchor = helper.createClientAnchor();
			//设置图像左上角的位置
			//然后调用picture的resize方法，自动关联到uzobiao
			anchor.setCol1(col1);
			anchor.setRow1(row1);
			anchor.setCol2(col2);
			anchor.setRow2(row2);
			Picture pict = drawing.createPicture(anchor, pictureIdx);
			
			//自动关联到新坐标
			//pict.resize();
		}
	private void createUrl(XSSFWorkbook wb,Row row,short col,String val){
		
		CreationHelper createHelper = wb.getCreationHelper();
		//设置单元格的格式为超级链接
		//默认，下划线，蓝色字体
		CellStyle hlink_style = wb.createCellStyle();
		Font hlink_font = wb.createFont();
		hlink_font.setUnderline(Font.U_SINGLE);
		hlink_font.setColor(IndexedColors.BLUE.index);
		hlink_font.setFontHeightInPoints((short)24);
		hlink_font.setFontName("Courier New");
		hlink_font.setItalic(true);
		
		hlink_style.setAlignment(CellStyle.ALIGN_CENTER_SELECTION);
		hlink_style.setFont(hlink_font);
		
		Cell cell = row.createCell(col);
		cell.setCellValue(val);
		
		Hyperlink link = createHelper.createHyperlink(Hyperlink.LINK_URL);
		
		link.setAddress("http://localhost:8087/softwareassignment/index.jsp");
		cell.setHyperlink(link);
		cell.setCellStyle(hlink_style);
		
	}
	//设置邮件格式
	private void createEmail(XSSFWorkbook wb,Row row,short col,String val){
		//得到创建助手
		CreationHelper createHelper = wb.getCreationHelper();
		//设置cell样式
		CellStyle hlink_style =wb.createCellStyle();
		//设置字体
		Font hlink_font = wb.createFont();
		hlink_font.setUnderline(Font.U_SINGLE);
		hlink_font.setColor(IndexedColors.BLUE.getIndex());
		hlink_style.setFont(hlink_font);
		//创建单元格，并且复制
		Cell cell = row.createCell(col);
		cell.setCellValue(val);
		//创建邮件样式
		Hyperlink link = createHelper.createHyperlink(Hyperlink.LINK_EMAIL);
		
		link.setAddress("mailto:"+val);
		cell.setHyperlink(link);
		cell.setCellStyle(hlink_style);
		
	}
}
