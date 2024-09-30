import sudoku.api.SudokuController;
import sudoku.application.SudokuService;
//import sudoku.dao.InMemorySudokuRepository;
import sudoku.dao.GeneratedSudokuRepository;
import sudoku.dao.SudokuRepository;

public class Main {
    public static void main(String[] args) {
        SudokuRepository repository = new GeneratedSudokuRepository();  // 메모리에서 데이터를 가져오는 구현체
        SudokuService sudokuService = new SudokuService(repository);   // 서비스에 주입
        SudokuController controller = new SudokuController(sudokuService);

        controller.startGame();  // 게임 시작
    }
}