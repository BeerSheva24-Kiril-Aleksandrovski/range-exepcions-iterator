package telran.range;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;

import telran.range.exceptions.OutOfRangeMaxValueException;
import telran.range.exceptions.OutOfRangeMinValueException;

public class RangeTest {

    private static final int MIN = 50;
    private static final int MAX = 100;
    Range range = Range.getRange(MIN, MAX);
    @Test
    void wrongRangeCreatingTest(){
        assertThrowsExactly(IllegalArgumentException.class, () -> Range.getRange(100, 50));
    }
    @Test
    void rightNumberTest() throws Exception{
        range.checkNumber(55);
    }
    void wrongNumberTest() throws Exception{
        assertThrowsExactly(OutOfRangeMaxValueException.class,() -> range.checkNumber(MAX + 1));
        assertThrowsExactly(OutOfRangeMinValueException.class, () -> range.checkNumber(MIN - 1));
        }
    @Test
    void iteratorTets() {
        Range rangeIt = Range.getRange(0, 5);
        Iterator <Integer> it = rangeIt.iterator();
        Integer [] expected = {0, 1, 2, 3, 4, 5};
        Integer [] actual = new Integer[expected.length];
    
        int index = 0;
        while(it.hasNext()) {
            actual[index++] = it.next();
        }
        assertArrayEquals(expected, actual);
        assertThrowsExactly(NoSuchElementException.class,it::next);
    }

    @Test
    void iteratorPredicateTest() {
        Range rangeIt = Range.getRange(0, 5);
        rangeIt.setPredicate((number) -> number % 2 == 0);
        Iterator <Integer> it = rangeIt.iterator();
        Integer [] expected = {0, 2, 4};
        Integer [] actual = new Integer[expected.length];

        int index = 0;
        while(it.hasNext()) {
            actual[index++] = it.next();
        }
        assertArrayEquals(expected, actual);
        assertThrowsExactly(NoSuchElementException.class,it::next);
    }
    @Test
    void iteratorPredicateTest2() {
        Range rangeIt = Range.getRange(98, 104);
        rangeIt.setPredicate((number) -> number >= 100);
        Iterator <Integer> it = rangeIt.iterator();
        Integer [] expected = {100,101, 102, 103,104};
        Integer [] actual = new Integer[expected.length];

        int index = 0;
        while(it.hasNext()) {
            actual[index++] = it.next();
        }
        assertArrayEquals(expected, actual);
        assertThrowsExactly(NoSuchElementException.class,it::next);
    }
}