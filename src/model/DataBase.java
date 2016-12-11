package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Влад on 11.12.2016.
 */
public class DataBase {

    private List<Request> requests = new ArrayList<>();
    private List<Department> departments = new ArrayList<>();

    public List<Request> getRequests() {
        return requests;
    }

    public List<Department> getDepartments() {
        return departments;
    }
}
