package com.rainsunset.codegenerate.bean;

import com.rainsunset.codegenerate.common.util.StringUtil;
import org.springframework.util.StringUtils;

import java.io.File;

/**
 * @ClassName PackagePathBO
 * @Description: 每层包文件配置
 * @Author: 李刚伟
 * @Company CMBI
 * @CreateDate: 2019/4/11 11:01
 */
public class PackagePathBO {

	// main
	private String packModel;

	private String packDao;

	private String packMapper;

	private String packService;

	private String packServiceImpl;

	/**
	 * dubbo类项目
	 */
	private String packManager;

	private String packController;

	// test
	private String packTestDao;

	private String packTestService;

	// page
	private String packPageControler;

	private String packPageService;

	private String packPageTemplate;

	private String packPageView;

	public PackagePathBO(String packModel, String packDao, String packMapper,
	                     String packService, String packServiceImpl, String packManager,
	                     String packController, String packTestDao, String packTestService,
	                     String packPageControler, String packPageService, String packPageTemplate,
	                     String packPageView) {
		this.packModel = packModel;
		this.packDao = packDao;
		this.packMapper = packMapper;
		this.packService = packService;
		this.packServiceImpl = packServiceImpl;
		this.packManager = packManager;
		this.packController = packController;
		this.packTestDao = packTestDao;
		this.packTestService = packTestService;
		this.packPageControler = packPageControler;
		this.packPageService = packPageService;
		this.packPageTemplate = packPageTemplate;
		this.packPageView = packPageView;
	}

	public String getPackModel() {
		return packModel;
	}

	/**
	 * 约定 - 按照代码的package层生成代码层文件夹
	 * 如service.impl为service实现层包路径，则生成的文件夹路径为service/impl
	 *
	 * @return the string
	 * @author : ligangwei / 2019-04-12
	 */
	public String getPackModelRelFilePath() {
		return packModel.replace(".", File.separator);
	}

	/**
	 * 约定 - 按照代码的package层命名对应的模板文件
	 * 如当前层package为空字符串则代表无当前层模板文件 亦返回空串
	 * 如service.impl为service实现层包路径，则实现层的模板文件名称为mainServiceImpl.flv
	 * main 为代码逻辑划分 目前分main test page 三个，
	 * main包含:model dal mapper service serviceImpl manager controller
	 * test包含:testdal testservice
	 * page包含:pageControler pageService pageTemplate pageView
	 *
	 * 其中mapper的模板比较特殊，分为Id为自增(Increment)的模板和非自增的模板，自增的模板为Increment.flv结尾
	 * @return the string
	 * @author : ligangwei / 2019-04-12
	 */
	public String getPackModelTemplateFileName() {
		return StringUtils.isEmpty(packModel) ? "" : StringUtil.conlitionStr("main" , StringUtil.formater2bigCamel(packModel),".flv");
	}

	public void setPackModel(String packModel) {
		this.packModel = packModel;
	}

	public String getPackDao() {
		return packDao;
	}

	public String getPackDaoRelFilePath() {
		return packDao.replace(".", File.separator);
	}

	public String getPackDaoTemplateFileName() {
		return StringUtils.isEmpty(packDao) ? "" : StringUtil.conlitionStr("main",StringUtil.formater2bigCamel(packDao),".flv");
	}

	public void setPackDao(String packDao) {
		this.packDao = packDao;
	}

	public String getPackMapper() {
		return packMapper;
	}

	public String getPackMapperRelFilePath() {
		return packMapper.replace(".", File.separator);
	}

	public String getPackMapperTemplateFileName() {
		return StringUtils.isEmpty(packMapper) ? "" : StringUtil.conlitionStr("main" , StringUtil.formater2bigCamel(packMapper),".flv");
	}

	public String getPackMapperIncrementTemplateFileName() {
		return StringUtils.isEmpty(packMapper) ? "" : StringUtil.conlitionStr("main" , StringUtil.formater2bigCamel(packMapper),"Increment.flv");
	}

	public void setPackMapper(String packMapper) {
		this.packMapper = packMapper;
	}

	public String getPackService() {
		return packService;
	}

	public String getPackServiceRelFilePath() {
		return packService.replace(".", File.separator);
	}

	public String getPackServiceTemplateFileName() {
		return StringUtils.isEmpty(packService) ? "" : StringUtil.conlitionStr("main" , StringUtil.formater2bigCamel(packService),".flv");
	}

	public void setPackService(String packService) {
		this.packService = packService;
	}

	public String getPackServiceImpl() {
		return packServiceImpl;
	}

	public String getPackServiceImplRelFilePath() {
		return packServiceImpl.replace(".", File.separator);
	}

	public String getPackServiceImplTemplateFileName() {
		return StringUtils.isEmpty(packServiceImpl) ? "" : StringUtil.conlitionStr("main" , StringUtil.formater2bigCamel(packServiceImpl),".flv");
	}

	public void setPackServiceImpl(String packServiceImpl) {
		this.packServiceImpl = packServiceImpl;
	}

	public String getPackManager() {
		return packManager;
	}

	public String getPackManagerRelFilePath() {
		return packManager.replace(".", File.separator);
	}

	public String getPackManagerTemplateFileName() {
		return StringUtils.isEmpty(packManager) ? "" : StringUtil.conlitionStr("main" , StringUtil.formater2bigCamel(packManager),".flv");
	}

	public void setPackManager(String packManager) {
		this.packManager = packManager;
	}

	public String getPackController() {
		return packController;
	}

	public String getPackControllerRelFilePath() {
		return packController.replace(".", File.separator);
	}

	public String getPackControllerTemplateFileName() {
		return StringUtils.isEmpty(packController) ? "" : StringUtil.conlitionStr("main" , StringUtil.formater2bigCamel(packController),".flv");
	}

	public void setPackController(String packController) {
		this.packController = packController;
	}

	public String getPackTestDao() {
		return packTestDao;
	}

	public String getPackTestDaoRelFilePath() {
		return packTestDao.replace(".", File.separator);
	}

	public String getPackTestDaoTemplateFileName() {
		return StringUtils.isEmpty(packTestDao) ? "" : StringUtil.conlitionStr("test" , StringUtil.formater2bigCamel(packTestDao),".flv");
	}

	public void setPackTestDao(String packTestDao) {
		this.packTestDao = packTestDao;
	}

	public String getPackTestService() {
		return packTestService;
	}

	public String getPackTestServiceRelFilePath() {
		return packTestService.replace(".", File.separator);
	}

	public String getPackTestServiceTemplateFileName() {
		return StringUtils.isEmpty(packTestService) ? "" : StringUtil.conlitionStr("test" , StringUtil.formater2bigCamel(packTestService),".flv");
	}

	public void setPackTestService(String packTestService) {
		this.packTestService = packTestService;
	}

	public String getPackPageControler() {
		return packPageControler;
	}

	public String getPackPageControlerRelFilePath() {
		return packPageControler.replace(".", File.separator);
	}

	public String getPackPageControlerTemplateFileName() {
		return StringUtils.isEmpty(packPageControler) ? "" : StringUtil.conlitionStr("page" , StringUtil.formater2bigCamel(packPageControler),".flv");
	}

	public void setPackPageControler(String packPageControler) {
		this.packPageControler = packPageControler;
	}

	public String getPackPageService() {
		return packPageService;
	}

	public String getPackPageServiceRelFilePath() {
		return packPageService.replace(".", File.separator);
	}

	public String getPackPageServiceTemplateFileName() {
		return StringUtils.isEmpty(packPageService) ? "" : StringUtil.conlitionStr("page" , StringUtil.formater2bigCamel(packPageService),".flv");
	}

	public void setPackPageService(String packPageService) {
		this.packPageService = packPageService;
	}

	public String getPackPageTemplate() {
		return packPageTemplate;
	}

	public String getPackPageTemplateRelFilePath() {
		return packPageTemplate.replace(".", File.separator);
	}

	public String getPackPageTemplateTemplateFileName() {
		return StringUtils.isEmpty(packPageTemplate) ? "" : StringUtil.conlitionStr("page" , StringUtil.formater2bigCamel(packPageTemplate),".flv");
	}

	public void setPackPageTemplate(String packPageTemplate) {
		this.packPageTemplate = packPageTemplate;
	}

	public String getPackPageView() {
		return packPageView;
	}

	public String getPackPageRelFilePath() {
		return packPageView.replace(".", File.separator);
	}

	public String getPackPageViewTemplateFileName() {
		return StringUtils.isEmpty(packPageView) ? "" : StringUtil.conlitionStr("page" , StringUtil.formater2bigCamel(packPageView),".flv");
	}

	public void setPackPageView(String packPageView) {
		this.packPageView = packPageView;
	}

	@Override
	public String toString() {
		return "PackagePathBO{" +
				"packModel='" + packModel + '\'' +
				", packDao='" + packDao + '\'' +
				", packMapper='" + packMapper + '\'' +
				", packService='" + packService + '\'' +
				", packServiceImpl='" + packServiceImpl + '\'' +
				", packManager='" + packManager + '\'' +
				", packController='" + packController + '\'' +
				", packTestDao='" + packTestDao + '\'' +
				", packTestService='" + packTestService + '\'' +
				", packPageControler='" + packPageControler + '\'' +
				", packPageService='" + packPageService + '\'' +
				", packPageTemplate='" + packPageTemplate + '\'' +
				", packPageView='" + packPageView + '\'' +
				'}';
	}
}
