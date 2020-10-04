package by.epam.inner.constants;

import by.epam.inner.beans.Trial;
import org.apache.log4j.Logger;

public class Constants {

	public static final Logger LOGGER = Logger.getLogger("logfile");

	public final static String PACKAGE_NAME = Trial.class.getPackageName() + ".";
	
	public final static String MSG_PASSED = "\nThe number of passed trials = ";
	public final static String MSG_SUM = "\nThe summs of the first and the second marks are";
	public final static String ALL_FAILED = "\nwhether all trials are failed: ";
	public final static String CSV_DELIMITER = ";";
	public final static String ARRAY_DELIMITER = ",";
	public final static String EXCEPTION_DELIMITER = "=> ";

	public final static String DOT_REGEX = "\\.";

	public final static String CLASS_FIELD = "class";
	public final static String ARGS_FIELD = "args";
	public final static String ACCOUNT_FIELD = "account";
	public final static String MARK1_FIELD = "mark1";
	public final static String MARK2_FIELD = "mark2";
	public final static String MARK3_FIELD = "mark3";

	public static int JSON_FIELDS_NUMBER = 2;
	public static int TRIAL_FIELDS_NUMBER = 3;
	public static int EXTRA_TRIAL_FIELDS_NUMBER = 4;

	public final static String EXTRA_DATA_IN_JSONOBJECT = "Extra data in json object";
	public final static String WRONG_TRIAL = "Trial is wrong";
	public final static String WRONG_CLASS_NAME = "ClassName not match or empty";
	public static final String FILE_NOT_FOUND = "couldn't open the file: ";
	public static final String EMPTY_FILE_NAME = "File name is empty or null";
	public static final String WRONG_CLASS_FIELD_NAME = "Wrong name of class field ";
	public static final String WRONG_ARGS_FIELD_NAME = "Wrong name of args field ";

	public static final String EXTRA_TRIAL_NAME = "ExtraTrial";



	
}
