package br.univali.kob.model;

import java.util.Objects;

public class GameRound {
    private TypesOfPlay playerAPlay;
    private TypesOfPlay playerBPlay;
    private Player winner;

    public GameRound(TypesOfPlay playerAPlay, TypesOfPlay playerBPlay) {
        this.playerAPlay = playerAPlay;
        this.playerBPlay = playerBPlay;
    }

    public TypesOfPlay getPlayerAPlay() {
        return this.playerAPlay;
    }

    public TypesOfPlay getPlayerBPlay() {
        return this.playerBPlay;
    }

    public Player getWinner() {
        return this.winner;
    }

    private void setWinner(Player winner) {
        this.winner = new Player(winner);
    }

    public void setGameRoundWinner(Player playerA, Player playerB) {
        switch (this.playerAPlay) {
            case ROCK:
                checkGameRoundWinner(playerA, playerB, (this.playerBPlay != TypesOfPlay.PAPER));
                break;
            case PAPER:
                checkGameRoundWinner(playerA, playerB, (this.playerBPlay != TypesOfPlay.SCISSOR));
                break;
            case SCISSOR:
                checkGameRoundWinner(playerA, playerB, (this.playerBPlay != TypesOfPlay.ROCK));
                break;
            default:
                setWinner(playerA);
                break;
        }
    }

    private void checkGameRoundWinner (Player playerA, Player playerB, boolean condition) {
        if (condition) {
            playerA.getPlayerPlays().insert(this.playerAPlay);
            setWinner(playerA);
        } else {
            playerB.getPlayerPlays().insert(this.playerBPlay);
            setWinner(playerB);
        }
    }

    @Override
    public String toString() {
        return "GameRound{" +
                "playerAPlay=" + playerAPlay +
                ", playerBPlay=" + playerBPlay +
                ", winner=" + winner +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameRound gameRound = (GameRound) o;
        return playerAPlay == gameRound.playerAPlay &&
                playerBPlay == gameRound.playerBPlay &&
                Objects.equals(winner, gameRound.winner);
    }

    @Override
    public int hashCode() {
        return Objects.hash(playerAPlay, playerBPlay, winner);
    }
}
