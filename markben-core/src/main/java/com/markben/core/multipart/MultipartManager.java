package com.markben.core.multipart;

import java.io.InputStream;

/**
 * Multipart管理接口；主要定义了文件的上传及删除接口
 * @author 乌草坡
 * @since 0.0.1
 */
public interface MultipartManager {

    /**
     * 上传文件
     * @param inputStream 上传文件流
     * @param fileSize 文件大小
     * @param contentType 文件类型
     * @param sourceFileName 源文件名称
     * @param isSupportDownload 是否支持下载；
     *                          注：设置支持下载后，点击链接会直接下载文件，即使是图片也不会显示
     * @return 返回上传文件的地址
     */
    String upload(InputStream inputStream, long fileSize, String contentType, String sourceFileName, boolean isSupportDownload);

    /**
     * 删除上的文件通过指定的地址
     * @param url 文件地址
     */
    void remove(String url);

}
