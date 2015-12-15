package ru.bagrusss.servces.account;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.bagrusss.servces.BaseInterface;

/**
 * Created by vladislav
 */
public interface AccountService extends BaseInterface {

    void removeAll();

    boolean isAdmin(String email);

    boolean addUser(@NotNull String userName, @NotNull UserProfile userProfile);

    boolean addSession(@NotNull String sessionId, @NotNull UserProfile userProfile);

    boolean removeSession(@NotNull String sessionId);

    @Nullable
    UserProfile getUser(@NotNull String userName);

    @Nullable
    UserProfile getSession(@NotNull String sessionId);

    long getCountActivatedUsers();

    long getCountRegisteredUsers();
}
