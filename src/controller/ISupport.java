package controller;

import com.sun.istack.internal.NotNull;
import javafx.collections.ObservableList;
import model.SupportRequest;

import java.util.List;

public interface ISupport extends IEmployee {

    // todo return an action confirmation
    void ask(@NotNull String email, @NotNull String question);

    void reply(int id, String text);

    ObservableList<SupportRequest> showRequests();
}
