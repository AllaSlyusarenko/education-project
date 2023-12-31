package ru.mts.hw_3;

import ru.mts.hw_3.entity.Animal;
import ru.mts.hw_3.entity.AnimalFactory;
import ru.mts.hw_3.entity.AnimalType;
import ru.mts.hw_3.service.CreateAnimalServiceImpl;
import ru.mts.hw_3.service.SearchService;
import ru.mts.hw_3.service.SearchServiceImpl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        AnimalFactory animalFactory = new AnimalFactory();
        CreateAnimalServiceImpl service = new CreateAnimalServiceImpl(animalFactory);
        SearchService searchService = new SearchServiceImpl();
        Animal[] animals10 = service.createAnimals();
        int numberOfNewAnimals = 20;
        Animal[] animalsN = service.createAnimals(numberOfNewAnimals);
        int maxAge = 35;

        System.out.println("findLeapYearNames-------------------------------------------------------------------------------------");
        System.out.println(Arrays.toString(searchService.findLeapYearNames(animals10)));
        System.out.println(Arrays.toString(searchService.findLeapYearNames(animalsN)));
        System.out.println(Arrays.toString(searchService.findLeapYearNames(new Animal[0])));
        System.out.println(Arrays.toString(searchService.findLeapYearNames(new Animal[5])));

        System.out.println("findOlderAnimal---------------------------------------------------------------------------------------");
        System.out.println(Arrays.toString(searchService.findOlderAnimal(animals10, maxAge)));
        System.out.println(Arrays.toString(searchService.findOlderAnimal(animalsN, maxAge)));
        System.out.println(Arrays.toString(searchService.findOlderAnimal(new Animal[0], maxAge)));
        System.out.println(Arrays.toString(searchService.findOlderAnimal(new Animal[5], maxAge)));

        System.out.println("findDuplicate-----------------------------------------------------------------------------------------");
        Animal[] animals = new Animal[8];
        animals[0] = null;
        animals[1] = animalFactory.createAnimal(AnimalType.DOG, "breed11", "name11", new BigDecimal("3224.68"), "character11", LocalDate.of(1970, 5, 22));
        animals[2] = animalFactory.createAnimal(AnimalType.WOLF, "breed21", "name21", new BigDecimal("34.68"), "character21", LocalDate.of(2010, 3, 22));
        animals[3] = animalFactory.createAnimal(AnimalType.DOG, "breed11", "name11", new BigDecimal("3224.68"), "character11", LocalDate.of(1970, 5, 22));
        animals[4] = null;
        animals[5] = animalFactory.createAnimal(AnimalType.DOG, "breed11", "name11", new BigDecimal("3224.68"), "character11", LocalDate.of(1970, 5, 22));
        animals[6] = animalFactory.createAnimal(AnimalType.DOG, "breed21", "name11", new BigDecimal("3224.68"), "character11", LocalDate.of(1970, 5, 22));
        animals[7] = animalFactory.createAnimal(AnimalType.WOLF, "breed21", "name21", new BigDecimal("34.68"), "character21", LocalDate.of(2010, 3, 22));

        System.out.println(Arrays.toString(searchService.findDuplicate(animals)));
        System.out.println(Arrays.toString(searchService.findDuplicate(animals10)));
        System.out.println(Arrays.toString(searchService.findDuplicate(new Animal[0])));
        System.out.println(Arrays.toString(searchService.findDuplicate(new Animal[5])));
    }
}