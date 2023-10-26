package seedu.address.model.appointment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class SortByAppointmentNameComparatorTest {
    @Test
    public void equals() {
        SortByAppointmentDateComparator firstComparator = new SortByAppointmentDateComparator();
        SortByAppointmentDateComparator secondComparator = new SortByAppointmentDateComparator();

        // same object -> returns true
        assertTrue(firstComparator.equals(firstComparator));

        // different types -> returns false
        assertFalse(firstComparator.equals(1));

        // null -> returns false
        assertFalse(firstComparator.equals(null));

        // different comparators -> returns true
        assertTrue(firstComparator.equals(secondComparator));
    }
    @Test
    public void toStringMethod() {
        SortByAppointmentDateComparator comparator = new SortByAppointmentDateComparator();

        String expected = SortByAppointmentDateComparator.class.getCanonicalName() + "{}";
        assertEquals(expected, comparator.toString());
    }
}
