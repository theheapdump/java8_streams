package runner;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.function.Function;
import java.util.function.ToDoubleFunction;
import java.util.regex.Pattern;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import javax.security.auth.login.AccountExpiredException;

import model.City;
import model.Country;
import model.People;

public class StreamsExamplesSetOne {

	public static void demoExampleSetOne() {
		List<City> cities = getCities(getThirtyPeople());
		mapFilterCount(getPeople());
		flatMapPeopleFromCity(cities);
		streamFromArray();
		streamFromFile();
		streamFromRegularExpressionSplit();
		intStreamExample();
		skipExample();
		takeWhile();
		streamAverage();
		reduceInts();
		reduceIntsIdentityElement();
		maxFunction();
		toDoubleFunction();
		summaryStatistics();
	}

	private static void toDoubleFunction() {
		ToDoubleFunction<String> myFunction = (line) -> {
			return line.chars().count();
		};
		try (Stream<String> lines = Files.lines(Path.of("/Users/adeep/Documents/work/learn/how_to_do/OAUTH2"))) {
			double max = lines.mapToDouble(myFunction).max().orElseThrow();
			System.out.println("Max Char Count | toDoubleFunction " + max);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void summaryStatistics() {
		ToDoubleFunction<String> myFunction = (line) -> {
			return line.chars().count();
		};
		try (Stream<String> lines = Files.lines(Path.of("/Users/adeep/Documents/work/learn/how_to_do/OAUTH2"))) {
			DoubleSummaryStatistics summaryStatistics = lines.mapToDouble(myFunction).summaryStatistics();
			System.out.println("Summary | summaryStatistics " + summaryStatistics);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void maxFunction() {
		Function<String, Long> myFunction = (line) -> {
			return line.chars().count();
		};
		try (Stream<String> lines = Files.lines(Path.of("/Users/adeep/Documents/work/learn/how_to_do/OAUTH2"))) {
			long max = lines.map(line -> myFunction.apply(line)).max(Comparator.naturalOrder()).orElseThrow();
			System.out.println("Max Char Count | maxFunction " + max);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void reduceIntsIdentityElement() {
		Stream<Integer> numbers = Stream.of(1, 1, 1, 1, 1, 1, 1, 1);
		Integer sum = numbers.reduce(0, (a, b) -> a + b);
		System.out.println("SUM | reduceIntsIdentityElement = " + sum);
	}

	private static void reduceInts() {
		Stream<Integer> numbers = Stream.of(1, 1, 1, 1, 1, 1, 1, 1);
		Optional<Integer> sum = numbers.reduce((a, b) -> a + b);
		System.out.println("SUM | reduceInts = " + sum.get());
	}

	private static void streamAverage() {
		System.out.println("Average |streamAverage " + getThirtyPeople().stream().mapToInt(people -> people.getAge())
				.filter(age -> age > 10).average().orElseThrow());
	}

	private static void takeWhile() {
		Class<?> clazz = AccountExpiredException.class;
		Stream.<Class<?>>iterate(clazz, c -> c.getSuperclass()).takeWhile(claz -> claz != null)
				.forEach(claz -> System.out.println(claz));
	}

	private static void skipExample() {
		IntStream.range(1, 30).skip(15).limit(3).forEach(num -> System.out.print(num + " "));
		System.out.println();
	}

	/*
	 * letter by letter processing
	 */
	private static void intStreamExample() {
		new String("this is a sample string of few characters").chars().mapToObj(code -> Character.toString(code))
				.distinct().sorted().forEach(System.out::print);
		System.out.println();
	}

	private static void streamFromRegularExpressionSplit() {
		long count = Pattern.compile(" ").splitAsStream(new String("this is a sample string of few characters"))
				.count();
		System.out.println("Count Lines | streamFromRegularExpressionSplit " + count);
	}

	private static void streamFromFile() {
		try (Stream<String> lines = Files.lines(Path.of("/Users/adeep/Documents/work/learn/how_to_do/OAUTH2"))) {
			// lines.skip(10).limit(4).forEach(System.out::println);
			System.out.println("Count Lines | streamFromFile " + lines.count());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void streamFromArray() {
		System.out.println("Count People | streamFromArray = " + Arrays.stream(getThirtyPeople().toArray()).count());
		System.out.println("Count People | streamFromArray= " + Stream.of(getThirtyPeople().toArray()).count());
	}

	/*
	 * For One to Many relationships
	 */
	private static void flatMapPeopleFromCity(List<City> cities) {
		long allPeople = cities.stream().flatMap(city -> city.getPeople().stream()).count();
		System.out.println("All PEOPLE COUNT " + allPeople);
	}

	private static List<City> getCities(List<People> people) {
		List<City> cities = new ArrayList<>();
		cities.add(new City(people.subList(0, 10), "CITY A"));
		cities.add(new City(people.subList(10, 20), "CITY B"));
		cities.add(new City(people.subList(20, 30), "CITY C"));
		return cities;
	}

	private static void mapFilterCount(List<People> people) {
		Instant now = Instant.now();
		long agedPeople = people.stream().map(p -> p.getAge()).filter(p -> p > 5000).count();
		Instant then = Instant.now();
		System.out.println(Duration.between(now, then).getSeconds() + " : " + agedPeople);
	}

	private static List<People> getPeople() {
		List<People> people = new LinkedList<>();
		for (int i = 1; i <= 10000; i++) {
			people.add(new People("Person " + i, new Random().nextInt(i), new Country("IN")));
		}
		return people;
	}

	private static List<People> getThirtyPeople() {
		List<People> people = new LinkedList<>();
		for (int i = 1; i <= 30; i++) {
			people.add(new People("Person " + i, new Random().nextInt(i), new Country("IN")));
		}
		return people;
	}
}
