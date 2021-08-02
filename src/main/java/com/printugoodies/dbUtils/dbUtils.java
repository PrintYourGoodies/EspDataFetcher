package com.printugoodies.dbUtils;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class dbUtils {

    static String url = "jdbc:postgresql://192.168.1.120:5432/smartHome";
    static String user = "mkiryluk";
    static String password = "qwerty";

    public static void test(float current, float power){

        String query = "INSERT INTO power_measurement(current, power) VALUES(?, ?)";

        try (Connection con = DriverManager.getConnection(url, user, password);
             PreparedStatement pst = con.prepareStatement(query)) {

            pst.setFloat(1, current);
            pst.setFloat(2, power);
            pst.executeUpdate();

        } catch (SQLException ex) {

            System.out.println(ex.getMessage());
        }
    }
}


