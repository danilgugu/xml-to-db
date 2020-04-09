package danil.xmltodb.helper;

import org.springframework.stereotype.Component;

import java.io.File;
import java.nio.file.Paths;

@Component
public class FileHelper {

    public File getFile(String path) {
        return Paths.get(path).toFile();
    }
}
