package ru.mts.hw_3.repository;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.mts.entity.Animal;
import ru.mts.entity.AnimalType;
import ru.mts.entity.Dog;
import ru.mts.entity.Wolf;
import ru.mts.service.CreateAnimalService;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DisplayName(value = "Tests of the AnimalsRepository class")
class AnimalsRepositoryImplTest {
    AnimalType animalTypeDog = AnimalType.DOG;
    AnimalType animalTypeWolf = AnimalType.WOLF;
    private final Map<String, List<Animal>> animalsMap = new HashMap<>();
    private final List<Animal> animalsDog = new ArrayList<>();
    @Autowired
    private AnimalsRepository animalsRepository;
    @Autowired
    private CreateAnimalService createAnimalService;

    protected void initAnimals() throws NoSuchFieldException, IllegalAccessException {
        animalsDog.add(new Dog("breed1", "tuzik", new BigDecimal("2844.68"), "character1", LocalDate.of(1980, 2, 8)));
        animalsDog.add(new Dog("breed2", "bumburuwka", new BigDecimal("3441.68"), "character2", LocalDate.of(1985, 4, 6)));
        animalsDog.add(new Dog("breed1", "tuzik", new BigDecimal("2844.68"), "character1", LocalDate.of(1983, 2, 8)));
        animalsDog.add(new Dog("breed4", "mushka", new BigDecimal("3061.6"), "character4", LocalDate.of(1998, 4, 10)));
        animalsDog.add(new Dog("breed5", "barsik", new BigDecimal("718.68"), "character5", LocalDate.of(1965, 1, 4)));
        animalsDog.add(new Dog("breed6", "persik", new BigDecimal("939.68"), "character6", LocalDate.of(1988, 4, 30)));
        animalsDog.add(new Dog("breed4", "mushka", new BigDecimal("3061.6"), "character4", LocalDate.of(1998, 4, 10)));
        animalsDog.add(new Dog("breed8", "mushka", new BigDecimal("3981.68"), "character8", LocalDate.of(2014, 9, 7)));
        animalsDog.add(new Dog("breed4", "mushka", new BigDecimal("3061.6"), "character4", LocalDate.of(1998, 4, 10)));
        animalsDog.add(new Dog("breed10", "abrikos", new BigDecimal("1388.68"), "character10", LocalDate.of(1994, 6, 20)));
        Field animalTypeFieldDog = createAnimalService.getClass().getDeclaredField("animalType");
        animalTypeFieldDog.setAccessible(true);
        animalTypeFieldDog.set(createAnimalService, animalTypeDog);
        animalsMap.put(animalTypeDog.name(), animalsDog);

        List<Animal> animalsWolf = new ArrayList<>();
        animalsWolf.add(new Wolf("breed1", "tuzik", new BigDecimal("2844.68"), "character1", LocalDate.of(1980, 2, 8)));
        animalsWolf.add(new Wolf("breed2", "bumburuwka", new BigDecimal("3441.68"), "character2", LocalDate.of(1985, 4, 6)));
        animalsWolf.add(new Wolf("breed1", "tuzik", new BigDecimal("2844.68"), "character1", LocalDate.of(1983, 2, 8)));
        animalsWolf.add(new Wolf("breed4", "mushka", new BigDecimal("3061.6"), "character4", LocalDate.of(1998, 4, 10)));
        animalsWolf.add(new Wolf("breed5", "barsik", new BigDecimal("718.68"), "character5", LocalDate.of(1970, 1, 4)));
        animalsWolf.add(new Wolf("breed6", "persik", new BigDecimal("939.68"), "character6", LocalDate.of(1988, 4, 30)));
        animalsWolf.add(new Wolf("breed4", "mushka", new BigDecimal("3061.6"), "character4", LocalDate.of(1998, 4, 10)));
        animalsWolf.add(new Wolf("breed8", "mushka", new BigDecimal("3981.68"), "character8", LocalDate.of(2014, 9, 7)));
        animalsWolf.add(new Wolf("breed4", "mushka", new BigDecimal("3061.6"), "character4", LocalDate.of(1998, 4, 10)));
        animalsWolf.add(new Wolf("breed10", "persik", new BigDecimal("1388.68"), "character10", LocalDate.of(1994, 6, 20)));
        Field animalTypeFieldWolf = createAnimalService.getClass().getDeclaredField("animalType");
        animalTypeFieldWolf.setAccessible(true);
        animalTypeFieldWolf.set(createAnimalService, animalTypeWolf);
        animalsMap.put(animalTypeWolf.name(), animalsWolf);
    }

    @BeforeAll
    void setUp() throws NoSuchFieldException, IllegalAccessException {
        initAnimals();
    }

    @Test
    @DisplayName(value = "Tests of the findLeapYearNames Correct")
    void findLeapYearNamesCorrect() throws NoSuchFieldException, IllegalAccessException {
        Field animalNamesField = animalsRepository.getClass().getDeclaredField("animals");
        animalNamesField.setAccessible(true);
        animalNamesField.set(animalsRepository, animalsMap);

        List<Animal> animalList = animalsMap.get(animalTypeDog.toString());
        assertThat(animalList.get(0), instanceOf(Animal.class));
        assertEquals("breed1", animalList.get(0).getBreed());

        Map<String, LocalDate> names = animalsRepository.findLeapYearNames();
        assertEquals(4, names.size());
        assertTrue(names.containsKey("DOG persik"));
        assertTrue(names.containsKey("WOLF persik"));
    }

    @Test
    @DisplayName(value = "Tests of the findLeapYearNames Incorrect - null")
    void findLeapYearNamesIncorrect() throws NoSuchFieldException, IllegalAccessException {
        Field animalNamesField = animalsRepository.getClass().getDeclaredField("animals");
        animalNamesField.setAccessible(true);
        animalNamesField.set(animalsRepository, null);
        Map<String, LocalDate> names = animalsRepository.findLeapYearNames();
        assertEquals(0, names.size());
    }

    @Test
    @DisplayName(value = "Tests of the findOlderAnimal correct")
    void findOlderAnimalCorrect() throws NoSuchFieldException, IllegalAccessException {
        int N = 15;
        Field animalNamesField = animalsRepository.getClass().getDeclaredField("animals");
        animalNamesField.setAccessible(true);
        animalNamesField.set(animalsRepository, animalsMap);
        Map<Animal, Integer> olderAnimals = animalsRepository.findOlderAnimal(N);
        assertEquals(14, olderAnimals.size());
        assertTrue(olderAnimals.containsKey(
                new Dog("breed1", "tuzik", new BigDecimal("2844.68"), "character1", LocalDate.of(1980, 2, 8))));
    }

    @Test
    @DisplayName(value = "Tests of the findOlderAnimal incorrect")
    void findOlderAnimalIncorrect() throws NoSuchFieldException, IllegalAccessException {
        int N = 15;
        Field animalNamesField = animalsRepository.getClass().getDeclaredField("animals");
        animalNamesField.setAccessible(true);
        animalNamesField.set(animalsRepository, null);
        Map<Animal, Integer> olderAnimals = animalsRepository.findOlderAnimal(N);
        assertEquals(0, olderAnimals.size());
    }

    @Test
    @DisplayName(value = "Tests of the findOlderAnimal max")
    void findOlderAnimalMax() throws NoSuchFieldException, IllegalAccessException {
        int N = 100;
        Field animalNamesField = animalsRepository.getClass().getDeclaredField("animals");
        animalNamesField.setAccessible(true);
        animalNamesField.set(animalsRepository, animalsMap);
        Map<Animal, Integer> olderAnimals = animalsRepository.findOlderAnimal(N);
        assertEquals(1, olderAnimals.size());
        assertTrue(olderAnimals.containsKey(
                new Dog("breed5", "barsik", new BigDecimal("718.68"), "character5", LocalDate.of(1965, 1, 4))));
    }

    @Test
    @DisplayName(value = "Tests of the findDuplicate correct")
    void findDuplicateCorrect() throws NoSuchFieldException, IllegalAccessException {
        Field animalNamesField = animalsRepository.getClass().getDeclaredField("animals");
        animalNamesField.setAccessible(true);
        animalNamesField.set(animalsRepository, animalsMap);
        Map<String, List<Animal>> duplicateAnimals = animalsRepository.findDuplicate();
        assertEquals(2, duplicateAnimals.size());
        assertEquals(1, duplicateAnimals.get(animalTypeDog.name()).size());
        assertEquals(1, duplicateAnimals.get(animalTypeWolf.name()).size());
    }

    @Test
    @DisplayName(value = "Tests of the findDuplicate incorrect")
    void findDuplicateIncorrect() throws NoSuchFieldException, IllegalAccessException {
        Field animalNamesField = animalsRepository.getClass().getDeclaredField("animals");
        animalNamesField.setAccessible(true);
        animalNamesField.set(animalsRepository, null);
        Map<String, List<Animal>> duplicateAnimals = animalsRepository.findDuplicate();
        assertEquals(0, duplicateAnimals.size());
    }

    @Test
    @DisplayName(value = "Tests of the findAverageAge correct")
    void findAverageAgeCorrect() {
        Integer sum = animalsDog.stream()
                .filter(x -> x.getBirthDate().isBefore(LocalDate.now()))
                .map(animal -> countYears(animal.getBirthDate()))
                .reduce(0, Integer::sum, Integer::sum);
        Double expect = (double) (sum / animalsDog.size());
        Double ageAverage = animalsRepository.findAverageAge(animalsDog);
        assertEquals(expect, ageAverage);
    }

    @Test
    @DisplayName(value = "Tests of the findAverageAge incorrect")
    void findAverageAgeIncorrect() {
        Double ageAverage = animalsRepository.findAverageAge(null);
        assertEquals(-1, ageAverage);
    }

    @Test
    @DisplayName(value = "Tests of the findOldAndExpensive correct")
    void findOldAndExpensiveCorrect() {
        List<Animal> oldAndExpensive = animalsRepository.findOldAndExpensive(animalsDog);
        assertEquals(7, oldAndExpensive.size());
        assertEquals(LocalDate.of(1980, 2, 8), oldAndExpensive.get(0).getBirthDate());
        assertEquals(LocalDate.of(2014, 9, 7), oldAndExpensive.get(oldAndExpensive.size() - 1).getBirthDate());
    }

    @Test
    @DisplayName(value = "Tests of the findOldAndExpensive InCorrect")
    void findOldAndExpensiveInCorrect() {
        List<Animal> oldAndExpensive = animalsRepository.findOldAndExpensive(null);
        assertEquals(0, oldAndExpensive.size());
    }

    @Test
    @DisplayName(value = "Tests of the findMinConstAnimals Correct")
    void findMinConstAnimalsCorrect() {
        List<String> names = animalsRepository.findMinConstAnimals(animalsDog);
        List<String> namesExpexted = new ArrayList<>();
        namesExpexted.add("persik");
        namesExpexted.add("barsik");
        namesExpexted.add("abrikos");
        assertEquals(3, names.size());
        Assertions.assertIterableEquals(namesExpexted, names);
    }

    @Test
    @DisplayName(value = "Tests of the findMinConstAnimals InCorrect")
    void findMinConstAnimalsInCorrect() {
        List<String> names = animalsRepository.findMinConstAnimals(null);
        assertEquals(0, names.size());
    }

    private Integer countYears(LocalDate localDate) {
        return Period.between(localDate, LocalDate.now()).getYears();
    }
}