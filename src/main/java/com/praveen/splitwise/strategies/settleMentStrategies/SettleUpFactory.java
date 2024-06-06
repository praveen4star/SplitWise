package com.praveen.splitwise.strategies.settleMentStrategies;

public class SettleUpFactory {
    public static Settlement getSettleUpStrategy(){
        return new PriorityQueueStrategy();
    }
}
