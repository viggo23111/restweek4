/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import dtos.PersonDTO;
import dtos.RenameMeDTO;
import entities.Person;
import entities.RenameMe;
import javax.persistence.EntityManagerFactory;
import utils.EMF_Creator;

/**
 *
 * @author tha
 */
public class Populator {
    public static void populate(){
        EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory();
        PersonFacade pf = PersonFacade.getPersonFacade(emf);
        pf.create(new PersonDTO(new Person("Viktor", "Nymand","56331233")));
        pf.create(new PersonDTO(new Person("Lars", "Larsen","3232313")));
        pf.create(new PersonDTO(new Person("Svend", "Svendsen","6677897")));
    }

    public static void tester(){
        EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory();
        PersonFacade pf = PersonFacade.getPersonFacade(emf);
      //  System.out.println(pf.getAllPersons());
       // System.out.println(pf.getPerson(1));
      //  pf.addPerson("Test","lnametest","testphone");
       // System.out.println(pf.getAllPersons());
        //pf.deletePerson(4);
        //System.out.println("DELETES ID 4");
        //System.out.println(pf.getAllPersons());
    }
    
    public static void main(String[] args) {
      //  populate();
        tester();
    }
}
