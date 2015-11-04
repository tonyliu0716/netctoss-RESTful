package org.tarena.netctoss.util;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.tarena.netctoss.entity.Privilege;

//想要执行XML解析，必须导入JAR包dom4j-1.6.1.jar
public class PrivilegeReader {
	private static List<Privilege> modules = new ArrayList<Privilege>();
	
	static{
		//将目标位置的XML文件转换成为输入流
		InputStream xml = PrivilegeReader.class.getClassLoader().
				getResourceAsStream("org/tarena/netctoss/config/privileges.xml");
		modules = toModuleList(xml);
	}
	
	//返回XML中所有权限数据
	public static List<Privilege> getModules(){
		return modules;
	}
	//根据权限id返回权限名
	public static String getModuleNameById(String id){
		List<Privilege> modules = getModules();
		for(Privilege module : modules){
			if(module.getId().equals(id)){
				return module.getName();
			}
		}
		return "";
	}
	//解析privileges.xml文件
	@SuppressWarnings("unchecked")
	protected static List<Privilege> toModuleList(InputStream xml){
		List<Privilege> modules = new ArrayList<Privilege>();
		try{
			SAXReader reader = new SAXReader();
			Document doc = reader.read(xml);
			Element root = doc.getRootElement();
			List<Element> moduleElements = root.elements("privilege");
			for(Element moduleElement : moduleElements){
				Privilege module = new Privilege();
				module.setId(moduleElement.attributeValue("id"));
				module.setName(moduleElement.elementText("name"));
				
				Element urlElement = moduleElement.element("urls");
				List<Element> urlElements = urlElement.elements();
				List<String> urls = new ArrayList<String>();
				
				for(Element element : urlElements){
					urls.add(element.getText());
				}
				module.setUrls(urls);
				modules.add(module);
			}
		}catch(DocumentException e){
			e.printStackTrace();
			throw new RuntimeException("", e);
		}
		return modules;
	}
	
	public static void main(String[] args){
		List<Privilege> list = PrivilegeReader.getModules();
		for(Privilege p : list){
			System.out.println(p.getId() + " " + p.getName());
		}
		System.out.println("=======");
		String name = PrivilegeReader.getModuleNameById("3");
		System.out.println(name);
	}
	
}


