package com.xjj.wefactory.sys.code;

import java.io.File;
import java.io.IOException;

import clojure.main;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.Version;

public class GCConfig {
	private static Configuration cfg = null;
	private static GCConfig instance = null;
	
	public static String DIR_BASE = "d:/xjj_code/";//生成代码的保存地址
	private String DIR_SRC = DIR_BASE + "java/";//生成的java源码的子目录
	private String DIR_WEB =  DIR_BASE + "view/";//生成的页面文件的子目录
	private String DIR_MAPPER =  DIR_BASE + "mapper/";//生成的页面文件的子目录
	private String INFO_AUTHOR = "zhanghejie";//生成代码的作者姓名
	private String INFO_COMPANY = "xjj";//生成代码的公司名称
	private String INFO_VERSION = "1.0";//代码的版本
	private String INFO_URLPATH = "/";//工程路径
	
	public static String DIR_TEMPLATE_VIEW = "ace";//页面模板的名字，默认defaults
	private GCConfig(){}
	
	public static GCConfig getInstance(){
		if(null== instance)
		{
			instance = new GCConfig();
		}
		
		instance.DIR_SRC=DIR_BASE + "/java/";
		instance.DIR_WEB=DIR_BASE + "/view/";
		instance.DIR_MAPPER=DIR_BASE + "/mapper/";
		return instance;
	}
	public Configuration getConfiguration() {
		
		if (null == cfg) {
			cfg = new Configuration();
			cfg.setClassForTemplateLoading(this.getClass(),
					"template");
			cfg.setDefaultEncoding("UTF-8");
		}
		return cfg;
	}
	
	
	public static void main(String[] args) throws IOException {
		Configuration cfgt = null;
		cfgt = new Configuration(Configuration.VERSION_2_3_27);
		//cfgt.setDirectoryForTemplateLoading(new File("D:/git/gitee/jeexjj_wefactory_init/wefactory-admin/src/main/java/com.xjj.wefactory.sys/code/template")); //如果是maven项目可以使用这种方式
		cfgt.setClassForTemplateLoading(GCConfig.class,"template");
		cfgt.setDefaultEncoding("UTF-8");
		
		System.out.println("cfgg==="+cfgt);
		
		try {
			//Template t1 = cfgg.getTemplate("template/web/Controller.java.ftl");
			Template t2 = cfgt.getTemplate("web/Controller.java.ftl");
			//Template t3 = cfgg.getTemplate("Controller.java.ftl");
			
			//System.out.println(t1);
			System.out.println(t2);
			//System.out.println(t3);
			
			System.out.println(GCConfig.class.getResource("/"));
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
		System.out.println("t_aa_bb".substring("t_".length()));
	}
	
	public String getBaseDir(){
		return DIR_BASE;
	}
	
	public String getSrcDir(){
		return DIR_SRC;
	}
	
	public String getViewTemplateDir(){
		if(DIR_TEMPLATE_VIEW == null || DIR_TEMPLATE_VIEW.equals("")){
			return "defaults/";
		}
		if(DIR_TEMPLATE_VIEW.endsWith("/")){
			return DIR_TEMPLATE_VIEW;
		}
		return DIR_TEMPLATE_VIEW+"/";
	}
	
	public String getWebDir(){
		return DIR_WEB;
	}
	public String getMapperDir(){
		return DIR_MAPPER;
	}
	
	public String getAuthor(){
		return INFO_AUTHOR;
	}
	
	public String getCompany(){
		return INFO_COMPANY;
	}
	
	public String getVersion(){
		return INFO_VERSION;
	}
	
	public String getUrlPath(){
		return INFO_URLPATH;
	}
}
