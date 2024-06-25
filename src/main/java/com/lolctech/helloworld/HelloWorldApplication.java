package com.lolctech.helloworld;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RequestMapping(value = "/hello-world")
@RestController
public class HelloWorldApplication {

    @Autowired
    private PersonRepository personRepository;

    public static void main(String[] args) {
        SpringApplication.run(HelloWorldApplication.class, args);
    }

    @GetMapping("/welcome")
    public String sayHello() {
        return String.format("Welcome to DevOps Course");
    }

    @Override
    public void run(String... args) throws Exception {
        // Sample data
        personRepository.save(new Person("John Doe", "123 Main St"));
        personRepository.save(new Person("Jane Smith", "456 Maple Ave"));
    }
}

@Entity
class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String address;

    public Person() {
    }

    public Person(String name, String address) {
        this.name = name;
        this.address = address;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}

@Repository
interface PersonRepository extends JpaRepository<Person, Long> {
}

@RestController
@RequestMapping("/api/persons")
class PersonController {
    @Autowired
    private PersonRepository personRepository;

    @GetMapping
    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Person> getPersonById(@PathVariable Long id) {
        return personRepository.findById(id);
    }

    @PostMapping
    public Person createPerson(@RequestBody Person person) {
        return personRepository.save(person);
    }

    @PutMapping("/{id}")
    public Person updatePerson(@PathVariable Long id, @RequestBody Person personDetails) {
        Person person = personRepository.findById(id).orElseThrow(() -> new RuntimeException("Person not found"));
        person.setName(personDetails.getName());
        person.setAddress(personDetails.getAddress());
        return personRepository.save(person);
    }

    @DeleteMapping("/{id}")
    public void deletePerson(@PathVariable Long id) {
        personRepository.deleteById(id);
    }
}
