package database;

import model.Department;
import model.Request;

import com.sun.istack.internal.NotNull;

import java.util.ArrayList;
import java.util.List;

public class DataBase {

    private List<Request> requests = new ArrayList<>();
    private List<Department> departments = new ArrayList<>();

    public void addRequest(@NotNull Request request) {
        requests.add(request);
    }

    public void addDepartment(@NotNull Department department) {
        departments.add(department);
    }

    public List<Request> getRequests() {
        return requests;
    }

    public List<Department> getDepartments() {
        return departments;
    }
}
