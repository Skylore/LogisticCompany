package database;

import model.Department;
import model.Request;

import com.sun.istack.internal.NotNull;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class DataBase {

    private Queue<Request> requests = new LinkedList<>();
    private List<Department> departments = new LinkedList<>();

    public void addRequest(@NotNull Request request) {
        requests.add(request);
    }

    public void addDepartment(@NotNull Department department) {
        departments.add(department);
    }

    public Queue<Request> getRequests() {
        return requests;
    }

    public List<Department> getDepartments() {
        return departments;
    }
}
