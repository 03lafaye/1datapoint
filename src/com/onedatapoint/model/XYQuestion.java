package com.onedatapoint.model;

public class XYQuestion extends Question {
	String XLabel;
	String YLabel;
	
	public XYQuestion(String id, String description, String XLabel, String YLabel) {
		super();
		this.setId(id);
		this.setDescription(description);
		this.setXLabel(XLabel);
		this.setYLabel(YLabel);
	}

	public String getXLabel() {
		return XLabel;
	}

	public void setXLabel(String xLabel) {
		XLabel = xLabel;
	}

	public String getYLabel() {
		return YLabel;
	}

	public void setYLabel(String yLabel) {
		YLabel = yLabel;
	}
	
	public String toString() {
		return this.getDescription() + ", " + this.getXLabel() + ", " + this.getYLabel();
	}
}
