package com.rainsunset.codegenerate.bean;

import com.rainsunset.codegenerate.common.enums.TemplatesTypeEnum;

import java.util.List;

/**
 * @ClassName PackagePathFactory
 * @Description: 代码分层包相对路径
 * @Author: 李刚伟
 * @Company rainsunset
 * @CreateDate: 2019/4/11 14:36
 */
public class PackagePathFactory {

	/**
	 * 不同模板在baseClassPath下的分层路径
	 * 依据文件性质不同在最外层分main test page三个包
	 * mapping的xml文件是基于main/resources的
	 * 包路径与模板配置的关系为 main|test|oage + path的大驼峰命名
	 *
	 * @param templateType the template type
	 * @return the package path bo
	 * @author : ligangwei / 2019-04-11
	 */
	public static PackageGenerateInfoBO getPackagePath(String templateType) {
		if (TemplatesTypeEnum.CMBI.getTemplateType().equals(templateType)) {
			PackageGenerateInfoBO packageGenerateInfoBO = new PackageGenerateInfoBO();
			packageGenerateInfoBO.addpackModel("dal.model", ".java", "mainDalModel.flv");
			packageGenerateInfoBO.addpackDao("dal.mapper", "Mapper.java", "mainDalMapper.flv");
			packageGenerateInfoBO.addpackMapper("mapper", "Mapper.xml", "mainMapper.flv");
			packageGenerateInfoBO.addpackService("service.api", "Service.java", "mainServiceApi.flv");
			packageGenerateInfoBO.addpackService("service.impl", "ServiceImpl.java", "mainServiceImpl.flv");
			packageGenerateInfoBO.addpackService("service.request", "BatchDelReqDTO.java", "mainServiceBatchDelReqDTO.flv");
			packageGenerateInfoBO.addpackService("service.request", "DetailReqDTO.java", "mainServiceDetailReqDTO.flv");
			packageGenerateInfoBO.addpackService("service.request", "ListReqDTO.java", "mainServiceListReqDTO.flv");
			packageGenerateInfoBO.addpackService("service.request", "ReqDTO.java", "mainServiceReqDTO.flv");
			packageGenerateInfoBO.addpackService("service.response", "ResDTO.java", "mainServiceResDTO.flv");
			packageGenerateInfoBO.addpackService("manager", "Manager.java", "mainManager.flv");
			packageGenerateInfoBO.addpackController("controller", "Controller.java", "mainController.flv");
			packageGenerateInfoBO.addpackTest("test.dal", "DaoTest.java", "testDal.flv");
			packageGenerateInfoBO.addpackTest("test.service", "ServiceTest.java", "testService.flv");
			return packageGenerateInfoBO;
		} else{
//			默认模板为 TemplatesTypeEnum.RAINSUNSET
			PackageGenerateInfoBO packageGenerateInfoBO = new PackageGenerateInfoBO();
			packageGenerateInfoBO.addpackModel("model", ".java", "mainModel.flv");
			packageGenerateInfoBO.addpackDao("dal", "Mapper.java", "mainDal.flv");
			packageGenerateInfoBO.addpackMapper("mapper", "Mapper.xml", "mainMapper.flv");
			packageGenerateInfoBO.addpackService("service", "Service.java", "mainService.flv");
			packageGenerateInfoBO.addpackService("service.impl", "ServiceImpl.java", "mainServiceImpl.flv");
			packageGenerateInfoBO.addpackController("controller", "Controller.java", "mainController.flv");
			packageGenerateInfoBO.addpackTest("test.dal", "DaoTest.java", "testDal.flv");
			packageGenerateInfoBO.addpackTest("test.service", "ServiceTest.java", "testService.flv");
			return packageGenerateInfoBO;
		}
	}

}
