package pl.isa.util;

public class ReturnToStartException extends Exception {
    public ReturnToStartException(String message) {
        super(message);
        System.out.println(this.getMessage());
        System.exit(0);
    }
}
