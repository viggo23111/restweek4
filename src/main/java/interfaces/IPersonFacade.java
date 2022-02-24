package interfaces;

import dtos.PersonDTO;
import entities.Person;

import java.util.List;

public interface IPersonFacade {
    public PersonDTO addPerson(String fName, String lName, String phone);
    public Person deletePerson(long id) throws Exception;;
    public PersonDTO getPerson(long id) throws Exception;;
    public List<PersonDTO> getAllPersons();
    public PersonDTO editPerson(PersonDTO p) throws Exception;;
}

