package hexlet.code;
import hexlet.code.Differ;
import picocli.CommandLine;

public class App {
    public static void main(String[] args) {
        int exitCode = new CommandLine(new Differ()).execute(args);
        System.exit(exitCode);
    }
}
