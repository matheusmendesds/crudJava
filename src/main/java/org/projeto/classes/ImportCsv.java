package org.projeto.classes;

import org.projeto.conn.ConnectionFactory;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ImportCsv {
    public static void main(String[] args) throws FileNotFoundException {
        String file = "dadosattt.csv";
        String sql = "INSERT INTO `dados`.`player_stats` (`player_name`, `age`, `team`, `position`, `games`, `games_started`, `minutes`, `pts`, `ast`, `orb`, `drb`, `trb`, `stl`, `blk`) VALUES (? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? );";
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            Connection conn = ConnectionFactory.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            String linha;
            br.readLine();

            while ((linha = br.readLine()) != null) {

                String[] d = linha.split(",");
                int i = 0;
                ps.setString(++i, d[0]);
                ps.setInt(++i, Integer.parseInt(d[1]));
                ps.setString(++i, d[2]);
                ps.setString(++i, d[3]);
                ps.setInt(++i, Integer.parseInt(d[4]));
                ps.setInt(++i, Integer.parseInt(d[5]));
                ps.setDouble(++i, Double.parseDouble(d[6]));
                ps.setDouble(++i, Double.parseDouble(d[7]));
                ps.setDouble(++i, Double.parseDouble(d[8]));
                ps.setDouble(++i, Double.parseDouble(d[9]));
                ps.setDouble(++i, Double.parseDouble(d[10]));
                ps.setDouble(++i, Double.parseDouble(d[11]));
                ps.setDouble(++i, Double.parseDouble(d[12]));
                ps.setDouble(++i, Double.parseDouble(d[13]));
                ps.executeUpdate();

            }
            System.out.println("Importação concluída!");
        } catch (IOException | SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
