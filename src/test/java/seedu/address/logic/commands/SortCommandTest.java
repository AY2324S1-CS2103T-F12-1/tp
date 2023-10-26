package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.CARL;
import static seedu.address.testutil.TypicalPersons.DANIEL;
import static seedu.address.testutil.TypicalPersons.ELLE;
import static seedu.address.testutil.TypicalPersons.FIONA;
import static seedu.address.testutil.TypicalPersons.GEORGE;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.SortByAppointmentComparator;
import seedu.address.model.person.SortByNameComparator;

public class SortCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_sortSortCommand() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 7);
        SortByNameComparator comparator = new SortByNameComparator();
        SortCommand command = new SortCommand(comparator);
        expectedModel.sortFilteredPersonList(comparator);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(model.getAppointmentList(), expectedModel.getAppointmentList());
        assertEquals(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE), model.getFilteredPersonList());
    }

    @Test
    public void equals() {
        SortByNameComparator nameComparator = new SortByNameComparator();
        SortByAppointmentComparator appointmentComparator = new SortByAppointmentComparator();

        SortCommand sortSortCommand = new SortCommand(nameComparator);
        SortCommand appointmentSortCommand = new SortCommand(appointmentComparator);

        // same object -> returns true
        assertTrue(sortSortCommand.equals(sortSortCommand));
        assertTrue(appointmentSortCommand.equals(appointmentSortCommand));

        // different types -> returns false
        assertFalse(sortSortCommand.equals(1));

        // null -> returns false
        assertFalse(sortSortCommand.equals(null));

        // different command -> returns false
        assertFalse(sortSortCommand.equals(appointmentSortCommand));
        assertFalse(appointmentSortCommand.equals(sortSortCommand));
    }

    @Test
    public void toStringMethod() {
        SortByNameComparator comparator = new SortByNameComparator();
        SortCommand sortCommand = new SortCommand(comparator);
        String expected = SortCommand.class.getCanonicalName() + "{comparator=" + comparator + "}";
        assertEquals(expected, sortCommand.toString());
    }

    @Test
    public void execute_sortAppointmentCommand() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 7);
        SortByAppointmentComparator comparator = new SortByAppointmentComparator();
        SortCommand command = new SortCommand(comparator);
        expectedModel.sortFilteredPersonList(comparator);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(model.getAppointmentList(), expectedModel.getAppointmentList());
        assertEquals(Arrays.asList(CARL, BENSON, GEORGE, FIONA, ELLE, DANIEL, ALICE), model.getFilteredPersonList());
    }

}
