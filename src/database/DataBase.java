package database;

import geolocation.controller.GoogleMapsAPI;
import geolocation.controller.GoogleMapsAPIImpl;
import model.Department;
import model.Request;

import com.sun.istack.internal.NotNull;
import model.SupportRequest;
import model.WorkRequest;

import java.util.*;

public class DataBase {

    // why this one is static
    public static List<Department> departments = DepartmentList.getDepartments();

    // why these are not static
    private List<Request> requests = new LinkedList<>();
    private List<Request> delivered = new ArrayList<>();
    private List<WorkRequest> workRequests = new ArrayList<>();
    private List<SupportRequest> supportRequests = new ArrayList<>();

    public static List<Department> getDepartments() {
        return departments;
    }

    public List<Request> getRequests() {
        return requests;
    }

    public List<Request> getDelivered() {
        return delivered;
    }

    public List<SupportRequest> getSupportRequests() {
        return supportRequests;
    }

    public void addWorkRequest(@NotNull WorkRequest request) {
        workRequests.add(request);
    }

    public WorkRequest removeWorkRequest(WorkRequest workRequest) {

        workRequests.remove(workRequest);
        return workRequest;
    }

    public List<WorkRequest> getWorkRequests() {
        return workRequests;
    }

    public void addRequest(@NotNull Request request) {
        requests.add(request);
    }

    public Request removeRequest(String name) {
        Request res = null;

        for (Request r : requests) {
            if (r.getProduct().getName().equals(name)) {
                res = r;
                break;
            }
        }
        requests.remove(res);
        return res;
    }

    public void deliver(@NotNull Request request) {
        delivered.add(request);
    }

    public Request removeDelivered(int id) {
        for (Request request : delivered) {
            if (request.getId() == id) {
                try {
                    delivered.remove(id);
                    return request;
                } catch (IndexOutOfBoundsException e) {
                    e.printStackTrace();
                }
            }
        }

        throw new IndexOutOfBoundsException();
    }

    public void addSupportRequest(@NotNull SupportRequest supportRequest) {
        supportRequests.add(supportRequest);
    }

    public SupportRequest removeSupportRequest(int id) {

        for (int i = 0; i < supportRequests.size(); i++) {
            if (supportRequests.get(i).getId() == id) {
                return supportRequests.remove(i);
            }
        }

        throw new NoSuchElementException();
    }


    private static class DepartmentList {

        private static List<Department> departments = new ArrayList<>();

        static {

            GoogleMapsAPI googleMapsAPI = new GoogleMapsAPIImpl();

            Department department4 = new Department(4, googleMapsAPI.
                    findLocation("Україна", "Запоріжжя", "Перемоги", "40"));

            Department department3 = new Department(3, googleMapsAPI.
                    findLocation("Україна", "Вінниця", "Коцюбинського", "30"));

            Department department2 = new Department(2, googleMapsAPI.
                    findLocation("Україна", "Харків", "Сумська", "126"));

            Department department1 = new Department(1, googleMapsAPI.
                    findLocation("Україна", "Львів", "Словацького", "1"));

            Department department = new Department(0, googleMapsAPI.
                    findLocation("Україна", "Київ", "Хрещатик", "22"));

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