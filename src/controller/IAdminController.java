package controller;

public interface IAdminController extends IEmployee {

    String showAllWorkRequests();

    void confirmWorkRequest();

    String showRequestsInTheDepartment(int id);
}
