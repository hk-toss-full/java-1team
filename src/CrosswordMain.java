import crosswordPuzzle.api.CrosswordPuzzleController;
import crosswordPuzzle.application.CrosswordPuzzleService;
import crosswordPuzzle.domain.Crossword;
import crosswordPuzzle.domain.CrosswordDirection;
import crosswordPuzzle.domain.CrosswordWord;

import java.util.ArrayList;
import java.util.List;

public class CrosswordMain {
    public static void main(String[] args) {
        // 십자말풀이 문제 설정 (예제 데이터를 기반으로 단어 추가)
        List<CrosswordWord> words = new ArrayList<>();
        words.add(new CrosswordWord("타임머신", 0, 0, CrosswordDirection.HORIZONTAL, "타임을 여행하는 장치"));
        words.add(new CrosswordWord("사시나무", 3, 1, CrosswordDirection.HORIZONTAL, "바람에 떨리는 나무"));
        words.add(new CrosswordWord("지도", 0, 2, CrosswordDirection.HORIZONTAL, "지도에서 임지의 위치"));
        words.add(new CrosswordWord("서당", 2, 3, CrosswordDirection.HORIZONTAL, "서당에 대한 설명"));
        words.add(new CrosswordWord("조개", 5, 3, CrosswordDirection.HORIZONTAL, "'성(性)'의 조각"));
        words.add(new CrosswordWord("오찬", 8, 8, CrosswordDirection.HORIZONTAL, "발행된 성과 오찬"));
        words.add(new CrosswordWord("주인공", 3, 6, CrosswordDirection.HORIZONTAL, "이야기의 중심 인물"));
        words.add(new CrosswordWord("설악산", 1, 7, CrosswordDirection.HORIZONTAL, "대한민국의 유명한 산"));
        words.add(new CrosswordWord("성격", 3, 8, CrosswordDirection.HORIZONTAL, "사람의 성품"));
        words.add(new CrosswordWord("편지", 0, 9, CrosswordDirection.HORIZONTAL, "소식을 전하는 글"));

        // Crossword 및 관련 객체 초기화
        Crossword crossword = new Crossword(words);
        CrosswordPuzzleService crosswordService = new CrosswordPuzzleService(crossword);
        CrosswordPuzzleController crosswordController = new CrosswordPuzzleController(crosswordService);

        // 게임 실행
        crosswordController.run();
    }
}