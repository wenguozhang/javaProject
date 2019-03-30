package com.wgz.frame.utils;

import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class JsonUtil {
	/**
	 * 将json转化成map
	 * @param json
	 * @return
	 */
	public static Map<String, Object> jsonToMap(String json) {
		Map<String, Object> map = JSON.parseObject(json, new TypeReference<Map<String, Object>>() {});
		return map;
	}

	/**
	 * 将map转化成json
	 * @param map
	 * @return
	 */
	public static String mapToJson(Map<String, Object> map) {
		String json = JSON.toJSONString(map);
		return json;
	}
	
	/**
	 * 将map转化成json 保留map中null值转换为空串
	 */
	public static String mapToJson_2(Map<String, String> map) throws Exception {
		String jString = JSON.toJSONString(map, SerializerFeature.WriteMapNullValue,SerializerFeature.WriteNullStringAsEmpty);
		return jString;
	}

	/**
	 * Object转Json 
	 * @param obj
	 * @return
	 */
	public static String objectToJson(Object obj) {
		String json = JSON.toJSONString(obj);
		return json;
	}

	/**
	 * Json转Object 
	 * @param obj
	 * @return
	 */
	public static <T> T jsonToObject(Class<T> clazz, String json) {
		T t = JSON.parseObject(json, clazz);
		return t;
	}

//	public static void main(String[] args) {
//		ExTransBase base = new ExTransBase();
//
//		base.setVersion("1.0");
//		base.setSystemReturnTime(DateUtil.format(new Date(), DateUtil.YYYYMMDDHHMMSS));
//
//		String json = objectToJson(base);
//		System.out.println(json);

//		String json = "{\"sysRtnTm\":\"2017-09-14T18:41:26\",\"version\":\"1.0\"}";
//		ExTransBase model = jsonToObject(ExTransBase.class, json);
//		System.out.println(model.toString());
//	}
}
