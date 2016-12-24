package controller;

import com.sun.istack.internal.NotNull;
import javafx.collections.ObservableList;
import model.SupportRequest;

public interface ISupport extends IEmployee {

    SupportRequest ask(@NotNull String email, @NotNull String question);

    void reply(SupportRequest supportRequest, String text);

    ObservableList<SupportRequest> showRequests();
}
