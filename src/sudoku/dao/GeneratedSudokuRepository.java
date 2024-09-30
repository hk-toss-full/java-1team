package sudoku.dao;

import sudoku.domain.SudokuGenerator;

public class GeneratedSudokuRepository implements SudokuRepository {
    private SudokuGenerator generator = new SudokuGenerator();
    private int[][] solution; // 한번 생성된 스도쿠 풀이 저장

    @Override
    public int[][] getSudokuPuzzle(int difficulty) {
        if(solution == null){
            solution = generator.generateSolution();
        }

        int emptySpaces;

        switch (difficulty) {
            case 1:
                emptySpaces = 30;  // 빈칸 30개 (초급)
                break;
            case 2:
                emptySpaces = 40;  // 빈칸 40개 (중급)
                break;
            case 3:
                emptySpaces = 50;  // 빈칸 50개 (고급)
                break;
            default:
                throw new IllegalArgumentException("잘못된 난이도입니다.");
        }

        return generator.generatePuzzle(solution, emptySpaces);
    }

    @Override
    public int[][] getSudokuSolution(int difficulty) {
        if(solution == null){
            solution = generator.generateSolution();
        }
        return solution;
    }
}
