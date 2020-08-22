package com.markben.rest.common.helper;

import com.markben.beans.enums.MarkbenStatusEnums;
import com.markben.beans.response.IBaseResponse;

/**
 * Rest模块通用辅助类
 * @author 乌草坡
 * @since 1.0
 */
public class RestCommonHelper {

    /**
     * 设置成功结果
     * @param response 响应对象
     */
    public static void setSuccessResult(IBaseResponse response) {
        if(null != response) {
            response.setStatus(MarkbenStatusEnums.SUCCESS.getStatus());
            response.setMsg(MarkbenStatusEnums.SUCCESS.getMsg());
        }
    }

    /**
     * 设置结果状态
     * @param response 响应对象
     * @param statusEnum 状态枚举对象
     */
    public static void setResponseStatus(IBaseResponse response, MarkbenStatusEnums statusEnum) {
        if(null == response || null == statusEnum) {
            return;
        }
        response.setStatus(statusEnum.getStatus());
        response.setMsg(statusEnum.getMsg());
    }
}
