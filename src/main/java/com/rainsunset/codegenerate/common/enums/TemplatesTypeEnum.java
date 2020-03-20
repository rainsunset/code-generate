package com.rainsunset.codegenerate.common.enums;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public enum TemplatesTypeEnum {

    RAINSUNSET("rainsunset",File.separator + "templates"+ File.separator+"rainsunset"),
    /** cmbi 1.5 框架 */
    CMBI1("cmbi1",File.separator + "templates"+ File.separator+"cmbi1"),
    /** cmbi 2.* 框架 */
    CMBI2("cmbi2",File.separator + "templates"+ File.separator+"cmbi2"),
    ;
    private String templateType;
    private String templatePath;

    TemplatesTypeEnum(String templateType, String templatePath){
        this.templateType = templateType;
        this.templatePath = templatePath;
    }

    public String getTemplateType() {
        return templateType;
    }

    public String getTemplatePath() {
        return templatePath;
    }

    /**
     * 返回模板根路径
     *
     * @param templateType the template type
     * @return the string
     * @author : ligangwei / 2020年3月20日 下午5:36:41
     */
    public static String getTemplatePathByComp(String templateType) {
        for (TemplatesTypeEnum templatesTypeEnum : TemplatesTypeEnum.values()) {
            if (templateType.equals(templatesTypeEnum.getTemplateType())) {
                return templatesTypeEnum.getTemplatePath();
            }
        }
        // 默认rainsunset
        return RAINSUNSET.getTemplatePath();
    }

    /**
     * 返回所有模板
     *
     * @return the list
     * @author : ligangwei / 2020年3月20日 下午5:36:30
     */
    public static List<String> getTemplateTypes() {
        List<String> templateTypes = new ArrayList<>();
        for (TemplatesTypeEnum templatesTypeEnum : TemplatesTypeEnum.values()) {
            templateTypes.add(templatesTypeEnum.getTemplateType());
        }
        return templateTypes;
    }

}