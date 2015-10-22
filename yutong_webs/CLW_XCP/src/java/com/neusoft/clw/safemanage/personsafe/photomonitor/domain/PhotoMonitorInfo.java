package com.neusoft.clw.safemanage.personsafe.photomonitor.domain;

public class PhotoMonitorInfo {

	private String vehicle_vin;

	private String vehicle_ln;

	private String vehicle_code;

	private String photo_time;
	/*
	 * 触发类型分别为全部、下发指令拍照、终端定时拍照、SOS拍照、碰撞侧翻拍照、开门拍照、关门拍照、行驶开关门拍照；
	 */
	private String photo_event;

	private String photo_falg;

	private String photo_desc;

	// 下发命令时间
	private String operate_time;

	private String photo_file;

	private String user_name;

	private String send_result;
	
	private String photo_id;
	
	private String operate_user_id;//拍照人
	
	private String operate_user_name;

	public String getPhoto_id() {
		return photo_id;
	}

	public void setPhoto_id(String photo_id) {
		this.photo_id = photo_id;
	}

	private int row_num;

	// 通道号
	private String chanle_code;

	private Integer total;// 总记录数

	private String query;

	private Integer page;// 当前第page页

	private Integer rp;// 每页记录数

	private String sortname;

	private String sortorder;
	
	//解除标记
	private String flag_user;
	
	private String flag_user_name;
	
	private String flag_time;
	
	//标记操作
	private String reli_user;
		
	private String reli_user_name;
		
	private String reli_time;
	
	public String getFlag_time() {
		return flag_time;
	}

	public void setFlag_time(String flag_time) {
		this.flag_time = flag_time;
	}

	public String getPhoto_event() {
		return photo_event;
	}

	public void setPhoto_event(String photo_event) {
		this.photo_event = photo_event;
	}

	

	public String getPhoto_falg() {
        return photo_falg;
    }

    public void setPhoto_falg(String photo_falg) {
        this.photo_falg = photo_falg;
    }

    public String getPhoto_desc() {
		return photo_desc;
	}

	public void setPhoto_desc(String photo_desc) {
		this.photo_desc = photo_desc;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getRp() {
		return rp;
	}

	public void setRp(Integer rp) {
		this.rp = rp;
	}

	public String getSortname() {
		return sortname;
	}

	public void setSortname(String sortname) {
		this.sortname = sortname;
	}

	public String getSortorder() {
		return sortorder;
	}

	public void setSortorder(String sortorder) {
		this.sortorder = sortorder;
	}

	public String getVehicle_vin() {
		return vehicle_vin;
	}

	public void setVehicle_vin(String vehicle_vin) {
		this.vehicle_vin = vehicle_vin;
	}

	public String getVehicle_ln() {
		return vehicle_ln;
	}

	public void setVehicle_ln(String vehicle_ln) {
		this.vehicle_ln = vehicle_ln;
	}

	public String getVehicle_code() {
		return vehicle_code;
	}

	public void setVehicle_code(String vehicle_code) {
		this.vehicle_code = vehicle_code;
	}

	public String getPhoto_time() {
		return photo_time;
	}

	public void setPhoto_time(String photo_time) {
		this.photo_time = photo_time;
	}

	public String getPhoto_file() {
		return photo_file;
	}

	public void setPhoto_file(String photo_file) {
		this.photo_file = photo_file;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getChanle_code() {
		return chanle_code;
	}

	public void setChanle_code(String chanle_code) {
		this.chanle_code = chanle_code;
	}

	public String getOperate_time() {
		return operate_time;
	}

	public void setOperate_time(String operate_time) {
		this.operate_time = operate_time;
	}

	public String getSend_result() {
		return send_result;
	}

	public void setSend_result(String send_result) {
		this.send_result = send_result;
	}

	public int getRow_num() {
		return row_num;
	}

	public void setRow_num(int row_num) {
		this.row_num = row_num;
	}

	public String getFlag_user() {
		return flag_user;
	}

	public void setFlag_user(String flag_user) {
		this.flag_user = flag_user;
	}

	public String getFlag_user_name() {
		return flag_user_name;
	}

	public void setFlag_user_name(String flag_user_name) {
		this.flag_user_name = flag_user_name;
	}

	

	public String getReli_user() {
		return reli_user;
	}

	public void setReli_user(String reli_user) {
		this.reli_user = reli_user;
	}

	public String getReli_user_name() {
		return reli_user_name;
	}

	public void setReli_user_name(String reli_user_name) {
		this.reli_user_name = reli_user_name;
	}

	public String getReli_time() {
		return reli_time;
	}

	public void setReli_time(String reli_time) {
		this.reli_time = reli_time;
	}

	public String getOperate_user_id() {
		return operate_user_id;
	}

	public void setOperate_user_id(String operate_user_id) {
		this.operate_user_id = operate_user_id;
	}

	public String getOperate_user_name() {
		return operate_user_name;
	}

	public void setOperate_user_name(String operate_user_name) {
		this.operate_user_name = operate_user_name;
	}
	
}