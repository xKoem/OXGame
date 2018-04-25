package pl.xkoem;

import pl.xkoem.gamestates.EndOfGame;
import pl.xkoem.gamestates.Init;
import pl.xkoem.gamestates.Match;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class OXGame {

    private Players players;
    private final Supplier<String> userInput;
    private final Consumer<String> userOutput;
    private GameConfiguration gameConfiguration;

    public OXGame(Players players, Supplier<String> userInput, Consumer<String> userOutput) {
        this.players = players;
        this.userInput = userInput;
        this.userOutput = userOutput;
        gameConfiguration = new GameConfiguration();
    }


    /**
     * Główna metoda uruchamiająca cały proces gry po kolei
     */

    public void run() {
        Init init = new Init(userInput, userOutput, players, gameConfiguration);
        players = init.askForNames();
        gameConfiguration = init.askForConfiguration(gameConfiguration);
        players.printPlayerNames();
        Match match = new Match(userInput, userOutput, players, gameConfiguration);

        do {
            match.begin();
        } while (!match.isFinished());

        EndOfGame endOfGame = new EndOfGame(userOutput, match.getResults());
        endOfGame.showResults();
    }


}