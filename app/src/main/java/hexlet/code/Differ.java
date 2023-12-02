package hexlet.code;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;
import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.security.MessageDigest;
import java.util.concurrent.Callable;

@Command(name = "differ", mixinStandardHelpOptions = true, version = "checksum 4.0",
        description = "Prints the checksum (SHA-256 by default) of a file to STDOUT.")
class Differ implements Callable<Integer> {

    @Parameters(index = "0", description = "The file whose checksum to calculate.")
    private String s1;
    @Parameters(index = "1", description = "The file whose checksum to calculate.")
    private String s2;

    @Override
    public Integer call() throws Exception { // your business logic goes here...
        System.out.printf(generate(s1, s2));
        return 0;
    }
    public static String generate (String fiePath1, String filePath2) throws FileNotFoundException {
        String s1 = FileComparator.read(fiePath1);
        String s2 = FileComparator.read(filePath2);
        return FileComparator.compare(s1, s2);
    }
}