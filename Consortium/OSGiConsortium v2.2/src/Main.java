import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.TreeMap;

public class Main {
	
	static TreeMap<String,Course> listCourse;
	static Scanner scan;
	public static void main(String[] args) {
		listCourse = new TreeMap<String, Course>();
		scan = new Scanner(System.in);
		String s = "";
		while (!s.equals("0")){
			displayLectureMenu();
			System.out.print("Enter a option: ");
			s = scan.nextLine();
			switch(s){
				case "1":
					importCSVMenu();
					break;
				case "2":
					addSessionMenu();
					break;				
			}
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
		String[] temp;
		for(String s : lines){
			temp = s.split(",");
			if (listCourse.get(temp[0])== null){
				System.out.println("Importing...... "+Arrays.toString(temp));
				listCourse.put(temp[0], new Course(temp[0], temp[1], Integer.parseInt(temp[2])));
			}else{
				System.out.println("Duplicate course. "+Arrays.toString(temp));
			}
			
		}
		System.out.println("Import Completed.");
	}
	
	public static void displayLectureMenu(){
		System.out.println("\n----- Lecture Menu -----");
		System.out.println("1. Import Course CSV");
		System.out.println("2. Add session to course");
		System.out.println("3. Set session recur");
		System.out.println("\n0. Logout");
		System.out.println("------------------------");
	}
	
	public static void importCSVMenu(){
		System.out.println("\n----- Import CSV -----");
		System.out.println("Enter CSV file path: ");
		
		File f = new File(scan.nextLine());
		if (f.exists()){
			System.out.println("Opening...... "+f.toString());
			importCourse(readCSV(f.toString()));
		}else{
			System.out.println("File does not exists.");
		}
	}
	
	public static void addSessionMenu(){
		listCourse();
	}
	
	public static void listCourse(){
		System.out.println("\n---------- Courses ----------");
		System.out.println("Course Code\tCourse Name\t\t\t\t\tLecturer ID\n");
		for (Entry<String, Course> entry : listCourse.entrySet()){
			System.out.println(entry.getValue().displayCourse());
		}
		System.out.println("Enter a course code: ");
	}
	

}
