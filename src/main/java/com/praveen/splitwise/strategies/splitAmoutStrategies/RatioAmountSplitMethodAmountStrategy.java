package com.praveen.splitwise.strategies.splitAmoutStrategies;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RatioAmountSplitMethodAmountStrategy implements AmountSplitMethod {
    @Override
    public Map<Long, Integer> splitAmount(List<Integer> amounts, List<Long> userIds, List<Integer> splitFactors) {
        int amount = amounts.stream().reduce(0, Integer::sum);
        Map<Long, Integer> userAmountMap = new HashMap<>();
        int totalSplitFactor = splitFactors.stream().reduce(0, Integer::sum);
        int remainAmount = amount;
        for(int i = 0; i< userIds.size(); i++){
            int userAmount = (int) Math.ceil((double) (splitFactors.get(i) * amount) /totalSplitFactor);
            if(i == userIds.size()-1){
                userAmount = remainAmount;
            }
            remainAmount -= userAmount;
            userAmountMap.put(userIds.get(i), userAmount);
        }
        return userAmountMap;
    }
}
