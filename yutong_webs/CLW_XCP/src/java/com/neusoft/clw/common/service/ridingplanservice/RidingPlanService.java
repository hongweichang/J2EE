package com.neusoft.clw.common.service.ridingplanservice;

import java.util.List;
import java.util.Map;

import com.neusoft.clw.common.exceptions.BusinessException;
import com.neusoft.clw.common.exceptions.DataAccessException;
import com.neusoft.clw.common.exceptions.DataAccessIntegrityViolationException;
import com.neusoft.clw.common.service.Service;

public interface RidingPlanService extends Service {
	public void batchAddRidingPlan(Map map, List driverList, List sichenList,
			List vssList, List vss_siteList) throws BusinessException,
			DataAccessIntegrityViolationException, DataAccessException;

	public Map batchDeletedRidingPlan(Map map,String usedPath) throws BusinessException,
			DataAccessIntegrityViolationException, DataAccessException;

	public Map batchUpdateRidingPlan(Map map, List driverList,
			List sichenList, List vssList, List vss_siteList,
			String vehicle_vin_old,String usedPath) throws BusinessException,
			DataAccessIntegrityViolationException, DataAccessException;

}