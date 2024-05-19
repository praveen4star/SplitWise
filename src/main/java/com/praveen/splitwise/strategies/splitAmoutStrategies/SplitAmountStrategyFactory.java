package com.praveen.splitwise.strategies.splitAmoutStrategies;

import com.praveen.splitwise.models.constants.SplitMethodType;

public class SplitAmountStrategyFactory {
    public static AmountSplitMethod getSplitAmountStrategy(SplitMethodType splitType){
        return switch (splitType) {
            case EXACT -> new ExactAmountSplitMethodAmountStrategy();
            case EQUAL -> new EqualAmountSplitMethodAmountStrategy();
            case PERCENT -> new PercentAmountSplitMethodAmountStrategy();
            case RATIO -> new RatioAmountSplitMethodAmountStrategy();
            default -> null;
        };
    }
}
