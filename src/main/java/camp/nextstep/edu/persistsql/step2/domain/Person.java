package camp.nextstep.edu.persistsql.step2.domain;

import javax.persistence.*;

@Table(name = "PERSON")
@Entity
public class Person {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private Integer age;

    @Transient
    private String etc;

    public Person() {
    }

    public Person(String name, Integer age) {
        this.name = name;
        this.age = age;
    }
}
