/**
 * @author Alexander Kelly - akelly3
 * CIS175 - Fall 2022
 * Sep 12, 2022
 */
package controller;

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

//Model import so this class can find DogList.Class
import model.DogList;

/**
 * @author 15152
 *
 */
public class DogHelper {
	//Global EntityManager using Project
static EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("DogPatients");
//scanner 
static Scanner in = new Scanner(System.in);

//Insert entity
public void insertItem (DogList dl) {
	//local entity manager
	EntityManager em = emfactory.createEntityManager();
	//begin transaction
	em.getTransaction().begin();
	em.persist(dl);
	em.getTransaction().commit();
	em.close();

}

//Edit entity
public static DogList editAPet(int idToEdit) {
	EntityManager em = emfactory.createEntityManager();
	em.getTransaction().begin();
	//pulls up dog
	DogList found = em.find(DogList.class, idToEdit);
	em.close();
	return found;
}

public void updatePet(DogList toEdit) {
	EntityManager em = emfactory.createEntityManager();
	em.getTransaction().begin();
	em.merge(toEdit);
	//Commits the changes -- Wasn't in the lab example...
	em.getTransaction().commit();
	em.close();
}


//View entity
public List<DogList> showAllDogs() {
	EntityManager em = emfactory.createEntityManager();
	//creates SQL query and returns dog list
	List<DogList> allDogs = em.createQuery("SELECT i FROM DogList i").getResultList();
	
	return allDogs;
}



public void cleanUp() {
	// TODO Auto-generated method stub
	emfactory.close();
}

/**
 * @param toDelete
 */
public void deleteItem(DogList toDelete) {
	// TODO Auto-generated method stub
	EntityManager em = emfactory.createEntityManager();
	em.getTransaction().begin();
	//set query for Dog name/breed/age
	TypedQuery<DogList> typedQuery = em.createQuery("select dh from DogList dh where dh.name = :selectedName and dh.breed = :selectedBreed and dh.age =:selectedAge", DogList.class);
	
	typedQuery.setParameter("selectedName", toDelete.getName());
	typedQuery.setParameter("selectedBreed", toDelete.getBreed());
	typedQuery.setParameter("selectedAge", toDelete.getAge());
	
	typedQuery.setMaxResults(1);
	
	DogList result = typedQuery.getSingleResult();
	
	em.remove(result);
	em.getTransaction().commit();
	em.close();
}

/**
 * @return
 */





}
