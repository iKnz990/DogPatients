/**
 * @author Alexander Kelly - akelly3
 * CIS175 - Fall 2022
 * Sep 11, 2022
 */
package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author 15152
 *
 */
@Entity
@Table(name="dogs")
/*POJO for a Vet Patient List - Doggos */
public class DogList {
	@Id
	@GeneratedValue
	@Column(name="ID")
	private int id;
	@Column(name="NAME")
	private String name;
	@Column(name="BREED")
	private String breed;
	@Column(name="AGE")
	private int age;
	
	
	//no arg constructor
	public DogList() {
		super();
	}
	public DogList(int id) {
		this.id = id;
	}
	//arg constructor
	public DogList(String name, String breed, int age) {
		this.name = name;
		this.breed = breed;
		this.age = age;
	}

	//getters / setters
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBreed() {
		return breed;
	}
	public void setBreed(String breed) {
		this.breed = breed;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}

	
	//returns information for ViewList - All of it
	public String returnDogInfo() {
		return "ID: " + this.id + "\nName: " + this.name + "\n" + "Breed: " + this.breed+ " \n" + "Age: " + this.age + "\n ------" ;
	}
	//returns ID for editing - Only Name and ID
	public String idforEdits() {
		return "\nID: " + this.getId() + "\nName: " + this.name + "\n" ;
	}
	//returns list of dogs, requires Name/Breed/Age for deletion
	public String idforDelete() {
		return "Name: " + this.name + "\n" + "Breed: " + this.breed+ " \n" + "Age: " + this.age ;
	}
	
	
	
}
