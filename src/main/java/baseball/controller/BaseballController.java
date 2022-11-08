package baseball.controller;

import baseball.service.BaseballService;
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
