package at.campus02.swe;

public interface Calculator {

    enum Operation {

        add, sub, mul, div, sin, cos, dotproduct, mod, random;
    };

    void push(double value);

    double pop() throws CalculatorException;

    double perform(Operation op) throws CalculatorException;

    void store(double result) throws CalculatorException;

    void store(double result, String register) throws CalculatorException;

    double load() throws CalculatorException;

    double load(String register) throws CalculatorException;

    void clear();
}