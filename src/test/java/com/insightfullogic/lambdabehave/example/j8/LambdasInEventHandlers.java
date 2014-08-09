package com.insightfullogic.lambdabehave.example.j8;

public class LambdasInEventHandlers {

/*

//Lambda expressions can be very useful when working with event handlers(Commonly they use une method
just on the interfaces).

//BEFORE JAVA-8
 btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
            }
        });

//WITH JAVA 8
        btn.setOnAction(
          event -> System.out.println("Hello World!")
        );
*/

}
