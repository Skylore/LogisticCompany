package database;

import geolocation.controller.GoogleMapsAPI;
import geolocation.controller.GoogleMapsAPIImpl;
import model.Department;

import java.util.LinkedList;
import java.util.Queue;

public class Departments {

    private static Queue<Department> queue = new LinkedList<>();

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

        queue.add(department);
        queue.add(department1);
        queue.add(department2);
        queue.add(department3);
        queue.add(department4);
    }

    public static Queue<Department> getQueue() {
        return queue;
    }
}
