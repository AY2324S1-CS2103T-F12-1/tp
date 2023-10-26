package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.person.Person;

/** Overrides the current appointment and replaces it with the new appointment after confirmation **/
public class ConfirmOverrideCommand extends Command {
    public static final String MESSAGE_SUCCESS = "Appointment updated!";
    private static Appointment appointment = null;
    private static Person personToEdit = null;

    /**
     * Default constructor for a confirm override command
     * @param appointment new appointment
     * @param personToEdit person whos old appointment will be replaced by the new appointment
     */
    public ConfirmOverrideCommand(Appointment appointment, Person personToEdit) {
        this.appointment = appointment;
        this.personToEdit = personToEdit;
    }

    public static Appointment getAppointment() {
        return appointment;
    }

    public static Person getPersonToEdit() {
        return personToEdit;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);

        Person personWithApt = createPersonWithAppointment(personToEdit);

        assert personWithApt.getAppointment() instanceof Appointment
                    : "Schedule Command: person should have appointment";

        appointment.setPerson(personWithApt); //sets person to appointment

        model.setPerson(personToEdit, personWithApt);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_SUCCESS, Messages.format(personWithApt)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ConfirmOverrideCommand)) {
            return false;
        }

        ConfirmOverrideCommand otherOverrideCommand = (ConfirmOverrideCommand) other;
        return appointment.equals(otherOverrideCommand.appointment);
    }

    /**
     * Returns the {@code Person} with the scheduled Appointment.
     *
     * @param personToEdit The Person the appointment is scheduled to.
     * @return The Person with scheduled appointment.
     */
    private Person createPersonWithAppointment(Person personToEdit) {
        return new Person(personToEdit.getName(), personToEdit.getPhone(), personToEdit.getEmail(),
                personToEdit.getAddress(), personToEdit.getNextOfKinName(), personToEdit.getNextOfKinPhone(),
                personToEdit.getFinancialPlans(), personToEdit.getTags(), appointment);
    }
}
