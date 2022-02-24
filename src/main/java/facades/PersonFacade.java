package facades;

import dtos.PersonDTO;
import dtos.RenameMeDTO;
import entities.Person;
import entities.RenameMe;
import errorhandling.ExceptionDTO;
import errorhandling.PersonNotFoundException;
import interfaces.IPersonFacade;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.TypedQuery;
import java.util.Date;
import java.util.List;

public class PersonFacade implements IPersonFacade {
    private static PersonFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private PersonFacade() {}


    /**
     *
     * @param _emf
     * @return an instance of this facade class.
     */
    public static PersonFacade getPersonFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new PersonFacade();
        }
        return instance;
    }
    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }


    @Override
    public PersonDTO addPerson(String fName, String lName, String phone) {
        Person p = new Person(fName,lName, phone);
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(p);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new PersonDTO(p);
    }

    public PersonDTO create(PersonDTO personDTO){
        Person p = new Person(personDTO.getfName(),personDTO.getlName(), personDTO.getPhone());
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(p);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new PersonDTO(p);
    }
    @Override
    public Person deletePerson(long id) throws PersonNotFoundException {
        EntityManager em = getEntityManager();
        Person p = em.find(Person.class, id);
        if (p == null)
            throw new PersonNotFoundException("No person with provided id found");
        em.getTransaction().begin();
        em.remove(p);
        em.getTransaction().commit();
        return p;
    }

    @Override
    public PersonDTO getPerson(long id) throws PersonNotFoundException {
        EntityManager em = emf.createEntityManager();
        try {
            Person person = em.find(Person.class,id);
            if (person == null) {
                throw new PersonNotFoundException("No person with provided id found");
            }
            return new PersonDTO(person);
        }finally {
            em.close();
        }

    }

    @Override
    public List<PersonDTO> getAllPersons() {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Person> query = em.createQuery("SELECT p FROM Person p", Person.class);
        List<Person> persons = query.getResultList();
        return PersonDTO.getDtos(persons);
    }

    @Override
    public PersonDTO editPerson(PersonDTO pDTO) {
        EntityManager em = emf.createEntityManager();
        if (pDTO.getId() == 0)
            throw new IllegalArgumentException("No Parent can be updated when id is missing");
        try {
            Person person = em.find(Person.class,pDTO.getId());
            if (pDTO.getfName() !=null){
                person.setFirstName(pDTO.getfName());
            }
            if (pDTO.getfName() !=null){
                person.setLastName(pDTO.getlName());
            }
            if (pDTO.getfName() !=null){
                person.setPhone(pDTO.getPhone());
            }
            person.setLastEdited(new Date());

            em.getTransaction().begin();
            em.merge(person);
            em.getTransaction().commit();
        }finally {
            em.close();
        }
        return pDTO;
    }

}
