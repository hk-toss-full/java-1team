package sudoku.dao;

public interface SudokuRepository {
    int[][] getSudokuPuzzle(String difficulty);   // 난이도에 맞는 문제 반환
    int[][] getSudokuSolution(String difficulty); // 난이도에 맞는 정답 반환
}