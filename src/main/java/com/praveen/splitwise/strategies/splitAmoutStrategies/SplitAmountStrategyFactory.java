package com.praveen.splitwise.strategies.splitAmoutStrategies;

import com.praveen.splitwise.models.constants.SplitMethodType;

public class SplitAmountStrategyFactory {
    public static SplitMethod getSplitAmountStrategy(SplitMethodType splitType){
        return switch (splitType) {
            case EXACT -> new ExactSplitMethodAmountStrategy();
            case EQUAL -> new EqualSplitMethodAmountStrategy();
            case PERCENT -> new PercentSplitMethodAmountStrategy();
            case RATIO -> new RatioSplitMethodAmountStrategy();
            default -> null;
        };
    }
}
