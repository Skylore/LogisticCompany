package controller;

import com.sun.istack.internal.NotNull;
import model.SupportRequest;

public interface ISupport extends IEmployee {

    void ask(@NotNull SupportRequest supportRequest);

    void reply(int id, String text);

    String showRequests();
}
