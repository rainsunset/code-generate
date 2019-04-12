package com.rainsunset.codegenerate.service.cmbi;

import freemarker.log.Logger;
import freemarker.template.*;

import java.io.*;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 *
 * @author whaosoft 网页静态化工具类 1.生成html页面 2.发送html邮件 3.静态化
 */
public class StaticizePage {

	private Configuration cfg;
	private Template template;
	private Logger logger = Logger.getLogger("Logger.LIBRARY_LOG4J");

	private void creatConfiguration() {
		// 初始化FreeMarker配置
		// 创建一个Configuration实例
		cfg = new Configuration();

	}

	private void setTemplateLoadingOfFile(File file) {
		// 加载freemarker模板文件
		try {

			if (!file.exists()) {
				file.mkdirs();
			}

			cfg.setDirectoryForTemplateLoading(file);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void setTemplateLoadingOfClass(Class<?> clazz, String templatePath) { // 要求
		// 模板在src某个文件夹下
		cfg.setClassForTemplateLoading(clazz, templatePath);
	}

	private void creatTemplate(Locale local, String encoding,
			String templateName) {
		cfg.setEncoding(local, encoding);
		// 设置对象包装器
		cfg.setObjectWrapper(new DefaultObjectWrapper());

		// 设计异常处理器
		cfg.setTemplateExceptionHandler(TemplateExceptionHandler.IGNORE_HANDLER);

		try {
			// 获取指定模板文件
			template = cfg.getTemplate(templateName);
			template.setLocale(local);
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("加载Template模版异常");
		}

	}

	public void buildHtmlPage(File file, Locale local, String encoding,
			String templateName, Writer out, Map<?,?> data) {
		try {
			creatConfiguration();
			setTemplateLoadingOfFile(file);
			creatTemplate(local, encoding, templateName);
			// 开始生成
			template.process(data, out);

			logger.info("Successful");

		} catch (TemplateException e) {
			e.printStackTrace();
			logger.error("处理Template模版异常");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				out.flush();
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void buildHtmlPage(Class<?> clazz, String templatePath, Locale local,
			String encoding, String templateName, Writer out, Map<?,?> data) {
		try {
			creatConfiguration();
			setTemplateLoadingOfClass(clazz, templatePath);
			creatTemplate(local, encoding, templateName);
			// 开始生成
			template.process(data, out);

			logger.info("Successful");

		} catch (TemplateException e) {
			e.printStackTrace();
			logger.error("处理Template模版异常");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				out.flush();
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}



	public String sendHtmlMail(Class<?> clazz, String templatePath, Locale local,
			String encoding, String templateName, Map<?,?> data) {
		try {
			creatConfiguration();
			setTemplateLoadingOfClass(clazz, templatePath);
			creatTemplate(local, encoding, templateName);
			StringWriter writer = new StringWriter();

			// 开始生成
			template.process(data, writer);

			logger.info("Successful");

			// System.out.println(writer.toString());

			return writer.toString();

		} catch (TemplateException e) {
			e.printStackTrace();
			logger.error("处理Template模版异常");
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public String sendHtmlMail(File file, Locale local, String encoding,
			String templateName, Map<?,?> data) {
		try {
			creatConfiguration();
			setTemplateLoadingOfFile(file);
			creatTemplate(local, encoding, templateName);
			StringWriter writer = new StringWriter();

			// 开始生成
			template.process(data, writer);

			logger.info("Successful");

			// System.out.println(writer.toString());

			return writer.toString();

		} catch (TemplateException e) {
			e.printStackTrace();
			logger.error("处理Template模版异常");
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public void staticizePage(File file,Locale local, String encoding,
			String templateName, PrintWriter out, Map<?,?> data) {
		try {
			creatConfiguration();
			setTemplateLoadingOfFile(file);
			creatTemplate(local, encoding, templateName);
			// 开始生成
			template.process(data, out);

			logger.info("Successful");

		} catch (TemplateException e) {
			e.printStackTrace();
			logger.error("处理Template模版异常");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void staticizePage(Class<?> clazz,
			String templatePath, Locale local, String encoding,
			String templateName, PrintWriter out, Map<?,?> data) {
		try {
			creatConfiguration();
			setTemplateLoadingOfClass(clazz, templatePath);
			creatTemplate(local, encoding, templateName);
			// 开始生成
			template.process(data, out);

			logger.info("Successful");

		} catch (TemplateException e) {
			e.printStackTrace();
			logger.error("处理Template模版异常");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
