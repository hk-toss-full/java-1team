package sudoku.dao;

import sudoku.global.Data;

public class InMemorySudokuRepository implements SudokuRepository {

    @Override
    public int[][] getSudokuPuzzle(int difficulty) {
        switch (difficulty) {
            case 1:
                return Data.sudokuTableEasy;
            case 2:
                return Data.sudokuTableMiddle;
            case 3:
                return Data.sudokuTableHard;
            default:
                throw new IllegalArgumentException("잘못된 난이도입니다.");
        }
    }

    @Override
    public int[][] getSudokuSolution(int difficulty) {
        switch (difficulty) {
            case 1:
                return Data.sudokuTableEasyAnswer;
            case 2:
                return Data.sudokuTableMiddleAnswer;
            case 3:
                return Data.sudokuTableHardAnswer;
            default:
                throw new IllegalArgumentException("잘못된 난이도입니다.");
        }
    }
}
