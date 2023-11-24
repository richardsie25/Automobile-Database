package Exception;
import java.io.*;
import java.sql.Timestamp;  
public class AutoException extends Exception{
	private int errorno;
	private String message;
	public AutoException() throws IOException{
		super();
		print();
		Timestamp t = new Timestamp(System.currentTimeMillis());
		FileWriter f = new FileWriter("Exception.txt",true);
		BufferedWriter w = new BufferedWriter(f);
		w.write("Error no: " + errorno);
		w.newLine();
		w.write("Error message: " + message);
		w.newLine();
		w.write(t.toString());
		w.newLine();
		w.close();
	}
	public AutoException(int errorno,String message) throws IOException {
		super();
		this.errorno = errorno;
		this.message = message;
		print();
		Timestamp t = new Timestamp(System.currentTimeMillis());
		FileWriter f = new FileWriter("Exception.txt",true);
		BufferedWriter w = new BufferedWriter(f);
		w.write("Error no: " + errorno);
		w.newLine();
		w.write("Error message: " + message);
		w.newLine();
		w.write(t.toString());
		w.newLine();
		w.close();
	}
	public int getErrorno() {
		return errorno;
	}
	public void setErrorno(int errorno) {
		this.errorno = errorno;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public void print() {
		System.out.println("Error num: " + errorno);
		System.out.println("Error message: " + message);
	}
	public String fix(int errorno) {
		FixAuto f = new FixAuto();
		String fileName;
		switch(errorno) {
			case 1:
				fileName = f.fixProblem(errorno);
				return fileName;
		}
		return "";
	}
}
