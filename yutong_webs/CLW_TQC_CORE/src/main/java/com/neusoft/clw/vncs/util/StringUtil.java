package com.neusoft.clw.vncs.util;

import java.io.IOException;
import java.util.Random;

import org.apache.commons.lang.StringUtils;

import sun.misc.BASE64Decoder;

import com.neusoft.clw.util.SUNBASE64;

public class StringUtil {
	    /**
	     * BASE64 编码
	     */
	    public static String encodeBASE64(String s) {
	        if (s == null) return null;
	        return (new sun.misc.BASE64Encoder()).encode(s.getBytes());
	    }

	    /**
	     * BASE64 解码
	     * @throws IOException
	     */
	    public static String decodeBASE64(String s) throws IOException {
	        if (s == null) return null;
	        BASE64Decoder decoder = new BASE64Decoder();
	        byte[] b = decoder.decodeBuffer(s);
	        return new String(b, "UTF-8");
	    }
	    
	    /**
	     * 判断字符串是否为空，包括空格的处理
	     * @param str 待验证字符串
	     * @return 结果
	     */
	    public static boolean isEmpty(String str) {
	        if (StringUtils.isEmpty(str)) {
	            return true;
	        }
	        if ("".equals(str.trim())) {
	            return true;
	        }
	        return false;
	    }

	    /**
	     * 取得随机数
	     * @param len 随机数长度
	     * @return 生成的随机数
	     */
	    public static String getRandomString(final int len) {
	        Random random = new Random();
	        /** 数字与字母字典 */
	        final char[] LETTER_AND_DIGIT = ("0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ")
	                .toCharArray();

	        if (len < 1) return "";
	        StringBuffer sb = new StringBuffer(len);
	        for (int i = 0; i < len; i++) {
	            sb.append(LETTER_AND_DIGIT[random.nextInt(LETTER_AND_DIGIT.length)]);
	        }
	        return sb.toString();
	    }

	    /**
	     * 获取设置日志模块sessionid的随机数串
	     * @return
	     */
	    public static String getLogRadomStr() {
//	        return DateUtil.now2string("yyyyMMddHHmmssms") + getRandomString(2);
	        return getRandomString(4);
	    }
	    
	    
	    /**
		 * 将空值转换为空字符串
		 * 
		 * @return String
		 */
		public static String nullToStr(String str) {
			return str == null || str.equals("null") ? "" : str.trim();
		}
		
		/**
		 * 将空值转换为空0
		 * 
		 * @return String
		 */
		public static String nullToZero(String str) {
			return str == null || str.equals("null") ? "0" : str.trim();
		}
		
		/**
		 * 将空值转换为空字符串
		 * 
		 * @return String
		 */
		public static String objToStr(Object str) {
			return str == null || str.equals("null") ? "0" : str.toString().trim();
		}
		
		/**
		 * 将空值转换为空字符串
		 * 
		 * @return String
		 */
		public static int objToZero(Object str) {        
			return str == null || str.equals("null") ? 0 : Integer.parseInt(str.toString().trim());
		}
		
		/**
		 * 将空值转换后，判断是否小于0
		 * 
		 * @return String
		 */
		public static float objToMaxZero(Object str) {
			float i=Float.parseFloat(str == null || str.equals("null") ? "0" : str.toString().trim());
			return i<0?0:i;
		}
		

		//查询天表空间名称
		public static String Sdate(String sdate){
			return sdate.substring(0, 4) + sdate.substring(5, 7)+ sdate.substring(8, 10);
		}
	    //查询月表空间名称
		public static String SdateM(String sdate){
			return sdate.substring(0, 4) + sdate.substring(5, 7);
		}

	    public static void main(String[] args) {
	        try {
//	            String bb=StringUtil.encodeBASE64("你好，中国！");
//	            String cc=StringUtil.decodeBASE64(bb);
	        	System.out.println(SUNBASE64.encodeString("xzce".getBytes()));
	            String aa = StringUtil.decodeBASE64("5omn6KGM5o6l5Y+j5Y+R55Sf5pyq5o2V6I635byC5bi4IG51bGzvvIzlr7zoh7TmjqXlj6Plip/og73lvILluLjpgIDlh7o=");
	            System.out.println("[" + aa + "]");
//	            System.out.println("cc=[" + cc + "]");
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }

}
