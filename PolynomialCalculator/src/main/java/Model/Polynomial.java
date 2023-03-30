package Model;

import java.util.*;

public class Polynomial {

        private Map<Integer, Monomial> monomials;

        public Polynomial() {
            monomials = new HashMap<>();
        }

        public int maxDegree()
        {
            int degree=-1;
            for(int m1 : this.getMonomials().keySet())
            {
                Monomial m = this.getMonomials().get(m1);
                if(m.getCoefficient()!=0 && m.getDegree()>degree)
                {
                    degree=m.getDegree();
                }
            }
            return degree;
        }
        public void addMonomial(Monomial m) {
            int degree = m.getDegree();
            if (monomials.containsKey(degree)) {
                Monomial current = monomials.get(degree);
                current.setCoefficient(current.getCoefficient() + m.getCoefficient());
            } else {
                monomials.put(degree, m);
            }
        }

        public void removeMonomial(int degree) {
            monomials.remove(degree);
        }

        public Map<Integer, Monomial> getMonomials() {
            return monomials;
        }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        List<Integer> degrees = new ArrayList<>(monomials.keySet());
        Collections.sort(degrees);


        for (int i = degrees.size() - 1; i >= 0; i--) {
            int degree = degrees.get(i);
            Monomial m = monomials.get(degree);
            if (m.getCoefficient() != 0) {
                if (sb.length() > 0) {
                    sb.append(m.getCoefficient() > 0 ? " + " : " - ");
                } else {
                    sb.append(m.getCoefficient() < 0 ? "-" : "");
                }
                sb.append(Math.abs(m.getCoefficient()));
                if (degree > 0) {
                    sb.append("x");
                    if (degree > 1) {
                        sb.append("^" + degree);
                    }
                }
            }
        }
        return sb.toString();
    }
    }

