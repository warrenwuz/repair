package org.tysf.gt.pojo;

/**
 * 公寓实体类
 * 
 * @author wuzhe
 *
 */
public class Flat {
	private int fid;
	private String fname;

	public Flat() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Flat(int fid) {
		super();
		this.fid = fid;
		
	}
	public Flat(int fid, String fname) {
		super();
		this.fid = fid;
		this.fname = fname;
	}

	public int getFid() {
		return fid;
	}

	public void setFid(int fid) {
		this.fid = fid;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	@Override
	public String toString() {
		return "Flat [fid=" + fid + ", fname=" + fname + "]";
	}

}
