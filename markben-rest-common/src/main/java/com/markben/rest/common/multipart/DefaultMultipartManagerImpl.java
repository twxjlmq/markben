package com.markben.rest.common.multipart;

import com.markben.beans.enums.MarkbenStatusEnums;
import com.markben.beans.exception.MarkbenStatusException;
import com.markben.common.logger.ILogger;
import com.markben.common.utils.LoggerUtils;
import com.markben.common.utils.StringUtils;
import com.markben.core.multipart.IMultipartManager;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * 默认的IMultipartManager接口实现类
 * @author 乌草坡
 * @since 1.0
 */
public class DefaultMultipartManagerImpl implements IMultipartManager {

    private static final ILogger logger = LoggerUtils.getLogger(DefaultMultipartManagerImpl.class);

    @Override
    public String upload(InputStream inputStream, long fileSize, String contentType, String sourceFileName, boolean isSupportDownload) {
        LoggerUtils.debug(logger, "正在处理上传文件...");
        LoggerUtils.debug(logger, "上传文件信息为：fileSize:[{}]---contentType:[{}]---sourceFileName:[{}].",
                fileSize, contentType, sourceFileName);
        if(null == inputStream || null == contentType || null == sourceFileName) {
            LoggerUtils.error(logger, "inputStream,contentType, sourceFileName三个参数不能为空(null)");
            throw new IllegalArgumentException("必要参数为空");
        }
        String suffix = StringUtils.getFileSuffix(sourceFileName);
        String uploadDir = getUploadDir(suffix);
        File dirFile = new File(uploadDir);
        if(!dirFile.exists()) {
            dirFile.mkdirs();
        }
        if (fileSize > MultipartUtils.getUploadMaxSize()) {
            String msg = "“"+sourceFileName+"”文件太大，不支持上传";
            LoggerUtils.error(logger, msg);
            throw new MarkbenStatusException(MarkbenStatusEnums.MULTIPART_UPLOAD_MAX_SIZE.getStatus(), msg);
        } else {
            String targetFileName = StringUtils.uuid() + "." + suffix;
            String targetFilePath = uploadDir+ "/" + targetFileName;
            File targetFile = new File(targetFilePath);
            try {
                //保存文件到磁盘
                saveUploadFile(inputStream, targetFile);
                LoggerUtils.debug(logger, "文件上传成功，路径为：{}", targetFilePath);
                targetFilePath = targetFilePath.replace(MultipartUtils.getRootDir(), "");
                return targetFilePath;
            } catch (Exception ex) {
                LoggerUtils.error(logger, ex);
                throw new MarkbenStatusException(MarkbenStatusEnums.MULTIPART_UPLOAD_ERROR.getStatus(), ex.getMessage());
            }
        }
    }

    @Override
    public void remove(String url) {

    }

    /**
     * 根据文件后缀名获取用于存放文件的目录路径
     * @param suffix 文件后缀名
     * @return 返回目录路径
     */
    private String getUploadDir(String suffix) {
        String uploadDir = null;
        if(MultipartUtils.checkImageType(suffix)) {
            uploadDir = MultipartUtils.getImageDir();
        } else if(MultipartUtils.checkDocType(suffix)) {
            uploadDir = MultipartUtils.getDocDir();
        } else if(MultipartUtils.checkVideoType(suffix)) {
            uploadDir = MultipartUtils.getVideoDir();
        } else if(MultipartUtils.checkAudioType(suffix)) {
            uploadDir = MultipartUtils.getAudioDir();
        } else if(MultipartUtils.checkFileType(suffix)){
            uploadDir = MultipartUtils.getFileDir();
        } else {
            uploadDir = MultipartUtils.getOtherDir();
        }
        if(StringUtils.isEmpty(uploadDir)) {
            uploadDir = MultipartUtils.getDefaultDir();
        }
        return uploadDir;
    }

    /**
     * 保存上传的文件到服务器
     * @param source 源文件
     * @param target 目标文件
     * @throws IOException
     */
    private void saveUploadFile(InputStream source, File target) throws IOException {
        LoggerUtils.info(logger, "正在保存文件...");
        if(null != source) {
            FileUtils.copyInputStreamToFile(source, target);
            if(null != source)
                source.close();
        }
    }
}
