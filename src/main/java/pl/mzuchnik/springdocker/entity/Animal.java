package pl.mzuchnik.springdocker.entity;

import javax.persistence.*;

@Entity
@Table(name = "animals")
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private int age;
    @Column(name = "wild")
    private boolean isWild;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Animal() {
    }

    public Animal(String name, int age, boolean isWild, User user) {
        this.name = name;
        this.age = age;
        this.isWild = isWild;
        this.user = user;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean getIsWild() {
        return isWild;
    }

    public void setIsWild(boolean wild) {
        isWild = wild;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", isWild=" + isWild +
                ", user=" + user +
                '}';
    }
}
