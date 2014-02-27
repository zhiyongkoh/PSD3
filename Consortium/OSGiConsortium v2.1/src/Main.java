import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	
	static ArrayList<Course> listCourse;

	public static void main(String[] args) {
		Scanner a = new Scanner(System.in);
		String s = "";
		while (!s.equals("0")){
			displayLectureMenu();
			System.out.print("Enter a option: ");
			s = a.nextLine();
		}
		

	}
	
	public static ArrayList<String> readCSV(String filename){
		ArrayList<String> lines =  new ArrayList<String>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(filename));
			String line;
			while ((line = br.readLine()) != null) {
				lines.add(line);
			}
			br.close();
			lines.remove(0);
			return lines;	
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void importCourse(ArrayList<String> lines){
		for(String s : lines){
			String[] temp = s.split(",");
			listCourse.add(new Course(temp[0], temp[1], Integer.parseInt(temp[2])));
		}
	}
	
	public static void displayLectureMenu(){
		System.out.println("----- Lecture Menu -----");
		System.out.println("1. Import Course CSV");
		System.out.println("2. Add session to course");
		System.out.println("3. Set session recur");
		System.out.println("\n0. Logout");
		System.out.println("------------------------");
	}
	

}
