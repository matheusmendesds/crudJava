package org.projeto;

import org.projeto.conn.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        testNomeTime("2TM");
    }

    public static void testNomeTime(String name) {
        try (Connection conn = ConnectionFactory.getConnection()){
            PreparedStatement ps = testeSql(conn,name);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println("Player: " + rs.getString("player_name"));
                System.out.println("Team: " + rs.getString("team"));
                System.out.println("Points per Game: " + rs.getString("pts"));
                System.out.println("---------------------");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private static PreparedStatement testeSql(Connection conn , String name) throws SQLException {
        String sql = "select * from dados.player_stats WHERE team LIKE ? ;";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, "%" + name + "%");
        return ps;
    }
}