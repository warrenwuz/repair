package org.tysf.gt.pojo;

public class Administrator {
	private String adminid;
	private String adminname;
	private String admintel;
	private String adminpassword;
	private int authorization;

	public Administrator() {
		super();
		// TODO Auto-generated constructor stub
	}
  
	public Administrator(String adminid) {
		super();
		this.adminid = adminid;
	}

	public Administrator(String adminid, String adminname, String admintel, String adminpassword,
			int authorization) {
		super();
		this.adminid = adminid;
		this.adminname = adminname;
		this.admintel = admintel;
		this.adminpassword = adminpassword;
		this.authorization = authorization;
	}

	public String getAdminid() {
		return adminid;
	}

	public void setAdminid(String adminid) {
		this.adminid = adminid;
	}

	public String getAdminname() {
		return adminname;
	}

	public void setAdminname(String adminname) {
		this.adminname = adminname;
	}

	public String getAdmintel() {
		return admintel;
	}

	public void setAdmintel(String admintel) {
		this.admintel = admintel;
	}

	public String getAdminpassword() {
		return adminpassword;
	}

	public void setAdminpassword(String adminpassword) {
		this.adminpassword = adminpassword;
	}

	public int getAuthorization() {
		return authorization;
	}

	public void setAuthorization(int authorization) {
		this.authorization = authorization;
	}

	@Override
	public String toString() {
		return "Administrator [adminid=" + adminid + ", adminname=" + adminname + ", admintel=" + admintel
				+ ", adminpassword=" + adminpassword + ", authorization=" + authorization + "]";
	}

}
