package sudoku.application;

import sudoku.dao.SudokuRepository;

public class SudokuService {
    private SudokuRepository sudokuRepository;
    private int[][] sudokuBoard;
    private int[][] sudokuAnswer;
    private int limitTime;
    private int lives;

    public SudokuService(SudokuRepository sudokuRepository) {
        this.sudokuRepository = sudokuRepository;
    }

    // 게임 초기화: 난이도에 따라 스도쿠 문제와 답안 로드, 제한시간 설정
    public void initializeGame(String difficulty) {
        this.sudokuBoard = sudokuRepository.getSudokuPuzzle(difficulty);
        this.sudokuAnswer = sudokuRepository.getSudokuSolution(difficulty);

        switch (difficulty.toLowerCase()) {
            case "easy":
                this.limitTime = 2400;  // 초급: 40분
                break;
            case "medium":
                this.limitTime = 1800;   // 중급: 30분
                break;
            case "hard":
                this.limitTime = 1200;   // 고급: 20분
                break;
            default:
                throw new IllegalArgumentException("잘못된 난이도입니다.");
        }

        this.lives = 3;  // 기회 초기화
    }

    // 숫자를 입력할 때 실행하는 메서드
    public boolean makeMove(int num, int x, int y) {
        if (sudokuAnswer[x][y] == num) {
            sudokuBoard[x][y] = num;
            return true;
        } else {
            lives--;
            System.out.println("남은 기회: " + lives);
            if (lives == 0) {
                System.out.println("기회를 모두 소진했습니다. 게임이 종료됩니다.");
                System.exit(0); // 기회 소진 시 게임 종료
            }
            return false;
        }
    }

    // 남은 빈 칸이 있는지 확인하는 메서드
    public boolean isGameOver() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (sudokuBoard[i][j] == 0) {
                    return false;  // 빈 칸이 있으면 게임이 끝나지 않음
                }
            }
        }
        return true;  // 빈 칸이 없으면 게임 종료
    }

    // 스도쿠 보드를 출력용 문자열로 반환하는 메서드
    public String getSudokuBoard() {
        StringBuilder sb = new StringBuilder();
        for (int[] row : sudokuBoard) {
            for (int cell : row) {
                sb.append(cell == 0 ? "." : cell).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    // 제한 시간을 반환하는 메서드
    public int getLimitTime() {
        return limitTime;
    }
}
