package sudoku.global;

import sudoku.domain.Sudoku;

import java.util.Scanner;

public class Utils {
    private static Scanner scanner = new Scanner(System.in);

    // 숫자 입력받는 유틸리티 메서드
    public static int inputNumber() {
        while (true) {
            try {
                System.out.print("채울 숫자를 입력하세요 (1-9): ");
                int num = Integer.parseInt(scanner.next());
                if (num < 1 || num > 9) {
                    System.out.println("숫자는 1에서 9 사이여야 합니다.");
                    continue;
                }
                return num;
            } catch (NumberFormatException e) {
                System.out.println("유효한 숫자를 입력하세요.");
            }
        }
    }

    // 좌표 입력받는 메서드 (0-8)
    public static int inputCoordinate(String message) {
        while (true) {
            try {
                System.out.print(message + ": ");
                int coord = Integer.parseInt(scanner.next())-1;
                if (coord < 0 || coord > 8) {
                    System.out.println("좌표는 1에서 9 사이의 숫자를 써주세요.");
                    continue;
                }
                return coord;
            } catch (NumberFormatException e) {
                System.out.println("유효한 좌표를 입력하세요.");
            }
        }
    }

    // 좌표와 숫자가 유효한지 확인하는 메서드
    public static boolean isValidNum(int row, int col, int num) {
        return (row >= 0 && row < 9 && col >= 0 && col < 9 && num > 0 && num <= 9);
    }

    // 제한 시간을 관리하는 쓰레드 시작 메서드
    public static Thread startTimer(int limitTime, Runnable timeoutCallback) {
        Thread timerThread = new Thread(() -> {
            try {
                System.out.println("제한 시간이 " + (limitTime/60)+"분으로 설정되었습니다.");
                Thread.sleep(limitTime * 1000L);
                timeoutCallback.run();  // 시간이 끝나면 콜백 실행
            } catch (InterruptedException e) {
                System.out.println("타이머가 중단되었습니다.");
            }
        });
        timerThread.start();
        return timerThread;
    }

    // 문자열 입력받는 유틸리티 메서드 (난이도 입력 등)
    public static int inputLevel() {
        return scanner.nextInt();
    }
}
