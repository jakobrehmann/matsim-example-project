package org.matsim.effectiveJava.Item2ConsiderBuilders;

public class Person {



    private String lastName ;
    private String firstName ;
    private int weight ;
    private int height ;
    private int age ;

    // telescoping constructors
    //telescoping constructors -- bad b/c what if you want a constructor with lastName, firstName and weight as input!
    // doesn't work
//    public Person(String lastName, String firstName) {
//        this(lastName, firstName, -1, -1, -1);
//    }
//
//    public Person(String lastName, String firstName, int age) {
//        this(lastName, firstName, -1, -1, age);
//    }
//    public Person(String lastName, String firstName, int weight, int height, int age) {
//        this.setLastName(lastName);
//        this.setFirstName(firstName);
//        this.setWeight(weight);
//        this.setHeight(height);
//        this.setAge(age);
//    }




    //Java Beans -- set defaults within constructor, and make user do inputs him/herself using setters
//    public Person(String lastName, String firstName) {
//        this.lastName = lastName;
//        this.firstName = firstName;
//        this.height = -1 ;
//        this.weight = -1 ;
//        this.age = -1 ;
//    }


    //opt 3: Builder Pattern
    // Builder allows you to construct multiple objects with similar attributes.
    // meant for heavy weight obj.
    // you don't want setters
    // factories are meant to build many objects of same type.
    // once you have builder, you can remove setters from Person.
    // go towards immutability
    //make constructor not public, such that you have to use builder --> use default
    public Person(String lastName, String firstName, int weight, int height, int age) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.weight = weight;
        this.height = height;
        this.age = age;
    }
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
