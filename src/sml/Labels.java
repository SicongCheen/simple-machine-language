package sml;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a Labels class.
 *
 * @author Sicong Chen (schen08)
 */
public final class Labels {

    private final List<String> labels;

    {
        labels = new ArrayList<>();
    }

    /**
     * Add label lab to this list and return its number in the list
     * (the first one added is number 0)
     * Precondition: the list has at most 49 entries
     *
     * @param lab the label to be added
     * @return int the index of the label added
     */
    public int addLabel(String lab) {
        labels.add(lab);
        return labels.size() - 1;
    }

    /**
     * = the number of label lab in the list
     * (= -1 if lab is not in the list)
     *
     * @param lab the label
     * @return int the index of the label
     */
    public int indexOf(String lab) {
        for (int i = 0; i != labels.size(); i++) {
            if (lab.equals(labels.get(i))) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Representation of this instance, "(label 0, label 1, ..., label (n-1))"
     *
     * @return String representation of the instance
     */
    @Override
    public String toString() {
        StringBuilder r = new StringBuilder("(");

        for (int i = 0; i != labels.size(); i++) {
            if (i == 0) {
                r.append(labels.get(i));
            } else {
                r.append(", ").append(labels.get(i));
            }
        }
        r.append(")");
        return r.toString();
    }

    /**
     * Sets the number of elements in the list to 0
     */
    public void reset() {
        labels.clear();
    }
}
