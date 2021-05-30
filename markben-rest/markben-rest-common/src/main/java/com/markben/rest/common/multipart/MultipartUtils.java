package com.markben.rest.common.multipart;

import com.markben.common.exception.NullArgumentException;
import com.markben.common.logger.Logger;
import com.markben.common.utils.ArrayUtils;
import com.markben.common.utils.LoggerUtils;
import com.markben.common.utils.StringUtils;
import com.markben.core.context.MarkbenContextFactory;

import java.io.File;

/**
 * 上传工具类
 * @author 乌草坡
 * @since 0.0.1
 */
public class MultipartUtils {

    private static final Logger logger = LoggerUtils.getLogger(MultipartUtils.class);
    
    /**
     * 验证是否为图片类型；图片类型从配置文件中获取,属性为:upload.image.type
     * @param fileSuffix 文件后缀
     * @return 是返回：true，否则返回：false
     */
    public static boolean checkImageType(String fileSuffix) {
        boolean is = false;
        if(StringUtils.isNotEmpty(fileSuffix)) {
            String imageType = MarkbenContextFactory.getConfiguration().getSystemConfig().getValue("upload.image.type");
            if(StringUtils.isNotEmpty(imageType)) {
                is = ArrayUtils.isArrayContainsIgnoreCase(imageType, fileSuffix, ",");
            } else {
                LoggerUtils.error(logger, "配置文件中没定义[upload.image.type]属性,所以不支持改该类文件的上传");
            }
        }
        return is;
    }
    
    
    /**
     * 验证是否为文档类型；文档类型从配置文件中获取,属性为:upload.doc.type
     * @param fileSuffix 文件后缀
     * @return 是返回：true，否则返回：false
     */
    public static boolean checkDocType(String fileSuffix) {
        boolean is = false;
        if(StringUtils.isNotEmpty(fileSuffix)) {
            String docType = MarkbenContextFactory.getConfiguration().getSystemConfig().getValue("upload.doc.type");
            if(StringUtils.isNotEmpty(docType)) {
                is = ArrayUtils.isArrayContainsIgnoreCase(docType, fileSuffix, ",");
            } else {
                LoggerUtils.error(logger, "配置文件中没定义[upload.doc.type]属性,所以不支持改该类文件的上传");
            }
        }
        return is;
    }
    
    /**
     * 验证是否为视频文件类型；视频文件类型从配置文件中获取,属性为:upload.video.type
     * @param fileSuffix 文件后缀
     * @return 是返回：true，否则返回：false
     */
    public static boolean checkVideoType(String fileSuffix) {
        boolean is = false;
        if(StringUtils.isNotEmpty(fileSuffix)) {
            String videoType = MarkbenContextFactory.getConfiguration().getSystemConfig().getValue("upload.video.type");
            if(StringUtils.isNotEmpty(videoType)) {
                is = ArrayUtils.isArrayContainsIgnoreCase(videoType, fileSuffix, ",");
            } else {
                LoggerUtils.error(logger, "配置文件中没定义[upload.video.type]属性,所以不支持改该类文件的上传");
            }
        }
        return is;
    }
    
    
    /**
     * 验证是否为音频文件类型；音频文件类型从配置文件中获取,属性为:upload.audio.type
     * @param fileSuffix 文件后缀
     * @return 是返回：true，否则返回：false
     */
    public static boolean checkAudioType(String fileSuffix) {
        boolean is = false;
        if(StringUtils.isNotEmpty(fileSuffix)) {
            String audioType = MarkbenContextFactory.getConfiguration().getSystemConfig().getValue("upload.audio.type");
            if(StringUtils.isNotEmpty(audioType)) {
                is = ArrayUtils.isArrayContainsIgnoreCase(audioType, fileSuffix, ",");
            } else {
                LoggerUtils.error(logger, "配置文件中没定义[upload.audio.type]属性,所以不支持改该类文件的上传");
            }
        }
        return is;
    }
    
    
    /**
     * 验证文件类型；文件类型从配置文件中获取,属性为:upload.file.type
     * @param fileSuffix 文件后缀
     * @return 验证成功返回：true，否则返回：false
     */
    public static boolean checkFileType(String fileSuffix) {
        boolean is = false;
        if(StringUtils.isNotEmpty(fileSuffix)) {
            String fileType = MarkbenContextFactory.getConfiguration().getSystemConfig().getValue("upload.file.type");
            if(StringUtils.isNotEmpty(fileType)) {
                is = ArrayUtils.isArrayContainsIgnoreCase(fileType, fileSuffix, ",");
            } else {
                LoggerUtils.error(logger, "配置文件中没定义[upload.file.type]属性,无法验证该文件类型");
            }
        }
        return is;
    }
    
    
    /**
     * 获取存放图片的路径；
     * 从配置文件中获取,属性为:upload.images.dir
     * @return 返回图片存放目录
     */
    public static String getImageDir() {
        String dirPath = MarkbenContextFactory.getConfiguration().getSystemConfig().getValue("upload.images.dir");
        dirPath = handleDir(dirPath);
        File dir = new File(dirPath);
        if(!dir.exists()) {
            dir.mkdirs();
        }
        return dirPath;
    }
    
    
    /**
     * 获取存放文档的路径；
     * 从配置文件中获取,属性为:upload.doc.dir
     * @return 返回文档存放目录
     */
    public static String getDocDir() {
        String dirPath = MarkbenContextFactory.getConfiguration().getSystemConfig().getValue("upload.doc.dir");
        dirPath = handleDir(dirPath);
        File dir = new File(dirPath);
        if(!dir.exists()) {
            dir.mkdirs();
        }
        return dirPath;
    }
    
    /**
     * 获取存放视频的路径；
     * 从配置文件中获取,属性为:upload.video.dir
     * @return 返回视频存放目录
     */
    public static String getVideoDir() {
        String dirPath = MarkbenContextFactory.getConfiguration().getSystemConfig().getValue("upload.video.dir");
        dirPath = handleDir(dirPath);
        File dir = new File(dirPath);
        if(!dir.exists()) {
            dir.mkdirs();
        }
        return dirPath;
    }
    
    /**
     * 获取存放音频的路径；
     * 从配置文件中获取,属性为:upload.audio.dir
     * @return 返回音频文件存放目录
     */
    public static String getAudioDir() {
        String dirPath = MarkbenContextFactory.getConfiguration().getSystemConfig().getValue("upload.audio.dir");
        dirPath = handleDir(dirPath);
        File dir = new File(dirPath);
        if(!dir.exists()) {
            dir.mkdirs();
        }
        return dirPath;
    }
    
    /**
     * 获取存放其他文件的路径；
     * 从配置文件中获取,属性为:upload.other.dir
     * @return 返回其他文件存放目录
     */
    public static String getOtherDir() {
        String dirPath = MarkbenContextFactory.getConfiguration().getSystemConfig().getValue("upload.other.dir");
        dirPath = handleDir(dirPath);
        File dir = new File(dirPath);
        if(!dir.exists()) {
            dir.mkdirs();
        }
        return dirPath;
    }

    /**
     * 获取存放文件的路径;
     * 从配置文件中获取,属性为:upload.file.dir
     * @return 返回其他文件存放目录
     */
    public static String getFileDir() {
        String dirPath = MarkbenContextFactory.getConfiguration().getSystemConfig().getValue("upload.file.dir");
        dirPath = handleDir(dirPath);
        File dir = new File(dirPath);
        if(!dir.exists()) {
            dir.mkdirs();
        }
        return dirPath;
    }

    /**
     * 获取默认的上传文件的目录
     * @return 返回默认路径
     */
    public static String getDefaultDir() {
        String dirPath = MarkbenContextFactory.getConfiguration().getSystemConfig().getValue("upload.dir");
        if(StringUtils.isEmpty(dirPath)) {
            dirPath = "/upload";
        }
        dirPath = handleDir(dirPath);
        File dir = new File(dirPath);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        return dirPath;
    }
    
    /**
     * 获取支持上传文件的最大文件大小；
     * 从配置文件中获取,属性为:upload.max.size
     * @return 返回支持的最大文件大小
     */
    public static long getUploadMaxSize() {
        String sizeStr = MarkbenContextFactory.getConfiguration().getSystemConfig().getValue("upload.max.size");
        long size = 0;
        try {
            size = Long.parseLong(sizeStr);
        } catch (Exception e) {
            size = 1024*1024*1000;
        }
        return size;
    }

    /**
     * 获取系统最原始的根路径；
     * 根目录从配置文件中获取，属性为：root.dir
     * @return 返回根路径
     */
    public static String getOriginalRootDir() {
        String rootDir = MarkbenContextFactory.getConfiguration().getSystemConfig().getValue("root.dir");
        if(StringUtils.isEmpty(rootDir)) {
            throw new NullArgumentException("请在系统配置文件中的设置“root.dir”属性.");
        }
        return rootDir;
    }

    /**
     * 获取根目录
     * @return 返回根目录
     */
    public static String getRootDir() {
        return getOriginalRootDir();
    }
    
    /**
     * 处理路径
     * @param dirStr 目录路径
     * @return 返回处理后的目录路径
     */
    private static String handleDir(String dirStr) {
        if(StringUtils.isNotEmpty(dirStr)) {
            String rootDir = getRootDir();
            if(!dirStr.contains(rootDir)) {
                String originalRootDir = getOriginalRootDir();
                dirStr = dirStr.replace(originalRootDir, rootDir);
            }
            LoggerUtils.debug(logger, "系统文件根目录为："+dirStr);
        }
        return dirStr;
    }
}
