package Adapter;

public interface UpdateAuto {
	public void updateOptionSetName(String modelName,String optionSetName,String newName);
	public void updateOptionPrice(String modelName,String optionSetName,String optionName,double newPrice);
	public void updateOptionName(String modelName,String optionSetName,String optionName,String newName);
}
