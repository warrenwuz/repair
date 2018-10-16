package org.tysf.gt.pojo;

import java.sql.Timestamp;

/**
 * 报修上传故障照片
 * @author wuzhe
 *
 */
public class SImage {
    private String simageid;//上传照片的编号(使用UUID)
    private String simagepath;//上传照片的保存路径
    private Timestamp timestamp;//上传照片的时间
    private String wpid;//微信报修单流水号(使用UUID)
    private int imagetype;//照片类型(0为报修照片1为完工照片)
	public SImage() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public SImage(String simageid, String simagepath, Timestamp timestamp) {
		super();
		this.simageid = simageid;
		this.simagepath = simagepath;
		this.timestamp = timestamp;
	}

	public SImage(String simageid, String simagepath, Timestamp timestamp, String wpid) {
		super();
		this.simageid = simageid;
		this.simagepath = simagepath;
		this.timestamp = timestamp;
		this.wpid = wpid;
	}
	
	public SImage(String simageid, String simagepath, Timestamp timestamp, String wpid, int imagetype) {
		super();
		this.simageid = simageid;
		this.simagepath = simagepath;
		this.timestamp = timestamp;
		this.wpid = wpid;
		this.imagetype = imagetype;
	}

	public String getSimageid() {
		return simageid;
	}
	public void setSimageid(String simageid) {
		this.simageid = simageid;
	}
	public String getSimagepath() {
		return simagepath;
	}
	public void setSimagepath(String simagepath) {
		this.simagepath = simagepath;
	}
	public Timestamp getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}
	public String getWpid() {
		return wpid;
	}
	public void setWpid(String wpid) {
		this.wpid = wpid;
	}
	
	public int getImagetype() {
		return imagetype;
	}

	public void setImagetype(int imagetype) {
		this.imagetype = imagetype;
	}

	@Override
	public String toString() {
		return "Simage [simageid=" + simageid + ", simagepath=" + simagepath + ", timestamp=" + timestamp + ", wpid="
				+ wpid + "]";
	}
    
}
