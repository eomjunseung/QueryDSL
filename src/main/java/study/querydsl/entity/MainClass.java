package study.querydsl.entity;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

class Person {

    String name;
    Boolean isMale;
    Integer age;

    public Person(String name, Boolean isMale, Integer age) {
        super();
        this.name = name;
        this.isMale = isMale;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getIsMale() {
        return isMale;
    }

    public void setIsMale(Boolean isMale) {
        this.isMale = isMale;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person [name : " + name + ", isMale : " + isMale + ", age : " + age + "]";
    }

}

public class MainClass {

    public static List<Person> randomPerson(int size) {
        Random random = new Random();
        List<Person> personList = new ArrayList<Person>();
        String[] familyName = new String[] { "Kim", "Kang", "Lee", "Park", "Oh", "Bang" };
        Boolean[] isMale = new Boolean[] { true, false };
        for (int i = 0; i < size; i++) {
            personList.add(new Person(familyName[random.nextInt(6)], isMale[random.nextInt(2)], random.nextInt(20) + 15));
        }
        return personList;
    }

    public static void main(String[] args) {

        List<Person> personList = randomPerson(10);

        System.out.println("[People]");
        personList.stream().forEach(System.out::println);
        System.out.println();

        Map<String, List<Person>> nameGroup = personList.stream().collect(Collectors.groupingBy(Person::getName));
        Set<String> nameSet = nameGroup.keySet();
        for (String name : nameSet) {
            System.out.println("[Name : " + name + "]");
            List<Person> list = nameGroup.get(name);
            list.stream().forEach(System.out::println);
        }
        System.out.println();

        Map<Integer, List<Person>> ageGroup = personList.stream().collect(Collectors.groupingBy((person) -> {
            Integer age = person.getAge();
            if (age >= 30) {
                return 30;
            } else if (age >= 20) {
                return 20;
            } else {
                return 10;
            }
        }, Collectors.toList()));

        List<Integer> ageList = ageGroup.keySet().stream().collect(Collectors.toList());
        ageList.sort(Comparator.naturalOrder());
        for (Integer age : ageList) {
            System.out.println("[Age from " + age + " to " + (age + 10) + "]");
            List<Person> list = ageGroup.get(age);
            list.stream().forEach(System.out::println);
        }
    }
}