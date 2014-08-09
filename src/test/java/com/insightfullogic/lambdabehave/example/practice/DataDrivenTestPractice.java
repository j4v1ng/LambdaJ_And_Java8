package com.insightfullogic.lambdabehave.example.practice;

import com.insightfullogic.lambdabehave.JunitSuiteRunner;
import org.junit.runner.RunWith;

import static com.insightfullogic.lambdabehave.Calculator.sumTwoNumbers;
import static com.insightfullogic.lambdabehave.Suite.describe;

@RunWith(JunitSuiteRunner.class)
public class DataDrivenTestPractice {
    {
        describe("a pair of numbers and their sum", it -> {
            it.uses(2, 2, 4).
                    and(3, 3, 6).
                    and(5, 4, 9).
                    toShow("%d and %d should be %d ", (expect, x, y, z) -> {
                        expect.that(sumTwoNumbers(x, y)).is(z);
                    });
        });
    }
}
