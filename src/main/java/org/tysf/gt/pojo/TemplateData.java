package org.tysf.gt.pojo;

public class TemplateData {
	private TemplateContent first;
	private TemplateContent keyword1;
	private TemplateContent keyword2;
	private TemplateContent keyword3;
	private TemplateContent  remark;
	public TemplateData(TemplateContent first, TemplateContent keyword1, TemplateContent keyword2,
			TemplateContent keyword3, TemplateContent remark) {
		super();
		this.first = first;
		this.keyword1 = keyword1;
		this.keyword2 = keyword2;
		this.keyword3 = keyword3;
		this.remark = remark;
	}
	
	public TemplateContent getFirst() {
		return first;
	}

	public void setFirst(TemplateContent first) {
		this.first = first;
	}

	public TemplateContent getKeyword1() {
		return keyword1;
	}

	public void setKeyword1(TemplateContent keyword1) {
		this.keyword1 = keyword1;
	}

	public TemplateContent getKeyword2() {
		return keyword2;
	}

	public void setKeyword2(TemplateContent keyword2) {
		this.keyword2 = keyword2;
	}

	public TemplateContent getKeyword3() {
		return keyword3;
	}

	public void setKeyword3(TemplateContent keyword3) {
		this.keyword3 = keyword3;
	}

	public TemplateContent getRemark() {
		return remark;
	}

	public void setRemark(TemplateContent remark) {
		this.remark = remark;
	}

	@Override
	public String toString() {
		return "TemplateData [first=" + first + ", keyword1=" + keyword1 + ", keyword2=" + keyword2 + ", keyword3="
				+ keyword3 + ", remark=" + remark + "]";
	}
	
	
}
