package theater;

/**
 * Calculator for quantities related to a performance.
 * (Will later be subclassed for different play types.)
 */
public class AbstractPerformanceCalculator {

    protected final Performance performance;
    protected final Play play;

    /**
     * Constructs a calculator for the given performance and play.
     *
     * @param performance the performance
     * @param play        the play details
     */
    public AbstractPerformanceCalculator(Performance performance, Play play) {
        this.performance = performance;
        this.play = play;
    }

    /**
     * Factory method for creating a performance calculator.
     * For now, this simply returns a basic calculator, but
     * later it will select an appropriate subclass.
     *
     * @param performance the performance
     * @param play        the play details
     * @return a new calculator
     */
    public static AbstractPerformanceCalculator createPerformanceCalculator(
            Performance performance, Play play) {
        return new AbstractPerformanceCalculator(performance, play);
    }
}
