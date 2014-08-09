package com.insightfullogic.lambdabehave.example.j8;

import com.insightfullogic.lambdabehave.example.j8.model.Person;
import com.insightfullogic.lambdabehave.example.j8.model.PersonSearchCriteria;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

import static com.insightfullogic.lambdabehave.example.j8.model.Sex.FEMALE;
import static com.insightfullogic.lambdabehave.example.j8.model.Sex.MALE;

public class UsingLambdas {

    //1 - Using directly conditions within loops
    public void _1_personsOverCertainAge(List<Person> persons, int age) {
        for(Person person : persons) {
            if(person.getAge() > age) {
                System.out.println(person);
            }
        }
    }

    //2 - More complex conditions within loops
    public void _2_personsWithinAgeRange(List<Person> persons, int from, int to) {
        for(Person person : persons) {
            if(person.getAge() >= from && person.getAge() <= to) {
                System.out.println(person);
            }
        }
    }

    //3 - Delegating the conditions to an interface
    public void _3_serachBasedOnCriteria(List<Person> persons, PersonSearchCriteria criteria) {
        for(Person person : persons) {
            if(criteria.isCorrectFor(person)) {
                System.out.println(person);
            }
        }
    }

    /*
    he syntax of anonymous classes is bulky considering that the CheckPerson interface contains only one method.
    In this case, you can use a lambda expression instead of an anonymous class.
    */
    public void _4_serchBasedOnCriteriaWithAnonymousInnerClass(List<Person> persons) {
        _3_serachBasedOnCriteria(persons, new PersonSearchCriteria() {
            @Override
            public boolean isCorrectFor(Person person) {
                return person.getAge() > 18;
            }
        });
    }

    /*
    The CheckPerson interface is a functional interface.
    A functional interface is any interface that contains only one abstract method.
    (A functional interface may contain one or more default methods or static methods.)
    Because a functional interface contains only one abstract method, you can omit the name of that method when you implement it.
     To do this, instead of using an anonymous class expression, you use a lambda expression
    */
    public void _5_usingALambdaExperssion(List<Person> persons) {
        //Lamda syntax way 1
//        _3_serachBasedOnCriteria(persons, (Person person) -> person.getAge() > 18);

        //Lamda syntax way 2(No need to specify type)
//        _3_serachBasedOnCriteria(persons, person -> person.getAge() > 18);

        //Lamda syntax way 3(using code block)
        _3_serachBasedOnCriteria(persons, person -> {
            return person.getAge() > 18 && person.getSex().equals(MALE);
        });
    }

    /*
        Predicate: Represents a predicate (boolean-valued function) of one argument.
        Consummer: Represents an operation that accepts a single input argument and returns no result.
        Function: Represents a function that accepts one argument and produces a result.
    */

    //Using predicates
    //We can use the type Predicate in parameters if we want to expect functional interfaces(interfaces with one abstract method)
    private void _6_usingPredicates(List<Person> persons, Predicate<Person> criteria) {
        for(Person person : persons) {
            if(criteria.test(person)) {
                System.out.println(person);
            }
        }
    }

    public void _6_passingAPredicate(List<Person> persons) {
        _6_usingPredicates(persons, person -> person.getAge() > 8 && person.getSex().equals(MALE));
    }


        /*
        Predicate: Represents a predicate (boolean-valued function) of one argument.
        Consummer: Represents an operation that accepts a single input argument and returns no result.
        Function: Represents a function that accepts one argument and produces a result.
        */
    private void _7_usingConsummersForCriterias(List<Person> persons, Predicate<Person> criteria, Consumer<Person> consumer) {
        for (Person person : persons) {
            if(criteria.test(person)) {
                consumer.accept(person);
            }
        }
    }

    private void _7_passingConsummerArguments(List<Person> persons) {
        _7_usingConsummersForCriterias(persons, person -> overAgePerson(person), person -> showPerson(person));
    }

        /*
        Predicate: Represents a predicate (boolean-valued function) of one argument.
        Consumer: Represents an operation that accepts a single input argument and returns no result.
        Function: Represents a function that accepts one argument and produces a result.
        */
    private void _8_usingFunctionsInArgument(List<Person> persons, Predicate<Person> criteria, Consumer<Person> consumer, Function<Person,String> someFunction) {
        for (Person person : persons) {
            if(criteria.test(person)) {
                consumer.accept(person); //Performs action without returning anything
                System.out.println(someFunction.apply(person));
            }
        }
    }

    public void _8_passingFunctionsToArguments(List<Person> persons) {
        _8_usingFunctionsInArgument(persons, p -> overAgePerson(p), p -> showPerson(p), p -> verification(p));
    }

    public <T,O> void _9_genericImplementation(Iterable<T> iterable, Predicate<T> criteria, Consumer<T> consumer, Function<T,O> someFunction) {
        for(T t : iterable) {
            if(criteria.test(t)) {
                consumer.accept(t);//Does not return value
                final O o = someFunction.apply(t);
                System.out.println(o);
            }
        }
    }

    public void _9_usingGenericArguments(List<Person> persons) {
        _9_genericImplementation(persons, p -> overAgePerson(p), p -> showPerson(p), p -> verification(p));
    }

    /*
        filter: Filters based on predicates
        map: transforms data by using functions
        forEach: executes actions using Consumers

        Note: By using stream() and combining it with aggregate operations, we no longer
        need loops or if statements.
    */
    public void _10_using_aggregate_opperations(List<Person> persons) {
        persons.stream().filter(overAge()).map(toText()).forEach(showMessage());
    }

    public static void main(String[] args) {
        List<Person> persons = new ArrayList<>();
        UsingLambdas usingLambdas = new UsingLambdas();
        persons.add(Person.createPerson(12, MALE));
        persons.add(Person.createPerson(18, MALE));
        persons.add(Person.createPerson(40, FEMALE));
        persons.add(Person.createPerson(19, FEMALE));
        persons.add(Person.createPerson(7, MALE));

//        usingLambdas._1_personsOverCertainAge(persons,18);
//        usingLambdas._2_personsWithinAgeRange(persons, 40, 50);
//        usingLambdas._3_serachBasedOnCriteria(persons, new SuitableForArmyCriteria());
//        usingLambdas._4_serchBasedOnCriteriaWithAnonymousInnerClass(persons);
//        usingLambdas._5_usingALambdaExperssion(persons);
//        usingLambdas._6_passingAPredicate(persons);
//          usingLambdas._7_passingConsummerArguments(persons);
//          usingLambdas._8_passingFunctionsToArguments(persons);
//          usingLambdas._9_usingGenericArguments(persons);
        usingLambdas._10_using_aggregate_opperations(persons);
    }

    //HELPER METHODS
    private void showPerson(Person person) {
        System.out.println(person);
    }

    private boolean overAgePerson(Person person) {
        return person.getAge() > 18;
    }

    private String verification(Person person) {
        return "Reviewed " + person.getSex() + " of age " + person.getAge();
    }


    private Function<Person, String> toText() {
        return new Function<Person, String>() {
            @Override
            public String apply(Person person) {
                return "Found " + person.getSex() + " age " + person.getAge();
            }
        };
    }

    private Predicate<Person> overAge() {
        return new Predicate<Person>() {
            @Override
            public boolean test(Person person) {
                return person.getAge() > 18;
            }
        };
    }

    private Consumer<String> showMessage() {
        return new Consumer<String>() {
            @Override
            public void accept(String data) {
                System.out.println(data);
            }
        };
    }
}
