package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.person.Person;

public class ConfirmOverrideCommandTest {

    private static final String APPOINTMENT_DESCRIPTION_STUB = "Review Insurance, 01-01-2023 20:00";

    @Test
    public void execute_overridingAppointment_success() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

        Person updatedPerson = expectedModel.getFilteredPersonList().get(1);
        Person personToEdit = model.getFilteredPersonList().get(1);
        Appointment newAppointment = Appointment.parseAppointmentDescription(APPOINTMENT_DESCRIPTION_STUB);

        Person newPerson = new Person(updatedPerson.getName(), updatedPerson.getPhone(), updatedPerson.getEmail(),
                updatedPerson.getAddress(), updatedPerson.getNextOfKinName(), updatedPerson.getNextOfKinPhone(),
                updatedPerson.getFinancialPlans(), updatedPerson.getTags(), newAppointment);

        expectedModel.setPerson(updatedPerson, newPerson);

        assertCommandSuccess(new ConfirmOverrideCommand(newAppointment, personToEdit), model,
                ConfirmOverrideCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
