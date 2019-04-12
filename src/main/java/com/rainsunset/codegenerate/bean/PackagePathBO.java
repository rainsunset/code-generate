package com.rainsunset.codegenerate.bean;

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

	private String packPage;

	public PackagePathBO(String packModel, String packDao, String packMapping,
	                     String packService, String packServiceImpl, String packManager,
	                     String packController, String packTestDao, String packTestService,
	                     String packPageControler, String packPageService, String packPageTemplate,
	                     String packPage) {
		this.packModel = packModel;
		this.packDao = packDao;
		this.packMapper = packMapping;
		this.packService = packService;
		this.packServiceImpl = packServiceImpl;
		this.packManager = packManager;
		this.packController = packController;
		this.packTestDao = packTestDao;
		this.packTestService = packTestService;
		this.packPageControler = packPageControler;
		this.packPageService = packPageService;
		this.packPageTemplate = packPageTemplate;
		this.packPage = packPage;
	}

	public String getPackModel() {
		return packModel;
	}

	public String getPackModelRelFilePath() {
		return packModel.replace(".", File.separator);
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

	public void setPackDao(String packDao) {
		this.packDao = packDao;
	}

	public String getPackMapper() {
		return packMapper;
	}

	public String getPackMapperRelFilePath() {
		return packMapper.replace(".", File.separator);
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

	public void setPackService(String packService) {
		this.packService = packService;
	}

	public String getPackServiceImpl() {
		return packServiceImpl;
	}

	public String getPackServiceImplRelFilePath() {
		return packServiceImpl.replace(".", File.separator);
	}

	public void setPackServiceImpl(String packServiceImpl) {
		this.packServiceImpl = packServiceImpl;
	}

	public String getPackController() {
		return packController;
	}

	public String getPackControllerRelFilePath() {
		return packController.replace(".", File.separator);
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

	public void setPackTestDao(String packTestDao) {
		this.packTestDao = packTestDao;
	}

	public String getPackTestService() {
		return packTestService;
	}

	public String getPackTestServiceRelFilePath() {
		return packTestService.replace(".", File.separator);
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

	public void setPackPageControler(String packPageControler) {
		this.packPageControler = packPageControler;
	}

	public String getPackPageService() {
		return packPageService;
	}

	public String getPackPageServiceRelFilePath() {
		return packPageService.replace(".", File.separator);
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

	public void setPackPageTemplate(String packPageTemplate) {
		this.packPageTemplate = packPageTemplate;
	}

	public String getPackPage() {
		return packPage;
	}

	public String getPackPageRelFilePath() {
		return packPage.replace(".", File.separator);
	}

	public void setPackPage(String packPage) {
		this.packPage = packPage;
	}

	public String getPackManager() {
		return packManager;
	}

	public String getPackManagerRelFilePath() {
		return packManager.replace(".", File.separator);
	}

	public void setPackManager(String packManager) {
		this.packManager = packManager;
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
				", packPage='" + packPage + '\'' +
				'}';
	}
}
