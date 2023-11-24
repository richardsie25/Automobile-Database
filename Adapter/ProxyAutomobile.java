package Adapter;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;
import Exception.*;
import Model.*;
import Util.*;
import Scale.*;
import Server.DefaultServerSocket;
public abstract class ProxyAutomobile {
	private static LinkedHashMap<String,Automobile> a1 = new LinkedHashMap<String,Automobile>();

	public synchronized void buildAuto(Object fileName,String fileType) {
		FileIO f = new FileIO();
		Automobile a = new Automobile();
		if(fileType.equals("txt")) {
			try {
				a = f.readFile(fileName.toString());
				a1.put(a.getName(),a);
			}
			catch (AutoException e) {
				
			}
		}
		else if(fileType.equals("properties")) {
			buildPropAuto(fileName);
		}
	}
	
	public void buildPropAuto(Object obj) {
		FileIO f = new FileIO();
		Automobile a = new Automobile();
		a = f.propertiesToAuto(obj);
		a1.put(a.getName(),a);
	}
	public void printAuto(String modelName) {
		a1.get(modelName).print();
	}
	public void chooseOption(String modelName,String setName,String optionName) {
		a1.get(modelName).setOptionChoice(setName,optionName);
	}
	public void printChoice(String modelName) {
		a1.get(modelName).printChoice();
	}
	public void updateOptionSetName(String modelName,String optionSetName,String newName) {
		a1.get(modelName).updateOptionSet(optionSetName,new OptionSet(newName));
	}
	public void updateOptionPrice(String modelName, String optionSetName,String optionName, double newPrice) {
		a1.get(modelName).updateOption(optionSetName,optionName,new Option(optionName,newPrice));
	}
	public void updateOptionName(String modelName,String optionSetName,String optionName,String newName) {
		a1.get(modelName).updateOption(optionSetName,optionName,new Option(newName));
	}
	public void operateAuto(int operationno,String[] carInfo) {
		EditOptions e = new EditOptions(operationno,carInfo);
		e.start();
	}
	
	public void serve(int port) {
		DefaultServerSocket d = new DefaultServerSocket(port);
		d.run();
	}
	
	public String getAllModels() {
		Collection<Automobile> c = a1.values();
		Iterator<Automobile> i = c.iterator();
		String names = "";
		int j = 1;
		while(i.hasNext()) {
			names +=  Integer.toString(j) + ". " + i.next().getName() + "\n";
			j++;
		}
		return names;
	}
	public Automobile getAutomobile(int index) {
		Collection<Automobile> c = a1.values();
		Iterator<Automobile> i = c.iterator();
		Automobile a = new Automobile();
		int j = 1;
		a = i.next();
		while(i.hasNext()) {
			if(j == index)
				break;
			a = i.next();
			j++;
		}
		return a;
	}
	
}
