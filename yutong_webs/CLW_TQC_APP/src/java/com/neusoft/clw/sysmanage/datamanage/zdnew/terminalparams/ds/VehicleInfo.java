/*******************************************************************************
 * @(#)VehicleInfo.java 2012-6-5
 *
 * Copyright 2012 Neusoft Group Ltd. All rights reserved.
 * Neusoft PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *******************************************************************************/
package com.neusoft.clw.sysmanage.datamanage.zdnew.terminalparams.ds;

/**
 * @author <a href="mailto:jin.p@neusoft.com">JinPeng </a>
 * @version $Revision 1.1 $ 2012-6-5 下午01:56:58
 */
public class VehicleInfo {
    /** 车辆ID值 **/
    private String vehicleId = "";
    /** 车牌号 **/
    private String vehicleLn = "";
    private String vehicleCode = "";
    
    public String getVehicleCode() {
		return vehicleCode;
	}
	public void setVehicleCode(String vehicleCode) {
		this.vehicleCode = vehicleCode;
	}
	public String getVehicleId() {
        return vehicleId;
    }
    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }
    public String getVehicleLn() {
        return vehicleLn;
    }
    public void setVehicleLn(String vehicleLn) {
        this.vehicleLn = vehicleLn;
    }
}
