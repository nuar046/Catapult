package org.utils.catapult;

public class readInput {
	String InputType;
	String ColumnType;
	String Description;
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
	
	public void setColumnType(String coltype)
	{
		ColumnType = coltype;
	}
	
	public void setDescription(String desc)
	{
		Description = desc;
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
	
	public String getColumnType()
	{
		return ColumnType;
	}
	
	public String getDescription()
	{
		return Description;
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