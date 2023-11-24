package Model;
import java.io.*;
public class Option implements Serializable{
		private String name;
		private double price;
		public Option() {
		}
		public Option(String name, double price) {
			this.name = name;
			this.price = price;
		}
		public Option(String name) {
			this.name = name;
		}
		protected String getName() {
			return name;
		}
		protected void setName(String name) {
			this.name = name;
		}
		protected double getPrice() {
			return price;
		}
		protected void setPrice(double price) {
			this.price = price;
		}
		protected void print() {
			System.out.printf("Option: %s\n",name);
			System.out.printf("Price: %.2f USD\n",price);
		}
}