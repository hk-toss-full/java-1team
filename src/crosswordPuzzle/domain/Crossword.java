package crosswordPuzzle.domain;

import java.util.List;

public class Crossword {

        private final CrosswordBoard board;        // 십자말풀이 보드
        private final List<CrosswordWord> words;   // 단어 리스트
        private final int maxHints = 3;            // 최대 힌트 개수
        private int remainingHints = maxHints;     // 남은 힌트 수

        public Crossword(List<CrosswordWord> words) {
            this.board = new CrosswordBoard();
            this.words = words;
        }

        // 보드 출력
        public void printBoard() {
            board.printBoard();
        }

        // 단어 설명 출력
        public void printDescriptions() {
            System.out.println("단어 설명:");
            for (CrosswordWord word : words) {
                System.out.println("[" + word.getX() + "," + word.getY() + "] " + word.getDescription());
            }
        }

        // 힌트 사용
        public void useHint() {
            if (remainingHints > 0) {
                remainingHints--;
                System.out.println("힌트를 사용합니다. 남은 힌트: " + remainingHints);
                for (CrosswordWord word : words) {
                    System.out.println("힌트: " + word.getHint());
                }
            } else {
                System.out.println("힌트를 모두 사용하였습니다.");
            }
        }

        // 단어 삽입
        public boolean insertWord(String wordStr, int x, int y, CrosswordDirection direction) {
            for (CrosswordWord word : words) {
                if (word.getWord().equals(wordStr) && word.getX() == x && word.getY() == y && word.getDirection() == direction) {
                    boolean success = board.insertWord(word);
                    if (success) {
                        System.out.println("단어를 맞췄습니다!");
                        return true;
                    }
                }
            }
            System.out.println("단어가 맞지 않습니다.");
            return false;
        }

        public boolean isCompleted() {
            return board.isCompleted();
        }
    }

}
