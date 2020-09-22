package model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class City {

	private List<People> people;
	private String name;

	public void addPeople(People people) {
		this.people.add(people);
	}
}
