package Client;
import Model.Automobile;
import java.io.*;
import java.util.*;
public class SelectCarOptions {
	private Scanner in = new Scanner(System.in);

	public SelectCarOptions() {

	}
	
	public void configureAuto(Object obj) {
		BufferedReader buff = new BufferedReader(new InputStreamReader(System.in));
		Automobile a = (Automobile) obj;
		String choice = null;
		System.out.println("We can begin configuring " + a.getName());
		a.print();
		for (int i = 0; i < a.getOpset().size(); i++) {
			System.out.println("Choose Option for " + a.getOpsetName(i) + ":");
			try {
				choice = buff.readLine();
			} catch (IOException e) {

			}
			a.setOptionChoice(a.getOpsetName(i), choice);
			System.out.println(a.getOpsetName(i) + " costs: $" + a.getOptionChoicePrice(a.getOpsetName(i)));
		}
	
		System.out.println("\nConfiguration completed successfully.");
		a.printChoice();
	}
	
	public boolean isAutomobile(Object obj) {
		boolean isAutomobile = false;

		try {
			Automobile a1 = (Automobile) obj;
			isAutomobile = true;
		}
		catch (ClassCastException e) {
			isAutomobile = false;
		}

		return isAutomobile;
	}

}
