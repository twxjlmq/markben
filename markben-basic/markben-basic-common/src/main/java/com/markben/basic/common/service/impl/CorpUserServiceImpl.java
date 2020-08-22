package com.markben.basic.common.service.impl;

import com.markben.basic.common.entity.TSysCorpUser;
import com.markben.basic.common.service.ICorpUserService;
import com.markben.common.enums.YesOrNoType;
import com.markben.common.utils.StringUtils;
import com.markben.core.service.EnhanceServiceImpl;
import com.markben.core.utils.EntityUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 乌草坡
 * @since 1.0
 */
@Service
public class CorpUserServiceImpl extends EnhanceServiceImpl<TSysCorpUser> implements ICorpUserService {

    @Override
    public List<TSysCorpUser> getCorpUser(String userId) {
        //StringUtils.isAssert(userId, "userId参数不能为空", this);
        return super.finds(query().eq(EntityUtils.toDbField("userId"), userId)
                .and(w -> w.eq("state", YesOrNoType.YES.getIndex())));
    }

    @Override
    public TSysCorpUser getCorpUser(String userId, String corpId) {
        return super.getOne(query().eq(EntityUtils.toDbField("userId"), userId)
                .and(w -> w.eq(EntityUtils.toDbField("corpId"), corpId)
                        .eq("state", YesOrNoType.YES.getIndex())), false);
    }
}
