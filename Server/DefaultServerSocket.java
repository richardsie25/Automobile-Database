package Server;

import Adapter.*;
import java.io.*;
import java.net.*;

public class DefaultServerSocket extends Thread implements Debuggable,ServerAuto {

	////////// PROPERTIES //////////

	private int port;
	private ServerSocket server;

	////////// CONSTRUCTORS //////////

	public DefaultServerSocket(int port) {
		this.port = port;
		try {
			this.server = new ServerSocket(port);
		}
		catch (IOException e) {
			System.err.println("Could not listen on port " + port + " ... ");
			System.exit(1);
		}
	}

	////////// INSTANCE METHODS //////////

	@Override
	public void run() {
		Socket clientSocket = null;

		while (true) {
			//Accept client
			try {
				clientSocket = server.accept();
			}
			catch (IOException e) {
				System.err.println("Error establishing client connection ... ");
				System.exit(1);
			}

			//Handle client-server interaction
			if (DEBUG)
				System.out.println(clientSocket.getLocalAddress());
			new DefaultSocketClient(clientSocket).start();

		}
	}

	public void serve(int port) {
		DefaultServerSocket d = new DefaultServerSocket(port);
		d.run();
	}
	
	public static void main (String[] args) {
		ServerAuto s = new BuildAuto();
		s.serve(4444);
	}

}

/* == TEST RUN ==
 * This program demonstrates the server and client interaction by uploading a properties file and
 * configuring the Automobile

Connecting to host ... 
Connected to host, creating object streams ... 
Communicating with host ... 
Received server response ... 

Enter 1 to upload a new Automobile
Enter 2 to configure an Automobile
Enter 0 to terminate connection

Response to server: 
1
Sending 1 to server ... 

Received server response ... 
Upload a file to create an Automobile
Response to server: 
FordZTW.txt
Sending FordZTW.txt to server ... 

Received server response ... 
Automobile object successfully added to database
Press any key to return to main menu
Response to server: 
1
Sending 1 to server ... 

Received server response ... 

Enter 1 to upload a new Automobile
Enter 2 to configure an Automobile
Enter 0 to terminate connection

Response to server: 
1
Sending 1 to server ... 

Received server response ... 
Upload a file to create an Automobile
Response to server: 
BugattiVeyron.properties
Sending {BasePrice=2000000, NumberOfOptions1=4, NumberOfOptions3=2, NumberOfOptions2=2, NumberOfOptionSets=3, Option14a=Orange, Option14b=500, Option12a=Black, Option12b=3000, Option22a=Standard, Option22b=1000, Option32a=2000HP, Option32b=700000, Make=Bugatti, OptionSet1=Color, OptionSet2=Transmission, OptionSet3=Horsepower, Model=Veyron, Option13a=White, Option13b=1000, Option11a=Blue, Option21a=Auto, Option11b=5000, Option21b=0, Option31a=1500HP, Option31b=300000} to server ... 

Received server response ... 
Automobile object successfully added to database
Press any key to return to main menu
Response to server: 
2
Sending 2 to server ... 

Received server response ... 

Enter 1 to upload a new Automobile
Enter 2 to configure an Automobile
Enter 0 to terminate connection

Response to server: 
2
Sending 2 to server ... 

Received server response ... 
Select an Automobile from the following list to configure: 
1. Ford Focus Wagon ZTW
2. Bugatti Veyron

Response to server: 
1
Sending 1 to server ... 

Received server response ... 
Model.Automobile@6d7b4f4c
We can begin configuring Ford Focus Wagon ZTW
Model Name: Ford Focus Wagon ZTW
Base Price: 29999.00

Color: 
Option: Red
Price: 500.00 USD
Option: Green
Price: 1000.00 USD
Option: White
Price: -200.00 USD

Transmission: 
Option: Auto
Price: 0.00 USD
Option: Standard
Price: -800.00 USD

Brakes: 
Option: Standard
Price: 0.00 USD
Option: ABS
Price: 400.00 USD
Option: ABS with Advanced Trac
Price: 1600.00 USD

Side Impact Air Bags: 
Option: None
Price: 0.00 USD
Option: With
Price: 350.00 USD

Power Moonroof: 
Option: None
Price: 0.00 USD
Option: With
Price: 915.00 USD

Horsepower: 
Option: 500HP
Price: 700.00 USD
Option: 700HP
Price: 2000.00 USD

Choose Option for Color:
Red
Color costs: $500.0
Choose Option for Transmission:
Auto
Transmission costs: $0.0
Choose Option for Brakes:
ABS
Brakes costs: $400.0
Choose Option for Side Impact Air Bags:
None
Side Impact Air Bags costs: $0.0
Choose Option for Power Moonroof:
With
Power Moonroof costs: $915.0
Choose Option for Horsepower:
500HP
Horsepower costs: $700.0

Configuration completed successfully.
Car Model: Ford Focus Wagon ZTW
Your Choices:
Base price: 29999.00 USD
Color: Red, 500.00 USD
Transmission: Auto, 0.00 USD
Brakes: ABS, 400.00 USD
Side Impact Air Bags: None, 0.00 USD
Power Moonroof: With, 915.00 USD
Horsepower: 500HP, 700.00 USD
Your final price is: 32514.00 USD

Response to server: 
o
Sending o to server ... 

Received server response ... 

Enter 1 to upload a new Automobile
Enter 2 to configure an Automobile
Enter 0 to terminate connection

Response to server: 
2
Sending 2 to server ... 

Received server response ... 
Select an Automobile from the following list to configure: 
1. Ford Focus Wagon ZTW
2. Bugatti Veyron

Response to server: 
2
Sending 2 to server ... 

Received server response ... 
Model.Automobile@1f28c152
We can begin configuring Bugatti Veyron
Model Name: Bugatti Veyron
Base Price: 2000000.00

Color: 
Option: Blue
Price: 5000.00 USD
Option: Black
Price: 3000.00 USD
Option: White
Price: 1000.00 USD
Option: Orange
Price: 500.00 USD

Transmission: 
Option: Auto
Price: 0.00 USD
Option: Standard
Price: 1000.00 USD

Horsepower: 
Option: 1500HP
Price: 300000.00 USD
Option: 2000HP
Price: 700000.00 USD

Choose Option for Color:
Blue
Color costs: $5000.0
Choose Option for Transmission:
Standard
Transmission costs: $1000.0
Choose Option for Horsepower:
1500HP
Horsepower costs: $300000.0

Configuration completed successfully.
Car Model: Bugatti Veyron
Your Choices:
Base price: 2000000.00 USD
Color: Blue, 5000.00 USD
Transmission: Standard, 1000.00 USD
Horsepower: 1500HP, 300000.00 USD
Your final price is: 2306000.00 USD

Response to server: 
o
Sending o to server ... 

Received server response ... 

Enter 1 to upload a new Automobile
Enter 2 to configure an Automobile
Enter 0 to terminate connection

Response to server: 
0
Sending 0 to server ... 

Closing client input stream ... 
Closing client output stream ... 
Closing client console input stream ... 
Closing client socket ... 
*/
