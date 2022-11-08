package baseball.controller;

import baseball.domain.Baseball;
import baseball.service.BaseballService;
import baseball.util.io.BaseballInput;
import baseball.util.io.BaseballOutput;
import baseball.validation.Validator;
import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.List;


public class BaseballController {

    private final BaseballService baseballService;
    private final Validator<List<Integer>> validator;

    public BaseballController(BaseballService baseballService, Validator<List<Integer>> validator) {
        this.baseballService = baseballService;
        this.validator = validator;
    }

    public void play() {
        BaseballOutput.printInit();
        do {
            playOneCycle();
        } while (BaseballInput.gameRestart());
    }

    public void playOneCycle() {
        List<Integer> goal = createGoal();
        while (true) {
            List<Integer> inputNumbers = BaseballInput.gamePlay();
            if (!validator.validate(inputNumbers)) {
                throw new IllegalArgumentException();
            }
            Baseball result = baseballService.judge(inputNumbers, goal);
            BaseballOutput.printResult(result);

            if (result.getStrike() == 3) {
                BaseballOutput.printSuccess();
                break;
            }
        }

    }

    private List<Integer> createGoal() {
        List<Integer> goal = new ArrayList<>();
        while (goal.size() < 3) {
            int randomNumber = Randoms.pickNumberInRange(1, 9);
            if (!goal.contains(randomNumber)) {
                goal.add(randomNumber);
            }
        }
        return goal;
    }

}
