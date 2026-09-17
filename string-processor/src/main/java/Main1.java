import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Main1 
{
    public static void main(String[] args) 
    {
        String inputFileName = "input.txt";
        String outputFileName = "output.txt";
        prepareInputFile(inputFileName);
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFileName));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName)))
        {
            String firstLine = reader.readLine();
            String secondLine = reader.readLine();
            if (firstLine == null || secondLine == null) 
            {
                System.out.println("Error: The file must contain at least 2 lines!");
                return;
            }
            String executionResult = StringProcessor.processVariant(firstLine, secondLine);
            writer.write(executionResult);
            System.out.println("✅ Processing completed. Results saved to " + outputFileName);
        } 
        catch (IOException e) 
        {
            System.err.println("File I/O error occurred: " + e.getMessage());
        }
    }

    private static void prepareInputFile(String fileName) 
    {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) 
        {
            bw.write("java;;54.32***16-45???75.85&&&25-90!!!code+++12");
            bw.newLine();
            bw.write(";*?&+!");
            bw.newLine();
        } 
        catch (IOException e) 
        {
            System.err.println("Failed to prepare the test file: " + e.getMessage());
        }
    }
}
