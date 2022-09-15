package Grouping;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;
import model.Country;
import model.People;

public class Grouping {

  public static void main(String[] args) {
    List<People> peeps = List.of(new People("Zeta", 60, new Country("India")),
        new People("Alpha", 28, new Country("Australia")),
        new People("Bta", 25, new Country("Zimbabwe")),
        new People("Epsilon", 28, new Country("Alabama")));

    Map<Integer, List<People>> group1 = peeps.stream()
        .collect(Collectors.groupingBy(People::getAge));

    group1.forEach((p, q) -> System.out.println(p + " " + q));
    System.out.println();
    // collect by grouping & Mapping to String
    Map<Integer, List<String>> group2 = peeps.stream()
        .collect(Collectors
            .groupingBy(People::getAge, Collectors
                .mapping(People::getCountryName, Collectors.toList())));

    group2.forEach((p, q) -> System.out.println(p + " " + q));
    System.out.println();

    //  group by first character of Country and reduce to lexographically first country
    Map<Object, Optional<People>> group3 = peeps.stream()
        .collect(Collectors
            .groupingBy(people -> people.getCountryName().charAt(0),
                Collectors.reducing(BinaryOperator.maxBy(People::compareNames))));
    group3.forEach((p, q) -> System.out.println(p + " " + q.get()));
    System.out.println();
  }
}
