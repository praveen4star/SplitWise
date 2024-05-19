package com.praveen.splitwise.strategies.splitAmoutStrategies;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EqualAmountSplitMethodAmountStrategy implements AmountSplitMethod {
    @Override
    public Map<Long, Integer> splitAmount(List<Integer> amounts, List<Long> userIds, List<Integer> splitFactors) {
        int amount = amounts.stream().reduce(0, Integer::sum);
        int splitAmount = amount/userIds.size();
        Map<Long, Integer> userAmountMap = new HashMap<>();
        for(int i = 0; i< userIds.size(); i++){
            userAmountMap.put(userIds.get(i), i == userIds.size()-1 ? amount - splitAmount*(userIds.size()-1) : splitAmount);
        }
        return userAmountMap;
    }
}
