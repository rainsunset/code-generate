package com.rainsunset.codegenerate.service.aop;

import com.rainsunset.codegenerate.common.bean.GlobalErrorInfoEnum;
import com.rainsunset.codegenerate.common.bean.GlobalErrorInfoException;
import com.rainsunset.codegenerate.common.bean.ResponseResult;
import com.rainsunset.codegenerate.common.bean.RestResultGenerator;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import javax.validation.ConstraintViolation;
import java.util.Set;

/**
 *
 * @version : 1.0
 * @description: 日志、异常统一处理
 * @author: ligangwei
 * @company rainsunset
 * @date: 2020 -02-15
 */
@Aspect
@Component
public class ServiceAspect {
    /**
     * Local validator factory bean
     */
    @Autowired
    private LocalValidatorFactoryBean localValidatorFactoryBean;
    /**
     * ERROR_MSG
     */
    private static final String ERROR_MSG = "call [{}] [{}] [{}ms] [{}] [{}] RESPONSE:Result{} Cause:{}";

    /**
     * Log
     */
    private final Logger log = LoggerFactory.getLogger(getClass());

    /**
     * Service access response result.
     *
     * @param joinPoint the join point
     * @return the response result
     * @author : ligangwei / 2020-02-15
     */
    @Around("execution(public * com.rainsunset.codegenerate.controller.CodegenerateController.*(..))")
    public ResponseResult serviceAccess(ProceedingJoinPoint joinPoint) {
        //计时
        long startTime = System.currentTimeMillis();

        ResponseResult response = null;
        String clazzName = joinPoint.getTarget().getClass().getSimpleName(); //获得类名
        //获得方法名
        String methodName = joinPoint.getSignature().getName();
        //获得参数列表
        Object[] args = joinPoint.getArgs();

        if (args != null) {
            //初始化日志ID
//            initMDC(args);
            Object argObject = args[0];
            log.info("call [{}] [{}] PARAMETER:[{}]", clazzName, methodName, argObject);
            try {
                //参数校验
                validate(argObject);
                //业务执行
                response = (ResponseResult) joinPoint.proceed();
                log.info("call [{}] [{}] [{}ms] [{}] [{}] RESPONSE:Result{}", clazzName, methodName,
                        getTime(startTime), "Success", response.getErrorcode(), response);
            } catch (GlobalErrorInfoException e) {
                response = RestResultGenerator.genResult(e.getErrorInfo());
                response.setMessage(e.getMessage());
                log.info(ERROR_MSG, clazzName, methodName, getTime(startTime), "Success",
                        response.getErrorcode(), response, e.getCause());
            } catch (Throwable throwable) {
                response = new ResponseResult();
                response = RestResultGenerator.genResult(GlobalErrorInfoEnum.SYSTEM_ERROR);
                response.setMessage(throwable.getMessage());
                log.error(ERROR_MSG, clazzName, methodName, getTime(startTime), "Failure",
                        response.getErrorcode(), response, throwable.getCause());
            }
        }
        return response;
    }

    /**
     * 参数校验
     *
     * @param <T>    the type parameter
     * @param object 校验对象
     * @param groups the groups
     * @throws GlobalErrorInfoException
     * @author : ligangwei / 2020-02-15
     */
    private <T> void validate(T object, Class<?>... groups) {
        Set<ConstraintViolation<T>> constraintViolations = localValidatorFactoryBean.validate(
                object, groups);
        if (constraintViolations != null && !constraintViolations.isEmpty()) {
            ConstraintViolation c = constraintViolations.iterator().next();
            throw new GlobalErrorInfoException(GlobalErrorInfoEnum.PARAM_ERROR);
        }
    }

    /**
     * 获取耗时
     *
     * @param startTime 开始时间
     * @return 耗时(单位毫秒) long
     * @author : ligangwei / 2020-02-15
     */
    private long getTime(long startTime) {
        return System.currentTimeMillis() - startTime;
    }

}