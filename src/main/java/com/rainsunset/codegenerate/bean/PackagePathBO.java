package com.rainsunset.codegenerate.bean;

import com.rainsunset.codegenerate.common.constants.Constants;
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
	 * 约定 - 按照代码的package层约定各层包文件(绝对)路径
	 * 如service.impl为service实现层包路径，则生成的文件夹路径为filePathModule/service/impl
	 * 生成文件结构如下
	 * |--moduleName
	 * |----model
	 * |----dal
	 * |----mapper
	 * |----service
	 * |----serviceimpl
	 * |----manager
	 * |----controller
	 * |----test
	 * |------testservice
	 * |------testdao
	 * |----page
	 * @param filePathModule 目标文件夹绝对路径
	 * @return the string
	 * @author : ligangwei / 2019-04-12
	 */
	public String getPackModelDirPath(String filePathModule) {
		String dirPathModel= StringUtils.isEmpty(packModel) ? null :
				StringUtil.conlitionStr(filePathModule, File.separator, packModel.replace(".", File.separator));
		return dirPathModel;
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

	public String getPackDaoDirPath(String filePathModule) {
		String dirPathDal = StringUtils.isEmpty(packDao) ? null :
				StringUtil.conlitionStr(filePathModule, File.separator, packDao.replace(".", File.separator));
		return dirPathDal;
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

	public String getPackMapperDirPath(String filePathModule) {
		String dirPathMapper= StringUtils.isEmpty(packMapper) ? null :
				StringUtil.conlitionStr(filePathModule, File.separator, packMapper.replace(".", File.separator));
		return dirPathMapper;
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

	public String getPackServiceDirPath(String filePathModule) {
		String dirPathService = StringUtils.isEmpty(packService) ? null :
				StringUtil.conlitionStr(filePathModule, File.separator, packService.replace(".", File.separator));
		return dirPathService;
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

	public String getPackServiceImplDirPath(String filePathModule) {
		String dirPathServiceImpl = StringUtils.isEmpty(packServiceImpl) ? null :
				StringUtil.conlitionStr(filePathModule, File.separator, packServiceImpl.replace(".", File.separator));
		return dirPathServiceImpl;
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

	public String getPackManagerDirPath(String filePathModule) {
		String dirPathManager= StringUtils.isEmpty(packManager) ? null :
				StringUtil.conlitionStr(filePathModule, File.separator, packManager.replace(".", File.separator));
		return dirPathManager;
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

	public String getPackControllerDirPath(String filePathModule) {
		String dirPathController = StringUtils.isEmpty(packController) ? null :
				StringUtil.conlitionStr(filePathModule, File.separator, packController.replace(".", File.separator));
		return dirPathController;
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

	public String getPackTestDaoDirPath(String filePathModule) {
		String dirPathTestDao= StringUtils.isEmpty(packTestDao) ? null :
				StringUtil.conlitionStr(filePathModule, File.separator, Constants.PACKAGE_TEST,
						File.separator, packTestDao.replace(".", File.separator));
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

	public String getPackTestServiceDirPath(String filePathModule) {
		String dirPathTestService= StringUtils.isEmpty(packTestService) ? null :
				StringUtil.conlitionStr(filePathModule, File.separator, Constants.PACKAGE_TEST,
						File.separator,packTestService.replace(".", File.separator));
		return dirPathTestService;
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

	public String getPackPageControlerDirPath(String filePathModule) {
		String dirPathPageControler= StringUtils.isEmpty(packPageControler) ? null :
				StringUtil.conlitionStr(filePathModule, File.separator, Constants.PACKAGE_PAGE,
						File.separator,packPageControler.replace(".", File.separator));
		return dirPathPageControler;
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

	public String getPackPageServiceDirPath(String filePathModule) {
		String dirPathPageService= StringUtils.isEmpty(packPageService) ? null :
				StringUtil.conlitionStr(filePathModule, File.separator, Constants.PACKAGE_PAGE,
						File.separator,packPageService.replace(".", File.separator));
		return dirPathPageService;
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

	public String getPackPageTemplateDirPath(String filePathModule) {
		String dirPathPageTemplate = StringUtils.isEmpty(packPageTemplate) ? null :
				StringUtil.conlitionStr(filePathModule, File.separator, Constants.PACKAGE_PAGE,
						File.separator,packPageTemplate.replace(".", File.separator));
		return dirPathPageTemplate;
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

	public String getPackPageViewDirPath(String filePathModule) {
		String dirPathPageView= StringUtils.isEmpty(packPageView) ? null :
				StringUtil.conlitionStr(filePathModule, File.separator, Constants.PACKAGE_PAGE,
						File.separator,packPageView.replace(".", File.separator));
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
