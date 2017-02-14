/*
 * https://github.com/spring-guides/gs-uploading-files/blob/master/complete/src/main/java/hello/storage/StorageException.java
 */
package applikaasie.utility.storage;

public class StorageException extends RuntimeException {

    public StorageException(String message) {
        super(message);
    }

    public StorageException(String message, Throwable cause) {
        super(message, cause);
    }
}
