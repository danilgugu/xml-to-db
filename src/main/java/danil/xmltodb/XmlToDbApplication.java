package danil.xmltodb;

import danil.xmltodb.logic.XmlToDbOperation;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Objects;

@SpringBootApplication
public class XmlToDbApplication implements CommandLineRunner {

    private final XmlToDbOperation operation;

    public XmlToDbApplication(XmlToDbOperation operation) {
        this.operation = operation;
    }

    public static void main(String[] args) {
        SpringApplication.run(XmlToDbApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        if (Objects.nonNull(args) && args.length > 0) {
            operation.process(args[0]);
        }
    }
}
