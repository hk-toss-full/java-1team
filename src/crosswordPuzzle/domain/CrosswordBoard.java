package crosswordPuzzle.domain;

import java.util.Arrays;

public class CrosswordBoard {

        private final char[][] board; // 10x10 보드 (빈칸은 □, 채워지지 않는 칸은 ■)
        private final int size = 10;  // 보드 크기

        public CrosswordBoard() {
            this.board = new char[size][size];
            initBoard();
        }

        // 보드를 빈칸과 채워지지 않는 칸으로 초기화
        private void initBoard() {
            for (int i = 0; i < size; i++) {
                Arrays.fill(board[i], '□'); // 기본 빈칸
            }

            // 채워지지 않는 칸을 설정 (예시로 설정)
            int[][] blockedPositions = {
                    {0, 2}, {0, 4}, {0, 5}, {0, 6}, {0, 7}, {0, 8}, {0, 9},
                    {1, 1}, {1, 3}, {1, 5}, {1, 7}, {1, 9}, {2, 2}, {2, 4},
                    {2, 6}, {2, 8}, {3, 3}, {3, 5}, {3, 7}, {4, 4}, {4, 6},
                    {4, 8}, {5, 1}, {5, 3}, {5, 5}, {5, 7}, {6, 2}, {6, 4},
                    {6, 6}, {6, 8}, {7, 1}, {7, 3}, {7, 5}, {7, 7}, {8, 2},
                    {8, 4}, {8, 6}, {8, 8}, {9, 1}, {9, 3}, {9, 5}, {9, 7}, {9, 9}
            };
            for (int[] pos : blockedPositions) {
                board[pos[0]][pos[1]] = '■';
            }
        }

        // 단어를 보드에 삽입
        public boolean insertWord(CrosswordWord word) {
            int x = word.getX();
            int y = word.getY();
            String wordStr = word.getWord();

            if (word.getDirection() == CrosswordDirection.HORIZONTAL) {
                for (int i = 0; i < wordStr.length(); i++) {
                    board[x][y + i] = wordStr.charAt(i);
                }
            } else if (word.getDirection() == CrosswordDirection.VERTICAL) {
                for (int i = 0; i < wordStr.length(); i++) {
                    board[x + i][y] = wordStr.charAt(i);
                }
            } else {
                return false;
            }
            return true;
        }

        // 보드 출력
        public void printBoard() {
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    System.out.print(board[i][j] + " ");
                }
                System.out.println();
            }
        }

        // 보드가 완성되었는지 확인 (빈칸이 없으면 완료)
        public boolean isCompleted() {
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if (board[i][j] == '□') {
                        return false;
                    }
                }
            }
            return true;
        }
    }
