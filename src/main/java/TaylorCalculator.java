

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;

public class TaylorCalculator 
{
    public static double calculateDouble(double x, int k) 
    {
        double eps = Math.pow(10, -k);
        double sum = 1.0;
        double term = 1.0;
        int n = 1;

        while (true) 
        {
            term *= (x * x) / ((2 * n - 1) * (2 * n));
            if (Math.abs(term) < eps) 
            {
                break;
            }
            sum += term;
            n++;
        }
        return sum;
    }

    public static BigDecimal calculateBigDecimal(BigDecimal x, int k) 
    {
        MathContext mc = new MathContext(k + 10, RoundingMode.HALF_UP);
        BigDecimal eps = BigDecimal.ONE.divide(BigDecimal.TEN.pow(k), mc);
        
        BigDecimal sum = BigDecimal.ONE;
        BigDecimal term = BigDecimal.ONE;
        BigDecimal xSq = x.multiply(x, mc);
        int n = 1;

        while (true) 
        {
            BigInteger factor1 = BigInteger.valueOf(2L * n - 1);
            BigInteger factor2 = BigInteger.valueOf(2L * n);
            BigInteger divisorBI = factor1.multiply(factor2);
            BigDecimal divisor = new BigDecimal(divisorBI);

            term = term.multiply(xSq, mc).divide(divisor, mc);
            if (term.abs().compareTo(eps) < 0) 
            {
                break;
            }
            sum = sum.add(term, mc);
            n++;
        }
        return sum.setScale(k + 1, RoundingMode.HALF_UP);
    }
}
