package Logic;

import Model.Monomial;
import Model.Polynomial;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class Operations {
    public static Polynomial add(Polynomial p1, Polynomial p2) {
        Polynomial result = new Polynomial();
        for (int degree : p1.getMonomials().keySet()) {
            Monomial m = p1.getMonomials().get(degree);
            result.addMonomial(new Monomial(m.getCoefficient(), degree));
        }
        for (int degree : p2.getMonomials().keySet()) {
            Monomial m = p2.getMonomials().get(degree);
            result.addMonomial(new Monomial(m.getCoefficient(), degree));
        }
        return result;
    }

    public static Polynomial subtract(Polynomial p1, Polynomial p2) {
        Polynomial result = new Polynomial();
        for (int degree : p1.getMonomials().keySet()) {
            Monomial m = p1.getMonomials().get(degree);
            result.addMonomial(new Monomial(m.getCoefficient(), degree));
        }
        for (int degree : p2.getMonomials().keySet()) {
            Monomial m = p2.getMonomials().get(degree);
            result.addMonomial(new Monomial(-m.getCoefficient(), degree));
        }
        return result;
    }

    public static Polynomial multiply(Polynomial p1, Polynomial p2) {
        Polynomial result = new Polynomial();
        for (int degree1 : p1.getMonomials().keySet()) {
            for (int degree2 : p2.getMonomials().keySet()) {
                Monomial m1 = p1.getMonomials().get(degree1);
                Monomial m2 = p2.getMonomials().get(degree2);
                double coef = m1.getCoefficient() * m2.getCoefficient();
                int deg = degree1 + degree2;
                result.addMonomial(new Monomial(coef, deg));
            }
        }
        return result;
    }


public static Polynomial divide(Polynomial p1, Polynomial p2) {
    Polynomial result = new Polynomial();
    int s1 = p1.maxDegree();
    int s2 = p2.maxDegree();
    if (s1 >= s2) {
        for (int i = 0; i <= s1 - s2; i++) {
            Polynomial temp = new Polynomial();
            int pow1 = p1.maxDegree();
            int pow2 = p2.maxDegree();
            double coeff1 = 0, coeff2 = 0;
            if (pow1 - pow2 >= 0) {

               for(Integer m : p1.getMonomials().keySet()){
                   Monomial monomial = p1.getMonomials().get(m);
                   if(monomial.getDegree() == pow1)
                   {
                        coeff1 = monomial.getCoefficient();
                   }
               }

                for(Integer m : p2.getMonomials().keySet()){
                    Monomial monomial = p2.getMonomials().get(m);
                    if(monomial.getDegree() == pow2)
                    {
                         coeff2 = monomial.getCoefficient();
                    }
                }
                double coeff = coeff1 / coeff2;
                Monomial monomial = new Monomial(coeff,pow1-pow2);
                Polynomial poly = new Polynomial();
                poly.addMonomial(monomial);
                temp = Operations.multiply(p2, poly);
                p1 = Operations.subtract(p1, temp);
                if (result.getMonomials().size() != 0) {
                    result = Operations.add(result, poly);
                } else result = poly;
            }
        }
    } else {
        result = p1;
    }
    return result;
}

        public static Polynomial differentiate(Polynomial p1)
        {
            Polynomial result=new Polynomial();
            for (int m1 : p1.getMonomials().keySet()) {
                Monomial m = p1.getMonomials().get(m1);
                if(m.getDegree()!=0)
                {
                    double coef = m.getCoefficient() * (m.getDegree());
                    int deg = m.getDegree()-1;
                    result.addMonomial(new Monomial(coef, deg));
                }

            }
            return result;
        }

        public static Polynomial integrate(Polynomial p1){
            Polynomial result = new Polynomial();
            for (Map.Entry<Integer, Monomial> entry : p1.getMonomials().entrySet()) {
                Monomial m = entry.getValue();
                double coef = m.getCoefficient() / (m.getDegree() + 1);
                int deg = m.getDegree() + 1;
                result.addMonomial(new Monomial(coef, deg));
            }
            return result;
        }
}
