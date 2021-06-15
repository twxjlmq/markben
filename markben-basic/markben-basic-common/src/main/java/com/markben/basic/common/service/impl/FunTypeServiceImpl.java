package com.markben.basic.common.service.impl;

import com.markben.basic.common.dao.FunTypeDao;
import com.markben.basic.common.entity.TSysFunType;
import com.markben.basic.common.service.FunTypeService;
import com.markben.core.service.MgrServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author 乌草坡
 * @since 0.0.1
 */
@Service
public class FunTypeServiceImpl extends MgrServiceImpl<FunTypeDao, TSysFunType> implements FunTypeService {

}
