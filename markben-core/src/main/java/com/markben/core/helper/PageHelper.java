package com.markben.core.helper;

import com.markben.common.config.SystemConfig;
import com.markben.common.constant.MarkbenConstant;
import com.markben.common.logger.ILogger;
import com.markben.common.utils.LoggerUtils;
import com.markben.common.utils.StringUtils;
import org.slf4j.Logger;

/**
 * 分页助手类
 * @author 乌草坡
 * @version 1.0
 */
public class PageHelper {

    protected static final ILogger logger = LoggerUtils.getLogger(PageHelper.class);
	
    /**
     * 获取每页显示数量
     * 如果传入的参数值小于1；则从配置文件中获取默认值；否则就是传入的值
     * @param perPageSize 每页显示数量
     * @return 返回每页显示数量
     */
    public static int getPerPageSize(int perPageSize) {
		if(perPageSize<1) {
			perPageSize = getPerPageSize();
		}
		return perPageSize;
	}
    
    /**
     * 默认获取每页显示数量
     * @return 返回默认每页显示数
     */
    public static int getPerPageSize() {
    	int perPageSize = 0;
    	String perPageStr = SystemConfig.getInstance().getValue("page.per.size");
    	if(StringUtils.isNotEmpty(perPageStr)) {
    		try {
    			perPageSize = Integer.parseInt(perPageStr);
    		} catch(Exception e) {
    			perPageSize = 0;
    		}
    	} else {
    		perPageSize = MarkbenConstant.PRE_PAGE_SIZE;
    	}
		return perPageSize;
	}
    
    /**
     * 获取当前页
     * 如果传入的参数
     * @param page 当前页
     * @return 返回当前页
     */
	public static int getPage(int page) {
		if(page<1) {
			page = 1;
		}
		return page;
	}
	
	/**
	 * 获取当前页数
	 * @param start 开始位置
	 * @param pageSize 每页显示数
	 * @return 返回当前页
	 */
	public static int getPageNo(long start, int pageSize) {
	    int pageNo = (int)Math.ceil(start/getPerPageSize(pageSize));
	    return pageNo;
	}
    
    /**
     * 计算总页数
     * @param total 总数据数
     * @param pageSize 每页显示数
     * @return 返回总页数
     */
	public static int getTotalPage(long total,int pageSize) {
		return (int) Math.ceil((double)total/pageSize);
	}
	
	/**
	 * 获取开始分页的位置
	 * @param page 当前页
	 * @param pageSize 每页显示数
	 * @return 返回开始分页的位置
	 */
	public static int getStartNum(int page,int pageSize) {
		int startNum = (getPage(page)-1)*getPerPageSize(pageSize);
		return startNum;
	}
	
	/**
	 * 获取开始分页的位置
	 * @param page 当前页
	 * @return 返回开始分页的位置
	 */
	public static int getStartNum(int page) {
		int startNum = (getPage(page)-1)*getPerPageSize();
		return startNum;
	}
	
	
	/**
     * 获取页面显示数量
     * @return 返回每页显示数
     */
    public static Integer[] getShowPageSize() {
        Integer[] showPageSizes = null;
        String pageSizeStr = SystemConfig.getInstance().getValue("page.show.size");
        boolean is = false;
        if(StringUtils.isNotEmpty(pageSizeStr)) {
            try {
                String[] pageSizeArray = pageSizeStr.split(MarkbenConstant.MULTI_VALUE_SPLIT);
                showPageSizes = new Integer[pageSizeArray.length];
                for (int i = 0; i < pageSizeArray.length; i++) {
                    showPageSizes[i] = Integer.parseInt(pageSizeArray[i].trim());
                }
                is = true;
            } catch (Exception ex) {
                is = false;
            }
        }
        if(!is) {
            showPageSizes = new Integer[] { 15, 25, 35, 50 };
        }
        return showPageSizes;
    }
	
    /**
     * 获取默认页面显示数量
     * @return 返回默认显示数
     */
    public static int defaultPageSize() {
        int pageSize = 0;
        try {
            pageSize = Integer.parseInt(SystemConfig.getInstance().getValue("page.per.size"));
        } catch (Exception e) {
            pageSize = MarkbenConstant.PRE_PAGE_SIZE;
        }
        return pageSize;
    }
}
