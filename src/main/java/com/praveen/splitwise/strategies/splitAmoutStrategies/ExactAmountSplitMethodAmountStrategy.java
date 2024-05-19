package com.praveen.splitwise.strategies.splitAmoutStrategies;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExactAmountSplitMethodAmountStrategy implements AmountSplitMethod {
    @Override
    public Map<Long, Integer> splitAmount(List<Integer> amounts, List<Long> userIds, List<Integer> splitFactors) {
        Map<Long, Integer> userAmountMap = new HashMap<>();
        for(int i = 0; i< userIds.size(); i++){
            userAmountMap.put(userIds.get(i), splitFactors.get(i));
        }
        return userAmountMap;
    }
}
