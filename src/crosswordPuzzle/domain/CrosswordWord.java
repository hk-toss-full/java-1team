package crosswordPuzzle.domain;

public class CrosswordWord {
    package crosswordPuzzle.domain;

    public class CrosswordWord {
        private final String word;                  // 단어
        private final int x;                        // 단어의 시작 x좌표
        private final int y;                        // 단어의 시작 y좌표
        private final CrosswordDirection direction; // 단어의 방향 (가로/세로)
        private final String description;           // 단어의 설명

        public CrosswordWord(String word, int x, int y, CrosswordDirection direction, String description) {
            this.word = word;
            this.x = x;
            this.y = y;
            this.direction = direction;
            this.description = description;
        }

        public String getWord() {
            return word;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public CrosswordDirection getDirection() {
            return direction;
        }

        public String getDescription() {
            return description;
        }

        public String getHint() {
            // 단어의 초성 반환 예: 기러기 -> ㄱㄹㄱ
            StringBuilder hint = new StringBuilder();
            for (char c : word.toCharArray()) {
                hint.append(Character.toString(c));
            }
            return hint.toString();
        }
    }
}
