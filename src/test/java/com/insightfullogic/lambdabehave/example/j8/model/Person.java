package com.insightfullogic.lambdabehave.example.j8.model;

import static java.lang.System.lineSeparator;

public class Person {
    private int age;
    private Sex sex;

    private Person(int age, Sex sex) {
        this.age = age;
        this.sex = sex;
    }

    public static Person createPerson(int age, Sex sex) {
        return new Person(age, sex);
    }


    public int getAge() {
        return age;
    }

    public Sex getSex() {
        return sex;
    }

    @Override
    public String toString() {
        return "Age:" + getAge() + lineSeparator() +
                "Sex:" + getSex();
    }
}
