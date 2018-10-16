package org.tysf.gt.pojo;

/**
 * 保修区域实体类
 * 
 * @author wuzhe
 *
 */
public class RepairArea {
	private int raid;
	private String raname;

	public RepairArea() {
		super();
	}

	public RepairArea(int raid, String raname) {
		super();
		this.raid = raid;
		this.raname = raname;
	}

	public int getRaid() {
		return raid;
	}

	public void setRaid(int raid) {
		this.raid = raid;
	}

	public String getRaname() {
		return raname;
	}

	public void setRaname(String raname) {
		this.raname = raname;
	}

	@Override
	public String toString() {
		return "RepairArea [raid=" + raid + ", raname=" + raname + "]";
	}

}
