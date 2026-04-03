package org.projeto;

import org.projeto.conn.ConnectionFactory;
import org.projeto.services.PlayerService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    private static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) throws SQLException {
        int option;
        while (true) {
            menu();
            option = Integer.parseInt(SCANNER.nextLine());
            if (option == 0) break;
            switch (option) {
                case 1 -> {
                    playerMenu();
                    option = Integer.parseInt(SCANNER.nextLine());
                    PlayerService.menu(option);

                }
            }


        }
    }

    private static void menu() {
        System.out.println("-----Database NBA----");
        System.out.println("Find informations here");
        System.out.println("1-Options");
        System.out.println("0-Exit");
    }

    private static void playerMenu() {
        System.out.println("1-Find player by name");
        System.out.println("2-Delete player by Id");
        System.out.println("3-Save new player");
        System.out.println("4-Update player");

    }

}
