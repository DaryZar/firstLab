import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StringProcessorTest 
{
    @Test
    public void testProcessVariantWithRealNumbersAndTime() 
    {
        String sourceLine = "java;;54.32***16-45???code";
        String delimiters = ";*?";
        String result = StringProcessor.processVariant(sourceLine, delimiters);
        assertTrue(result.contains("Source line: " + sourceLine));
        assertTrue(result.contains("1. Real numbers found: [54.32]"));
        assertTrue(result.contains("2. Valid time tokens (HH-MM): [16-45]"));
        assertFalse(result.contains("Final modified line: java;;"));
    }

    @Test
    public void testProcessVariantNoRealNumbers() 
    {
        String sourceLine = "hello***12-30???world";
        String delimiters = "*?";
        String result = StringProcessor.processVariant(sourceLine, delimiters);
        assertTrue(result.contains("1. Real numbers found: []"));
        assertTrue(result.contains("No real numbers found. Random number"));
    }

    @Test
    public void testProcessVariantInvalidTimeTokens() 
    {
        String sourceLine = "abc;;25-70***99-15???xyz";
        String delimiters = ";*?";
        String result = StringProcessor.processVariant(sourceLine, delimiters);
        assertTrue(result.contains("2. Valid time tokens (HH-MM): []"));
    }
}
