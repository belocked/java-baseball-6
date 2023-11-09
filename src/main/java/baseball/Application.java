package baseball;

import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.List;

public class Application {
    public static void userSendValidation(String send) {
        if (send.length() != 3) {
            throw new IllegalArgumentException();
        }
        for (int index = 0; index < 3; index++) {
            if (send.charAt(index) < '0' || send.charAt(index) > '9') {
                throw new IllegalArgumentException();
            }
        }
    }

    public static void continueValidation(String send) {
        if (send.length() != 1) {
            throw new IllegalArgumentException();
        }
        if (send.charAt(0) != '1' && send.charAt(0) != '2') {
            throw new IllegalArgumentException();
        }
    }

    public static int checkBall(String send, List<Integer> computer) {
        int ballCount = 0;
        for (int cIdx = 0; cIdx < 3; cIdx++) {
            for (int sIdx = 0; sIdx < 3; sIdx++) {
                if (cIdx == sIdx) {
                    continue;
                }
                if (computer.get(cIdx) == (int) (send.charAt(sIdx) - '0')) {
                    ballCount++;
                }
            }
        }
        return ballCount;
    }

    public static int checkStrike(String send, List<Integer> computer) {
        int strikeCount = 0;
        for (int index = 0; index < 3; index++) {
            if (computer.get(index) == (int) (send.charAt(index) - '0')) {
                strikeCount++;
            }
        }
        return strikeCount;
    }

    public static void makeGoal(List<Integer> computer) {
        computer.clear();
        while (computer.size() < 3) {
            int randomNumber = Randoms.pickNumberInRange(1, 9);
            if (!computer.contains(randomNumber)) {
                computer.add(randomNumber);
            }
        }
    }

    public static void main(String[] args) {
        // TODO: 프로그램 구현
        List<Integer> computer = new ArrayList<>();
        makeGoal(computer);
        System.out.println("숫자 야구 게임을 시작합니다.");

        while (true) {
            System.out.printf("숫자를 입력해주세요 : ");
            String user_send = Console.readLine();

            userSendValidation(user_send);

            int cnt_ball = checkBall(user_send, computer);
            int cnt_strike = checkStrike(user_send, computer);

            if (cnt_ball > 0 && cnt_strike==0) {
                System.out.println(cnt_ball + "볼");
            }

            if (cnt_strike > 0 && cnt_ball == 0) {
                System.out.println(cnt_strike + "스트라이크");
            }

            if (cnt_strike > 0 && cnt_ball > 0) {
                System.out.println(cnt_ball + "볼 " + cnt_strike + "스트라이크");
            }

            if (cnt_ball + cnt_strike == 0) {
                System.out.println("낫싱");
            }

            if (cnt_strike == 3) {
                System.out.println("3개의 숫자를 모두 맞히셨습니다! 게임 종료");
                System.out.println("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.");
                String continueSend = Console.readLine();
                continueValidation(continueSend);

                if (continueSend.charAt(0) == '2') {
                    return;
                }
                makeGoal(computer);
            }
        }
    }
}
