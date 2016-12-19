package controller;

public interface IEmployee {

    // if wrong input data ,throw exception
    void checkIn(String password);

    void checkOut();
}
