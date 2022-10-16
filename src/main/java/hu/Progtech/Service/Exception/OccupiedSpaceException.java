package hu.Progtech.Service.Exception;

public class OccupiedSpaceException extends RuntimeException {
    public OccupiedSpaceException(String message) {
        super(message);
    }
}