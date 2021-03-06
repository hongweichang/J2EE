package com.neusoft.pushmessage.util;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.introspect.AnnotationIntrospectorPair;
import com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationIntrospector;
import com.neusoft.clw.exception.JacksonDeserializeException;
import com.neusoft.clw.exception.JacksonSerializeException;

/**
 * @author <a href="mailto:yi_liu@neusoft.com">yi_liu </a>
 * @version $Revision 1.0 $ 2013-3-25 下午4:12:58
 */
public class JacksonUtils {

//	private static Logger logger = LoggerFactory.getLogger(JacksonUtils.class);
	/**
	 * jackson映射器
	 */
	private static ObjectMapper iMapper = new ObjectMapper();

//	@SuppressWarnings("unused")
//	private static String[] DECODER_CHAR = new String[] { "\b", "\t", "\n",
//			"\f", "\r", "\"", "\\" };
//	@SuppressWarnings("unused")
//	private static String[] ENCODER_CHAR = new String[] { "\\b", "\\t", "\\n",
//			"\\f", "\\r", "\\\"", "\\\\" };

	/**
	 * 将给定的目标对象根据指定的条件参数转换成 {@code JSON} 格式的字符串。
	 * 
	 * @param obj
	 *            目标对象。
	 * @return 目标对象的 {@code JSON} 格式的字符串。
	 * @throws JsonGenerationException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	public static String toJson(Object obj) throws IOException {
		return iMapper.writeValueAsString(obj);
	}

	/**
	 * 将给定的 {@code JSON} 字符串转换成指定的类型对象。<strong>此方法通常用来转换普通的 {@code JavaBean}
	 * 对象。</strong>
	 * 
	 * @param json
	 *            给定的 {@code JSON} 字符串。
	 * @param clz
	 *            要转换的目标类。
	 * @return 给定的 {@code JSON} 字符串表示的指定的类型对象。
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	public static <T> T fromJson(String json, Class<T> clz) throws IOException {
		return iMapper.readValue(json, clz);
	}

	/**
	 * 将给定的目标对象根据指定的条件参数转换成 {@code JSON} 格式的字符串。
	 * 方法会将抛出的异常封装为服务器内部异常抛出，调用者不需处理转换过程中出现的异常
	 * 
	 * @param obj
	 *            目标对象。
	 * @return 目标对象的 {@code JSON} 格式的字符串。
	 */
	public static String toJsonRuntimeException(Object obj) {
		String str = new String();
		try {
			str = iMapper.writeValueAsString(obj);
		} catch (Exception e) {
			throw new JacksonSerializeException(e);
		}
		return str;
	}

	/**
	 * 将给定的 {@code JSON} 字符串转换成指定的类型对象。<strong>此方法通常用来转换普通的 {@code JavaBean}
	 * 对象。</strong> 方法会将抛出的异常封装为服务器内部异常抛出，调用者不需处理转换过程中出现的异常
	 * 
	 * @param json
	 *            给定的 {@code JSON} 字符串。
	 * @param clz
	 *            要转换的目标类。
	 * @return 给定的 {@code JSON} 字符串表示的指定的类型对象。
	 */
	public static <T> T fromJsonRuntimeException(String json, Class<T> clz) {
		T t = null;
		try {
			t = iMapper.readValue(json, clz);
		} catch (Exception e) {
			throw new JacksonDeserializeException(e);
		}
		return t;
	}

	/**
	 * 将json格式转换成map对象
	 * 
	 * @param jsonStr
	 *            给定的 {@code JSON} 字符串。
	 * @return objMap 转换成功的MAP对象
	 */
	public static Map<?, ?> jsonToMap(String json) throws IOException {
		return iMapper.readValue(json, Map.class);
	}

	/**
	 * 将json格式转换成map对象
	 * 
	 * @param jsonStr
	 *            给定的 {@code JSON} 字符串。
	 * @return objMap 转换成功的MAP对象
	 */
	public static Map<?, ?> jsonToMapRuntimeException(String json) {
		try {
			return iMapper.readValue(json, Map.class);
		} catch (IOException e) {
			throw new JacksonDeserializeException(e);
		}
	}

	/**
	 * 将json格式转换成list对象
	 * 
	 * @param jsonStr
	 *            给定的 {@code JSON} 字符串。
	 * @return objList 转换成功的LIST对象
	 */
	public static List<?> jsonToList(String json) throws IOException {
		return iMapper.readValue(json, List.class);
	}

	/**
	 * 将json格式转换成list对象
	 * 
	 * @param jsonStr
	 *            给定的 {@code JSON} 字符串。
	 * @return objList 转换成功的LIST对象
	 */
	public static List<?> jsonToListRuntimeException(String json) {
		try {
			return iMapper.readValue(json, List.class);
		} catch (IOException e) {
			throw new JacksonDeserializeException(e);
		}
	}

	/**
	 * 配置jackson映射器
	 */
	static {
		// 配置属性可见性为任意，包含private
		iMapper.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
		Logger logger = LoggerFactory.getLogger(JacksonUtils.class);
		// 如果是Debug模式则将输出字符串格式化
		if (logger.isErrorEnabled()) {
			// 输出格式化
			iMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
		}
		// 如果属性的值为null，则在输出时不显示该属性。
		iMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		// 配置标签过滤器，使用Jaxb兼容标签
		AnnotationIntrospector introspector = new JaxbAnnotationIntrospector(
				TypeFactory.defaultInstance());
		AnnotationIntrospector primary = new JacksonAnnotationIntrospector();
		AnnotationIntrospectorPair pair = new AnnotationIntrospectorPair(
				introspector, primary);
		iMapper.setAnnotationIntrospector(pair);
	}
}
