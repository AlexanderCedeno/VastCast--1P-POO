package Classes;

/**
 *
 * @author Kevin Cedeno
 * @author Kevin Valle
 * @author Gustavo Castro
 */
public class Observation {

    private String value;
    private String date;

    public Observation(String v, String d) {
        value = v;
        date = d;
    }

    public String getValue() {
        return value;
    }

    public String getDate() {
        return date;
    }
}
