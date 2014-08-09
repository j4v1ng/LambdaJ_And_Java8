package com.insightfullogic.lambdabehave.example.j8.model;

import static com.insightfullogic.lambdabehave.example.j8.model.Sex.MALE;

/**
 * Created by ocean on 09/08/14.
 */
public class SuitableForArmyCriteria implements PersonSearchCriteria {
    @Override
    public boolean isCorrectFor(Person person) {
        return person.getAge() >= 18 && person.getSex().equals(MALE);
    }
}
