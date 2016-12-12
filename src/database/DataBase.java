package database;

import geolocation.controller.GoogleMapsAPI;
import geolocation.controller.GoogleMapsAPIImpl;
import model.Department;
import model.Request;

import com.sun.istack.internal.NotNull;

import java.util.*;

public class DataBase {

    public static final List<Department> departments = DepartmentList.getDepartments();

    private List<Request> requests = new LinkedList<>();
    private List<Request> delivered = new ArrayList<>();

    public void addRequest(@NotNull Request request) {
        requests.add(request);
    }

    public Request removeRequest() {

        if (requests.size() != 0) {
            Request res = requests.get(requests.size() - 1);
            requests.remove(requests.size() - 1);
            return res;
        }

        throw new EmptyStackException();
    }

    public void deliver (@NotNull Request request) {
        delivered.add(request);
    }

    public Request removeDelivered() {
        if (delivered.size() != 0) {
            Request res = delivered.get(delivered.size() - 1);
            delivered.remove(delivered.size() - 1);
            return res;
        }

        throw new EmptyStackException();
    }

    public static List<Department> getDepartments() {
        return departments;
    }

    public List<Request> getRequests() {
        return requests;
    }

    public List<Request> getDelivered() {
        return delivered;
    }

    private static class DepartmentList {

        private static List<Department> departments = new ArrayList<>();

        static {

            GoogleMapsAPI googleMapsAPI = new GoogleMapsAPIImpl();

            Department department4 = new Department(4, googleMapsAPI.
                    findLocation("Україна", "Запоріжжя", "Перемоги", "40"), null);

            Department department3 = new Department(3, googleMapsAPI.
                    findLocation("Україна", "Вінниця", "Коцюбинського", "30"), department4);

            Department department2 = new Department(2, googleMapsAPI.
                    findLocation("Україна", "Харків", "Сумська", "126"), department3);

            Department department1 = new Department(1, googleMapsAPI.
                    findLocation("Україна", "Львів", "Словацького", "1"), department2);

            Department department = new Department(0, googleMapsAPI.
                    findLocation("Україна", "Київ", "Хрещатик", "22"), department1);

            departments.add(department);
            departments.add(department1);
            departments.add(department2);
            departments.add(department3);
            departments.add(department4);
        }

        private static List<Department> getDepartments() {
            return departments;
        }
    }
}