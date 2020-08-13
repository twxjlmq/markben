package com.markben.rest.common.helper;

import com.markben.rest.common.response.RestBaseResponse;
import com.markben.rest.common.response.RestCollectionResponse;
import com.markben.rest.common.response.RestPaginateResponse;
import com.markben.rest.common.response.RestResultResponse;
import com.markben.beans.response.IBaseResponse;
import com.markben.beans.response.ICollectionResponse;
import com.markben.beans.response.IPaginateResponse;
import com.markben.beans.response.IResultResponse;
import com.markben.common.exception.NullArgumentException;

/**
 * 响应对象转化辅助类
 * @author 乌草坡
 * @since 1.0
 */
public class RestResponseConvertHelper {

    /**
     * 基础默认响应 {@link com.markben.beans.response.impl.DefaultBaseResponse }
     * 对象转化为{@link RestBaseResponse} 响应对象
     * @param response 默认响应对象
     * @return 返回 {@link RestBaseResponse } 对象
     */
    public static IBaseResponse baseConvert(IBaseResponse response) {
        if(null == response) {
            throw new NullArgumentException("response参数为null");
        }
        return new RestBaseResponse(response.getStatus(), response.getMsg());
    }

    /**
     * 结果默认响应 {@link com.markben.beans.response.impl.DefaultResultResponse }
     * 对象转化为{@link RestResultResponse} 响应对象
     * @param resultResp 默认响应对象
     * @param <T>
     * @return 返回 {@link RestResultResponse } 对象
     */
    public static <T> IResultResponse<T> convert(IResultResponse<T> resultResp) {
        if(null == resultResp) {
            throw new NullArgumentException("resultResp参数为null");
        }
        return new RestResultResponse<T>(resultResp.getStatus(),
                resultResp.getMsg(), resultResp.getResult());
    }

    /**
     * 集合默认响应 {@link com.markben.beans.response.impl.DefaultCollectionResponse }
     * 对象转化为{@link RestCollectionResponse} 响应对象
     * @param resultResp 默认响应对象
     * @param <T>
     * @return 返回 {@link RestCollectionResponse } 对象
     */
    public static <T> ICollectionResponse<T> convertCollection(ICollectionResponse<T> resultResp) {
        if(null == resultResp) {
            throw new NullArgumentException("resultResp参数为null");
        }
        return new RestCollectionResponse<T>(resultResp.getStatus(),
                resultResp.getMsg(), resultResp.getData());
    }

    /**
     * 分页默认响应 {@link com.markben.beans.response.impl.DefaultPaginateResponse }
     * 对象转化为{@link RestPaginateResponse} 响应对象
     * @param resultResp 默认响应对象
     * @param <T>
     * @return 返回 {@link RestPaginateResponse } 对象
     */
    public static <T> IPaginateResponse<T> convertPaginate(IPaginateResponse<T> resultResp) {
        if(null == resultResp) {
            throw new NullArgumentException("resultResp参数为null");
        }
        return new RestPaginateResponse<T>(resultResp.getStatus(),resultResp.getMsg(),
                resultResp.getData(),
                resultResp.getTotal(), resultResp.getSize(), resultResp.getTotalPage());
    }
}
