package org.matsim.effectiveJava.item6;

public class item6 {
    public static void main(String[] args) {


        // I T E M   6: Avoid Creating Unnecessary Objects

        long time ;


        // Example 1 :

//        time = System.currentTimeMillis();
//        for (int i = 0; i < 10000000; i++) {
//            String s = new String("panda");
//        }
//        System.out.println("time: " + (System.currentTimeMillis() - time) + " ms");
//
//        time = System.currentTimeMillis();
//        for (int i = 0; i < 10000000; i++) {
//            String s = "panda";
//        }
//        System.out.println("time: " + (System.currentTimeMillis() - time)+ " ms");



//
//
        // Example 2: Expensive Objects
        // If you are going to use an expensive object over and over again, it doesn't make
        // sense to instantiate it multiple times. --> cache it

//        time = System.currentTimeMillis();
//        for (int i = 0; i < 1000; i++) {
//            isRomanNumeral("XIII");
//        }
//
//
//        System.out.println("time: " + (System.currentTimeMillis() - time)+ " ms");
//
//        time = System.currentTimeMillis();
////        RomanNumerals rn = new RomanNumerals();
//        for (int i = 0; i < 1000; i++) {
//            RomanNumerals.isRomanNumeral("XIII") ;
//        }
//        System.out.println("time: " + (System.currentTimeMillis() - time)+ " ms");



        // Example 3: Autoboxing
        // Prefer primitives to boxed primitive types
        time = System.currentTimeMillis();
        Long sum = 0L ;
        for (long i = 0; i <= Integer.MAX_VALUE; i++) {
            sum += i ;
        }
        System.out.println("time: " + (System.currentTimeMillis() - time)+ " ms");

        time = System.currentTimeMillis();
        long sum2 = 0L ;
        for (long i = 0; i <= Integer.MAX_VALUE; i++) {
            sum2 += i ;
        }
        System.out.println("time: " + (System.currentTimeMillis() - time)+ " ms");



//
//
//        // Constructor must create new object each time it is called.
//                // Use Static factories instead --> reuse immutable objects.
//
//                // Creating Boolean object
//                time = System.currentTimeMillis();
//        for (int i = 0; i < 1000000000; i++) {
//            if (new Boolean(10 < 5))
//                System.out.println("hello");
//
//        }
//        System.out.println("static factory - time: " + (System.currentTimeMillis() - time)+ " ms");
//
//
//        // Reusing Boolean object
//        time = System.currentTimeMillis();
//        for (int i = 0; i < 1000000000; i++) {
//            if (Boolean.valueOf(10 < 5)) {
//                System.out.println("hello");
//            }
//        }
//        System.out.println("static factory - time: " + (System.currentTimeMillis() - time)+ " ms");
//        time = System.currentTimeMillis();
//        for (int i = 0; i < 1000000000; i++) {
//            if (10 < 5) {
//                System.out.println("hello");
//            }
//        }
//        System.out.println("static factory - time: " + (System.currentTimeMillis() - time)+ " ms");


    }

    static boolean isRomanNumeral(String s) {
        return s.matches("^(?=.)M*(C[MD]|D?C{0,3})"
                +"(X[CL]|L?X{0,3})(I[XV]|V?I{0,3})$") ;
    }
}







