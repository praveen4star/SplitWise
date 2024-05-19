package com.praveen.splitwise.strategies.splitAmoutStrategies;

import java.util.List;
import java.util.Map;

public interface SplitMethod {
    Map<Long, Integer> splitAmount(int amount, List<Long> userIds, List<Integer> splitFactors);
}
