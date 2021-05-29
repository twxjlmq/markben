package com.markben.core.bean;

/**
 * 实体主键前缀接口；
 * 如果主键为字符串并且想要在主键前加前缀，那么就实现该接口
 * @author 乌草坡
 * @since 0.0.1
 */
public interface PKPrefix {

    /**
     * 获取前缀；生成主键时，会把前缀加前面并通过“_”连接；
     * 如：前缀为：u；值为：123456，生成后的主键为：u_123456
     * @return 返回前缀
     */
    String getPrefix();

}
