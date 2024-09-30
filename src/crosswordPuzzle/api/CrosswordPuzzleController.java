package crosswordPuzzle.api;

import crosswordPuzzle.application.CrosswordPuzzleService;
import crosswordPuzzle.domain.CrosswordDirection;

import java.util.Scanner;

public class CrosswordPuzzleController {
    private final CrosswordPuzzleService crosswordService;
    private final Scanner scanner;

    // 생성자에서 CrosswordService 객체와 Scanner 객체를 초기화
    public CrosswordPuzzleController(CrosswordPuzzleService crosswordService) {
        this.crosswordService = crosswordService;
        this.scanner = new Scanner(System.in);
    }

    // 십자말풀이 게임을 실행하고 사용자 입력을 처리
    public void run() {
        crosswordService.startGame();  // 게임 시작 및 보드 출력

        while (true) {
            System.out.println("옵션을 선택하세요: ");
            System.out.println("1. 단어 입력");
            System.out.println("2. 힌트 사용");
            System.out.println("3. 게임 종료");

            int option = scanner.nextInt();
            scanner.nextLine();  // 개행 문자 처리

            switch (option) {
                case 1:
                    insertWord();
                    break;
                case 2:
                    crosswordService.useHint();
                    break;
                case 3:
                    System.out.println("게임을 종료합니다.");
                    return;
                default:
                    System.out.println("잘못된 옵션입니다. 다시 시도하세요.");
                    break;
            }

            if (crosswordService.isGameCompleted()) {
                System.out.println("모든 단어를 맞췄습니다! 게임을 성공적으로 완료하였습니다.");
                break;
            }
        }
    }

    // 단어 입력 옵션
    private void insertWord() {
        System.out.print("단어를 입력하세요: ");
        String word = scanner.nextLine();

        System.out.print("단어의 시작 x 좌표를 입력하세요: ");
        int x = scanner.nextInt();

        System.out.print("단어의 시작 y 좌표를 입력하세요: ");
        int y = scanner.nextInt();
        scanner.nextLine();  // 개행 문자 처리

        System.out.print("단어의 방향 (HORIZONTAL/ VERTICAL)을 입력하세요: ");
        String directionStr = scanner.nextLine().toUpperCase();

        CrosswordDirection direction;
        try {
            direction = CrosswordDirection.valueOf(directionStr);
        } catch (IllegalArgumentException e) {
            System.out.println("잘못된 방향 입력입니다. 다시 시도하세요.");
            return;
        }

        boolean success = crosswordService.insertWord(word, x, y, direction);
        if (success) {
            System.out.println("단어가 보드에 성공적으로 삽입되었습니다.");
        } else {
            System.out.println("단어를 보드에 삽입할 수 없습니다. 다시 시도하세요.");
        }

        crosswordService.startGame();  // 현재 보드와 설명을 다시 출력
    }
}
