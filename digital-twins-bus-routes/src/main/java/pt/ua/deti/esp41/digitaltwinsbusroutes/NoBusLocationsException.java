package pt.ua.deti.esp41.digitaltwinsbusroutes;

public class NoBusLocationsException extends Exception {
    public NoBusLocationsException(String errorMessage) {
        super(errorMessage);
    }
}