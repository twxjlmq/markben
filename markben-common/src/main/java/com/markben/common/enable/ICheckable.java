package com.markben.common.enable;

/**
 * 可检查的接口；如果检查参数等
 * @author 乌草坡
 * @since 1.0
 */
public interface ICheckable {

    /**
     * 检查参数
     * @return 如果检查成功返回true；否则返回false或抛出运行时异常
     */
    boolean check();

}
