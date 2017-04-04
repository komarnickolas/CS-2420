package sort_evaluations;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * @author Nickolas Komarnitsky
 * u0717854
 * 2/11/2017
 * 2420
 * Assignment 05
 * I pledge that the work done here was my own and that I have learned how to write this program, such that I could throw it out and restart and finish it in a timely manner. I am not turning in any work that I cannot understand, describe, or recreate. (Name)
 * Nickolas Komarnitsky
 */
public class Timing_Data {
    private final SimpleIntegerProperty count_data;
    private final SimpleDoubleProperty sorted_data;
    private final SimpleDoubleProperty reverse_data;
    private final SimpleDoubleProperty random_data;
    private final SimpleDoubleProperty same_data;
//    private final SimpleDoubleProperty time_random_divided_by_N_log_N_data;
//    private final SimpleDoubleProperty time_worst_divided_by_N_log_N_data;

    /**
     *
     * @param count - test count
     * @param sorted - time for sorted array
     * @param reverse - time for reverse sorted array
     * @param random - time for random array
     * @param same - time for same array
     */
    public Timing_Data(int count, double sorted, double reverse, double random, double same){
        this.count_data = new SimpleIntegerProperty(count);
        this.sorted_data = new SimpleDoubleProperty(sorted);
        this.reverse_data = new SimpleDoubleProperty(reverse);
        this.random_data = new SimpleDoubleProperty(random);
        this.same_data = new SimpleDoubleProperty(same);
    }

    public int getCount_data() {
        return count_data.get();
    }

    public SimpleIntegerProperty count_dataProperty() {
        return count_data;
    }

    public double getSorted_data() {
        return sorted_data.get();
    }

    public SimpleDoubleProperty sorted_dataProperty() {
        return sorted_data;
    }

    public double getReverse_data() {
        return reverse_data.get();
    }

    public SimpleDoubleProperty reverse_dataProperty() {
        return reverse_data;
    }

    public double getRandom_data() {
        return random_data.get();
    }

    public SimpleDoubleProperty random_dataProperty() {
        return random_data;
    }

    public double getSame_data() {
        return same_data.get();
    }

    public SimpleDoubleProperty same_dataProperty() {
        return same_data;
    }
}
