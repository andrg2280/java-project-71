package hexlet.code;
import com.fasterxml.jackson.core.JsonProcessingException;
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

    @Parameters(index = "0", description = "path to first file")
    private String filepath1;
    @Parameters(index = "1", description = "path to second file")
    private String filepath2;
    @Option(names = {"-f", "--format"}, description = "output format [default: stylish]")
    private String format = "stylish";

    @Override
    public Integer call() throws Exception { // your business logic goes here...
        System.out.printf(generate(filepath1, filepath2));
        return 0;
    }
    public static String generate (String filePath1, String filePath2) throws Exception {
        String s1 = FileComparator.read(filePath1);
        String s2 = FileComparator.read(filePath2);
        System.out.println("file1="+JacksonFileComparator.getData(s1));
        System.out.println("file2="+JacksonFileComparator.getData(s2));
        return FileComparator.compare(s1, s2);
    }
}