package bbdn.rest;

public abstract class RestConstants {

	public final static String AUTH_PATH = "/learn/api/public/v1/oauth2/token";

	public final static String DATASOURCE_PATH = "/learn/api/public/v1/dataSources";
	public final static String DATASOURCE_ID = "BBDN-DSK-JAVA";
	public final static String DATASOURCE_DESCRIPTION = "My REST Application's Datasource Key";

	public final static String TERM_PATH = "/learn/api/public/v1/terms";
	public final static String TERM_ID = "BBDN-TERM-SPRING";
	public final static String TERM_NAME = "Spring 2018 Term";
	public final static String TERM_DESCRIPTION = "This is the term for Spring 2018";

	public final static String COURSE_PATH = "/learn/api/public/v2/courses";
	public final static String COURSE_ID = "BIO-101";
	public final static String COURSE_NAME = "Introduction to Biology";
	public final static String COURSE_DESCRIPTION = "This course will serve as an introduction to the wonderful world of biology.";

	public final static String USER_PATH = "/learn/api/public/v1/users";
	public final static String USER_PROF_ID = "ProfessorPlum";
	public final static String USER_PROF_NAME = "professorplum";
	public final static String USER_PROF_PASS = "Bl@ckb0ard!";
	public final static String USER_PROF_GIVEN = "Professor";
	public final static String USER_PROF_FAMILY = "Plum";
	public final static String USER_PROF_EMAIL = "professor.plum@myschool.edu";
	public final static String USER_STUD_ID = "AwesomeStudent";
	public final static String USER_STUD_NAME = "awesomestudent";
	public final static String USER_STUD_PASS = "Bl@ckb0ard!";
	public final static String USER_STUD_GIVEN = "Awesome";
	public final static String USER_STUD_FAMILY = "Student";
	public final static String USER_STUD_EMAIL = "awesome.student@myschool.edu";

	public final static String MEMBERSHIP_BASE_PATH = "/learn/api/public/v1/courses";

	public final static String CONTENT_BASE_PATH = "/learn/api/public/v1/courses";
	public final static String CONTENT_FOLDER_TITLE = "My Workshop";
	public final static String CONTENT_FOLDER_DESCRIPTION = "A folder created during the REST Workshop.";
	public final static String CONTENT_FOLDER_HANDLER = "resource/x-bb-folder";

	public final static String FILE_UPLOAD_PATH = "/learn/api/public/v1/uploads";

	public final static String ASSIGNMENT_TITLE = "My Workshop Assignment";
	public final static String ASSIGNMENT_INSTRUCTIONS = "Complete this assignment";
	public final static String ASSIGNMENT_DESCRIPTION = "This the assignment created during the REST Workshop.";

	public final static String CALENDAR_PATH = "/learn/api/public/v1/calendars/items";
	public final static String CALENDAR_TITLE = "My Calendar Item";
	public final static String CALENDAR_DESCRIPTION = "The Calendar Item created as part of the REST workshop.";

	public final static String ANNOUNCEMENT_PATH = "/learn/api/public/v1/courses";
	public final static String ANNOUNCEMENT_TITLE = "My Announcement";
	public final static String ANNOUNCEMENT_BODY = "The announcement created as part of the REST workshop.";

	public final static String COLUMN_BASE_PATH = "/learn/api/public/v2/courses";

	public final static String ATTEMPT_BASE_PATH = "/learn/api/public/v2/courses";


}
