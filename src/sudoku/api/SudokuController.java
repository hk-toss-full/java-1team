package sudoku.api;

import sudoku.global.Utils;
import sudoku.application.SudokuService;

public class SudokuController {
    private SudokuService sudokuService;
    private boolean isTimeUp = false;

    public SudokuController(SudokuService sudokuService) {
        this.sudokuService = sudokuService;
    }

    // 게임 시작 메서드
    public void startGame() {
        System.out.println("난이도를 선택하세요 - easy:1 / middle:2 / hard:3");
        int difficulty = Utils.inputLevel();  // 사용자에게 난이도 입력받기

        sudokuService.initializeGame(difficulty);  // 난이도에 맞는 게임 초기화

        // 제한 시간 쓰레드 시작
        Thread timerThread = Utils.startTimer(sudokuService.getLimitTime(difficulty), this::gameOverByTime);

        // 게임 진행 쓰레드 시작
        Thread gameThread = new Thread(() -> {
            while (!sudokuService.isGameOver() && !isTimeUp) {
                System.out.println(sudokuService.getSudokuBoard());

                int row = Utils.inputCoordinate("행 좌표 (1-9)");  // 행 좌표 입력 (0~8)
                int col = Utils.inputCoordinate("열 좌표 (1-9)");  // 열 좌표 입력 (0~8)
                int num = Utils.inputNumber();  // 채울 숫자 입력

                // 유효성 검사 적용
                if (!Utils.isValidNum(row, col, num)) {
                    System.out.println("잘못된 좌표 또는 숫자입니다. 다시 입력하세요.");
                    continue;
                }

                boolean success = sudokuService.fillCell(row, col, num);
                if (!success) {
                    System.out.println("잘못된 입력입니다.");
                }

                System.out.println("현재 스도쿠 상태:");
                System.out.println(sudokuService.getSudokuBoard());  // 업데이트된 보드 출력

                if (sudokuService.isGameOver()) {
                    System.out.println("축하합니다! 게임을 클리어했습니다.");
                    break;
                }
            }
        });
        gameThread.start();

        try {
            gameThread.join();  // 게임이 끝날 때까지 대기
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        timerThread.interrupt();  // 게임이 끝났으므로 타이머 쓰레드 종료
    }

    // 시간이 초과되면 게임 종료
    private void gameOverByTime() {
        System.out.println("시간이 초과되었습니다. 게임이 종료됩니다.");
        isTimeUp = true;
        System.exit(0);  // 프로그램 종료
    }
}

