package database;

import exceptions.BookedLoginException;
import geolocation.controller.GoogleMapsAPI;
import geolocation.controller.GoogleMapsAPIImpl;
import geolocation.controller.Location;
import model.*;

import com.sun.istack.internal.NotNull;

import java.util.*;

public class DataBase implements Dao {

    private static DataBase instance;

    public DataBase() {
    }

    static {
        String fromJson = new Logger().read();
        if (fromJson.isEmpty()) {
            instance = new DataBase();
        } else {
            instance = Converter.fromJson(fromJson, DataBase.class);
        }
    }

    public static DataBase getInstance() {
        return instance;
    }

    private int id = 0;
    private static List<Department> departments = DepartmentList.getDepartments();

    private Map<Integer, Request> requests = new HashMap<>();
    private Map<Integer, Request> delivered = new HashMap<>();
    private List<WorkRequest> workRequests = new ArrayList<>();
    private Map<Integer, SupportRequest> supportRequests = new HashMap<>();
    private Map<String, User> users = new HashMap<>();

    public void addUser(@NotNull User user) throws BookedLoginException {
        if (users.containsKey(user.getLogin())) {
            throw new BookedLoginException("Please choose another login");
        }

        users.put(user.getLogin(), user);
    }

    public User removeUser(@NotNull String password) {
        return users.remove(password);
    }

    public void addWorkRequest(@NotNull WorkRequest request) {
        workRequests.add(request);
    }

    public int addRequest(String email, Product product, int price, Location from, Location to) {

        requests.put(id, new Request(email, product, price, from, to));
        return id++;
    }

    public Request requestToDelivered(String name) {

        Optional<Integer> id = requests.keySet().stream().filter(key -> requests.get(key).getProduct().getName().equals(name)).findFirst();
        delivered.put(id.get(), requests.get(id.get()));
        return requests.remove(id.get());
    }


    public Request removeDelivered(int id) {
        return delivered.remove(id);

    }

    public void addSupportRequest(@NotNull SupportRequest supportRequest) {
        supportRequests.put(id++, supportRequest);
    }

    public SupportRequest removeSupportRequest(SupportRequest supportRequest) {

        Optional<Integer> id = supportRequests.keySet().stream().filter(key -> supportRequests.get(key).equals(supportRequest)).findFirst();
        return supportRequests.remove(id.get());

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

    public static List<Department> getDepartments() {
        return departments;
    }

    public Map<Integer, Request> getRequests() {
        return requests;
    }

    public Map<Integer, Request> getDelivered() {
        return delivered;
    }

    public Map<Integer, SupportRequest> getSupportRequests() {
        return supportRequests;
    }

    public List<WorkRequest> getWorkRequests() {
        return workRequests;
    }

    public Map<String, User> getUsers() {
        return users;
    }
}