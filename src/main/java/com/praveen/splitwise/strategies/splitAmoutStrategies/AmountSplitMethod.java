package com.praveen.splitwise.strategies.splitAmoutStrategies;

import java.util.List;
import java.util.Map;

public interface AmountSplitMethod {
    Map<Long, Integer> splitAmount(List<Integer> amounts, List<Long> userIds, List<Integer> splitFactors);
}
