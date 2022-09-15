package comparators;

import java.util.Comparator;
import java.util.List;
import java.util.function.ToIntFunction;
import model.Country;
import model.People;

public class Comparing {

  private static Comparator<People> comparatorNamesAsc = (p1, p2) -> p1.compareNames(p2);
  private static Comparator<People> comparatorLengthAsc = (p1, p2) -> p1.compareLength(p2);
  private static Comparator<People> comparatorLengthDesc = comparatorLengthAsc.reversed();

  public static void main(String[] args) {
    List<People> peeps = List.of(new People("Zeta", 60, new Country("India")),
        new People("Alpha", 28, new Country("Australia")),
        new People("Bta", 25, new Country("Zimbabwe")),
        new People("Epsilon", 28, new Country("AlabWama")));

    peeps.stream().sorted(comparatorNamesAsc).forEach(System.out::println);
    System.out.println();
    peeps.stream().sorted(comparatorLengthAsc).forEach(System.out::println);
    System.out.println();
    peeps.stream().sorted(comparatorLengthDesc).forEach(System.out::println);
    System.out.println();

    peeps.stream().min(People::byAge).ifPresent(System.out::println);
    peeps.stream().max(People::compareNames).ifPresent(System.out::println);

    compareByName(peeps);
    compareByAge(peeps);
    compareByAgeThenByCountry(peeps);

  }

  private static void compareByAgeThenByCountry(List<People> peeps) {
    System.out.println("Comparing By Age Then By Name");
    peeps.stream()
        .sorted(Comparator
            .comparing(People::getAge)
            .thenComparing(People::getCountryName))
        .forEach(System.out::println);
    System.out.println();
  }

  private static void compareByAge(List<People> peeps) {
    // comparingInt take an IntFunction
    // (People, Integer) = people -> String
    ToIntFunction<People> compareByAge = people -> people.getAge();
    System.out.println("Comparing By Age");
    peeps.stream()
        .sorted(Comparator.comparingInt(compareByAge)
            .reversed())
        .forEach(System.out::println);
    System.out.println();
  }

  private static void compareByName(List<People> peeps) {
    // comparing take a Function
    // (People, String) = people -> String
    System.out.println("Comparing By Name");
    peeps.stream().sorted(Comparator.comparing(People::getName)).forEach(System.out::println);
    System.out.println();
  }
}
