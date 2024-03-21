package edu.miu.cs.cs544.exceptions;

public class LowBalanceException extends RuntimeException {
    //exception for low balance
    public LowBalanceException(String message) {
        super(message);
    }
}
