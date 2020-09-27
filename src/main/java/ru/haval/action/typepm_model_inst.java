package ru.haval.action;

public class typepm_model_inst
{
	private String type_pm;
	
	public typepm_model_inst()
	{
		
	}
	
	public typepm_model_inst(String type_pm)
	{
		this.type_pm = type_pm;
	}
	
	 public String gettypepm() {
	        return type_pm;
	    }
	 
	    public void settypepm(String type_pm) {
	        this.type_pm = type_pm;
	    }
	    
	    @Override
	    public String toString()  {
	        return this.type_pm;
	    }
}