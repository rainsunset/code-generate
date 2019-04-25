package com.rainsunset.codegenerate.bean;

import com.rainsunset.codegenerate.common.enums.TemplatesTypeEnum;

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
	public static PackagePathBO getPackagePath(String templateType) {
		if (TemplatesTypeEnum.CMBI.getTemplateType().equals(templateType)) {
			PackagePathBO packagePathBO = new PackagePathBO("dal.model", "service.request",
					"service.response","dal.mapper", "mapper","service.api",
					"service.impl","manager", "controller","dal",
					"service","","", "", "");
			return packagePathBO;
		} else{
//			默认模板为 TemplatesTypeEnum.RAINSUNSET
			PackagePathBO packagePathBO = new PackagePathBO("model","","", "dal", "mapper",
					"service", "service.impl","", "controller",
					"dal", "service",
					"","", "", "");
			return packagePathBO;
		}
	}
}
