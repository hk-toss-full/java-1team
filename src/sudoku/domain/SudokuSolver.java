package sudoku.domain;

public class SudokuSolver {
    private static final int GRID_SIZE = 9;

    // 스도쿠 문제를 풀어서 가능한 답의 수를 반환하는 메서드
    public int countSolutions(int[][] board) {
        return solve(board, 0, 0, 0);
    }

    // 백트래킹을 사용해 가능한 해법을 찾는 메서드
    private int solve(int[][] board, int row, int col, int solutionCount) {
        if (row == GRID_SIZE) {
            return solutionCount + 1;
        }

        int nextRow = (col == GRID_SIZE - 1) ? row + 1 : row;
        int nextCol = (col == GRID_SIZE - 1) ? 0 : col + 1;

        if (board[row][col] != 0) {
            return solve(board, nextRow, nextCol, solutionCount);
        }

        for (int num = 1; num <= GRID_SIZE; num++) {
            if (isValidMove(board, row, col, num)) {
                board[row][col] = num;
                solutionCount = solve(board, nextRow, nextCol, solutionCount);
                board[row][col] = 0;  // 백트래킹
            }

            if (solutionCount > 1) {
                return solutionCount;  // 여러 해답이 있으면 중단
            }
        }

        return solutionCount;
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
