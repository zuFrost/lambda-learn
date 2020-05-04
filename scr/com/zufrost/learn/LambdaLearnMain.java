package com.zufrost.learn;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.function.DoubleToIntFunction;

public class LambdaLearnMain {
    public static void main(String[] args) {


        String[] array1 = {"мама", "мыла", "раму"};
        String[] array2 = {"я", "очень", "люблю", "java"};
        String[] array3 = {"мир", "труд", "май"};

        List<String[]> arrays = new ArrayList<>();
        arrays.add(array1);
        arrays.add(array2);
        arrays.add(array3);

//        arrays.sort(new Comparator<String[]>() {
//            @Override
//            public int compare(String[] o1, String[] o2) {
//                return o1.length - o2.length;
//            }
//        });

        arrays.sort((o1, o2) -> -o1.length + o2.length);

        toString(arrays);

        Callable<Integer> c = () -> 42;
        System.out.println(c);

//        int x = 1;


//        new Thread(() -> System.out.println("Hello world!")).start();

//        while (true) {
//            new Thread(() -> System.out.println("Hello world!")).start();
//        }

        // создаем кота и выводим на экран чтоб убедиться, что он "пустой"
        Cat myCat = new Cat();
        System.out.println(myCat);

        // создаем лямбду
        Settable<Cat> s = (obj, name, age) -> {
            obj.setName(name);
            obj.setAge(age);
        };

        // вызываем метод, в который передаем кота и лямбду
        changeEntity(myCat, s);
        // выводим на экран и видим, что состояние кота изменилось (имеет имя и возраст)
        System.out.println(myCat);




    }

    private static <T extends WithNameAndAge>  void changeEntity(T entity, Settable<T> s) {
        s.set(entity, "Мурзик", 3);
    }

    public static void toString(List<String[]> o) {
        for (String[] stringArray : o) {
            System.out.print("[");
            for (String string : stringArray) {
                System.out.print(string + ", ");
            }
            System.out.println("]");
        }
    }
}

interface WithNameAndAge {
    void setName(String name);
    void setAge(int age);
}

interface Settable<C extends WithNameAndAge> {
    void set(C entity, String name, int age);
}

class Cat implements WithNameAndAge {
    private String name;
    private int age;

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Cat{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
