package database;

import com.sun.istack.internal.NotNull;
import exceptions.BookedLoginException;
import model.Request;
import model.SupportRequest;
import model.User;
import model.WorkRequest;

import java.util.*;

public interface Dao {

    List<Request> requests = new LinkedList<>();
    List<Request> delivered = new ArrayList<>();
    List<WorkRequest> workRequests = new ArrayList<>();
    List<SupportRequest> supportRequests = new ArrayList<>();
    Map<String, User> users = new HashMap<>();

    void addUser(@NotNull User user) throws BookedLoginException;

    User removeUser(@NotNull String login);

    void addWorkRequest(@NotNull WorkRequest request);

    WorkRequest removeWorkRequest(WorkRequest workRequest);

    void addRequest(@NotNull Request request);

    void deliver(@NotNull Request request);

    void addSupportRequest(@NotNull SupportRequest supportRequest);
}
