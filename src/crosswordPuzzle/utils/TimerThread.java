package crosswordPuzzle.utils;

// TimerThread 클래스는 제한 시간을 관리하고 시간이 다 되면 게임 종료 메시지를 출력합니다.
public class TimerThread extends Thread {
    private final int timeLimit;  // 제한 시간 (초 단위)
    private boolean timeUp;       // 시간 초과 여부를 나타내는 플래그

    // 생성자에서 제한 시간을 초기화 (20분 = 1200초)
    public TimerThread(int timeLimitInSeconds) {
        this.timeLimit = timeLimitInSeconds;
        this.timeUp = false;
    }

    @Override
    public void run() {
        try {
            // 제한 시간 동안 1초마다 남은 시간을 출력하고, 타이머를 진행
            for (int remainingTime = timeLimit; remainingTime > 0; remainingTime--) {
                // 남은 시간을 분과 초로 변환하여 출력
                int minutes = remainingTime / 60;
                int seconds = remainingTime % 60;

                if (remainingTime == 600) { // 10분 남았을 때
                    System.out.printf("남은 시간: %02d분 %02d초 (10분 남음)\n", minutes, seconds);
                } else if (remainingTime <= 300 && remainingTime % 90 == 0 && remainingTime > 60) { // 5분 이하, 1분 이상 남았을 때 (90초 간격)
                    System.out.printf("남은 시간: %02d분 %02d초\n", minutes, seconds);
                } else if (remainingTime <= 60 && remainingTime % 30 == 0 && remainingTime > 10) { // 1분 이하, 10초 이상 남았을 때 (30초 간격)
                    System.out.printf("남은 시간: %02d분 %02d초\n", minutes, seconds);
                } else if (remainingTime <= 10) { // 10초 이하 남았을 때 (1초 간격)
                    System.out.printf("남은 시간: %02d분 %02d초\n", minutes, seconds);
                }
                // 1초 대기
                Thread.sleep(1000);
            }
            // 제한 시간이 끝나면 타이머 종료 및 시간 초과 플래그 설정
            System.out.println("제한 시간이 모두 지났습니다. 게임이 종료됩니다!");
            timeUp = true;

        } catch (InterruptedException e) {
            // 스레드가 외부에서 강제로 종료되었을 때의 예외 처리
            System.out.println("타이머가 중지되었습니다.");
            timeUp = true;
        }
    }

    // 시간 초과 여부를 확인하는 메서드
    public boolean isTimeUp() {
        return timeUp;
    }

    // 타이머를 강제로 종료할 수 있도록 설정하는 메서드 (게임이 중간에 종료되었을 때 사용)
    public void stopTimer() {
        this.interrupt();
    }
}
