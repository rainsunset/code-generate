package com.rainsunset.codegenerate.common.enums;

import java.io.File;

public enum TemplatesTypeEnum {

    RAINSUNSET("rainsunset",File.separator + "templates"+ File.separator+"rainsunset"),
    CMBI("cmbi",File.separator + "templates"+ File.separator+"cmbi")
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
}