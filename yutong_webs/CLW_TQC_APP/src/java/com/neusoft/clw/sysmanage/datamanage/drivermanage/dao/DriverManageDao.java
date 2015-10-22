package com.neusoft.clw.sysmanage.datamanage.drivermanage.dao;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.neusoft.clw.common.dao.impl.ExtendSqlMapDao;
import com.neusoft.clw.common.exceptions.DataAccessException;
import com.neusoft.clw.common.exceptions.DataAccessIntegrityViolationException;
import com.neusoft.clw.sysmanage.datamanage.drivermanage.domain.DriverInfo;

public class DriverManageDao extends ExtendSqlMapDao {
    /**
     * 导入驾驶员信息
     */
    public Object insert(Class clazz, Object obj)
            throws DataAccessIntegrityViolationException, DataAccessException {
        SqlMapClient sqlmapclient = this.getSqlMapClient();
        Object o = null;
        List < DriverInfo > list = null;

        try {
            if (null == obj) {
                throw new DataAccessException("the obj value is null");
            }
            if (!(obj instanceof List)) {
                throw new UnsupportedOperationException(
                        "the obj should be List instance");
            } else {
                list = (List < DriverInfo >) obj;
            }
            // 开启批处理
            sqlmapclient.startBatch();
            for (DriverInfo driverInfo : list) {
                o = getSqlMapClientTemplate().insert("DriverManage.mergeDriverInfo",
                        driverInfo);
            }
            // 批处理执行
            sqlmapclient.executeBatch();
            return o;
        } catch (SQLException e) {
            throw new DataAccessException(e);
        } catch (Exception e) {
            throw new DataAccessException(e);
        }
    }

}
