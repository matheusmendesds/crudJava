package org.projeto.classes;

import org.projeto.conn.ConnectionFactory;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ImportCsv {
    public static void main(String[] args) throws FileNotFoundException {
        String file = "dados.csv";
        String sql = "INSERT INTO `dados`.`player_stats` (`player_name`, `age`, `team`, `position`, `games`, `games_started`, `minutes`, `fg_made`, `fg_atp`, `fg_pct`, `three_made`, `three_atp`, `three_pct`, `two_made`, `two_atp`, `two_pct`, `efg_pct`, `ft_made`, `ft_atp`, `ft_pct`, `orb`, `drb`, `trb`, `ast`, `stl`, `blk`, `tov`, `pf`, `pts`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?, ?, ?, ?, ?, ?,?,?, ?,?,?,?,?, ? , ?, ?, ?);";
        try (BufferedReader br = new BufferedReader(new FileReader(file))){
            Connection conn = ConnectionFactory.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            String linha;
            br.readLine();

            while ((linha = br.readLine()) != null) {

                String[] d = linha.split(",");
                System.out.println("LINHA: [" + linha + "]");
                int i = 0;
                ps.setString(++i,d[0]);
                ps.setInt(++i,Integer.parseInt(d[1]));
                ps.setString(++i,d[2]);
                ps.setString(++i,d[3]);
                ps.setInt(++i,Integer.parseInt(d[4]));
                ps.setInt(++i,Integer.parseInt(d[5]));
                ps.setDouble(++i, Double.parseDouble(d[6]));
                ps.setDouble(++i, Double.parseDouble(d[7]));
                ps.setDouble(++i, Double.parseDouble(d[8]));
                ps.setDouble(++i, Double.parseDouble(d[9]));
                ps.setDouble(++i, Double.parseDouble(d[10]));
                ps.setDouble(++i, Double.parseDouble(d[11]));
                ps.setDouble(++i, Double.parseDouble(d[12]));
                ps.setDouble(++i, Double.parseDouble(d[13]));
                ps.setDouble(++i, Double.parseDouble(d[14]));
                ps.setDouble(++i, Double.parseDouble(d[15]));
                ps.setDouble(++i, Double.parseDouble(d[16]));
                ps.setDouble(++i, Double.parseDouble(d[17]));
                ps.setDouble(++i, Double.parseDouble(d[18]));
                ps.setDouble(++i, Double.parseDouble(d[19]));
                ps.setDouble(++i, Double.parseDouble(d[20]));
                ps.setDouble(++i, Double.parseDouble(d[21]));
                ps.setDouble(++i, Double.parseDouble(d[22]));
                ps.setDouble(++i, Double.parseDouble(d[23]));
                ps.setDouble(++i, Double.parseDouble(d[24]));
                ps.setDouble(++i, Double.parseDouble(d[25]));
                ps.setDouble(++i, Double.parseDouble(d[26]));
                ps.setDouble(++i, Double.parseDouble(d[27]));
                ps.setDouble(++i, Double.parseDouble(d[28]));

                ps.executeUpdate();

            }
            System.out.println("Importação concluída!");
        } catch (IOException | SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
