package br.univali.kob.test;

import br.univali.kob.model.Game;
import br.univali.kob.model.Player;

import java.util.Scanner;

public class GameTest {
    public static void main(String[] args) {
        System.out.println("##### Simulate Game #####");
        System.out.println("Enter with the number of rounds you want to simulate: ");
        Scanner scanner = new Scanner(System.in);

        int numberOfRounds = scanner.nextInt();



        Player playerA = new Player("Player_A", numberOfRounds);
        Player playerB = new Player("Player_B", numberOfRounds);


        Game game = new Game(playerA, playerB, numberOfRounds);

        game.simulateGame();
    }
}
