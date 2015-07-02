package util;

import java.io.FileNotFoundException;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFComment;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;


public class ExcelUtil {
	public static HSSFWorkbook workbook = new HSSFWorkbook();

	public static void main(String[] args) {
		LinkedList lt = new LinkedList();
		Object[] obj = new Object[3];
		obj[0] = new String("孙智强");
		obj[1] = null;
		obj[2] = new Integer(23);

		lt.add(obj);
		OutputStream out;
		OutputStream out1;
		try {
			out = new FileOutputStream("E:\\a.xls");
			out1 = new FileOutputStream("E:\\b.xls");
			ExcelUtil.exportExcel("我的个人信息3",
					new String[] { "姓名", "更新日期", "年纪" }, lt, out,
					"yyyy-MM-dd hh:mm", false, false);
			if (ExcelUtil.exportExcel("我的个人信息2", new String[] { "姓名", "更新日期",
					"年纪" }, lt, out, "yyyy-MM-dd hh:mm", false, true)) {
				System.out.println("good");
			}
			ExcelUtil.exportExcel("我的个人信息3",
					new String[] { "姓名", "更新日期", "年纪" }, lt, out1,
					"yyyy-MM-dd hh:mm", true, true);
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * 
	 * @param title
	 *            工作表的名字
	 * @param headers
	 *            表标题
	 * @param data
	 *            数据
	 * 
	 * @param out
	 *            写出文件
	 * @param pattern
	 *            日期格式 如“yyyy-mm-dd hh:ss"
	 * @param createNew
	 *            是否此EXCEL中的第一个工作表
	 * @param isLast
	 *            是否是此EXCEL中的最后一个工作表
	 * @return
	 */
	public static boolean exportExcel(String title, String[] headers,
			Collection data, OutputStream out, String pattern,
			boolean createNew, boolean isLast) {
		// 声明一个工作薄
		if (createNew) {
			workbook = new HSSFWorkbook();
		}

		// 生成一个表格

		HSSFSheet sheet = workbook.createSheet(title);
		// 设置表格默认列宽度为15个字节

		sheet.setDefaultColumnWidth(15);

		// 生成一个样式

		HSSFCellStyle style = workbook.createCellStyle();

		// 设置这些样式

		style.setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index);

		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);

		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);

		style.setBorderRight(HSSFCellStyle.BORDER_THIN);

		style.setBorderTop(HSSFCellStyle.BORDER_THIN);

		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);

		// 生成一个字体

		HSSFFont font = workbook.createFont();

		font.setColor(HSSFColor.VIOLET.index);

		font.setFontHeightInPoints((short) 12);

		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);

		// 把字体应用到当前的样式

		style.setFont(font);

		// 生成并设置另一个样式

		HSSFCellStyle style2 = workbook.createCellStyle();

		style2.setFillForegroundColor(HSSFColor.WHITE.index);

		style2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

		style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);

		style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);

		style2.setBorderRight(HSSFCellStyle.BORDER_THIN);

		style2.setBorderTop(HSSFCellStyle.BORDER_THIN);

		style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);

		style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

		// 生成另一个字体

		HSSFFont font2 = workbook.createFont();

		font2.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);

		// 把字体应用到当前的样式

		style2.setFont(font2);

		// 声明一个画图的顶级管理器

		HSSFPatriarch patriarch = sheet.createDrawingPatriarch();

		// 定义注释的大小和位置,详见文档

		HSSFComment comment = patriarch.createComment(new HSSFClientAnchor(0,
				0, 0, 0, (short) 4, 2, (short) 6, 5));

		// 设置注释内容

		comment.setString(new HSSFRichTextString("可以在POI中添加注释！"));

		// 设置注释作者，当鼠标移动到单元格上是可以在状态栏中看到该内容.

		comment.setAuthor("leno");

		// 产生表格标题行

		HSSFRow row = sheet.createRow(0);

		for (int i = 0; i < headers.length; i++) {

			HSSFCell cell = row.createCell(i);

			cell.setCellStyle(style);

			HSSFRichTextString text = new HSSFRichTextString(headers[i]);

			cell.setCellValue(text);

		}

		// 遍历集合数据，产生数据行

		Iterator<Object[]> it = data.iterator();

		int index = 0;
		HSSFFont font3 =workbook.createFont();
		while (it.hasNext()) {

			index++;

			row = sheet.createRow(index);

			Object[] t = (Object[]) it.next();
		
			// 最好先判断对象的长度是否与header长度一致

			for (int i = 0; i < t.length; i++) {
				HSSFCell cell = row.createCell(i);
				cell.setCellStyle(style2);
				Object value = t[i];
				String textValue = null;
			
				if (value instanceof Boolean) {

					boolean bValue = (Boolean) value;

					textValue = "是";

					if (!bValue) {

						textValue = "否";

					}

				} else if (value instanceof Date) {

					Date date = (Date) value;

					SimpleDateFormat sdf = new SimpleDateFormat(pattern);

					textValue = sdf.format(date);

				} else if (value instanceof byte[]) {

					// 有图片时，设置行高为60px;

					row.setHeightInPoints(60);

					// 设置图片所在列宽度为80px,注意这里单位的一个换算

					sheet.setColumnWidth(i, (short) (35.7 * 80));

					// sheet.autoSizeColumn(i);

					byte[] bsValue = (byte[]) value;

					HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0,

					1023, 255, (short) 6, index, (short) 6, index);

					anchor.setAnchorType(2);

					patriarch.createPicture(anchor, workbook.addPicture(

					bsValue, HSSFWorkbook.PICTURE_TYPE_JPEG));

				}else if(value==null){
					textValue="未定义";
				}else {

					// 其它数据类型都当作字符串简单处理

					textValue = value.toString();

				}
				HSSFRichTextString richString;
			

				// 如果不是图片数据，就利用正则表达式判断textValue是否全部由数字组成

				if (textValue != null) {

					Pattern p = Pattern.compile("^\\d+(\\.\\d+)?$");

					Matcher matcher = p.matcher(textValue);

//					if (matcher.matches()) {
//
//						// 是数字当作double处理
//
//						cell.setCellValue(Double.parseDouble(textValue));
//
//					} else {

					richString = new HSSFRichTextString(
								textValue);

					
						font3.setColor(HSSFColor.BLUE.index);

						richString.applyFont(font3);

						cell.setCellValue(richString);

//					}

				}
			}

		}

		try {
			if (isLast)
				workbook.write(out);
			return true;

		} catch (IOException e) {

			// TODO Auto-generated catch block

			e.printStackTrace();

		}

		return false;

	}

}
