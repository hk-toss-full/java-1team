package sudoku.domain;

import java.util.Random;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

public class SudokuGenerator {
    private static final int GRID_SIZE = 9;
    private Random random = new Random();
    private SudokuSolver solver = new SudokuSolver();

    // 스도쿠 정답을 생성하는 메서드
    public int[][] generateSolution() {
        int[][] board = new int[GRID_SIZE][GRID_SIZE];
        fillBoard(board);
        return board;
    }

    // 백트래킹을 사용해 스도쿠 보드를 채움 (숫자를 무작위로 섞어서 시도)
    private boolean fillBoard(int[][] board) {
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                if (board[row][col] == 0) {
                    List<Integer> numbers = generateRandomNumbers();  // 1부터 9까지 랜덤 순서로 배열 생성
                    for (int num : numbers) {
                        if (isValidMove(board, row, col, num)) {
                            board[row][col] = num;

                            if (fillBoard(board)) {
                                return true;
                            }

                            // 실패한 경우 되돌림 (백트래킹)
                            board[row][col] = 0;
                        }
                    }
                    return false;  // 어떤 숫자도 유효하지 않으면 되돌아감
                }
            }
        }
        return true;
    }

    // 1부터 9까지의 숫자를 무작위 순서로 배열한 리스트를 생성
    private List<Integer> generateRandomNumbers() {
        List<Integer> numbers = new ArrayList<>();
        for (int i = 1; i <= GRID_SIZE; i++) {
            numbers.add(i);
        }
        Collections.shuffle(numbers);  // 숫자를 무작위로 섞음
        return numbers;
    }

    // 스도쿠 문제를 생성하는 메서드 (난이도에 따라 빈칸 개수 조절)
    public int[][] generatePuzzle(int[][] solution, int emptySpaces) {
        int[][] puzzle = new int[GRID_SIZE][GRID_SIZE];

        // 먼저 정답을 복사
        for (int row = 0; row < GRID_SIZE; row++) {
            System.arraycopy(solution[row], 0, puzzle[row], 0, GRID_SIZE);
        }

        // 유일한 해답을 유지하면서 빈칸을 만들기
        int spacesRemoved = 0;
        while (spacesRemoved < emptySpaces) {
            int row = random.nextInt(GRID_SIZE);
            int col = random.nextInt(GRID_SIZE);

            if (puzzle[row][col] != 0) {
                int backup = puzzle[row][col];
                puzzle[row][col] = 0;

                // 빈칸을 만들었을 때 해답이 유일한지 확인
                if (solver.countSolutions(puzzle) != 1) {
                    // 유일하지 않으면 다시 채움
                    puzzle[row][col] = backup;
                } else {
                    spacesRemoved++;
                }
            }
        }

        return puzzle;
    }

    // 주어진 숫자가 해당 위치에 들어갈 수 있는지 검사
    private boolean isValidMove(int[][] board, int row, int col, int num) {
        for (int i = 0; i < GRID_SIZE; i++) {
            if (board[row][i] == num || board[i][col] == num) {
                return false;
            }
        }

        int boxRowStart = (row / 3) * 3;
        int boxColStart = (col / 3) * 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[boxRowStart + i][boxColStart + j] == num) {
                    return false;
                }
            }
        }

        return true;
    }
}
