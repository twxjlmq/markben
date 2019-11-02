package com.markben.core;

import com.markben.core.dao.IReadonlyDao;
import com.markben.core.dao.IWriteOnlyDao;

/**
 * 有能力配置实体DAO
 * @author 乌草坡
 * @since 1.0
 */
public interface ConfigurableEntityDao {

    void setReadonlyDao(IReadonlyDao readonlyDao);

    IReadonlyDao getReadonlyDao();

    void setWriteOnlyDao(IWriteOnlyDao writeOnlyDao);

    IWriteOnlyDao getWriteOnlyDao();

}
