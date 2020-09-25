import by.epam.inner.beans.ExtraTrial;
import by.epam.inner.beans.LightTrial;
import by.epam.inner.beans.StrongTrial;
import by.epam.inner.beans.Trial;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;



public class Runner {

	public static void main(String[] args) {
			
		//1. Create an ArrayList implementation for 9 entities (3 � for a superclass and 2 � for every subclass).
		List<Trial> trialList = new ArrayList<>(Arrays.asList(
				new Trial("Stepanenko", 70, 65),
				new Trial("Tretinnikov", 68, 25),
				new Trial("Metelskaya", 78, 85),
				new LightTrial("Alesenko", 94, 80),
				new LightTrial("Merkulovich", 42, 38),
				new StrongTrial("Shardin", 87, 82),
				new StrongTrial("Astryakov", 47, 36),
				new ExtraTrial("Yanochkin", 33, 75, 42),
				new ExtraTrial("Rusetskiy", 97, 85, 88)));


		/*
		//2. Print the collection content (one element per line).

		trialList.forEach(System.out::println);
		
		//3. Print the number of passed trials.
		
		long passedNumber = trialList.stream()
								.filter(Trial::isPassed)
								.count();
		System.out.println(Constants.MSG_PASSED + passedNumber);
		
		//4. Sort the collection by the sum of first and second marks.
		
		ToIntFunction <Trial> sumMarks = trial -> trial.getMark1() + trial.getMark2();
		
		trialList.sort(Comparator.comparingInt(sumMarks));
		
		//5. Print sums of first and second marks from the collection (one sum per line).
		System.out.println(Constants.MSG_SUM);
		
		trialList.stream()
					.mapToInt(sumMarks)
					.forEach(System.out::println);;
		
		
		//6. Create a new collection from unpassed trials, clear all marks and print this collection. Check whether all trials are failed (the result type is boolean). 
		
		List<Trial> unpassedList = trialList.stream()
								.filter(trial -> !trial.isPassed())
								.map(Trial::copy)
								.peek(Trial::clearMarks)
								.peek(System.out::println)
								.collect(Collectors.toList());
		
		System.out.println(Constants.ALL_FAILED + unpassedList.stream().allMatch(trial -> !trial.isPassed()));
		
		//7. Create a numeric array from sums of first and second marks of sorted collection (see item 4) and print it in the format: sum[0], sum[1], � , sum[sum.length - 1]

		int[] summArray = trialList.stream()
							.mapToInt(sumMarks)
							.toArray();
		
		System.out.println(Arrays.stream(summArray)
							.mapToObj(Integer::toString)
							.collect(Collectors.joining(",")));

	
		
		
		
		*/
		
		


	}

}
