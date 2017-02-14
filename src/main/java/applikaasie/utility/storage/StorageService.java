/*
 * https://github.com/spring-guides/gs-uploading-files/blob/master/complete/src/main/java/hello/storage/StorageService.java
 */
package applikaasie.utility.storage;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;

public interface StorageService {

    void init();

    String store(MultipartFile file);

    Stream<Path> loadAll();

    Path load(String filename);

    Resource loadAsResource(String filename);

    void deleteAll();

}