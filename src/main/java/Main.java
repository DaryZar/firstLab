import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Formatter;
import java.util.Scanner;



public class Main 
{
    public static void main(String[] args) 
    {
        try 
        {
            System.out.println("=== Option with Primitive Types (Scanner) ===");
            Scanner scanner = new Scanner(System.in);
            
            System.out.print("Enter x (double): ");
            double xDouble = scanner.nextDouble();
            System.out.print("Enter k (natural number): ");
            int kDouble = scanner.nextInt();

            double resultDouble = TaylorCalculator.calculateDouble(xDouble, kDouble);
            double standardDouble = Math.cosh(xDouble);

            int widthOut = kDouble + 12;
            int precisionOut = kDouble + 1;
            
            System.out.printf("Taylor Series: %+0" + widthOut + "." + precisionOut + "f\n", resultDouble);
            System.out.printf("Math.cosh():   %+0" + widthOut + "." + precisionOut + "f\n", standardDouble);

            System.out.println("\n=== Option with Big Types (BufferedReader) ===");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            
            System.out.print("Enter x (BigDecimal): ");
            BigDecimal xBig = new BigDecimal(reader.readLine());
            System.out.print("Enter k (natural number): ");
            int kBig = Integer.parseInt(reader.readLine());

            BigDecimal resultBig = TaylorCalculator.calculateBigDecimal(xBig, kBig);
            BigDecimal standardBig = BigDecimal.valueOf(Math.cosh(xBig.doubleValue()));

            System.out.printf("Taylor Series (Big): %+" + (kBig + 12) + "." + (kBig + 1) + "f\n", resultBig);
            System.out.printf("Math.cosh() (Big):   %+" + (kBig + 12) + "." + (kBig + 1) + "f\n", standardBig);

            System.out.println("\n=== Formatting Demonstration (Formatter) ===");
            Formatter formatter = new Formatter();
            
            int xRounded = (int) Math.round(xDouble);
            formatter.format("Rounded x in octal form:       %#o\n", xRounded);
            formatter.format("Rounded x in hexadecimal form: %#x\n", xRounded);
            
            int width = kDouble + 15;
            int precision = kDouble + 1;
            
            formatter.format("Flags (+, 0): %+0" + width + "." + precision + "f\n", resultDouble);
            formatter.format("Flag (-):     %-" + width + "." + precision + "f (left aligned)\n", resultDouble);
            
            System.out.print(formatter.toString());
            formatter.close();

        }
         catch (IOException e) 
        {
            System.out.println("I/O Error: " + e.getMessage());
        } catch (Exception e) 
        {
            System.out.println("Invalid input data: " + e.getMessage());
        }
    }
}
