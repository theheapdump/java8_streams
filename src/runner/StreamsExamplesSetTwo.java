package runner;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

import model.Country;
import model.People;

public class StreamsExamplesSetTwo {

	public static void demoExampleSetTwo() {
		collectAsList();
		collectAsSet();
		joinElements();
		listToStreamToArray();
		listToMap();
		List<People> tenPeople = getTenPeople();
		downStreamCollector(tenPeople);
		downnStreamCollectorOperations(tenPeople);
		collectorSumOperations(tenPeople);
		downnStreamCollectorSumOperation(tenPeople);
		entryComparator(tenPeople);
	}

	private static void entryComparator(List<People> tenPeople) {
		Map<String, Integer> stringLongMap = tenPeople.stream().collect(Collectors
				.groupingBy(people -> people.getCountry().getName(), Collectors.summingInt(people -> people.getAge())));
		Entry<String, Integer> maxAgeSum = stringLongMap.entrySet().stream().max(Entry.comparingByValue())
				.orElseThrow();
		System.out.println("Max Age Sum | entryComparator : " + maxAgeSum);

	}

	private static void downnStreamCollectorSumOperation(List<People> tenPeople) {
		Map<String, Integer> stringLongMap = tenPeople.stream().collect(Collectors
				.groupingBy(people -> people.getCountry().getName(), Collectors.summingInt(people -> people.getAge())));
		System.out.println(" Map of sum of ages of people is | downnStreamCollectorSumOperation = " + stringLongMap);
	}

	private static void collectorSumOperations(List<People> tenPeople) {
		Map<String, List<People>> stringListMap = tenPeople.stream()
				.collect(Collectors.groupingBy(people -> people.getCountry().getName()));
		stringListMap.entrySet().stream().forEach(entry -> {
			int sum = stringListMap.get(entry.getKey()).stream()
					.collect(Collectors.summingInt(people -> people.getAge()));
			System.out.println("collectorSumOperations | SUM OF AGE for country |  " + entry.getKey() + " = " + sum);
		});

		/*
		 * Another way
		 */
		System.out.println("################ ANOTHER WAY ##############");
		stringListMap.entrySet().stream().forEach(entry -> {
			int sum = stringListMap.get(entry.getKey()).stream().mapToInt(people -> people.getAge()).sum();
			System.out.println("collectorSumOperations | SUM OF AGE for country |  " + entry.getKey() + " = " + sum);
		});
	}

	private static void downnStreamCollectorOperations(List<People> tenPeople) {
		Map<String, Long> stringLongMap = tenPeople.stream()
				.collect(Collectors.groupingBy(people -> people.getCountry().getName(), Collectors.counting()));
		Entry<String, Long> maxPeopleCountry = stringLongMap.entrySet().stream()
				.max(Comparator.comparing(entry -> entry.getValue())).orElseThrow();
		System.out.println("Max People Country  | downnStreamCollectorOperations  " + maxPeopleCountry);

		Entry<String, Long> minPeopleCountry = stringLongMap.entrySet().stream()
				.min(Comparator.comparing(entry -> entry.getValue())).orElseThrow();
		System.out.println("Min People Country  | downnStreamCollectorOperations  " + minPeopleCountry);

		double avgerageNumberPeopleFromMap = stringLongMap.entrySet().stream().mapToDouble(entry -> entry.getValue())
				.average().orElseThrow();
		System.out.println("avgerageNumberPeopleFromMap People Country  | downnStreamCollectorOperations  "
				+ avgerageNumberPeopleFromMap);
	}

	private static void downStreamCollector(List<People> tenPeople) {
		Map<String, Long> stringLongMap = tenPeople.stream()
				.collect(Collectors.groupingBy(people -> people.getCountry().getName(), Collectors.counting()));
		System.out.println("Country Str _ Count  Ppl | downStreamCollector " + stringLongMap);
	}

	private static void listToMap() {
		Map<Country, List<People>> pplCtryGrp = getTenPeople().stream()
				.collect(Collectors.groupingBy(people -> people.getCountry()));
		System.out.println("Country _  List  Ppl Map | listToMap " + pplCtryGrp);
		Map<String, List<People>> pplCtryStrGrp = getTenPeople().stream()
				.collect(Collectors.groupingBy(people -> people.getCountry().getName()));
		System.out.println("Country Str _ List  Ppl | listToMap " + pplCtryStrGrp);
	}

	private static void listToStreamToArray() {
		People[] peeArray = getFivePeople().stream().toArray(People[]::new);
		for (People people : peeArray) {
			System.out.println(people.getClass());
		}
	}

	private static void joinElements() {
		String joined = getFivePeople().stream().map(people -> people.getName())
				.collect(Collectors.joining("YO:YO", "<start>", "<end>	"));
		System.out.println("JOINED String | joinElements " + joined);
	}

	private static void collectAsList() {
		List<String> names = getFivePeople().stream().map(people -> people.getName()).collect(Collectors.toList());
		names.forEach(System.out::print);
		System.out.println();
	}

	private static void collectAsSet() {
		Set<String> namesSet = getFivePeople().stream().map(people -> people.getName()).collect(Collectors.toSet());
		namesSet.forEach(System.out::print);
		System.out.println();
	}

	private static List<People> getFivePeople() {
		List<People> people = new LinkedList<>();
		for (int i = 1; i <= 5; i++) {
			people.add(new People("Person " + i, new Random().nextInt(i), new Country("IN")));
		}
		return people;
	}

	private static List<People> getTenPeople() {
		List<Country> ctry = List.of(new Country("IN"), new Country("US"), new Country("UK"), new Country("AU"));
		List<People> people = new LinkedList<>();
		Random rand = new Random();
		for (int i = 1; i <= 10; i++) {
			people.add(new People("Person " + i, new Random().nextInt(i), ctry.get(rand.nextInt(ctry.size()))));
		}
		return people;
	}
}
