package org.projeto.services;

import org.projeto.models.PlayerModel;
import org.projeto.repository.PlayerRepository;

import java.util.Scanner;

public class PlayerService {
    private static final Scanner SCANNER = new Scanner(System.in);
    public static void menu(int option){
        switch (option) {
            case 1 -> findByName();
            case 2 -> delete();
            case 3 -> save();
        }
    }
    public static void findByName() {
        System.out.println("Type the name or empty to all");
        String name = SCANNER.nextLine();
        PlayerRepository.findByName(name)
                .forEach(p-> System.out.printf("[%d] - %s%n",p.getId(),p.getPlayer_name()));

    }
    public static void delete() {
        System.out.println("Type the id of the player you want to delete");
        int id = Integer.parseInt(SCANNER.nextLine());
        System.out.println("Are you sure? S/N");
        String choice = SCANNER.nextLine();
        if ("s".equalsIgnoreCase(choice)) {
            PlayerRepository.delete(id);
        }
    }
    public static void save() {
        System.out.println("Type name of the player:");
        String name = SCANNER.nextLine();
        System.out.println("Age of the player:");
        int age = Integer.parseInt(SCANNER.nextLine());
        System.out.println("Team of the player (Only three letters , ex: GSW,LAL,LAC) :");
        String team = SCANNER.nextLine();
        System.out.println("Positon of the player(Ex:PG,SG,SF,PF,C):");
        String position = SCANNER.nextLine();
        System.out.println("Games played:");
        int games = Integer.parseInt(SCANNER.nextLine());
        System.out.println("Games started:");
        int games_started = Integer.parseInt(SCANNER.nextLine());
        System.out.println("Minutes per Game");
        double minutes = Double.parseDouble(SCANNER.nextLine());
        System.out.println("Points per Game");
        double pts = Double.parseDouble(SCANNER.nextLine());
        System.out.println("Assists per Game");
        double ast = Double.parseDouble(SCANNER.nextLine());
        System.out.println("Ofensive Rebounds per Game");
        double orb = Double.parseDouble(SCANNER.nextLine());
        System.out.println("Defensive Rebounds per Game");
        double drb = Double.parseDouble(SCANNER.nextLine());
        double trb = drb + orb;
        System.out.println("Steals per Game");
        double stl = Double.parseDouble(SCANNER.nextLine());
        System.out.println("Blocks per Game");
        double blk = Double.parseDouble(SCANNER.nextLine());
        PlayerModel playerModel = PlayerModel.builder()
                .player_name(name)
                .age(age)
                .team(team)
                .position(position)
                .games(games)
                .games_started(games_started)
                .minutes(minutes)
                .pts(pts)
                .ast(ast)
                .orb(orb)
                .drb(drb)
                .trb(trb)
                .stl(stl)
                .blk(blk)
                .build();
        PlayerRepository.save(playerModel);

    }
}
