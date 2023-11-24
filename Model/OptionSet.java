package Model;
import java.io.*;
import java.util.*;
public class OptionSet implements Serializable{
	private String name;
	private ArrayList<Option> opt;
	private Option choice;
	public OptionSet() {
		opt = new ArrayList<Option>();
		choice = new Option();
	}
	public OptionSet(String name) {
		this.name = name;
		opt = new ArrayList<Option>();
		choice = new Option();
	}
	protected String getName() {
		return name;
	}
	protected void setName(String name) {
		this.name = name;
	}
	protected ArrayList<Option> getOpt(){
		return opt;
	}
	protected void setOpt(ArrayList<Option> opt) {
		this.opt = opt;
	}
	protected Option getOptionChoice() {
		if(choice==null)
			return null;
		return choice;
	}
	protected void setOptionChoice(String choice) {
		int index = findOptionIndex(choice);
		if(index!=-1)
			this.choice = opt.get(index);
	}
	protected void addOption(String name, double price) {
		opt.add(new Option(name,price));
	}
	protected boolean findOption(String name) {
		for(int i = 0; i < opt.size(); i++){
			if(opt.get(i).getName().equals(name))
				return true;
		}
		return false;
	}
	protected int findOptionIndex(String name) {
		for(int i = 0; i < opt.size(); i++) {
			if(opt.get(i).getName().equals(name))
				return i;
		}
		return -1;
	}
	protected void deleteOption(String name) {
		int index = findOptionIndex(name);
		if(index!=-1)
			opt.remove(index);
	}
	protected void updateOption(String name, Option newOpt) {
		int index = findOptionIndex(name);
		System.out.println("Index of Option: "+index);
		if(index!=-1)
			opt.set(index,newOpt);
	}
	protected void print() {
		System.out.printf("%s: \n",name);
		for(int i = 0; i < opt.size(); i++) {
			opt.get(i).print();
		}
	}
}