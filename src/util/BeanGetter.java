package util;


import java.io.FileNotFoundException;
import java.sql.SQLException;

import mybatis.dao.ImsiMapper;
import mybatis.model.Imsi;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanGetter {
	private static ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");

	public static Object getBean(String beanName) { 
		return ctx.getBean(beanName);
	}

	public static void main(String[] args) throws FileNotFoundException, SQLException {
	
	

	//	GridbuildingMapper fm=(GridbuildingMapper)BeanGetter.getBean("gridbuildingMapper");
		//System.out.println(((Gridbuilding)fm.selectAll()).getUdepartment());
	//	PageHelper.startPage(2,20);
		
//		Gridbuilding gb=new Gridbuilding();
//				gb.setAcceptTime(DateHelper.getCurrentYYYYMMDDHHMMSS());
//		gb.setHasAssess(true);
//		gb.setName("测试区域2");
//		fm.insert(gb);
	
//	System.out.println(fm.selectByAll(null, "苏宁环球", null, null, null, null, null, null, null, null, null, null).size());
//	
//	List<Gridbuilding> t=fm.selectByAll(null, "苏宁环球", null, null, null, null, null, null, null, null, null, null);
//	System.out.println(t.get(0).getName());
//	System.out.println(t.get(1).getId());
//	
	
	
		ImsiMapper im=(ImsiMapper)BeanGetter.getBean("imsiMapper");
		Imsi imsi=new Imsi();
		imsi.setName("good");
		imsi.setSubmittime(DateHelper.getCurrentYYYYMMDDHHMMSS());
		imsi.setTesttime(DateHelper.getCurrentYYYYMMDDHHMMSS());
		im.insert(imsi);
 
	
	//	System.out.println(((Page)fm.selectAll()).getPageSize());
		
//List<VItem> news = im.getLatestItems(new Integer(101), 0, 10);
//System.out.println(news.size());
//		OmOrderManager f=(OmOrderManager)BeanGetter.getBean("OmOrderManager");
//		OmOrder s=(OmOrder)f.findByOrderCode("030").get(0);
//	System.out.println(s.getOmSoSla());
//	 ConfDAO sss=(ConfDAO)BeanGetter.getBean("ConfDAO");
//	 System.out.println(sss.findAll().size())
//	;
		
	}

}
