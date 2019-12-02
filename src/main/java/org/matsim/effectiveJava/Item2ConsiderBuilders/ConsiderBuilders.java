package org.matsim.effectiveJava.Item2ConsiderBuilders;

public class ConsiderBuilders {
    public static void main(String[] args) {

        // JavaBeans Patterns


        // Builder
//        Person person = new PersonBuilder().setLastName("rehmann").setFirstName("jakob").setWeight(1).setHeight(1).setAge(1).createPerson();
        PersonBuilder builder = new PersonBuilder(); // Ctrl + Shift + J --> pulls up the following as dot arguments
        builder.setLastName("rehmann");
        builder.setFirstName("jakob");
        builder.setAge(2);
        builder.build() ;

//        person.setAge(3);
//        person.setHeight(10
//        );

    }
}
