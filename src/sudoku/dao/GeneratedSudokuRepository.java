package sudoku.dao;

import sudoku.domain.SudokuGenerator;

public class GeneratedSudokuRepository implements SudokuRepository {
    private SudokuGenerator generator = new SudokuGenerator();

    @Override
    public int[][] getSudokuPuzzle(int difficulty) {
        int[][] solution = generator.generateSolution();
        int emptySpaces;

        switch (difficulty) {
            case 1:
                emptySpaces = 40;  // 빈칸 40개 (초급)
                break;
            case 2:
                emptySpaces = 50;  // 빈칸 50개 (중급)
                break;
            case 3:
                emptySpaces = 60;  // 빈칸 60개 (고급)
                break;
            default:
                throw new IllegalArgumentException("잘못된 난이도입니다.");
        }

        return generator.generatePuzzle(solution, emptySpaces);
    }

    @Override
    public int[][] getSudokuSolution(int difficulty) {
        return generator.generateSolution();
    }
}
