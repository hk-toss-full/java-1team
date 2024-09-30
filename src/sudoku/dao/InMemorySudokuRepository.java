package sudoku.dao;

import sudoku.global.Data;

public class InMemorySudokuRepository implements SudokuRepository {

    @Override
    public int[][] getSudokuPuzzle(String difficulty) {
        switch (difficulty.toLowerCase()) {
            case "easy":
                return Data.sudokuTableEasy;
            case "medium":
                return Data.sudokuTableMiddle;
            case "hard":
                return Data.sudokuTableHard;
            default:
                throw new IllegalArgumentException("잘못된 난이도입니다.");
        }
    }

    @Override
    public int[][] getSudokuSolution(String difficulty) {
        switch (difficulty.toLowerCase()) {
            case "easy":
                return Data.sudokuTableEasyAnswer;
            case "medium":
                return Data.sudokuTableMiddleAnswer;
            case "hard":
                return Data.sudokuTableHardAnswer;
            default:
                throw new IllegalArgumentException("잘못된 난이도입니다.");
        }
    }
}
