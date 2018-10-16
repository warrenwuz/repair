package org.tysf.gt.pojo;

/**
 * 学生信息
 * 
 * @author wuzhe
 *
 */
public class StudentMsg {
	private String stuid;
	private String sname;
	private Flat flat;
	private String dormitory;// 学生宿舍号
	private String tel;// 学生电话
	private String password;//学生密码
    private String openid;//微信公众号对于用户的唯一标识
	public StudentMsg() {
		super();
		// TODO Auto-generated constructor stub
	}

	public StudentMsg(String stuid, String sname,String dormitory, String tel, String password) {
		super();
		this.stuid = stuid;
		this.sname = sname;
		this.dormitory = dormitory;
		this.tel = tel;
		this.password = password;
	}
   
	public StudentMsg(String stuid) {
		this.stuid = stuid;
	}

	public String getStuid() {
		return stuid;
	}

	public void setStuid(String stuid) {
		this.stuid = stuid;
	}

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public String getDormitory() {
		return dormitory;
	}

	public void setDormitory(String dormitory) {
		this.dormitory = dormitory;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}
    
	public Flat getFlat() {
		return flat;
	}

	public void setFlat(Flat flat) {
		this.flat = flat;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
     
	

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	@Override
	public String toString() {
		return "StudentMsg [stuid=" + stuid + ", sname=" + sname + ", flat=" + flat + ", dormitory=" + dormitory
				+ ", tel=" + tel + ", password=" + password + ", openid=" + openid + "]";
	}


}
