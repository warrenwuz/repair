package org.tysf.gt.pojo;

public class TemplateContent {
	private String value;
	private String color;
	public TemplateContent() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public TemplateContent(String value) {
		super();
		this.value = value;
	}

	public TemplateContent(String value, String color) {
		super();
		this.value = value;
		this.color = color;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	
}
