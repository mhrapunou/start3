
import by.epam.inner.beans.Trial;
import by.epam.inner.constants.Constants;
import by.epam.inner.factories.TrialFactory;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import org.apache.log4j.Logger;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.util.*;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;



public class Runner {
	public static final Logger LOGGER = Logger.getLogger("logfile");

	public static void main(String[] args) {
			
		//1. Create an ArrayList implementation for 9 entities (3 � for a superclass and 2 � for every subclass).
		List<Trial> trials = new ArrayList<>();

		try {
			Reader reader = new FileReader("src/in.json");
			Gson gson = new GsonBuilder().setPrettyPrinting().create();

			List<JsonObject> jsonObjects = gson.fromJson(reader, new TypeToken<List<JsonObject>>(){}.getType());
			jsonObjects
					.stream()
					.map(TrialFactory::getTrialFromFactory)
					.filter(Optional::isPresent)
					.map(Optional::get)
					.forEach(trials::add);

		} catch (FileNotFoundException e) {
			//antipattern!)
			e.printStackTrace();
		}



		//2. Print the collection content (one element per line).

		trials.forEach(LOGGER::info);
		
		//3. Print the number of passed trials.
		
		long passedNumber = trials.stream()
								.filter(Trial::isPassed)
								.count();
		LOGGER.info(Constants.MSG_PASSED + passedNumber);
		
		//4. Sort the collection by the sum of first and second marks.
		
		ToIntFunction <Trial> sumMarks = trial -> trial.getMark1() + trial.getMark2();
		
		trials.sort(Comparator.comparingInt(sumMarks));
		
		//5. Print sums of first and second marks from the collection (one sum per line).
		LOGGER.info(Constants.MSG_SUM);
		
		trials.stream()
					.mapToInt(sumMarks)
					.forEach(sum -> LOGGER.info(sum));
		
		
		//6. Create a new collection from unpassed trials, clear all marks and print this collection. Check whether all trials are failed (the result type is boolean). 
		
		List<Trial> unpassedList = trials.stream()
								.filter(trial -> !trial.isPassed())
								.map(Trial::copy)
								.peek(Trial::clearMarks)
								.peek(LOGGER::info)
								.collect(Collectors.toList());
		
		System.out.println(Constants.ALL_FAILED + unpassedList.stream().noneMatch(Trial::isPassed));
		
		//7. Create a numeric array from sums of first and second marks of sorted collection (see item 4) and print it in the format: sum[0], sum[1], � , sum[sum.length - 1]

		int[] summArray = trials.stream()
							.mapToInt(sumMarks)
							.toArray();
		
		LOGGER.info(Arrays.stream(summArray)
							.mapToObj(Integer::toString)
							.collect(Collectors.joining(",")));

	
		
		
		

		
		


	}

}
