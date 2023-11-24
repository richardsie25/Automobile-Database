package Client;

import java.io.*;
import java.util.*;

public class CarModelOptionsIO {

	////////// PROPERTIES //////////


	////////// CONSTRUCTORS //////////

	public CarModelOptionsIO() {

	}

	////////// INSTANCE METHODS //////////

	public Object loadPropsFile(String fname) {
		Properties props = new Properties();
		try {
			props.load(new FileInputStream(fname));
		}
		catch (FileNotFoundException e) {
			System.err.println("Error in file directory ... ");
			System.exit(1);
		}
		catch (IOException e) {
			System.err.println("Error reading file from directory ... ");
			System.exit(1);
		}

		return props;
	}

	public Object loadTextFile(String fname) {
		try {
			BufferedReader buff = new BufferedReader(new FileReader(fname));
			buff.close();
			return fname;
		}
		catch (FileNotFoundException e) {
			System.err.println("Error in file directory ... ");
			System.exit(1);
		}
		catch (IOException e) {
			System.err.println("Error reading file from directory ... ");
			System.exit(1);
		}
		return "";

	}

}
