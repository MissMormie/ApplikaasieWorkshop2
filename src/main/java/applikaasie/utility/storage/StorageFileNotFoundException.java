/*
 * https://github.com/spring-guides/gs-uploading-files/blob/master/complete/src/main/java/hello/storage/StorageFileNotFoundException.java
 */
package applikaasie.utility.storage;


public class StorageFileNotFoundException extends StorageException {

    public StorageFileNotFoundException(String message) {
        super(message);
    }

    public StorageFileNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}