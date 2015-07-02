import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.http.HttpServletResponse;

import mybatis.dao.ImsiMapper;
import mybatis.model.Imsi;

import org.apache.struts2.ServletActionContext;

import util.BeanGetter;
import util.DateHelper;
import util.MyVersion;

import com.opensymphony.xwork2.ActionSupport;

public class VpdnAction extends ActionSupport {
	ImsiMapper im=(ImsiMapper)BeanGetter.getBean("imsiMapper");
	private String imsiString;

	public String getImsiString() {
		return imsiString;
	}

	public void setImsiString(String imsiString) {
		this.imsiString = imsiString;
	}
	public String addImsi(){
		System.out.println(imsiString);
		if(imsiString!=null&&!imsiString.trim().equals("")){
			String[] imsiItems=imsiString.split("/");
			for(String it:imsiItems){
				String []imsitime=it.split(",");
				Imsi imsi=new Imsi();
				imsi.setName(imsitime[0]);
				imsi.setTesttime(DateHelper.parseDateDetail(imsitime[1]));
				imsi.setSubmittime(DateHelper.getCurrentYYYYMMDDHHMMSS());
				im.insert(imsi);
			}
		}
		return null;
	}
	
	public String getVersion() {
		System.out.println("gooood");
		HttpServletResponse response = ServletActionContext.getResponse();
		try {

			 response.setContentType("text/html;charset=utf-8");
			  response.setCharacterEncoding("UTF-8");
		       
			  
	
	            //创建一个FileReader
	         //   FileReader  f=new FileReader( ServletActionContext.getServletContext().getRealPath("")+"\\update.txt","gbk");
	           // InputStreamReader read = new InputStreamReader(new FileInputStream(ServletActionContext.getServletContext().getRealPath("")+"\\update.txt"),"utf-8");           
	         //   BufferedReader bw=new BufferedReader(read);
	              
//	              
//	             //读出一行数据 或者使用for循环都区全部内容   
//	            String version=bw.readLine().trim();
//	             String comment=bw.readLine();
//	             System.out.println("gooood2");
//	             
//	             //一定要关闭文件流     
//	            bw.close();
//	           
			  
	        
	             String returned="{\"versionCode\":"+Integer.parseInt(MyVersion.VERSIONCODE)+",\"comment\":\""+MyVersion.COMMENT+"\"}";
	
		 
			response.getWriter().write(returned);
			
			response.flushBuffer();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
