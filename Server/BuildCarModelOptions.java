package Server;
import java.util.Properties;

import Adapter.*;
public class BuildCarModelOptions extends ProxyAutomobile{
	private static final int WAITING = 0;
	private static final int REQUEST_BUILD_AUTO = 1;
	private static final int REQUEST_CONFIGURE_AUTO = 2;
	private int state = WAITING;

	public BuildCarModelOptions() {
		
	}

	public Object processRequest(Object obj) {
		Object toClient = null;
		if (state == REQUEST_BUILD_AUTO) {
			if(obj instanceof String)
				buildAuto(obj,"txt");
			if(obj instanceof Properties)
				buildAuto(obj,"properties");
			toClient = "Automobile object successfully added to database\n"
					+ "Press any key to return to main menu";
		}
		else if (state == REQUEST_CONFIGURE_AUTO) {
			toClient = getAutomobile(Integer.parseInt(obj.toString()));
		}
		else {

		}
		this.state = WAITING;	
		return toClient;
	}

	public String setRequest(int i) {
		String output = null;
		
		if (i == 1) {
			this.state = REQUEST_BUILD_AUTO;
			output = "Upload a file to create an Automobile";
		}
		else if (i == 2) {
			this.state = REQUEST_CONFIGURE_AUTO;
			output = "Select an Automobile from the following list to configure: \n" + 
			super.getAllModels();
		}
		else {
			output = "Invalid request";
		}
		return output;
	}
}
