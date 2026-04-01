package org.projeto.services;

import org.projeto.repository.PlayerRepository;

import java.util.Scanner;

public class PlayerService {
    private static final Scanner SCANNER = new Scanner(System.in);
    public static void menu(int option){
        switch (option) {
            case 1 -> findByName();
        }
    }
    public static void findByName() {
        System.out.println("Type the name or empty to all");
        String name = SCANNER.nextLine();
        PlayerRepository.findByName(name)
                .forEach(p-> System.out.printf("[%d] - %s%n",p.getId(),p.getPlayer_name()));

    }
}
