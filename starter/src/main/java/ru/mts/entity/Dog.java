package ru.mts.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Dog extends Pet {
    public Dog() {
    }


    public Dog(AnimalType animalType, String breed, String name, BigDecimal cost, String character, LocalDate birthDate) {
        super(animalType, breed, name, cost, character, birthDate);
    }

    /**
     * Метод - возвращает строку, представляющую объект Dog(Собака)
     */
    @Override
    public String toString() {
        return "Dog {" +
                "breed='" + breed + '\'' +
                ", name='" + name + '\'' +
                ", cost=" + cost +
                ", character='" + character + '\'' +
                ", birthDate=" + birthDate +
                ", secretInformation='" + secretInformation + '\'' +
                '}';
    }
}