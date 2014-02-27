import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.TreeMap;

public class Main {

	public static TreeMap<Integer, Course> listCourse;
	public static TreeMap<String, Integer> courseMap;
	public static TreeMap<Integer, Lecturer> listLecturer;
	public static TreeMap<Integer, Room> listRoom;
	public static String[] listSessionType = { "ONEOFF", "WEEKLY",
			"FORTNIGHTLY" };
	static Scanner scan;
	static DBController myDB;

	public static void main(String[] args) throws SQLException {
		myDB = new DBController();
		listCourse = new TreeMap<Integer, Course>();
		courseMap = new TreeMap<String, Integer>();
		listLecturer = new TreeMap<Integer, Lecturer>();
		listRoom = new TreeMap<Integer, Room>();
		reloadDatabase();
		scan = new Scanner(System.in);
		String s = "";
		while (!s.equals("0")) {
			displayLectureMenu();
			System.out.print("Enter a option: ");
			s = scan.nextLine();
			switch (s) {
			case "1":
				importCSVMenu();
				break;
			case "2":
				addSessionMenu();
				break;
			case "3":
				reloadDatabase();
				break;
			case "0":
				System.out.println("Logging out......");
				break;
			}
		}
		System.out.println("Logged out successfully.");

	}

	public static ArrayList<String> readCSV(String filename) {
		ArrayList<String> lines = new ArrayList<String>();
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

	public static void importCourse(ArrayList<String> lines)
			throws SQLException {
		String[] temp;
		for (String s : lines) {
			temp = s.split(",");
			if (courseMap.get(temp[0]) == null) {
				System.out.println("Importing...... " + Arrays.toString(temp));
				myDB.updateQueryDB("INSERT INTO course (`courseCode`, `courseName`, `lecturerId`) VALUES (\""
						+ temp[0] + "\", \"" + temp[1] + "\", " + temp[2] + ")");
			} else {
				System.out.println("Duplicate Course Code. "
						+ Arrays.toString(temp));
			}

		}
		System.out.println("Import Completed.");
		reloadDatabase();
	}

	public static void displayLectureMenu() {
		System.out.println("\n----- Lecture Menu -----");
		System.out.println("1. Import Course CSV");
		System.out.println("2. Add session to course");
		System.out.println("3. Refresh database");
		System.out.println("\n0. Logout");
		System.out.println("------------------------");
	}

	public static void importCSVMenu() throws SQLException {
		System.out.println("\n----- Import CSV -----");
		System.out.println("Enter CSV file path: ");

		File f = new File(scan.nextLine());
		if (f.exists()) {
			System.out.println("Opening...... " + f.toString());
			importCourse(readCSV(f.toString()));
		} else {
			System.out.println("File does not exists.");
		}
	}

	public static void addSessionMenu() throws SQLException {

		System.out.println("\n----- Add Session To Course -----");

		listCourse();
		String courseCode = scan.nextLine().trim().toUpperCase();
		if (courseMap.get(courseCode) == null) {
			System.out.println("Invalid Course Code");
			return;
		}

		listLecturer();
		int lectureId;
		try {
			lectureId = Integer.parseInt(scan.nextLine().trim().toUpperCase());
			if (listLecturer.get(lectureId) == null) {
				System.out.println("Invalid Lecturer ID");
				return;
			}
		} catch (Exception e) {
			System.out.println("Invalid Input");
			return;
		}

		listRoom();
		int roomId;
		try {
			roomId = Integer.parseInt(scan.nextLine().trim().toUpperCase());
			if (listCourse.get(roomId) == null) {
				System.out.println("Invalid Room ID");
				return;
			}
		} catch (Exception e) {
			System.out.println("Invalid Input");
			return;
		}

		listSessionType();
		int sessionTypeId;
		String sessionType;
		int repeatFreq;
		try {
			sessionTypeId = Integer.parseInt(scan.nextLine().trim()
					.toUpperCase());
			if (sessionTypeId < 1 || sessionTypeId > 3) {
				System.out.println("Invalid Option");
				return;
			}
		} catch (Exception e) {
			System.out.println("Invalid Input");
			return;
		}

		sessionType = listSessionType[sessionTypeId - 1].toUpperCase();
		repeatFreq = 0;
		if (sessionTypeId != 1) {
			while (true) {
				System.out.print("Enter Repeat Frequency (" + sessionType
						+ "): ");
				try {
					repeatFreq = Integer.parseInt(scan.nextLine().trim()
							.toUpperCase());
				} catch (Exception e) {
					System.out.println("Invalid Input");
					return;
				}

				if (sessionTypeId == 2 && repeatFreq >= 1 && repeatFreq <= 52) {
					break;
				} else if (sessionTypeId == 3 && repeatFreq >= 1
						&& repeatFreq <= 26) {
					break;
				} else {
					System.out
							.println("Over Maximum Duration Assign (1 Year Max)");
				}
			}
		}

		SimpleDateFormat mySQLDateFormat = new SimpleDateFormat(
				"yyyy-MM-dd hh:mm:ss");
		System.out.print("Enter First Session Start Date(DDMMYYYY:HHMM eg. 18022014:1400): ");
		Calendar firstDate;
		try {
			String date = scan.nextLine().trim().toUpperCase();
			firstDate = Calendar.getInstance();
			firstDate.set(Integer.parseInt(date.subSequence(4, 8).toString()),
					Integer.parseInt(date.subSequence(2, 4).toString()) - 1,
					Integer.parseInt(date.subSequence(0, 2).toString()) - 1,
					Integer.parseInt(date.subSequence(9, 11).toString()),
					Integer.parseInt(date.subSequence(12, 13).toString()), 0);
		} catch (Exception e) {
			System.out.println("Invalid Input");
			return;
		}

		String[] durationA;
		try {
			System.out.print("Enter Session Duration(HH,MM eg. \"02,45\" for 2 hours 45 minutes): ");
			String duration = scan.nextLine().trim().toUpperCase();
			durationA = duration.split(",");
		} catch (Exception e) {
			System.out.println("Invalid Input");
			return;
		}

		int max_attendance;

		while (true) {
			System.out.print("Enter Max Attendances: ");
			try {
				max_attendance = Integer.parseInt(scan.nextLine().trim()
						.toUpperCase());
			} catch (Exception e) {
				System.out.println("Invalid Input");
				return;
			}

			if (listRoom.get(roomId).getCapacity() >= max_attendance) {
				break;
			} else {
				System.out.println("Max Attendances is "
						+ listRoom.get(roomId).getCapacity());
			}
		}

		listCompulsory();
		int compulsory;
		try {
			compulsory = Integer.parseInt(scan.nextLine().trim().toUpperCase());
		} catch (Exception e) {
			System.out.println("Invalid Input");
			return;
		}

		if (compulsory != 0 && compulsory != 1) {
			System.out.println("Invalid Option");
			return;
		}
		System.out.println("Creating sessions......");
		myDB.updateQueryDB("INSERT INTO session(`tutorId`,`sessionType`,`compulsory`,`courseId`)VALUES("
				+ lectureId
				+ ",\""
				+ sessionType
				+ "\","
				+ compulsory
				+ ","
				+ courseMap.get(courseCode) + ")");

		ResultSet temp = myDB
				.queryDB("SELECT * FROM session ORDER BY sessionId DESC LIMIT 1");
		int sessionId = -1;
		while (temp.next()) {
			sessionId = temp.getInt("sessionId");
		}

		for (int i = 0; i <= repeatFreq; i++) {
			String startTime = mySQLDateFormat.format(firstDate.getTime());
			firstDate.add(Calendar.HOUR_OF_DAY, Integer.parseInt(durationA[0]));
			firstDate.add(Calendar.MINUTE, Integer.parseInt(durationA[1]));
			String endTime = mySQLDateFormat.format(firstDate.getTime());
			myDB.updateQueryDB("INSERT INTO timeslot(`roomId`,`sessionId`,`capacity`,`startdate`,`enddate`)VALUES("
					+ roomId
					+ ","
					+ sessionId
					+ ","
					+ max_attendance
					+ ",\""
					+ startTime + "\",\"" + endTime + "\")");
			firstDate.add(Calendar.DAY_OF_MONTH, (sessionTypeId - 1) * 7);
			firstDate
					.add(Calendar.HOUR_OF_DAY, -Integer.parseInt(durationA[0]));
			firstDate.add(Calendar.MINUTE, -Integer.parseInt(durationA[1]));

		}
		System.out.println("Session created successfully.");
		reloadDatabase();
	}

	public static void listCompulsory() {
		System.out.println("\n1. Yes\n0. No");
		System.out.println("---------------------------------");
		System.out.print("Attendance Compulsory: ");
	}

	public static void listSessionType() {
		System.out.println("\n1. One Off\n2. Weekly\n3. Fortnightly");
		System.out.println("---------------------------------");
		System.out.print("Session Interval: ");
	}

	public static void listCourse() {
		System.out.println("\nCourse Code\tCourse Name\t\t\t\t\tLecturer ID");
		System.out.println("---------------------------------------------------------------------------");
		for (Entry<Integer, Course> entry : listCourse.entrySet()) {
			System.out.println(entry.getValue().displayCourse());
		}
		System.out.println("---------------------------------------------------------------------------");
		System.out.print("Enter Course Code To Add Session: ");
	}

	public static void listLecturer() {
		System.out.println("\nUser ID\t\tUsername\t\tName");
		System.out.println("---------------------------------------------------");
		for (Entry<Integer, Lecturer> entry : listLecturer.entrySet()) {
			System.out.println(entry.getValue().displayUserDetail());
		}
		System.out.println("---------------------------------------------------");
		System.out.print("Enter Lecturer ID To Assign: ");
	}

	public static void listRoom() {
		System.out.println("\nRoom ID\t\tLocation");
		System.out.println("---------------------------------");
		for (Entry<Integer, Room> entry : listRoom.entrySet()) {
			System.out.println(entry.getValue().displayRoom());
		}
		System.out.println("---------------------------------");
		System.out.print("Enter Room ID To Locate: ");
	}

	public static void loadCourseData(ResultSet rs) {
		System.out.println("Loading course data......");
		try {
			while (rs.next()) {
				int course_id = rs.getInt("courseId");
				int lecturer_id = rs.getInt("lecturerId");
				String course_code = rs.getString("courseCode");
				String course_name = rs.getString("courseName");
				listCourse.put(course_id, new Course(course_code, course_name,
						lecturer_id));
				courseMap.put(course_code, course_id);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void loadLecturerData(ResultSet rs) {
		System.out.println("Loading lecturer data......");
		try {
			while (rs.next()) {
				int userid = rs.getInt("userid");
				String username = rs.getString("username");
				String name = rs.getString("name");
				listLecturer.put(userid, new Lecturer(userid, username,
						USERTYPE.LECTURER, name));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void loadSessionData(ResultSet rs) {
		System.out.println("Loading session data......");
		try {
			while (rs.next()) {
				int session_id = rs.getInt("sessionId");
				int tutor_id = rs.getInt("tutorId");
				String session_type = rs.getString("sessionType");
				int compulsory = rs.getInt("compulsory");
				int course_id = rs.getInt("courseId");
				listCourse.get(course_id).add_Session(
						new Session(session_id, course_id, compulsory));
				listCourse.get(course_id).getSessionList().get(0)
						.setTutor_id(tutor_id);
				listCourse.get(course_id).getSessionList().get(0)
						.setSession_type(session_type);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void loadRoomData(ResultSet rs) {
		System.out.println("Loading room data......");
		try {
			while (rs.next()) {
				int room_id = rs.getInt("roomId");
				int room_capacity = rs.getInt("roomCapacity");
				String location = rs.getString("location");
				listRoom.put(room_id,
						new Room(room_id, room_capacity, location));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void reloadDatabase() throws SQLException {
		System.out.println("\nRefreshing database......");
		listCourse.clear();
		courseMap.clear();
		listLecturer.clear();
		loadCourseData(myDB.queryDB("SELECT * FROM course"));
		loadSessionData(myDB.queryDB("SELECT * FROM session"));
		loadLecturerData(myDB
				.queryDB("SELECT * FROM User WHERE usertype = \"lecturer\""));
		loadRoomData(myDB.queryDB("SELECT * FROM room"));
		System.out.println("Refresh completed");
	}

}
