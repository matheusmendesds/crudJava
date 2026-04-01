package org.projeto.repository;

import lombok.extern.log4j.Log4j2;
import org.projeto.conn.ConnectionFactory;
import org.projeto.models.PlayerModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public class PlayerRepository {
    public static List<PlayerModel> findByName(String name) {
        List<PlayerModel> playerList = new ArrayList<>();
        try(Connection conn = ConnectionFactory.getConnection();
        PreparedStatement ps = createdPreparedStatementFindByName(conn,name);
            ResultSet rs = ps.executeQuery();) {
            while (rs.next()) {
                PlayerModel player = PlayerModel.builder()
                        .id(rs.getInt("id"))
                        .player_name(rs.getString("player_name"))
                        .age(rs.getInt("age"))
                        .team(rs.getString("team"))
                        .position(rs.getString("position"))
                        .games(rs.getInt("games"))
                        .games_started(rs.getInt("games_started"))
                        .minutes(rs.getDouble("minutes"))
                        .pts(rs.getDouble("pts"))
                        .ast(rs.getDouble("ast"))
                        .orb(rs.getDouble("orb"))
                        .drb(rs.getDouble("drb"))
                        .trb(rs.getDouble("trb"))
                        .stl(rs.getDouble("stl"))
                        .blk(rs.getDouble("blk"))
                        .build();
                playerList.add(player);
            }

        } catch (SQLException e) {
            log.error("Error while trying to find all producers ", e);

        }
        return playerList;
    }

    public static PreparedStatement createdPreparedStatementFindByName(Connection conn, String name) throws SQLException {
        String sql = "SELECT * FROM dados.player_stats WHERE player_name LIKE ?;";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, String.format("%%%s%%", name));
        return ps;
    }
}
