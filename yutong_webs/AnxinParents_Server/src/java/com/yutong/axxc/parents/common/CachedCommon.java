/**
 * 
 */
package com.yutong.axxc.parents.common;

/**
 * @author wyg
 */
public class CachedCommon {

	/**
	 * XCPAPI缓存的名称.
	 */
	public static final int CACHED_MINTER_7D = 10080*60;
	public static final int CACHED_MINTER_1D = 1440*60;
	public static final int CACHED_MINTER_5H = 300*60;
	public static final int CACHED_MINTER_2H = 120*60;
	public static final int CACHED_MINTER_1H = 60*60;
	public static final int CACHED_MINTER_30M = 30*60;
	public static final int CACHED_MINTER_EVER = 0;

	public static final String CACHED_PRE = "xcpapi_";// 所有缓存key的前缀
	
	public static final String CACHED_TOKEN_KEY = "token_";//用户token
	public static final String CACHED_ACCOUNT = "account_";//用户
	public static final String CACHED_CHILD_LIST = "child_list_";//学生列表
	
	public static final String CACHED_CHILD = "child_";//某个学生
	public static final String CACHED_PUNCH = "child_punch_";//某个学生的打卡信息
	public static final String CACHED_AUTH = "has_auth_";//已授权用户

	public static final String CACHED_VEHICLE = "vehicle_";//车辆信息
	public static final String CACHED_ROUTE = "route_";//线路信息
	public static final String CACHED_STATION = "station_";//站点信息
	public static final String CACHED_RULE = "rule_";//推送规则
	public static final String CACHED_REMIND = "remind_";//提醒设置
	
	public static final String CACHED_FILE = "file_";//文件信息
	public static final String CACHED_CHILD_ZONE= "child_zone_";//学生的地区
	public static final String CACHED_WEATHER = "weather_";//天气信息
	public static final String CACHED_VERSION = "version";//版本信息
	public static final String CACHED_NEWS_LIST = "news_list_";//新闻列表
	public static final String CACHED_NEWS_ONE = "news_one_";//新闻详情


}
