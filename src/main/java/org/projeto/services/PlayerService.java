package org.projeto.services;

import org.projeto.repository.PlayerRepository;

import java.util.Scanner;

public class PlayerService {
    private static final Scanner SCANNER = new Scanner(System.in);
    public static void menu(int option){
        switch (option) {
            case 1 -> findByName();
            case 2 -> delete();
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
}
