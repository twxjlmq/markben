package com.markben.rest.common.helper;

import com.markben.beans.response.DefaultBaseResponseImpl;
import com.markben.beans.response.DefaultCollectionResponseImpl;
import com.markben.beans.response.DefaultPaginateResponseImpl;
import com.markben.beans.response.DefaultResultResponseImpl;
import com.markben.rest.common.response.RestBaseResponse;
import com.markben.rest.common.response.RestCollectionResponse;
import com.markben.rest.common.response.RestPaginateResponse;
import com.markben.rest.common.response.RestResultResponse;
import com.markben.beans.response.BaseResponse;
import com.markben.beans.response.CollectionResponse;
import com.markben.beans.response.PaginateResponse;
import com.markben.beans.response.ResultResponse;
import com.markben.common.exception.NullArgumentException;

/**
 * 响应对象转化辅助类
 * @author 乌草坡
 * @since 0.0.1
 */
public class RestResponseConvertHelper {

    /**
     * 基础默认响应 {@link DefaultBaseResponseImpl }
     * 对象转化为{@link RestBaseResponse} 响应对象
     * @param response 默认响应对象
     * @return 返回 {@link RestBaseResponse } 对象
     */
    public static BaseResponse baseConvert(BaseResponse response) {
        if(null == response) {
            throw new NullArgumentException("response参数为null");
        }
        return new RestBaseResponse(response.getStatus(), response.getMsg());
    }

    /**
     * 结果默认响应 {@link DefaultResultResponseImpl }
     * 对象转化为{@link RestResultResponse} 响应对象
     * @param resultResp 默认响应对象
     * @param <T>
     * @return 返回 {@link RestResultResponse } 对象
     */
    public static <T> ResultResponse<T> convert(ResultResponse<T> resultResp) {
        if(null == resultResp) {
            throw new NullArgumentException("resultResp参数为null");
        }
        return new RestResultResponse<T>(resultResp.getStatus(),
                resultResp.getMsg(), resultResp.getResult());
    }

    /**
     * 集合默认响应 {@link DefaultCollectionResponseImpl }
     * 对象转化为{@link RestCollectionResponse} 响应对象
     * @param resultResp 默认响应对象
     * @param <T>
     * @return 返回 {@link RestCollectionResponse } 对象
     */
    public static <T> CollectionResponse<T> convertCollection(CollectionResponse<T> resultResp) {
        if(null == resultResp) {
            throw new NullArgumentException("resultResp参数为null");
        }
        return new RestCollectionResponse<T>(resultResp.getStatus(),
                resultResp.getMsg(), resultResp.getData());
    }

    /**
     * 分页默认响应 {@link DefaultPaginateResponseImpl }
     * 对象转化为{@link RestPaginateResponse} 响应对象
     * @param resultResp 默认响应对象
     * @param <T>
     * @return 返回 {@link RestPaginateResponse } 对象
     */
    public static <T> PaginateResponse<T> convertPaginate(PaginateResponse<T> resultResp) {
        if(null == resultResp) {
            throw new NullArgumentException("resultResp参数为null");
        }
        return new RestPaginateResponse<T>(resultResp.getStatus(),resultResp.getMsg(),
                resultResp.getData(),
                resultResp.getTotal(), resultResp.getSize(), resultResp.getTotalPage());
    }
}
