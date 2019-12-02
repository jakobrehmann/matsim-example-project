package org.matsim.effectiveJava.Item2ConsiderBuilders;

public class PersonBuilder {
    private String lastName;
    private String firstName;
    private int weight;
    private int height;
    private int age;

    public PersonBuilder setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public PersonBuilder setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public PersonBuilder setWeight(int weight) {
        this.weight = weight;
        return this;
    }

    public PersonBuilder setHeight(int height) {
        this.height = height;
        return this;
    }

    public PersonBuilder setAge(int age) {
        this.age = age;
        return this;
    }

    public Person createPerson() {
        return new Person(lastName, firstName, weight, height, age);
    }

    public Person build() {
        return new Person(lastName, firstName, weight, height, age);

    }
}