package sudoku.domain;

public class Sudoku {
    private int[][] puzzleBoard;   // 사용자에게 제공되는 문제 보드
    private int[][] solutionBoard; // 정답 보드
    private int lives;             // 남은 목숨 (기회)

    public Sudoku(int[][] puzzleBoard, int[][] solutionBoard) {
        this.puzzleBoard = puzzleBoard;
        this.solutionBoard = solutionBoard;
        this.lives = 3; // 기본 목숨을 3번으로 설정
    }

    // 숫자를 입력받아 보드 업데이트 (x, y 좌표에 숫자 num을 넣음)
    public boolean fillCell(int x, int y, int num) {
        // 정답 보드와 비교해서 맞는지 확인
        if (solutionBoard[x][y] == num) {
            puzzleBoard[x][y] = num;
            return true;
        } else {
            lives--;  // 틀리면 목숨 감소
            return false;
        }
    }

    // 남은 목숨을 확인하는 메서드
    public int getLives() {
        return lives;
    }

    // 게임이 종료되었는지 확인 (모든 칸이 채워졌는지 확인)
    public boolean isGameOver() {
        if (lives <= 0) {
            return true; // 목숨이 다했으면 게임 종료
        }
        // 빈칸이 남아있는지 확인
        for (int i = 0; i < puzzleBoard.length; i++) {
            for (int j = 0; j < puzzleBoard[i].length; j++) {
                if (puzzleBoard[i][j] == 0) {
                    return false;
                }
            }
        }
        return true; // 모든 칸이 채워졌으면 게임 종료
    }

    // 현재 스도쿠 보드를 출력용 문자열로 반환
    public String getSudokuBoard() {
        StringBuilder sb = new StringBuilder();
        for (int[] row : puzzleBoard) {
            for (int cell : row) {
                sb.append(cell == 0 ? "." : cell).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
