package org.utils.catapult;

public class readInput {
	String InputType;
	boolean canEdit;
	String label;
	boolean mandatory;
	
	public readInput() {
		InputType = "input";
		canEdit = false;
		label = "label";
		mandatory = false;
	}
	
	public void setInputType(String inputtype)
	{
		InputType = inputtype;
	}
	
	public void setCanEdit(Boolean canedit)
	{
		canEdit = canedit;
	}
	
	public void setLabel(String lbl)
	{
		label = lbl;
	}
	
	public void setMandatory(Boolean md)
	{
		mandatory = md;
	}
	
	public String getInputType()
	{
		return InputType;
	}
	
	public Boolean getCanEdit()
	{
		return canEdit;
	}	
	
	public String getLabel()
	{
		return label;
	}

	public Boolean getMandatory()
	{
		return mandatory;
	}
}