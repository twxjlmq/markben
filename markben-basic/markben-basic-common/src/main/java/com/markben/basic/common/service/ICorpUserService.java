package com.markben.basic.common.service;

import com.markben.basic.common.entity.TSysCorpUser;
import com.markben.core.service.IMgrService;

import java.util.List;

/**
 * @author 乌草坡
 * @since 1.0
 */
public interface ICorpUserService extends IMgrService<TSysCorpUser> {

    /**
     * 获取企业用户实体对象类表通过用户ID
     * @param userId 用户ID
     * @return 返回企业用户实体对象列表
     */
    List<TSysCorpUser> getCorpUser(String userId);

    /**
     * 获取企业用户实体对象通过用户ID和企业ID；
     * 如果获取数据返回对于的实体类，否则返回null
     * @param userId 用户ID
     * @param corpId 企业ID
     * @return 返回企业用户实体类或null
     */
    TSysCorpUser getCorpUser(String userId, String corpId);

}
