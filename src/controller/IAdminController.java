package controller;

public interface IAdminController {

    String showAllWorkRequests();

    void confirmWorkRequest();

    String showRequestsInTheDepartment(int id);
}
