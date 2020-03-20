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

    public static String getTemplatePathByComp(String templateType) {
        for (TemplatesTypeEnum templatesTypeEnum : TemplatesTypeEnum.values()) {
            if (templateType.equals(templatesTypeEnum.getTemplateType())) {
                return templatesTypeEnum.getTemplatePath();
            }
        }
        // 默认rainsunset
        return RAINSUNSET.getTemplatePath();
    }

    public static  List<String> getTemplateTypes() {
        List<String> templateTypes = new ArrayList<>();
        for (TemplatesTypeEnum templatesTypeEnum : TemplatesTypeEnum.values()) {
            templateTypes.add(templatesTypeEnum.getTemplateType());
        }
        // 默认rainsunset
        return templateTypes;
    }

}