package sudoku.dao;

public interface SudokuRepository {
    int[][] getSudokuPuzzle(int difficulty);   // 난이도에 맞는 문제 반환
    int[][] getSudokuSolution(int difficulty); // 난이도에 맞는 정답 반환
}