import java.io.IOException;

public class Start {
    public static void main(String[] args) {
        try {
            String javaPath = "java";
            String classPath = "classes";
            String className = "Home";

            ProcessBuilder processBuilder = new ProcessBuilder(javaPath, "-cp", classPath, className);
            Process process = processBuilder.start();
            process.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}