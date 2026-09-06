import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.math.RoundingMode;
import static org.junit.jupiter.api.Assertions.*;

public class TaylorCalculatorTest {

    private static final double DELTA = 1e-6;

    @Test
    public void testCalculateDouble_Zero() {
        double result = TaylorCalculator.calculateDouble(0.0, 5);
        assertEquals(1.0, result, DELTA);
    }

    @Test
    public void testCalculateDouble_PositiveValue() {
        double x = 1.5;
        double expected = Math.cosh(x);
        double result = TaylorCalculator.calculateDouble(x, 6);
        assertEquals(expected, result, DELTA);
    }

    @Test
    public void testCalculateDouble_NegativeValue() {
        double x = -1.5;
        double expected = Math.cosh(x);
        double result = TaylorCalculator.calculateDouble(x, 6);
        assertEquals(expected, result, DELTA);
    }

    @Test
    public void testCalculateBigDecimal_Zero() {
        BigDecimal x = BigDecimal.ZERO;
        int k = 5;
        BigDecimal result = TaylorCalculator.calculateBigDecimal(x, k);
        BigDecimal expected = BigDecimal.ONE.setScale(k + 1, RoundingMode.HALF_UP);
        assertEquals(expected, result);
    }
}
