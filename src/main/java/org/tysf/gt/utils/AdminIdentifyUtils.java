package org.tysf.gt.utils;

import org.tysf.gt.api.AddressApi;

public class AdminIdentifyUtils {
	//编写员工的工号
   public static String getAdminIdentify(int count){
	   String identify="0000"+(count+1);
	   System.out.println(identify);
	return AddressApi.SCHOOLCODE+identify.substring(identify.length()-4);
   }
}
