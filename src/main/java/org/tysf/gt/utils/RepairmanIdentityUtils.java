package org.tysf.gt.utils;

import org.tysf.gt.api.AddressApi;

public class RepairmanIdentityUtils {
	//编写员工的工号
	   public static String getRepairmaIdentify(int count){
		   String identify="00000"+(count+1);
		   System.out.println(identify);
		return AddressApi.SCHOOLCODE+identify.substring(identify.length()-5);
	   }
	   public static void main(String[] args) {
		 
		   System.out.println(  getRepairmaIdentify(5));
	}
}
