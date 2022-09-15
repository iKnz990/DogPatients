import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


import controller.DogHelper;
//import DogList
import model.DogList;

/**
 * @author Alexander Kelly - akelly3
 * CIS175 - Fall 2022
 * Sep 11, 2022
 */

/**
 * @author 15152
 *
 */
public class DogRunner {
//Scanner to read inputs.
static Scanner in = new Scanner(System.in);
//create object
static DogHelper dh = new DogHelper();

static EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("DogPatients");

private static void addAnItem() {
	//enter name into object
	System.out.print("Enter the dogs name: ");
	String name = in.nextLine();
	
	//enter breed into object
	System.out.print("Enter the breed of the dog: ");
	String breed = in.nextLine();
	
	//enter age into object use .nextInt
	System.out.print("Enter the age of the dog: ");
	int age = in.nextInt();
	//now add Object to DB
	DogList toAdd = new DogList(name, breed, age);
	dh.insertItem(toAdd);
	
}

public static void main(String[] args) {
	// TODO Auto-generated method stub
	runMenu();

}

private static void runMenu() {
	//initialize menu options
	boolean goAgain = true;
	//print menu choices
	System.out.println("Dog Patient : Add / Edit / View / Delete");
	//loop for options
	while(goAgain) {
		//menu choices
		System.out.println("Select an option: ");
		System.out.println("1. Add a patient");
		System.out.println("2. Edit a patient");
		System.out.println("3. Delete a patient");
		System.out.println("4. View a list of patients");
		System.out.println("5. Exit the program.");
		//initialize if statement to pick an option or exit
		int selection = in.nextInt();
		in.nextLine();
		
		if (selection == 1) {
			addAnItem(); //add
		}else if (selection == 2) {
			editAPet(); //edit
		}else if (selection == 3) {
			deleteAnItem(); //delete
		}else if (selection == 4) {
			viewTheList(); // view
		} else {
			dh.cleanUp();
			System.out.println("All done.");
			goAgain = false;
		}
		
	}
	
}

/**
 * 
 */
private static void editAPet() {	
	//List ID of Dogs in DB
	List<DogList> viewDog = dh.showAllDogs();
	for(DogList singleDog : viewDog) {
		System.out.println(singleDog.idforEdits());
	}
	
	//pick the ID to edit
	System.out.print("What is the ID: ");
	int idToEdit = in.nextInt();
	DogList toEdit = DogHelper.editAPet(idToEdit);
	
	//Lists current Information about chosen dog
	System.out.println("Name: " + toEdit.getName() + "\nBreed: " + toEdit.getBreed() + "\nAge: " + toEdit.getAge());
	
	//select info to update
	System.out.println("What details do you need to edit for " + toEdit.getName() + "\n 1. Name" + "\n 2. Breed" + "\n 3. Age");
	
	int update = in.nextInt();
	in.nextLine();
	//Choice if's
	if (update == 1) {
		System.out.print("New Name: ");
		String newName = in.nextLine();
		toEdit.setName(newName);
	} else if (update == 2){
		System.out.print("New Breed: ");
		String newBreed = in.nextLine();
		toEdit.setBreed(newBreed);
	} else if (update == 3) {
		System.out.print("New Age: ");
		int newAge = in.nextInt();
		toEdit.setAge(newAge);
	}
	//Calls to Merge 
	dh.updatePet(toEdit);
	return;
}



public static void viewTheList() {
	List<DogList> allDogs = dh.showAllDogs();
	for(DogList singleDog : allDogs) {
		//uses returnDogInfo method
		System.out.println(singleDog.returnDogInfo());
	}
	
}

/**
 * 
 */
private static void deleteAnItem() {
	// Show list of dogs. Requires Name/Breed/Age to delete.
	List<DogList> viewDog = dh.showAllDogs();
	for(DogList singleDog : viewDog) {
		//returns information using idforDelete method
		System.out.println(singleDog.idforDelete());
	}
	
	System.out.println("You will need Name, Breed and Age" );
	System.out.print("Enter the Name of the Dog to delete: ");
	String dogName = in.nextLine();
	System.out.print("Enter the Breed of the Dog to delete: ");
	String dogBreed = in.nextLine();
	System.out.print("Enter the Age of the Dog to delete: ");
	int dogAge = in.nextInt();

	DogList toDelete = new DogList(dogName, dogBreed, dogAge);
	dh.deleteItem(toDelete);
}

/**
 * 
 */





}
