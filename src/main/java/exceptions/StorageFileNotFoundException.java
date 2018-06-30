package exceptions;

public class StorageFileNotFoundException extends RuntimeException {

    public StorageFileNotFoundException() {
        super();
    }

    public StorageFileNotFoundException(String message) {
        super(message);
    }


    public StorageFileNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }


    public StorageFileNotFoundException(Throwable cause) {
        super(cause);
    }

}
