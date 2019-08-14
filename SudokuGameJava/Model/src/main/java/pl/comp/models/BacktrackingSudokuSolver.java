package pl.comp.models;

import java.util.ArrayList;
import java.util.Random;

public class BacktrackingSudokuSolver implements SudokuSolver {
    public void solve(SudokuBoard board) {
        fillBoard(board);
    }

    private boolean fillBoard(SudokuBoard board) {
        Random rand = new Random();
        boolean good = true;
        int n = 9;
        int row = 0;
        int col = 0;
        ArrayList<Integer> chose = new ArrayList<Integer>(n);
        boolean flaga = true;
        for (int i = 0; i < n; i++) {
            chose.add(i + 1);
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board.getNumberFromPosition(i, j) == 0) {
                    flaga = false;
                    row = i;
                    col = j;
                    break;
                }
            }
            if (!flaga) {
                break;
            }
        }
        if (flaga) {
            return true;
        }
        int biggestPossibleNumber = 9;
        for (int i = 0; i < n; i++) {
            int w = rand.nextInt(biggestPossibleNumber - i);
            flaga = board.setNumberOnPosition(row, col, chose.get(w));
            chose.remove(w);
            if (flaga) {
                good = fillBoard(board);
                if (!good) {
                    continue;
                } else {
                    break;
                }
            }
        }
        if (!flaga || !good) {
            board.setNumberOnPosition(row, col, 0);
            return false;
        }
        return true;

    }
}
