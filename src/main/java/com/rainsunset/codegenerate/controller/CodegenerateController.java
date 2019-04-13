package com.rainsunset.codegenerate.controller;

import com.rainsunset.codegenerate.bean.DataBaseConfigReqDTO;
import com.rainsunset.codegenerate.bean.GenerateConfigReqDTO;
import com.rainsunset.codegenerate.common.bean.ResponseResult;
import com.rainsunset.codegenerate.common.bean.RestResultGenerator;
import com.rainsunset.codegenerate.service.CodegenerateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * @Description: 代码生成器暴露接口
 * @Author: ligangwei
 * @Company rainsunset
 * @CreateDate: 2019.04.10
 * @Version : 1.0
 */
@CrossOrigin
@RestController
@RequestMapping("/generate")
public class CodegenerateController {

	private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
	private CodegenerateService codegenerateService;

	/**
	 * 链接测试
	 *
	 * @param dataBaseConfigReqDTO the data base config dto
	 * @return string
	 * @author : ligangwei / 2019-04-10
	 */
	@RequestMapping(value = "/test",method = RequestMethod.POST)
	public ResponseResult<Boolean> doTestConnection(@RequestBody DataBaseConfigReqDTO dataBaseConfigReqDTO) {
		boolean res = codegenerateService.doTestConnection(dataBaseConfigReqDTO);
		return RestResultGenerator.genResult(res);
	}

	/**
	 * 查询数据库表
	 *
	 * @param dataBaseConfigReqDTO the data base config dto
	 * @return the list
	 * @author : ligangwei / 2019-04-10
	 */
	@RequestMapping(value = "/getTabs",method = RequestMethod.POST)
	public ResponseResult<List<String>> doQueryTableNames(@RequestBody DataBaseConfigReqDTO dataBaseConfigReqDTO) {
		List<String> tableNameList =  codegenerateService.doQueryTableNames(dataBaseConfigReqDTO);
		return RestResultGenerator.genResult(tableNameList);
	}

	/**
	 * 代码生成
	 * @param generatePathConfigReqDTO
	 * @throws Exception
	 */
	@RequestMapping(value = "/execute",method = RequestMethod.POST)
	public ResponseResult<Boolean> generateCode(@RequestBody GenerateConfigReqDTO generatePathConfigReqDTO){
		boolean res = codegenerateService.generateCode(generatePathConfigReqDTO);
		return RestResultGenerator.genResult(res);
	}
}
