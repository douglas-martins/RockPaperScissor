package br.univali.kob.model;

import java.util.Objects;

public class Player {
    private String name;
    private List<TypesOfPlay> playerPlays;
    private List<GameRound> playerWins;

    public Player(String name, int length) {
        this.name = name;
        this.playerPlays = new List<>(length);
        this.playerWins = new List<>(length);
    }

    public Player(Player player) {
        this.name = player.getName();
        this.playerPlays = new List<>(player.getPlayerPlays());
        this.playerWins = new List<>(player.getPlayerWins());
    }

    public String getName() {
        return this.name;
    }

    public List<TypesOfPlay> getPlayerPlays() {
        return this.playerPlays;
    }

    public List<GameRound> getPlayerWins() {
        return this.playerWins;
    }

    public int playerNumberOfPlayRock() {
        return numberOfTypePlay(TypesOfPlay.ROCK);
    }

    public int playerNumberOfPlayPaper() {
        return numberOfTypePlay(TypesOfPlay.PAPER);
    }

    public int playerNumberOfPlayScissor() {
        return numberOfTypePlay(TypesOfPlay.SCISSOR);
    }

    private int numberOfTypePlay(TypesOfPlay type) {
        int value = 0;
        for (int i = 0; i < this.playerPlays.getSize(); i++) {
            if (List.convertInstanceOfObject(
                    this.playerPlays.getElement(i), TypesOfPlay.class).equals(type)) {
                value++;
            }
        }
        return value;
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", playerPlays=" + playerPlays +
                ", playerWins=" + playerWins +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(name, player.name) &&
                Objects.equals(playerPlays, player.playerPlays) &&
                Objects.equals(playerWins, player.playerWins);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, playerPlays, playerWins);
    }
}
