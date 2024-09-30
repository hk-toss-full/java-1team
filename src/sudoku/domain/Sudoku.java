package sudoku.domain;

public class Sudoku {
    private String level;
    private int life = 3;
    private int timer;
    private int answer;

    private int[][] sudokuTable;
    private int[][] sudokuTableAnswer;

    public Sudoku(String level, int life, int timer, int answer, int[][] sudokuTable, int[][] sudokuTableAnswer) {
        this.level = level;
        this.life = life;
        this.timer = timer;
        this.answer = answer;
        this.sudokuTable = sudokuTable;
        this.sudokuTableAnswer = sudokuTableAnswer;
    }

    public String getLevel() {
        return level;
    }

    public int getLife() {
        return life;
    }

    public int getTimer() {
        return timer;
    }

    public int getAnswer() {
        return answer;
    }

    public int[][] getSudokuTable() {
        return sudokuTable;
    }

    public int[][] getSudokuTableAnswer() {
        return sudokuTableAnswer;
    }

}

