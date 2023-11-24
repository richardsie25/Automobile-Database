package Util;
import Model.*;
import Exception.*;
import java.io.*;
import java.util.*;

public class FileIO implements Serializable {
	public Automobile readFile (String fileName) throws AutoException{
		Automobile a = new Automobile();
		int i = 0, b = 0;
		try {
			FileReader file = null;
			BufferedReader buff = null;
			double basePrice = 0;
			double price = 0;
			boolean eof = false, firstLine = false, secondLine = false,problemFixed = false;;
	
			//Opening file
			do {
				try {
					try {
						file = new FileReader(fileName);
						buff = new BufferedReader(file);
						problemFixed = true;
					} 
					catch(FileNotFoundException f) {
						throw new AutoException(1,"File Not Found");
					}
				} 
				catch (AutoException e) {
					fileName = e.fix(1);
				}
			} while(problemFixed == false);
			
			while(!eof) {
				String line = buff.readLine();
				if(line==null)
					eof = true;
				else if (!firstLine) {
					String []token = line.split(":");
					if(token.length==1)
						throw new AutoException(2,"Missing Model Name");
					a.setName(token[1]);
					firstLine = true;
				}
				else if (!secondLine) {
					String [] token = line.split(":");
					if(token.length==1)
						throw new AutoException(3,"Missing Base Price");
					basePrice = Double.parseDouble(token[1]);
					a.setBasePrice(basePrice);
					secondLine = true;
				}
				else if(b%2==0) {
					String[]token = line.split(":");
					a.addOptionSet(token[0]);
					b++;
				}
				else {
					String []token = line.split(",");
					for(int j = 0; j < token.length; j = j+2) {
						if(token[j].equals(""))
							throw new AutoException(4,"Missing Option Name");
						if(token[j+1].equals(""))
							throw new AutoException(5,"Missing Option Price");
						price = Double.parseDouble(token[j+1]);
						a.addOption(a.getOpsetName(i),token[j],price);
					}
					i++;
					b++;
				}
			}
			buff.close();
			file.close();
		}
		catch(IOException e) {
		}
		return a;
	}
	public void serializeAuto(Automobile a,String fileName) {
		try {
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName));
			out.writeObject(a);
			out.close();
		}
		catch (Exception e) {
		}
	}
	public Automobile deserializeAuto(String fileName) {
		Automobile temp = new Automobile();
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName));
			temp = (Automobile) in.readObject();
			in.close();
		}
		catch (Exception e) {
		}
		return temp;
	}
	public Automobile propertiesToAuto(Object obj) {
		Properties p = (Properties) obj;
		String name = p.getProperty("Make") +" "+ p.getProperty("Model");
		double basePrice = Double.parseDouble(p.getProperty("BasePrice"));
		String optionName;
		String optionSetName;
		double price;
		Automobile a = new Automobile(name,basePrice);
		for (int i = 0; i < Integer.parseInt(p.getProperty("NumberOfOptionSets")); i++) {
			optionSetName = p.getProperty("OptionSet" + Integer.toString(i+1));
			a.addOptionSet(optionSetName);
			for(int j = 0; j < Integer.parseInt(p.getProperty("NumberOfOptions"+Integer.toString(i+1))); j++){
				optionName = p.getProperty("Option"+Integer.toString(10*(i+1)+(j+1))+"a");
				price = Double.parseDouble(p.getProperty(("Option"+Integer.toString(10*(i+1)+(j+1))+"b")));
				a.addOption(optionSetName, optionName, price);
			}
		}
		return a;
	}
}
