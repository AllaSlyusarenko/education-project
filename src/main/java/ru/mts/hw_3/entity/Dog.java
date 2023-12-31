package ru.mts.hw_3.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Dog extends Pet {
    public Dog(String breed, String name, BigDecimal cost, String character, LocalDate birthDate) {
        super(breed, name, cost, character, birthDate);
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
                ", birthDate=" + getBirthDateString() +
                '}';
    }
}