package ru.bagrusss.servces.database.executor;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.sql.*;
import java.util.List;

/**
 * Created by vladislav
 */

@SuppressWarnings("unused")
public class Executor {

    public Executor(String confpath) {
        mConnectionPool = ConnectionPool.getInstance(confpath);
    }

    private final ConnectionPool mConnectionPool;

    public <T> T runTypedQuery(String sql, TResultHandler<T> tHandler) throws SQLException {
        try (Connection connection = mConnectionPool.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            return tHandler.handle(resultSet);
        }
    }

    public long runUpdate(@NotNull String sql) throws SQLException {
        try (Connection connection = mConnectionPool.getConnection();
             Statement statement = connection.createStatement()) {
            return statement.executeUpdate(sql);
        }
    }


    public <T> T runUpdate(@NotNull String sql, @NotNull TResultHandler<T> handler) throws SQLException {
        try (Connection connection = mConnectionPool.getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
            try (ResultSet resultSet = statement.getGeneratedKeys()) {
                return handler.handle(resultSet);
            }
        }
    }


}
