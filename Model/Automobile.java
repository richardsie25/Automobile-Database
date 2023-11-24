package Model;
import java.io.*;
import java.util.*;
public class Automobile implements Serializable{
	private String name;
	private String make;
	private String model;
	private double basePrice;
	private ArrayList<OptionSet> opset;
	private ArrayList<Option> choice;
	public Automobile() {
		opset = new ArrayList<OptionSet>();
		choice = new ArrayList<Option>();
	}
	public Automobile(String name,double basePrice) {
		if(!name.equals("")) {
			String[] temp = name.split(" ");
			make = temp[0];
			for(int i = 1; i < temp.length; i++) {
				model = model + temp[i];
			}
		}
		else {
			make = "";
			model =  "";
		}
		this.name = name;
		this.basePrice = basePrice;
		opset = new ArrayList<OptionSet>();
		choice = new ArrayList<Option>();
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
		String[] temp = name.split(" ");
		make = temp[0];
		for(int i = 1; i < temp.length; i++) {
			model = model + temp[i];
		}
	}
	public String getMake() {
		return name;
	}
	public void setMake(String make) {
		this.make = make;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public double getBasePrice() {
		return basePrice;
	}
	public void setBasePrice(double basePrice) {
		this.basePrice = basePrice;
	}
	public ArrayList<OptionSet> getOpset() {
		return opset;
	}
	public String getOpsetName(int index) {
		return opset.get(index).getName();
	}
	public void setOpset(ArrayList<OptionSet> opset) {
		this.opset = opset;
	}
	public void setChoice() {
		for(int i = 0; i < opset.size(); i++) {
			Option o = new Option();
			choice.add(o);
		}
	}
	public synchronized String getOptionChoiceName(String setName) {
		int index = findOptionSetIndex(setName);
		if(index!=-1)
			return choice.get(index).getName();
		return "";
	}
	public synchronized double getOptionChoicePrice(String setName) {
		int index = findOptionSetIndex(setName);
		if(index!=-1)
			return choice.get(index).getPrice();
		return 0;
	}
	public synchronized void setOptionChoice(String setName, String optionName) {
		if(findOption(setName,optionName)) {
			int index = findOptionSetIndex(setName);
			if(index!=-1) {
				opset.get(index).setOptionChoice(optionName);
				setChoice();
				choice.set(index,opset.get(index).getOptionChoice());
			}
		}
	}
	public double getTotalPrice() {
		double extraPrice = 0;
		for(int i = 0; i < choice.size(); i++) {
			extraPrice = extraPrice + choice.get(i).getPrice();
		}
		return basePrice + extraPrice;
	}
	public synchronized void addOptionSet(String setName) {
		opset.add(new OptionSet(setName));
	}
	public synchronized void addOption(String setName, String optionName,double price) {
		int index = findOptionSetIndex(setName);
		if(index!=-1)
			opset.get(index).addOption(optionName,price);
	}
	public synchronized boolean findOptionSet(String setName) {
		for(int i = 0; i < opset.size(); i++) {
			if(opset.get(i).getName().equals(setName))
				return true;
		}
		return false;
	}
	public synchronized int findOptionSetIndex(String setName) {
		for(int i = 0; i < opset.size(); i++) {
			if(opset.get(i).getName().equals(setName))
				return i;
		}
		return -1;
	}
	public synchronized boolean findOption(String setName,String optionName) {
		int index = findOptionSetIndex(setName);
		if(index!=-1) {
			if(opset.get(index).findOption(optionName))
				return true;
		}
		return false;
	}
	public synchronized void deleteOptionSet(String setName) {
		int index = findOptionSetIndex(setName);
		if(index!=-1) 
			opset.remove(index);
	}
	public synchronized void deleteOption(String setName,String optionName) {
		int index = findOptionSetIndex(setName);
		if(index!=-1)
			opset.get(index).deleteOption(optionName);
	}
	public synchronized void updateOptionSet(String setName,OptionSet newOpt) {
		int index = findOptionSetIndex(setName);
		if(index!=-1) 
			opset.set(index,newOpt);
	}
	public synchronized void updateOptionSetName(String setName,String newSetName) {
		int index = findOptionSetIndex(setName);
		if(index!=-1) 
			opset.get(index).setName(newSetName);
	}
	public synchronized void updateOption(String setName,String optionName,Option newOpt) {
		int index = findOptionSetIndex(setName);
		if(index!=-1) 
			opset.get(index).updateOption(optionName, newOpt);
	}
	public void print() {
		System.out.printf("Model Name: %s\n",name);
		System.out.printf("Base Price: %.2f\n\n",basePrice);
		for(int i = 0; i < opset.size(); i++) {
			opset.get(i).print();
			System.out.printf("\n");
		}
	}
	public void printChoice() {
		System.out.printf("Car Model: %s\n",name);
		System.out.printf("Your Choices:\n");
		System.out.printf("Base price: %.2f USD\n",basePrice);
		for(int i = 0; i < opset.size(); i++) {
			System.out.printf("%s: %s, %.2f USD\n",opset.get(i).getName(),choice.get(i).getName(),choice.get(i).getPrice());
		}
		System.out.printf("Your final price is: %.2f USD\n\n",getTotalPrice());
	}
}
