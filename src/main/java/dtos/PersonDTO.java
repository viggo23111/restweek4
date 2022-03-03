package dtos;

import entities.Address;
import entities.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PersonDTO {
    private long id;
    private String fName;
    private String lName;
    private String phone;
    private String city;
    private String zip;
    private String street;


    public PersonDTO(Person p) {
        if(p.getId() != null)
            this.id = p.getId();
        this.fName = p.getFirstName();
        this.lName = p.getLastName();
        this.phone = p.getPhone();

        if(p.getAddress()!=null) {
            this.city = p.getAddress().getCity();
            this.zip = p.getAddress().getZip();
            this.street = p.getAddress().getStreet();
        }
    }

    public PersonDTO(String fn, String ln, String phone) {
        this.fName = fn;
        this.lName = ln;
        this.phone = phone;
    }

    public PersonDTO() {
    }

    public long getId() {
        return id;
    }

    public String getfName() {
        return fName;
    }

    public String getlName() {
        return lName;
    }

    public String getPhone() {
        return phone;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public static List<PersonDTO> getDtos(List<Person> persons){
        List<PersonDTO> personDTOS = new ArrayList();
        persons.forEach(person->personDTOS.add(new PersonDTO(person)));
        return personDTOS;
    }

    @Override
    public String toString() {
        return "PersonDTO{" +
                "id=" + id +
                ", fName='" + fName + '\'' +
                ", lName='" + lName + '\'' +
                ", phone='" + phone + '\'' +
                ", city='" + city + '\'' +
                ", zip='" + zip + '\'' +
                ", street='" + street + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonDTO personDTO = (PersonDTO) o;
        return id == personDTO.id && Objects.equals(fName, personDTO.fName) && Objects.equals(lName, personDTO.lName) && Objects.equals(phone, personDTO.phone) && Objects.equals(city, personDTO.city) && Objects.equals(zip, personDTO.zip) && Objects.equals(street, personDTO.street);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fName, lName, phone, city, zip, street);
    }
}

