package controller;

@FunctionalInterface
public interface IEmployee {

    void checkIn(String password) throws IllegalAccessException;
}
