package org.example.section2;

public class Person {
    private Address address;
    private String name;
    private int age;

    public Person() {
        this.address = null;
        this.name = "anonymous";
        this.age = 0;
    }

    public Person(String name) {
        this.address = null;
        this.name = name;
        this.age = 0;
    }

    public Person(String name, int age) {
        this.address = null;
        this.name = name;
        this.age = age;
    }

    public Person(Address address, String name, int age) {
        this.address = address;
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
            "address=" + address +
            ", name='" + name + '\'' +
            ", age=" + age +
            '}';
    }
}
