package crosswordPuzzle.application;
import crosswordPuzzle.domain.Crossword;
import crosswordPuzzle.domain.CrosswordDirection;

public class CrosswordPuzzleService {
    private final Crossword crossword;

    // 생성자에서 Crossword 객체를 초기화
    public CrosswordPuzzleService(Crossword crossword) {
        this.crossword = crossword;
    }

    // 십자말풀이 게임을 시작하고 보드를 출력
    public void startGame() {
        System.out.println("십자말풀이 게임을 시작합니다!");
        crossword.printBoard();
        crossword.printDescriptions();
    }

    // 사용자가 단어를 입력하면 해당 단어를 보드에 삽입
    public boolean insertWord(String word, int x, int y, CrosswordDirection direction) {
        return crossword.insertWord(word, x, y, direction);
    }

    // 힌트를 사용하도록 요청
    public void useHint() {
        crossword.useHint();
    }

    // 게임 종료 여부를 확인
    public boolean isGameCompleted() {
        return crossword.isCompleted();
    }
}
