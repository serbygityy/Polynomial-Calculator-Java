package View;
import Logic.Operations;
import Model.Monomial;
import Model.Polynomial;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.*;


public class CalculatorController {

    private CalculatorView calculatorView;
    private Operations operations;

    public CalculatorController(CalculatorView calculatorView) {
        this.calculatorView = calculatorView;
    }

    public void AbuttonActionPerformed() {
        try {
            Polynomial p1 = readPolynomial(0);
            Polynomial p2 = readPolynomial(1);
            Polynomial result = operations.add(p1, p2);
            calculatorView.setRField(result.toString());
        }catch(NumberFormatException exc){
            System.out.println(exc.getMessage());;
        }


    }


    public void DebuttonActionPerformed() {
        try {
            Polynomial p1 = readPolynomial(0);

            Polynomial result = operations.differentiate(p1);
            calculatorView.setRField(result.toString());
        }catch(NumberFormatException exc){
            System.out.println(exc.getMessage());;
        }
    }

    public void SbuttonActionPerformed() {
        try {
            Polynomial p1 = readPolynomial(0);
            Polynomial p2 = readPolynomial(1);
            Polynomial result = operations.subtract(p1,p2);
            calculatorView.setRField(result.toString());
        }catch(NumberFormatException exc){
            System.out.println(exc.getMessage());
        }
    }

    public void MbuttonActionPerformed() {
        try {
            Polynomial p1 = readPolynomial(0);
            Polynomial p2 = readPolynomial(1);
            Polynomial result = operations.multiply(p1,p2);
            calculatorView.setRField(result.toString());
        }catch(NumberFormatException exc){
            System.out.println(exc.getMessage());;
        }
    }

    public void DbuttonActionPerformed() {
        try {
            Polynomial p1 = readPolynomial(0);
            Polynomial p2 = readPolynomial(1);
            Polynomial result = operations.divide(p1,p2);
            calculatorView.setRField(result.toString());
        }catch(NumberFormatException exc){
            System.out.println("m-am blocat!!!");

        }
    }

    public void IbuttonActionPerformed() {
        try {
            Polynomial p1 = readPolynomial(0);
            Polynomial result = operations.integrate(p1);
            calculatorView.setRField(result.toString());
        }catch(NumberFormatException exc){
            System.out.println(exc.getMessage());
        }
    }
    public Polynomial readPolynomial(int first)throws NumberFormatException{
        String polynomString;
        if(first==0){
            polynomString = calculatorView.getFPolynomial().getText();
        }else{
            polynomString = calculatorView.getSPolynomial().getText();
        }
        String valid = "[^\\dx+^-]";
        if(Pattern.compile(valid).matcher(polynomString).find()){

            calculatorView.printDialogMessage("Polynomial input error! Try again.");
            return new Polynomial();
        }else {
            return convertString(polynomString);
        }
    }
    public Polynomial convertString(String convertS){
        Polynomial polynom=new Polynomial();
        String stringPattern = "(\\+?-?\\d*x?\\^?\\d?)";
        Pattern pattern = Pattern.compile(stringPattern);
        Matcher match = pattern.matcher(convertS);
        while(match.find()){

            Monomial monom = new Monomial();
            String monomialString = match.group(0);

            if(!monomialString.isEmpty()) {

                Pattern degreePattern = Pattern.compile("(\\^\\d*)");
                Matcher degreeMatcher = degreePattern.matcher(monomialString);

                if(degreeMatcher.find() && !degreeMatcher.group().isEmpty()){

                    monom.setDegree(Integer.parseInt(degreeMatcher.group().substring(1)));
                    String coeffString = monomialString.substring(0, monomialString.length() - degreeMatcher.group().length() - 1);
                    monom.setCoefficient(convertCoefficient(coeffString));

                }else{

                    if(monomialString.charAt(monomialString.length() - 1) == 'x') {
                        monom.setCoefficient(convertCoefficient(monomialString.substring(0,monomialString.length()-1)));
                        monom.setDegree(1);

                    }else{
                        monom.setCoefficient(convertCoefficient(monomialString));
                        monom.setDegree(0);
                    }
                }
                polynom.addMonomial(monom);
            }
        }
        return  polynom;
    }

    public int convertCoefficient(String s){
        if(s.equals("-")){
            return (int) -1.0;
        }else if(s.equals("+")) {
            return (int) 1.0;
        }else{
            return (int) Double.parseDouble(s);
        }
    }
}
