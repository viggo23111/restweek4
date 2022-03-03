/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import dtos.PersonDTO;
import dtos.RenameMeDTO;
import entities.Address;
import entities.Person;
import entities.RenameMe;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import utils.EMF_Creator;

/**
 *
 * @author tha
 */
public class Populator {
    public static void populate(){
        EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory();
        EntityManager em = emf.createEntityManager();
        PersonFacade pf = PersonFacade.getPersonFacade(emf);
        Person p1 = new Person("Viktor", "Nymand","56331233");
        Address a1 = new Address("Test1","1111","Test1111");
        Address a2 = new Address("Test2","2222","Test2222");
        Person p2 = new Person("Vincent", "Buch","2323232");
        Person p3= new Person("TEST#", "dwdwd","232332323232");

        p1.addAddress(a1);
        p2.addAddress(a1);
        p3.addAddress(a2);

        em.getTransaction().begin();
        em.persist(a1);
        em.persist(a2);
        em.getTransaction().commit();
        em.close();
       // pf.create(new PersonDTO(p1));
       // pf.create(new PersonDTO(new Person("Lars", "Larsen","3232313")));
        //pf.create(new PersonDTO(new Person("Svend", "Svendsen","6677897")));


    }

    public static void tester(){
        EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory();
        PersonFacade pf = PersonFacade.getPersonFacade(emf);
         System.out.println(pf.getAllPersons());
       // System.out.println(pf.getPerson(1));
      //  pf.addPerson("Test","lnametest","testphone");
       // System.out.println(pf.getAllPersons());
        //pf.deletePerson(4);
        //System.out.println("DELETES ID 4");
        //System.out.println(pf.getAllPersons());
    }
    
    public static void main(String[] args) {
        //populate();
        tester();
    }
}
