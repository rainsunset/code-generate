package com.rainsunset.codegenerate.bean;

import com.rainsunset.codegenerate.common.constants.Constants;

/**
 * @author whaosoft
 * @Description: 代码各层包文件路径
 * @Author: ligangwei
 * @Company rainsunset
 * @CreateDate: 2019.04.11
 * @Version : 1.0
 */
public class FilePathBO{

    // main
    private String filePathModel;

    private String filePathDao;

    private String filePathMapper;

    private String filePathService;

    private String filePathServiceImpl;

    private String filePathManager;

    private String filePathController;

    // test
    private String filePathTestDao;

    private String filePathTestService;

    // page
    private String filePathPageControler;

    private String filePathPageService;

    private String filePathPageTemplate;

    private String filePathPageView;

    public String getFilePathDao() {
        return filePathDao + Constants.SLASH;
    }

    public void setFilePathDao(String filePathDao) {
        this.filePathDao = filePathDao;
    }

    public String getFilePathService() {
        return filePathService + Constants.SLASH;
    }

    public void setFilePathService(String filePathService) {
        this.filePathService = filePathService;
    }

    public String getFilePathServiceImpl() {
        return filePathServiceImpl + Constants.SLASH;
    }

    public void setFilePathServiceImpl(String filePathServiceImpl) {
        this.filePathServiceImpl = filePathServiceImpl;
    }

    public String getFilePathMapper() {
        return filePathMapper + Constants.SLASH;
    }

    public void setFilePathMapper(String filePathMapper) {
        this.filePathMapper = filePathMapper;
    }

    public String getFilePathTestDao() {
        return filePathTestDao + Constants.SLASH;
    }

    public void setFilePathTestDao(String filePathTestDao) {
        this.filePathTestDao = filePathTestDao;
    }

    public String getFilePathTestService() {
        return filePathTestService + Constants.SLASH;
    }

    public void setFilePathTestService(String filePathTestService) {
        this.filePathTestService = filePathTestService;
    }

    public String getFilePathPageControler() {
        return filePathPageControler + Constants.SLASH;
    }

    public void setFilePathPageControler(String filePathPageControler) {
        this.filePathPageControler = filePathPageControler;
    }

    public String getFilePathPageService() {
        return filePathPageService + Constants.SLASH;
    }

    public void setFilePathPageService(String filePathPageService) {
        this.filePathPageService = filePathPageService;
    }

    public String getFilePathPageTemplate() {
        return filePathPageTemplate + Constants.SLASH;
    }

    public void setFilePathPageTemplate(String filePathPageTemplate) {
        this.filePathPageTemplate = filePathPageTemplate;
    }

    public String getFilePathPageView() {
        return filePathPageView + Constants.SLASH;
    }

    public void setFilePathPageView(String filePathPageView) {
        this.filePathPageView = filePathPageView;
    }

    public String getFilePathController() {
        return filePathController + Constants.SLASH;
    }

    public void setFilePathController(String filePathController) {
        this.filePathController = filePathController;
    }

    public String getFilePathModel() {
        return filePathModel + Constants.SLASH;
    }

    public void setFilePathModel(String filePathModel) {
        this.filePathModel = filePathModel;
    }

    public String getFilePathManager() {
        return filePathManager;
    }

    public void setFilePathManager(String filePathManager) {
        this.filePathManager = filePathManager;
    }

    @Override
    public String toString() {
        return "FilePathBO{" +
                "filePathModel='" + filePathModel + '\'' +
                ", filePathDao='" + filePathDao + '\'' +
                ", filePathMapper='" + filePathMapper + '\'' +
                ", filePathService='" + filePathService + '\'' +
                ", filePathServiceImpl='" + filePathServiceImpl + '\'' +
                ", filePathManager='" + filePathManager + '\'' +
                ", filePathController='" + filePathController + '\'' +
                ", filePathTestDao='" + filePathTestDao + '\'' +
                ", filePathTestService='" + filePathTestService + '\'' +
                ", filePathPageControler='" + filePathPageControler + '\'' +
                ", filePathPageService='" + filePathPageService + '\'' +
                ", filePathPageTemplate='" + filePathPageTemplate + '\'' +
                ", filePathPageView='" + filePathPageView + '\'' +
                '}';
    }
}
