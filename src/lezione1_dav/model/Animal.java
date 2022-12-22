package lezione1_dav.model;

import java.time.LocalDate;

public abstract class Animal {
    private String name;
    private String preferedFood;
    private int age;
    private LocalDate dateEntry;
    private double weight;
    private double height;

    protected Animal(String name, String preferedFood, int age, LocalDate dateEntry, double weight, double height) {
        this.name = name;
        this.preferedFood = preferedFood;
        this.age = age;
        this.dateEntry = dateEntry;
        this.weight = weight;
        this.height = height;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPreferedFood(String preferedFood) {
        this.preferedFood = preferedFood;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setDateEntry(LocalDate dateEntry) {
        this.dateEntry = dateEntry;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public String getName() {
        return name;
    }

    public String getPreferedFood() {
        return preferedFood;
    }

    public int getAge() {
        return age;
    }

    public LocalDate getDateEntry() {
        return dateEntry;
    }

    public double getWeight() {
        return weight;
    }

    public double getHeight() {
        return height;
    }
}
