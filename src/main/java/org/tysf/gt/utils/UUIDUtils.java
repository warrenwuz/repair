package org.tysf.gt.utils;

import java.util.UUID;
/**
 * 生成UUID的工具类
 * @author wuzhe
 *
 */
public class UUIDUtils {
   public static String getUUID(){
	   String uuid=UUID.randomUUID().toString().replaceAll("-", "");
	   return uuid;
   }
}
