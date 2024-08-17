package telran.range;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Predicate;

import telran.range.exceptions.OutOfRangeMaxValueException;
import telran.range.exceptions.OutOfRangeMinValueException;

public class Range implements Iterable<Integer>{
    private static final String ERROR_MESSAGE = "max les or eqyal min";
    private int max, min;
    private Predicate<Integer> predicate = null;

    private Range (int min, int max){
        this.min = min;
        this.max = max;
    }

    public void setPredicate(Predicate<Integer> predicate) {
        this.predicate = predicate;
    }

    public static Range getRange(int min, int max) {
        if (max <= min)  {
            throw new IllegalArgumentException(ERROR_MESSAGE);
        }
        return new Range (min, max);
    }
    public void checkNumber (int number)throws OutOfRangeMaxValueException, OutOfRangeMinValueException{
        if (number > max) {
            throw new OutOfRangeMaxValueException(max, number);
        }
        if (number < min) {
            throw new OutOfRangeMinValueException(min, number);
        }
    }
    @Override
    public Iterator<Integer> iterator() {
        return new RangeIterator();
    }
    private class RangeIterator implements Iterator<Integer> {
        int current;

        public RangeIterator() {
            this.current = getNextCurrent(min);
        }

        private int getNextCurrent(int number) {
            int next = number;
            while (predicate != null && !predicate.test(next)) {
                next++;
            }
            return next;
        }

        @Override
        public boolean hasNext() {
            return current <= max;
        }

        @Override
        public Integer next() {
            if(! hasNext()) {
                throw new NoSuchElementException();
            }

            int temp = current;
            current = getNextCurrent(temp + 1);
            return temp;
        }

    }
}

