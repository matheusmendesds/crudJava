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

    private static PreparedStatement createdPreparedStatementFindByName(Connection conn, String name) throws SQLException {
        String sql = "SELECT * FROM dados.player_stats WHERE player_name LIKE ?;";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, String.format("%%%s%%", name));
        return ps;
    }
    public static void delete(int id) {
        try(Connection conn = ConnectionFactory.getConnection();
        PreparedStatement ps = createdPreparedStatementDelete(conn,id)) {
            ps.execute();
            log.info("Deleted player data '{}' from the database", id);
        } catch (SQLException e) {
            log.error("Error while trying to delete player '{}'", id, e);
        }
    }
    private static PreparedStatement createdPreparedStatementDelete(Connection conn , int id) throws SQLException {
        String sql = "DELETE FROM `dados`.`player_stats` WHERE (`id` = ?);";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1,id);
        return ps;
    }
    public static void save(PlayerModel player) {
        log.info("Saving Producer '{}'", player);
        try (Connection conn = ConnectionFactory.getConnection();
        PreparedStatement ps = createdPreparedStatementSave(conn,player)){
            ps.execute();

        } catch (SQLException e) {
            log.error("Error while trying to save player '{}'", player.getId(), e);
        }

    }
    private static PreparedStatement createdPreparedStatementSave(Connection conn, PlayerModel player) throws SQLException {
        String sql = "INSERT INTO `dados`.`player_stats` (`player_name`, `age`, `team`, `position`, `games`, `games_started`, `minutes`, `pts`, `ast`, `orb`, `drb`, `trb`, `stl`, `blk`) VALUES (?, ?, ?, ?,?, ?, ?, ?,?, ?, ?, ?,?, ?);";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1,player.getPlayer_name());
        ps.setInt(2,player.getAge());
        ps.setString(3,player.getTeam());
        ps.setString(4,player.getPosition());
        ps.setInt(5,player.getGames());
        ps.setInt(6,player.getGames_started());
        ps.setDouble(7,player.getMinutes());
        ps.setDouble(8,player.getPts());
        ps.setDouble(9,player.getAst());
        ps.setDouble(10,player.getOrb());
        ps.setDouble(11,player.getDrb());
        ps.setDouble(12,player.getTrb());
        ps.setDouble(13,player.getStl());
        ps.setDouble(14,player.getBlk());
        return ps;
    }

}
