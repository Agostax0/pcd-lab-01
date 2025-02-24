package pcd.lab01.ex01;

import static org.fusesource.jansi.Ansi.*;
import static pcd.lab01.ex01.AuxLib.getWordsPos;

import org.fusesource.jansi.AnsiConsole;

/**
 *
 * Simple program showing basic features
 * of J-ANSI lib.
 *
 * To be run from a command line.
 *
 */
public class VerticalFallingWordsScreen {

    public static void main(String[] args) {

        Screen screen = Screen.getInstance();
        screen.clear();

        var sentence = "This is a simple sentence with words ready to fall";

        var wordList = getWordsPos(sentence);



        for(int threadIndex = 0; threadIndex < Runtime.getRuntime().availableProcessors() - 1 && threadIndex < wordList.size(); threadIndex++){
            var wordThread = new FallingWordThread(
                    screen,
                    wordList.get(threadIndex).word(),
                    threadIndex,
                    wordList.get(threadIndex).pos()
            );

            wordThread.start();

        }
    }

}
