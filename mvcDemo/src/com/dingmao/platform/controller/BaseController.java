package com.dingmao.platform.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.dingmao.platform.controller.editor.DateEditor;
import com.dingmao.platform.util.PropertiesUtil;


/**
 * 功能:基础控制器.
 *<p>
 * 修改历史:对程序的修改历史进行记录
 * </p>
 * 
 * @param <T>
 */
public class BaseController<T> {
	private Log log = LogFactory.getLog(this.getClass());
    private static String ENCODING = "UTF-8";
    private static String CONTENT_TYPE = "application/json;charset=UTF-8";
    private static String SUCCESS = "{\"success\":true}";
    private static String ERROR = "{\"success\":false}";
    
    /**
     * 处理特殊的数据绑定
     *
     * @param binder
     * @throws Exception
     */
    @InitBinder
    protected void initBinder(ServletRequestDataBinder binder) throws Exception {
        // 对于需要转换为Date类型的属性，使用DateEditor进行处理
        binder.registerCustomEditor(Date.class, new DateEditor());
    }
    
    /**
     * ajax请求返回成功信息 {"success":true}
     *
     * @param response
     */
    public void ajaxSuccess(HttpServletResponse response) {
        writeJSON(response, SUCCESS);
    }

    /**
     * ajax请求返回成功信息 {"success":true}
     *
     * @param response
     */
    public void ajaxError(HttpServletResponse response) {
        writeJSON(response, ERROR);
    }
    
    /**
     * 异步返回JSON字符串
     *
     * @param json     合法的JSON字符串
     * @param response
     */
    public void writeJSON(HttpServletResponse response, String json) {
        response.setCharacterEncoding(ENCODING);
        response.setContentType(CONTENT_TYPE);
        try {
            response.getOutputStream().write(json.getBytes(ENCODING));
        } catch (Exception e) {
            exceptionHandler(e);
        } finally {
            finallyHandler(response);
        }
    }
    /**
     * 异常处理
     * @param e
     */
    private void exceptionHandler(Exception e) {
        if (log.isErrorEnabled()) {
            log.error("异步返回json失败,错误信息", e);
        }
    }

    /**
     * 输出流关闭
     * @param response
     */
    private void finallyHandler(HttpServletResponse response) {
        try {
            response.getOutputStream().close();
        } catch (IOException e) {
            log.error("response输出流关闭异常", e);
        }
    }
    
    /**
     * ajax文件上传、支持多文件
     * 上传文件大小application.properties  upload.maxSize 设置
     *
     * @param request
     * @param response
     * @param prePath  路径前缀;用于区分各模块不同文件
     * @return
     */
    @RequestMapping(value = "/upload")
    public String upload(HttpServletRequest request, HttpServletResponse response, String prePath) {
        try {
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            /** 得到文件保存目录的真实路径 **/
            String dir = PropertiesUtil.getInstance().getPropertiesValue("file.upload.path");
            /** 根据真实路径创建目录 **/
            dir = dir.concat(File.separator).concat(prePath);
            File uploadFilePath = new File(dir);
            if (!uploadFilePath.exists()) {
                boolean crt = uploadFilePath.mkdirs();
                if (!crt) {
                    log.error("无法创建上传文件路径");
                    throw new FileNotFoundException(dir);
                }
            }
            /** 页面控件的文件流 **/
            Map<String, MultipartFile> multipartFiles = multipartRequest.getFileMap();
            for (Iterator<MultipartFile> iterator = multipartFiles.values().iterator(); iterator.hasNext(); ) {
                MultipartFile multipartFile = iterator.next();

                String origFileName = multipartFile.getOriginalFilename();
                if (StringUtils.isBlank(origFileName)) continue;
                /** 拼成完整的文件保存路径加文件 **/
                String fileName = dir.concat(File.separator).concat(origFileName);
                multipartFile.transferTo(new File(fileName));
            }

            // response.getWriter().print("<script>parent.callBack('success');</script>");
            ajaxSuccess(response);
        } catch (Exception e) {
            if (log.isErrorEnabled()) {
                log.error("文件上传处理异常", e);
            }
            ajaxError(response);
        }
        return null;

    }
    
    /**
     * ajax文件下载
     *
     * @param response
     * @param fileName  
     * @return
     */
    @RequestMapping(value = "/download")
    public void download(HttpServletResponse response, String fileName) {
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "attachment;fileName=" + new String(fileName.getBytes("UTF-8"), "ISO8859-1"));

            String dir = PropertiesUtil.getInstance().getPropertiesValue("file.download.path");
            /** 根据真实路径创建目录 **/
            File downloadFilePath = new File(dir);
            if (!downloadFilePath.exists()) {
                boolean crt = downloadFilePath.mkdirs();
                if (!crt) {
                    log.error("无法创建下载文件路径");
                    throw new FileNotFoundException(dir);
                }
            }
            File file = new File(dir + File.separator + fileName);

            inputStream = new FileInputStream(file);
            outputStream = response.getOutputStream();
            byte[] b = new byte[1024];
            int length;
            while ((length = inputStream.read(b)) > 0) {
                outputStream.write(b, 0, length);
            }
            outputStream.flush();

        } catch (Exception e) {
            if (log.isErrorEnabled()) {
                log.error("文件下载处理异常", e);
            }
            throw new RuntimeException(e);
        } finally {
            try {
                if (outputStream != null)
                    outputStream.close();
                if (inputStream != null)
                    inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
