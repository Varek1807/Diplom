package ru.netology.data;

import lombok.val;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLHelper {
    private static final String url = System.getProperty("dbUrl");
    private static final String user = "app";
    private static final String password = "pass";

    public static String getScalarFromTable(String column, String tableName) throws SQLException {
        QueryRunner runner = new QueryRunner();
        try (val conn = DriverManager.getConnection(url, user, password)
        ) {
            val info = "SELECT " + column + " FROM " + tableName + " ORDER BY created DESC LIMIT 1;";
            val scalar = runner.query(conn, info, new ScalarHandler<String>());
            return scalar;
        }
    }

    public static String getStatusFromPaymentEntity() throws SQLException {
        return getScalarFromTable("status", "payment_entity");
    }

    public static String getStatusFromCreditRequestEntity() throws SQLException {
        return getScalarFromTable("status", "credit_request_entity");
    }


    public static void cleanDB() {
        val runner = new QueryRunner();
        try (val conn = DriverManager.getConnection(url, user, password)
        ) {
            runner.update(conn, "DELETE  FROM credit_request_entity;");
            runner.update(conn, "DELETE  FROM order_entity;");
            runner.update(conn, "DELETE  FROM payment_entity;");

        } catch (SQLException e) {
            System.out.println(e.toString());
        }
    }

}
