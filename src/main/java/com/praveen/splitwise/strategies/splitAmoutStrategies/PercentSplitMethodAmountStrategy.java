package com.praveen.splitwise.strategies.splitAmoutStrategies;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PercentSplitMethodAmountStrategy implements SplitMethod {
    @Override
    public Map<Long, Integer> splitAmount(int amount, List<Long> userIds, List<Integer> splitFactors) {
        Map<Long, Integer> userAmountMap = new HashMap<>();
        int remainAmount = amount;
        for(int i = 0; i< userIds.size(); i++){
            int userAmount = (int) Math.ceil((splitFactors.get(i)*amount)/100.0);
            if(i == userIds.size()-1){
                userAmount = remainAmount;
            }
            remainAmount -= userAmount;
            userAmountMap.put(userIds.get(i), userAmount);
        }
        return userAmountMap;
    }
}
