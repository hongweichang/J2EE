/*******************************************************************************
 * @(#)AffairDao.java 2011-3-29
 *
 * Copyright 2011 Neusoft Group Ltd. All rights reserved.
 * Neusoft PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *******************************************************************************/
package com.neusoft.clw.common.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.neusoft.clw.sysmanage.datamanage.vehiclemanage.domain.VehcileInfo;
import com.neusoft.clw.sysmanage.sysset.oilset.domain.OilSet;

/**
 * 数据库批量处理类
 * @author <a href="gao.jian@neusoft.com">jian.gao </a>
 * @version $Revision 1.1 $ 2011-3-29 下午08:31:26
 */
public class AffairDao extends SqlMapDao {

    /**
     * 批量更新处理方法
     */
    public void addBatchOilSet(List < OilSet > obj) throws SQLException {
        try {
            SqlMapClient sqlmapclient = this.getSqlMapClient();
            sqlmapclient.startBatch();

            for (OilSet os : obj) {

                getSqlMapClientTemplate().insert("OilSet.insertOilSetInfo", os);
                getSqlMapClientTemplate().insert("OilSet.insertOilSetV", os);
            }

            sqlmapclient.executeBatch();
        } catch (SQLException e) {
            throw new SQLException(e.toString());
        }
    }

    /**
     * 批量删除处理方法
     */
    public void deleteBatchOilSet(OilSet obj) throws SQLException {
        try {
            SqlMapClient sqlmapclient = this.getSqlMapClient();
            sqlmapclient.startBatch();

            // 删除考核油耗表信息
            getSqlMapClientTemplate().update("OilSet.deletebyOilSetid", obj);
            // 删除关联表
            getSqlMapClientTemplate().update("OilSet.deleteTypeOilSet", obj);
            // 删除车辆类型表信息
            // getSqlMapClientTemplate().update("OilSet.deleteTypebyid", obj);
            // int res = (Integer) getSqlMapClientTemplate().queryForObject(
            // "OilSet.getVonum", obj);
            //
            // if (res == 0) {
            // getSqlMapClientTemplate().update("OilSet.deleteTypebyid", obj);
            // }

            sqlmapclient.executeBatch();
        } catch (SQLException e) {
            throw new SQLException(e.toString());
        }
    }

    /**
     * 批量修改处理方法
     */
    public void updateBatchOilSet(OilSet obj) throws SQLException {
        try {
            SqlMapClient sqlmapclient = this.getSqlMapClient();
            sqlmapclient.startBatch();

            // 修改考核油耗表信息
            getSqlMapClientTemplate().update("OilSet.updateOilSetInfomation",
                    obj);

            // 修改车辆类型表信息
            getSqlMapClientTemplate().update("OilSet.updateVelTypebyid", obj);

            sqlmapclient.executeBatch();
        } catch (SQLException e) {
            throw new SQLException(e.toString());
        }
    }

    /**
     * 批量修改处理方法
     */
    public String batchCanleVel(List < VehcileInfo > obj) throws SQLException {
    	StringBuffer sb = new StringBuffer();
        try {
            SqlMapClient sqlmapclient = this.getSqlMapClient();
            sqlmapclient.startBatch();
            int i=0,j=0;
            for (VehcileInfo vi : obj) {
            	List listData = getSqlMapClientTemplate().queryForList("VehicleManage.getvehiclebeatchexeplan_count", vi);
            	if(listData.size() == 0) {      //==0是去除分配车辆未分配线路。现在改为>=0，以后要再改回来
	                // 查询车辆组织ID-通过VIN
	                String org = (String) getSqlMapClientTemplate().queryForObject("VehicleManage.getOrgIdByVin", vi);
	
	                vi.setOrganization_id(org);
	                // 去除车辆分配
	                getSqlMapClientTemplate().update("VehicleManage.batchCanclebyVehicleVin", vi);
	
	                if (vi.getOrganization_id() != null) {
	                    // 对企业关联进行处理-查询是否该企业下无车辆关联
	                    int res = (Integer) getSqlMapClientTemplate().queryForObject("VehicleManage.getCountEntiCars",vi);
	
	                    if (res == 0) {
	                        getSqlMapClientTemplate().update("VehicleManage.updateorgidFlagByOrgId", vi);
	                    }
	                }
                
	            } else {
	            	i++;
	            	Map mapData=(Map)listData.get(0);
	            	//System.out.println( "----------->>:"+mapData.get("VEHICLE_LN"));
	            	//String ln=mapData.get("VEHICLE_LN").toString();
	            	if(i<4){//三个及三个以下，全部显示，多余三个的直接用..代替
	            		//sb.append(mapData.get("VEHICLE_CODE").toString()+"("+mapData.get("VEHICLE_LN").toString()+") ");
	            		sb.append(mapData.get("VEHICLE_CODE").toString()+"号 ");
	            	}
	            	else{
	            		j++;
	            	}
	            	//sb.append(length.)
	            }
            	if(j>0){sb.append("..");}

            }
            
            sqlmapclient.executeBatch();
        } catch (SQLException e) {
            throw new SQLException(e.toString());
        }
        return sb.toString();
    }

}
