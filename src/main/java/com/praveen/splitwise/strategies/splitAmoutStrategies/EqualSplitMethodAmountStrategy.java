package com.praveen.splitwise.strategies.splitAmoutStrategies;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EqualSplitMethodAmountStrategy implements SplitMethod {
    @Override
    public Map<Long, Integer> splitAmount(int amount, List<Long> userIds, List<Integer> splitFactors) {
        int splitAmount = amount/userIds.size();
        Map<Long, Integer> userAmountMap = new HashMap<>();
        for(int i = 0; i< userIds.size(); i++){
            userAmountMap.put(userIds.get(i), i == userIds.size()-1 ? amount - splitAmount*(userIds.size()-1) : splitAmount);
        }
        return userAmountMap;
    }
}
