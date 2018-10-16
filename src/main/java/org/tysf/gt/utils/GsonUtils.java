package org.tysf.gt.utils;

import com.google.gson.Gson;
//JSON格式化工具类
public class GsonUtils {
	private static final Gson gson=new Gson();
public static <E> E getObject(String json,Class<E> e){
	return gson.fromJson(json, e);
}
public static String getJsonStr(Object object){
	return gson.toJson(object);
}
}
