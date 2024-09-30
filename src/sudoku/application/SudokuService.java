package sudoku.application;

import sudoku.domain.Sudoku;
import sudoku.dao.SudokuRepository;

public class SudokuService {
    private SudokuRepository sudokuRepository;
    private Sudoku sudokuGame;

    public SudokuService(SudokuRepository sudokuRepository) {
        this.sudokuRepository = sudokuRepository;
    }

    // 게임 초기화
    public void initializeGame(int difficulty) {
        int[][] puzzleBoard = sudokuRepository.getSudokuPuzzle(difficulty);
        int[][] solutionBoard = sudokuRepository.getSudokuSolution(difficulty);
        sudokuGame = new Sudoku(puzzleBoard, solutionBoard);  // 스도쿠 게임 초기화
    }

    // 숫자를 입력할 때 실행하는 메서드
    public boolean fillCell(int num, int x, int y) {
        return sudokuGame.fillCell(x, y, num);  // `Sudoku` 클래스의 메서드를 통해 보드 업데이트
    }

    // 남은 목숨을 확인
    public int getLives() {
        return sudokuGame.getLives();
    }

    // 게임 종료 여부 확인
    public boolean isGameOver() {
        return sudokuGame.isGameOver();
    }

    // 현재 스도쿠 보드 출력
    public String getSudokuBoard() {
        return sudokuGame.getSudokuBoard();
    }

    // 제한 시간을 반환하는 메서드
    public int getLimitTime() {
        return 1200;  // 예시로 20분 제한 시간 설정
    }
}
