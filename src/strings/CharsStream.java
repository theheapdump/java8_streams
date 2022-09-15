package strings;

public class CharsStream {

  public static void main(String[] args) {
    String name = "Anmol Deep";
    name.chars()
        .filter(ch -> ch == 'e')
        .mapToObj(ch -> Character.valueOf((char) ch))
        .forEach(System.out::println);
  }
}
