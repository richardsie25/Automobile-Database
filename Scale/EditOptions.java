package Scale;
import Adapter.*;

public class EditOptions extends ProxyAutomobile implements Runnable {
	private int operationno;
	private int threadno;
	private static int threadcount = 0;
	private Thread t;
	private String carInfo[];
	public EditOptions() {
		threadno = ++threadcount;
	}
	public EditOptions(int operationno,String carInfo[]) {
		this.operationno = operationno;
		this.carInfo = carInfo;
		t = new Thread(this);
		threadno = ++threadcount;
	}
	public void start() {
		t.start();
	}
	public void run() {
		switch(operationno) {
		case 1:
			System.out.printf("Starting thread no.%d, Operation: updateOptionSetName\n",threadno);
			updateOptionSetName(carInfo[0],carInfo[1],carInfo[2]);
			break;
		case 2:
			System.out.printf("Starting thread no.%d, Operation: updateOptionPrice\n",threadno);
			updateOptionPrice(carInfo[0],carInfo[1],carInfo[2],Double.parseDouble(carInfo[3]));
			break;
		case 3:
			System.out.printf("Starting thread no.%d, Operation: updateOptionName\n",threadno);
			updateOptionName(carInfo[0],carInfo[1],carInfo[2],carInfo[3]);
			break;
		}
		System.out.printf("Stopping thread no.%d\n",threadno);
	}
}
