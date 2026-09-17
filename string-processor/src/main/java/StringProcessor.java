import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.Locale;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

public class StringProcessor 
{
    public static String processVariant(String sourceLine, String delimiters) 
    {
        StringBuilder resultLog = new StringBuilder();
        resultLog.append("Source line: ").append(sourceLine).append("\n");
        resultLog.append("Delimiters: ").append(delimiters).append("\n\n");
        StringTokenizer tokenizer = new StringTokenizer(sourceLine, delimiters);
        ArrayList<String> allTokens = new ArrayList<>();
        ArrayList<Double> doubleNumbers = new ArrayList<>();
        ArrayList<String> nonNumberTokens = new ArrayList<>();
        ArrayList<String> timeTokens = new ArrayList<>();
        while (tokenizer.hasMoreTokens()) 
        {
            String token = tokenizer.nextToken();
            allTokens.add(token);
            try 
            {
                double val = Double.parseDouble(token);
                doubleNumbers.add(val);
            } 
            catch (NumberFormatException e) 
            {
                nonNumberTokens.add(token);
            }
        }
        Double[] doubleArray = doubleNumbers.toArray(new Double[0]);
        resultLog.append("1. Real numbers found: ").append(Arrays.toString(doubleArray)).append("\n");
        Pattern timePattern = Pattern.compile("^(0[0-9]|1[0-9]|2[0-3])-(0[0-9]|[1-5][0-9])$");
        SimpleDateFormat sdf = new SimpleDateFormat("HH-mm");
        sdf.setLenient(false);
        for (String token : nonNumberTokens) 
        {
            if (timePattern.matcher(token).matches()) 
            {
                try 
                {
                    sdf.parse(token);
                    timeTokens.add(token);
                } 
                catch (Exception ignored) {}
            }
        }
        resultLog.append("2. Valid time tokens (HH-MM): ").append(timeTokens).append("\n");
        String[] nonNumbersArray = nonNumberTokens.toArray(new String[0]);
        Arrays.sort(nonNumbersArray, new Comparator<String>() 
        {
            @Override
            public int compare(String s1, String s2) 
            {
                return s1.compareToIgnoreCase(s2);
            }
        });
        resultLog.append("3. Sorted non-number tokens: ").append(Arrays.toString(nonNumbersArray)).append("\n\n");
        int shortestTokenIndexInSource = -1;
        String shortestToken = null;
        if (!allTokens.isEmpty()) 
        {
            for (String token : allTokens) 
            {
                if (shortestToken == null || token.length() < shortestToken.length()) 
                {
                    shortestToken = token;
                }
            }
            if (shortestToken != null) 
            {
                shortestTokenIndexInSource = sourceLine.indexOf(shortestToken);
            }
        }
        StringBuilder sb = new StringBuilder(sourceLine);
        Random random = new Random();
        int randomVal = random.nextInt(100);
        String insertStr = " " + randomVal;
        if (!doubleNumbers.isEmpty()) 
        {
            String firstDoubleStr = doubleNumbers.get(0).toString();
            int doubleIdx = sourceLine.indexOf(firstDoubleStr); 
            if (doubleIdx != -1) 
            {
                int insertPosition = doubleIdx + firstDoubleStr.length();
                sb.insert(insertPosition, insertStr);
                resultLog.append("-> Random number ").append(randomVal)
                         .append(" added after the first real number (").append(firstDoubleStr).append(")\n");
                if (shortestTokenIndexInSource >= insertPosition) 
                {
                    shortestTokenIndexInSource += insertStr.length();
                }
            }
        } 
        else 
        {
            int middle = sb.length() / 2;
            String midInsertStr = " " + randomVal + " ";
            sb.insert(middle, midInsertStr);
            resultLog.append("-> No real numbers found. Random number ").append(randomVal)
                     .append(" added to the middle of the line.\n");
            if (shortestTokenIndexInSource >= middle) 
            {
                shortestTokenIndexInSource += midInsertStr.length();
            }
        }
        if (shortestToken != null && shortestTokenIndexInSource != -1) 
        {
            sb.delete(shortestTokenIndexInSource, shortestTokenIndexInSource + shortestToken.length());
            resultLog.append("-> Shortest token '").append(shortestToken).append("' removed from the line.\n");
        }
        resultLog.append("Final modified line: ").append(sb.toString()).append("\n\n");
        Locale ukLocale = Locale.UK;
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(ukLocale);
        NumberFormat percentFormat = NumberFormat.getPercentInstance(ukLocale);
        DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
        resultLog.append("--- 📊 Formatting Demonstration (Locale: UK) ---\n");
        if (!doubleNumbers.isEmpty()) 
        {
            double testNum = doubleNumbers.get(0);
            resultLog.append(String.format(" String.format method: %.2f\n", testNum));
            resultLog.append(" Currency (UK): ").append(currencyFormat.format(testNum)).append("\n");
            resultLog.append(" Percentage: ").append(percentFormat.format(testNum / 100)).append("\n");
            resultLog.append(" DecimalFormat class: ").append(decimalFormat.format(testNum)).append("\n");
        } 
        else 
        {
            resultLog.append("No real numbers available for formatting.\n");
        }

        resultLog.append("\n Reverse demonstration: 'Variant7' -> ")
                 .append(new StringBuilder("Variant7").reverse()).append("\n");
        return resultLog.toString();
    }
}
