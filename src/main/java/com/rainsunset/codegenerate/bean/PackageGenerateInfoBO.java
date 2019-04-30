package com.rainsunset.codegenerate.bean;

import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName PackageGenerateInfoBO
 * @Description: 每层包文件配置
 * @Author: 李刚伟
 * @Company rainsunset
 * @CreateDate: 2019/4/11 11:01
 */
public class PackageGenerateInfoBO {

	// main
	private List<FileGererateInfoBO> packModel;

	private List<FileGererateInfoBO> packDao;

	private List<FileGererateInfoBO> packMapper;

	private List<FileGererateInfoBO> packService;

	private List<FileGererateInfoBO> packController;

	// test
	private List<FileGererateInfoBO> packTest;

	// page
	private List<FileGererateInfoBO> packPage;

	public void addpackModel(String packagePath, String fileSuffix, String templatesName) {
		if (CollectionUtils.isEmpty(this.packModel)) {
			this.packModel = new ArrayList<>();
		}
		FileGererateInfoBO fileGererateInfoBO = new FileGererateInfoBO(packagePath, fileSuffix, templatesName);
		packModel.add(fileGererateInfoBO);
	}

	public void addpackDao(String packagePath, String fileSuffix, String templatesName) {
		if (CollectionUtils.isEmpty(this.packDao)) {
			this.packDao = new ArrayList<>();
		}
		FileGererateInfoBO fileGererateInfoBO = new FileGererateInfoBO(packagePath, fileSuffix, templatesName);
		packDao.add(fileGererateInfoBO);
	}

	public void addpackMapper(String packagePath, String fileSuffix, String templatesName) {
		if (CollectionUtils.isEmpty(this.packMapper)) {
			this.packMapper = new ArrayList<>();
		}
		FileGererateInfoBO fileGererateInfoBO = new FileGererateInfoBO(packagePath, fileSuffix, templatesName);
		packMapper.add(fileGererateInfoBO);
	}

	public void addpackService(String packagePath, String fileSuffix, String templatesName) {
		if (CollectionUtils.isEmpty(this.packService)) {
			this.packService = new ArrayList<>();
		}
		FileGererateInfoBO fileGererateInfoBO = new FileGererateInfoBO(packagePath, fileSuffix, templatesName);
		packService.add(fileGererateInfoBO);
	}

	public void addpackController(String packagePath, String fileSuffix, String templatesName) {
		if (CollectionUtils.isEmpty(this.packController)) {
			this.packController = new ArrayList<>();
		}
		FileGererateInfoBO fileGererateInfoBO = new FileGererateInfoBO(packagePath, fileSuffix, templatesName);
		packController.add(fileGererateInfoBO);
	}

	public void addpackTest(String packagePath, String fileSuffix, String templatesName) {
		if (CollectionUtils.isEmpty(this.packTest)) {
			this.packTest = new ArrayList<>();
		}
		FileGererateInfoBO fileGererateInfoBO = new FileGererateInfoBO(packagePath, fileSuffix, templatesName);
		packTest.add(fileGererateInfoBO);
	}

	public void addpackPage(String packagePath, String fileSuffix, String templatesName) {
		if (CollectionUtils.isEmpty(this.packPage)) {
			this.packPage = new ArrayList<>();
		}
		FileGererateInfoBO fileGererateInfoBO = new FileGererateInfoBO(packagePath, fileSuffix, templatesName);
		packPage.add(fileGererateInfoBO);
	}

	public List<FileGererateInfoBO> getPackModel() {
		return packModel;
	}

	public List<FileGererateInfoBO> getPackDao() {
		return packDao;
	}

	public List<FileGererateInfoBO> getPackService() {
		return packService;
	}

	public List<FileGererateInfoBO> getPackController() {
		return packController;
	}

	public List<FileGererateInfoBO> getPackTest() {
		return packTest;
	}

	public List<FileGererateInfoBO> getPackPage() {
		return packPage;
	}

	public List<FileGererateInfoBO> getPackMapper() {
		return packMapper;
	}
}
