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
                System.out.println(word.getNumber()+ ". " + "[" + word.getX() + "," + word.getY() + "] " + word.getDescription());
            }
        }

        // 힌트 사용
    public void useHint(int problemNumber) {
        if (remainingHints <= 0) {
            System.out.println("더 이상 사용할 수 있는 힌트가 없습니다.");
            return;
        }

        for (CrosswordWord word : words) {
            if (word.getNumber() == problemNumber) {
                System.out.println("힌트: " + word.getHint());
                remainingHints--;  // 힌트 사용 시 남은 힌트 수 감소
                System.out.println("남은 힌트 수: " + remainingHints);
                return;
            }
        }

        System.out.println("해당 문제 번호가 존재하지 않습니다.");
    }

        // 단어 삽입
        public boolean insertWord(int problemNumber, String wordStr, CrosswordDirection direction) {
            for (CrosswordWord word : words) {
                if (word.getNumber() == problemNumber && word.getDirection() == direction) {
                    if (word.getWord().equals(wordStr)) {
                        if (direction == CrosswordDirection.HORIZONTAL) {
                            for(int i = 0; i < wordStr.length(); i++) {
                                board.insertCharacter(word.getX(), word.getY() + i, wordStr.charAt(i));
                            }
                        } else if (direction == CrosswordDirection.VERTICAL) {
                            for(int i = 0; i < wordStr.length(); i++) {
                                board.insertCharacter(word.getX() + i, word.getY(), wordStr.charAt(i));
                            }
                        }
                        System.out.println("정답입니다! " + word.getDescription() + " 단어가 추가되었습니다.");
                        return true;
                    }
                }
            }
            System.out.println("정답이 아닙니다. 다시 시도하세요.");
            return false;
        }

        public boolean isCompleted() {
            return board.isCompleted();
        }
    }
