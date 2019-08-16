package com.mipo.common.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.util.Map;

public class JsonUtil {

	public static String toJsonString(Object object) {
		String result = JSON
        .toJSONString(object, SerializerFeature.WriteNullStringAsEmpty, SerializerFeature.WriteDateUseDateFormat);
		result = result.replace("\r\n", "\\r\\n").replace("\t", "\\t");
		return result;
	}

	/**
	 * 转换JSON
	 *
	 * @param json
	 * @param cls
	 * @param <T>
	 * @return
	 * @throws Exception
	 */
	public static <T> T fromJson(String json, Class<T> cls) throws Exception {
		return JSON.parseObject(json, cls);
	}

	public static <T> T fromJson(JSONObject json, Class<T> cls) throws Exception {
		return JSONObject.toJavaObject(json, cls);
	}

	public static Map fromJson(String json) throws Exception {
		return JSON.parseObject(json, Map.class);
	}

	public static JSONObject parseObject(String json){

		JSONObject jsonObject = JSON.parseObject(json);
		return jsonObject;

	}


	/**
	 * 转化为JSON
	 *
	 * @param t
	 * @param <T>
	 * @return
	 * @throws Exception
	 */
	public static <T> String toJson(T t) throws Exception {
		return JSON.toJSONString(t, false);
	}

	public static <T> String toJson(T t, boolean isSerializer) throws Exception {
		if (isSerializer) {
			return JSON.toJSONString(t, SerializerFeature.BrowserCompatible);
		} else {
			return JSON.toJSONString(t);
		}
	}

//	/**
//	 * 通过IO获取JSON数据
//	 *
//	 * @param ins
//	 * @param cls
//	 * @param <T>
//	 * @return
//	 * @throws Exception
//	 */
//	public static <T> T loadJsonFromIO(InputStream ins, Class<T> cls) throws Exception {
//		ByteOutputStream bos = new ByteOutputStream();
//		if (ins != null) {
//			bos.write(ins);
//			bos.flush();
//		}
//		return fromJson(bos.toString(), cls);
//	}
}
