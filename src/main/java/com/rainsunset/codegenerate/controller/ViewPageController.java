package com.rainsunset.codegenerate.controller;

import com.rainsunset.codegenerate.common.enums.ProvideDbTypeEnum;
import com.rainsunset.codegenerate.common.enums.TemplatesTypeEnum;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @ClassName ViewPageController
 * @Description: html页面
 * @Author: 李刚伟
 * @Company rainsunset
 * @CreateDate: 2019/4/14 15:02
 */
@Controller
public class ViewPageController {

	@RequestMapping(value = "/",method = RequestMethod.GET)
	public String indexHtml(Model model) {
		model.addAttribute("templatesType", TemplatesTypeEnum.getTemplateTypes());
		model.addAttribute("provideDbType", ProvideDbTypeEnum.initProvideDbType());
		return "index";
	}

}
