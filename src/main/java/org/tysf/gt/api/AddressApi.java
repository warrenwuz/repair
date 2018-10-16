package org.tysf.gt.api;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 外网地址
 * @author wuzhe
 *
 */
public class AddressApi {
  public static String Address=null;
  public static final String SCHOOLCODE="10119";//学校代码
  static{
	  Properties p = new Properties();
	  try {
		InputStream in=AddressApi.class.getResourceAsStream("/wx.properties");
		p.load(in);
		Address=p.getProperty("wx.address");
	} catch (FileNotFoundException e) {
		System.out.println(e.getMessage());
	} catch (IOException e) {
		System.out.println(e.getMessage());
	}
  }

}
