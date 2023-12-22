package hexlet.code;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;
import java.util.concurrent.Callable;

@CommandLine.Command(name = "differ", mixinStandardHelpOptions = true, version = "differ 4.0",
        description = "Finds the difference between two JSON or YAML files.")
public class App implements Callable<Integer> {
    private static final int SUCCESS = 0;
    private static final int ERROR = 1;
    @CommandLine.Parameters(index = "0", description = "path to the first file")
    private String filepath1;
    @CommandLine.Parameters(index = "1", description = "path to the second file")
    private String filepath2;
    @CommandLine.Option(names = {"-f", "--format"}, description = "output format [default: stylish]")
    private static String outputFormat = "stylish";
    @Override
    public Integer call() {
        try {
            System.out.println(Differ.generate(filepath1, filepath2, outputFormat));
            return SUCCESS;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ERROR;
        }
    }
    public static void main(String[] args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }
}
