package com.markben.core.bean;

/**
 * 实体主键前缀接口；
 * 如果主键为字符串并且想要在主键前加前缀，那么就实现该接口
 * @author 乌草坡
 * @since 1.0
 */
public interface IPKPrefix {

    /**
     * 获取前缀；生成主键时，会把前缀中的字母转化为大写；
     * 如：u_会转化为：U_
     * @return 返回前缀
     */
    String getPrefix();

}
