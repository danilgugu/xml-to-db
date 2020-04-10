package danil.xmltodb.helper;

import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Component
public class FileHelper {

    public File getFile(String path) {
        return Paths.get(path).toFile();
    }

    public void write(String content, String path) {
        try {
            Files.write(Paths.get(path), content.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
