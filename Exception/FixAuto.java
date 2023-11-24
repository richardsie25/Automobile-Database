package Exception;
import java.util.*;
public class FixAuto {
	public static Scanner s = new Scanner(System.in);
	public String fixProblem(int errorno) {
		switch(errorno) {
		case 1:
			System.out.printf("Invalid File, please re-enter file name: ");
			String fileName = s.nextLine();
			return fileName;
		}
		return "";
	}
}
