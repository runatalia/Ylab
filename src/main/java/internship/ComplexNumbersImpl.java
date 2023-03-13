package internship;

public class ComplexNumbersImpl implements ComplexNumbers{
    private double realNumber;
    private double imaginaryNumber;
    public ComplexNumbersImpl(double realNumber){
         this.realNumber = realNumber;
    }
    public ComplexNumbersImpl(double realNumber, double imaginaryNumber){
        this(realNumber);
        this.imaginaryNumber = imaginaryNumber;
    }

    public double getRealNumber() {
        return realNumber;
    }

    public double getImaginaryNumber() {
        return imaginaryNumber;
    }

    public ComplexNumbersImpl sum(ComplexNumbersImpl complexNumbers){
        double sumRealNumber = complexNumbers.realNumber + this.realNumber;
        double sumImaginaryNumber = complexNumbers.imaginaryNumber + complexNumbers.imaginaryNumber;
        return new ComplexNumbersImpl(sumRealNumber, sumImaginaryNumber);
}

    public ComplexNumbersImpl minus(ComplexNumbersImpl complexNumbers){
        double minusRealNumber = complexNumbers.realNumber - this.realNumber;
        double minusImaginaryNumber = complexNumbers.imaginaryNumber - complexNumbers.imaginaryNumber;
        return new ComplexNumbersImpl(minusRealNumber, minusImaginaryNumber);
    }

    public ComplexNumbersImpl multiply(ComplexNumbersImpl complexNumbers){
        double multiplyRealNumber = complexNumbers.realNumber * this.realNumber;
        double multiplyImaginaryNumber = complexNumbers.imaginaryNumber * complexNumbers.imaginaryNumber;
        return new ComplexNumbersImpl(multiplyRealNumber, multiplyImaginaryNumber);
    }

    public double abs(){
        return Math.round(Math.sqrt(this.realNumber * this.realNumber
                + imaginaryNumber * imaginaryNumber) * 100.0) / 100.0;
    }

    @Override
    public String toString() {
        return "ComplexNumbers: " + realNumber + " + " + imaginaryNumber + "*i";
    }
}
