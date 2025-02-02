package at.campus02.swe.logic;


import at.campus02.swe.Calculator;
import at.campus02.swe.CalculatorException;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Stack;

public class CalculatorImpl implements Calculator {

    private Random random = new Random();

    private Stack<Double> stack_ = new Stack<Double>();
    private Double register;
    private Map<String, Double> namedRegisters = new HashMap<>();

    public CalculatorImpl() {
    }

    public CalculatorImpl(Random random) {
        this.random = random;
    }

    @Override
    public double perform(Operation op) throws CalculatorException {

        double b;
        double a;

        switch (op) {
            case add:
                b = pop();
                a = pop();
                return a + b;
            case sub:
                b = pop();
                a = pop();
                return a - b;
            case div:
                b = pop();
                a = pop();
                double c = a / b;
                if (Double.isInfinite(c))
                    throw new CalculatorException("Division by zero");
                return c;
            case mul:
                b = pop();
                a = pop();
                return a * b;
            case mod:
                b = pop();
                a = pop();
                return a % b;
            case sin:
                a = pop();
                return Math.sin(Math.toRadians(a));
            case cos:
                a = pop();
                return Math.cos(Math.toRadians(a));
            case random:
                b = pop();  // b = Maximum
                a = pop();  // a = Minimum
                return random.nextInt((int)b - (int)a) + (int)a;
            case dotproduct:
                return calculateDotProduct();
            default:
                throw new CalculatorException("Unsupported operation: " + op);
        }
    }

	private double calculateDotProduct() throws CalculatorException {
        int len = (int)pop();
        double dotproduct = 0;
        double[] vec1 = new double[len];
        for (int i = 0; i < len; i++)
            vec1[i] = pop();
        for (int i = 0; i < len; i++)
            dotproduct += pop() * vec1[i];
        return dotproduct;
    }

    @Override
    public double pop() throws CalculatorException {
        if (stack_.isEmpty())
            throw new CalculatorException();
        return stack_.pop();
    }

    @Override
    public void store(double result) throws CalculatorException {
        register = result;
    }

	@Override
	public void store(double result, String register) throws CalculatorException {
		namedRegisters.put(register, result);
	}

	@Override
    public double load() throws CalculatorException {
        if (register == null)
            throw new CalculatorException("Register is empty");
        return register;
    }

    @Override
    public double load(String register) throws CalculatorException {
        if (!namedRegisters.containsKey(register))
            throw new CalculatorException("No such register: " + register);
        return namedRegisters.getOrDefault(register, 0.);
    }

    @Override
    public void push(double v) {
        stack_.push(v);
    }

    @Override
    public void clear() {
        stack_.clear();
    }

}
