package internship;

import static java.lang.Integer.MAX_VALUE;
import static java.lang.Integer.MIN_VALUE;

public class StatsAccumulatorImpl implements StatsAccumulator {
    private int value;
    private int max = MIN_VALUE;
    private int min = MAX_VALUE;
    private int count;
    @Override
    public void add(int value) {
        this.value=value;
        count++;
        if(value<min)
            min=value;
        else if(value>max)
            max=value;
    }

    @Override
    public int getMin() {
        return min;
    }

    @Override
    public int getMax() {
        return max;
    }

    @Override
    public int getCount() {
        return count;
    }

    @Override
    public Double getAvg() {
        if(count == 0)
            return null;
        else
        return ((double)max + min)/count;
    }
}
