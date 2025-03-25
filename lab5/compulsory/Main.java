package org.example;

import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Repository repository = new Repository();

        repository.addImage(new Image("cat", LocalDate.now(), List.of("white", "small"),
                "C:/Users/diana/Desktop/java/lab5/lab5_compulsory/Images/cat.jpg"));
        repository.addImage(new Image("dog", LocalDate.now(), List.of("bichon", "white"),
                "C:/Users/diana/Desktop/java/lab5/lab5_compulsory/Images/dog.jpg"));

        repository.displayImage("dog");
    }
}
