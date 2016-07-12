package org.fao.fenix.oecd.utils.d3s.services;

import org.postgresql.Driver;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;


public class DataSource {

    static {
        try {
            Class.forName(Driver.class.getName());
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }


    private String url, usr, psw;

    public void init(String url, String usr, String psw) {
        this.url = url;
        this.usr = usr;
        this.psw = psw;
    }

    public Connection getConnection(HashMap<String, String> config) throws SQLException {
        if (url == null)
            init(
                    config.get("policy.db.url"),
                    config.get("policy.db.usr"),
                    config.get("policy.db.psw")
            );
        Connection connection = DriverManager.getConnection(url, usr, psw);
        connection.setAutoCommit(false);
        return connection;
    }
}
