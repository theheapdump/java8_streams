package resusability;

import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class ReuseLambdas {

  private static final Function<String, Predicate<String>> namesStartingWith
      = input -> name -> name.contains(input);

  private static final List<String> names = List.of("Anmol", "Anila", "Nitika", "Nikita", "Nirvi",
      "Animesh");

  public static void main(String[] args) {
    names.stream().filter(namesStartingWith.apply("An")).forEach(System.out::println);
    names.stream().filter(namesStartingWith.apply("Nir")).forEach(System.out::println);
  }
}
