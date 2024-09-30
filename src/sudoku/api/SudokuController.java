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
        Thread timerThread = new Thread(() -> {
            // 제한 시간이 설정되었다는 메시지 출력
            Utils.startTimer(sudokuService.getLimitTime(difficulty), this::gameOverByTime);
        });

        // 먼저 제한 시간 메시지를 출력하고 타이머를 실행한 후 게임 입력 진행
        timerThread.start();

        try {
            // 타이머 스레드가 실행될 동안, 약간의 딜레이를 주어 먼저 출력이 완료되도록 보장
            Thread.sleep(100);  // 짧은 대기 시간 (0.1초)
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 게임 진행 쓰레드 시작
        Thread gameThread = new Thread(() -> {
            while (!sudokuService.isGameOver() && !isTimeUp) {
                System.out.println(sudokuService.getSudokuBoard());

                int row = Utils.inputCoordinate("행 좌표 (1-9)");  // 행 좌표 입력 (1~9)
                int col = Utils.inputCoordinate("열 좌표 (1-9)");  // 열 좌표 입력 (1~9)
                int num = Utils.inputNumber();  // 채울 숫자 입력

                // 유효성 검사 적용
                if (!Utils.isValidNum(row, col, num)) {
                    System.out.println("잘못된 좌표 또는 숫자입니다. 다시 입력하세요.");
                    continue;
                }

                boolean success = sudokuService.fillCell(row, col, num);  // 배열은 0부터 시작하므로 -1
                if (!success) {
                    System.out.println("틀렸습니다. 기회를 1회 차감합니다.");
                    System.out.println("남은 기회 : "+ sudokuService.getLives());
                }

                if (sudokuService.isGameOver()) {
                    System.out.println("게임 오버하였습니다.");
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
