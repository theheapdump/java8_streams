package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class People {

  private String name;

  private Integer age;

  private Country country;

  public int byAge(People people) {
    return this.getAge() - people.getAge();
  }

  public int compareNames(People people) {
    return this.name.compareTo(people.name);
  }

  public int compareLength(People people) {
    return Integer.compare(this.name.length(), people.name.length());
  }

  public String getCountryName() {
    return this.getCountry().getName();
  }
}
