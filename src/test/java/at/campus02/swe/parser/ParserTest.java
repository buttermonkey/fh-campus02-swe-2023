package at.campus02.swe.parser;

import java.io.File;
import java.io.FileNotFoundException;

import org.junit.Test;
import static org.mockito.Mockito.*;

import at.campus02.swe.Calculator;
import at.campus02.swe.Calculator.Operation;


public class ParserTest {

    @Test(expected = IllegalArgumentException.class)
    public void testNullParser() {
        new Parser(null);
    }

    @Test(expected = FileNotFoundException.class)
    public void testParserInvalidFile() throws Exception {

        Calculator cal = mock(Calculator.class);

        Parser parser = new Parser(cal);
        parser.parse(new File("invalid"));
    }

    @Test
    public void testParserTest01Xml() throws Exception {

        Calculator cal = mock(Calculator.class);

        Parser parser = new Parser(cal);
        parser.parse(new File("src/test/resources/test01.xml"));

        verify(cal).push(1.0);
        verify(cal).push(2.0);
        verify(cal).perform(Operation.add);

        verifyNoMoreInteractions(cal);
    }

    @Test
    public void testParserTest02Xml() throws Exception {

        Calculator cal = mock(Calculator.class);

        Parser parser = new Parser(cal);
        parser.parse(new File("src/test/resources/test04.xml"));

        verify(cal).push(1.0);
        verify(cal).push(2.0);
        verify(cal).perform(Operation.mod);

        verifyNoMoreInteractions(cal);
    }

    @Test
    public void testParserTest05Sin() throws Exception {

        Calculator cal = mock(Calculator.class);

        Parser parser = new Parser(cal);
        parser.parse(new File("src/test/resources/test05sin.xml"));

        verify(cal).push(90.0);
        verify(cal).perform(Operation.sin);

        verifyNoMoreInteractions(cal);
    }

    @Test
    public void testParserTest06Cos() throws Exception {

        Calculator cal = mock(Calculator.class);

        Parser parser = new Parser(cal);
        parser.parse(new File("src/test/resources/test06cos.xml"));

        verify(cal).push(0.0);
        verify(cal).perform(Operation.cos);

        verifyNoMoreInteractions(cal);
    }

    @Test
    public void testParserTest08Vectors() throws Exception {

        Calculator cal = mock(Calculator.class);

        Parser parser = new Parser(cal);
        parser.parse(new File("src/test/resources/test08vectors.xml"));

        verify(cal).push(1.0);
        verify(cal).push(3.0);
        verify(cal).push(4.0);
        verify(cal).push(5.0);
        verify(cal).push(2.0);
        verify(cal).perform(Operation.dotproduct);

        verifyNoMoreInteractions(cal);
    }

    @Test
    public void testParserTest09StoreAndLoad() throws Exception {

        Calculator cal = mock(Calculator.class);

        Parser parser = new Parser(cal);
        parser.parse(new File("src/test/resources/test09storeAndLoad.xml"));

        verify(cal).push(11.0);
        verify(cal).push(31.0);
        verify(cal).perform(Operation.add);
        verify(cal).store(.0);  // zero, cause we just run a mock
        verify(cal).load();

        verifyNoMoreInteractions(cal);
    }
}