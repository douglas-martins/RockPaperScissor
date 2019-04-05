package br.univali.kob.model;

import java.util.Random;
import java.util.Scanner;

public class Game {
    private Player playerA;
    private Player playerB;
    private int numberOfRounds;
    private List<GameRound> rounds;

    public Game(Player playerA, Player playerB, int numberOfRounds) {
        this.playerA = new Player(playerA);
        this.playerB = new Player(playerB);
        this.numberOfRounds = numberOfRounds;
        this.rounds = new List<>(numberOfRounds);
    }

    public Player getPlayerA() {
        return this.playerA;
    }

    public Player getPlayerB() {
        return this.playerB;
    }

    public int getNumberOfRounds() {
        return this.numberOfRounds;
    }

    public List<GameRound> getRounds() {
        return this.rounds;
    }

    public void simulateRound() {
        GameRound round = new GameRound(TypesOfPlay.values()[new Random().nextInt(3)],
                TypesOfPlay.values()[new Random().nextInt(3)]);
        round.setGameRoundWinner(playerA, playerB);
        rounds.insert(round);
    }

    public Player getGameWinner() {
        int playerAWins = 0;
        int playerBWins = 0;

        for (int i = 0; i < this.rounds.getSize(); i++) {
            if (List.convertInstanceOfObject
                    (this.rounds.getElement(i), GameRound.class)
                    .getWinner().getName().equals(playerA.getName())) {
                this.playerA.getPlayerWins().insert(
                        List.convertInstanceOfObject(this.rounds.getElement(i), GameRound.class));
                playerAWins++;
            } else {
                this.playerB.getPlayerWins().insert(
                        List.convertInstanceOfObject(this.rounds.getElement(i), GameRound.class));
                playerBWins++;
            }
        }

        return (playerAWins > playerBWins ? this.playerA : this.playerB);
    }

    public void simulateGame() {
        int roundQuantity = 0;
        char continueGame = 'Y';
        System.out.println("########## Rock, Paper & Scissor!! (Just a simulation) ##########");

        while (Character.toUpperCase(continueGame) == 'Y') {
            do {
                simulateRound();
                roundQuantity++;
            } while(roundQuantity < this.numberOfRounds);

            Player winner = getGameWinner();

            System.out.println("Game Over!");
            System.out.println("The winner is: " + winner.getName());

            printGameRounds();
            System.out.println("=========================================================");
            printGameStats(roundQuantity);

            System.out.println("Do you want to simulate again? (Y to yes / N to no)?");
            Scanner scanner = new Scanner(System.in);
            continueGame = scanner.next().charAt(0);

            if (Character.toUpperCase(continueGame) == 'Y') {
                roundQuantity = 0;
            }
        }
    }

    private void printGameRounds() {
        System.out.println("Game Rounds {");
        for (int i = 0; i < this.numberOfRounds; i++) {
            System.out.println("    Round: " + (i + 1));
            System.out.println("    Player_A (" +
                    List.convertInstanceOfObject(this.rounds.getElement(i), GameRound.class).getPlayerAPlay() + ")"
                    + " X " + "Player_B (" +
                    List.convertInstanceOfObject(this.rounds.getElement(i), GameRound.class).getPlayerBPlay() + ")");
            System.out.println("    The winner is: "
                    + List.convertInstanceOfObject(this.rounds.getElement(i), GameRound.class).getWinner().getName());
            System.out.println("}");
        }
    }

    private void printGameStats(int roundQuantity) {
        System.out.println("Game Stats {");
        System.out.println("    Number of rounds simulated: " + roundQuantity);
        System.out.println("    Player A {");
        System.out.println("        Number of rounds win: " + this.playerA.getPlayerWins().getSize());
        System.out.println("        Number of ROCK played: " + this.playerA.playerNumberOfPlayRock());
        System.out.println("        Number of PAPER played: " + this.playerA.playerNumberOfPlayPaper());
        System.out.println("        Number of SCISSOR played: " + this.playerA.playerNumberOfPlayScissor());
        System.out.println("    }");
        System.out.println("    Player B {");
        System.out.println("        Number of rounds win: " + this.playerB.getPlayerWins().getSize());
        System.out.println("        Number of ROCK played: " + this.playerB.playerNumberOfPlayRock());
        System.out.println("        Number of PAPER played: " + this.playerB.playerNumberOfPlayPaper());
        System.out.println("        Number of SCISSOR played: " + this.playerB.playerNumberOfPlayScissor());
        System.out.println("    }");
        System.out.println("}");
    }
}
