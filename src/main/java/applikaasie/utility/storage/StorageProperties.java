/*
 * https://github.com/spring-guides/gs-uploading-files/blob/master/complete/src/main/java/hello/storage/StorageProperties.java
 */
package applikaasie.utility.storage;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;

@ConfigurationProperties("storage")
public class StorageProperties {

    /**
     * Folder location for storing files
     */
    private String location = "src\\main\\webapp\\images\\artikelen";

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

}