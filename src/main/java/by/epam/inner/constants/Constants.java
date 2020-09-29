package by.epam.inner.constants;

import by.epam.inner.beans.Trial;
import com.google.gson.Gson;
import org.apache.log4j.Logger;

public class Constants {

	public static final Logger LOGGER = Logger.getLogger("logfile");
	public static final Gson GSON = new Gson();

	public final static String FILE_NAME = "src/in.json";
	public final static String PACKAGE_NAME = Trial.class.getPackageName() + ".";
	
	public final static String MSG_PASSED = "\nThe number of passed trials = ";
	public final static String MSG_SUM = "\nThe summs of the first and the second marks are";
	public final static String ALL_FAILED = "\nwhether all trials are failed: ";
	public final static String CSV_DELIMITER = ";";
	public final static String ARRAY_DELIMITER = ",";
	public final static String EXCEPTION_DELIMITER = "=> ";

	public final static String CLASS_FIELD = "class";
	public final static String ARGS_FIELD = "args";

	public static int JSON_FIELDS_NUMBER = 2;
	public static int TRIAL_FIELDS_NUMBER = 3;
	public static int EXTRA_TRIAL_FIELDS_NUMBER = 4;

	public final static String EXTRA_DATA_IN_JSONOBJECT = "Extra data in json object";
	public final static String INCORRECT_DATA_IN_ARGS = "Wrong number of fields in args";
	public final static String EMPTY_DATA_IN_JSONOBJECT = "Vital data for creating Trial entity isn't present in JSON object";
	public final static String WRONG_JSON_SYNTAX = "Type of JSON data mismatches the type of Trial data";
	public final static String EMPTY_NAME = "Empty name ";
	public final static String WRONG_MARK1 = "Mark1 is wrong ";
	public final static String WRONG_MARK2 = "Mark2 is wrong";
	public final static String WRONG_MARK3 = "Mark3 is wrong";
	public final static String WRONG_TRIAL = "Trial is wrong";
	public final static String WRONG_CLASS_NAME = "ClassName not match or empty";
	public static final String FILE_NOT_FOUND = "couldn't open the file: ";
	public static final String EMPTY_FILE_NAME = "File name is empty or null";


	
}
