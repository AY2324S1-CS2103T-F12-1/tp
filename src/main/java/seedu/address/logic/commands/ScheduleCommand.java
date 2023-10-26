package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APPOINTMENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APPOINTMENT_DATE;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.NullAppointment;
import seedu.address.model.person.Person;

/**
 * Schedules an appointment for a person.
 */
public class ScheduleCommand extends Command {

    public static final String COMMAND_WORD = "schedule";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Schedules an appointment of the person identified "
            + "by the index number used in the displayed person list. "
            + "Parameters: INDEX(must be a positive integer) "
            + "[" + PREFIX_APPOINTMENT + "Appointment Name] "
            + "[" + PREFIX_APPOINTMENT_DATE + "Appointment Date] "
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_APPOINTMENT + "Review Insurance "
            + PREFIX_APPOINTMENT_DATE + "01-01-2023 20:00";
    public static final String MESSAGE_SCHEDULE_SUCCESS = "New appointment added: %1$s";
    public static final String CONFIRM_OVERRIDE_MESSAGE = "Previous appointment found.";
    private final Index index;
    private final Appointment toAdd;

    /**
     * Creates a ScheduleCommand to schedule the specified {@code Appointment} to the indexed person.
     *
     * @param index The index of the person.
     * @param appointment The Appointment to schedule.
     */
    public ScheduleCommand(Index index, Appointment appointment) {
        requireNonNull(index);
        requireNonNull(appointment);

        this.index = index;
        this.toAdd = appointment;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(index.getZeroBased());

        if (personToEdit.getAppointment() instanceof NullAppointment) {
            Person personWithApt = createPersonWithAppointment(personToEdit);

            assert personWithApt.getAppointment() instanceof Appointment
                    : "Schedule Command: person should have appointment";

            toAdd.setPerson(personWithApt); //sets person to appointment

            model.setPerson(personToEdit, personWithApt);
            model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
            return new CommandResult(String.format(MESSAGE_SCHEDULE_SUCCESS, Messages.format(personWithApt)));
        } else {
            return new CommandResult(CONFIRM_OVERRIDE_MESSAGE,
                    false, false, true, personToEdit, toAdd);
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ScheduleCommand)) {
            return false;
        }

        ScheduleCommand otherScheduleCommand = (ScheduleCommand) other;
        return index.equals(otherScheduleCommand.index)
                && toAdd.equals(toAdd);
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
                personToEdit.getFinancialPlans(), personToEdit.getTags(), toAdd);
    }
}
