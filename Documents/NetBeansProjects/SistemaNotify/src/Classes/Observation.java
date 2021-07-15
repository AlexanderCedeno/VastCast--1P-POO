package Classes;
/**
 *
 * @author Kevin Cedeno
 * @author Kevin Valle
 */
public class Observation {
    private String value;

    public Observation(String v) {
        value = v;
    }

    public double getValue() {
        return Double.parseDouble(value);
    }
}
