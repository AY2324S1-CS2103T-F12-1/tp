package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APPOINTMENT_DATE;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.appointment.NullAppointment;
import seedu.address.model.person.Person;

/**
 * Completes and removes appointments from Person.
 */
public class CompleteCommand extends Command {

    public static final String COMMAND_WORD = "complete";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Completes an appointment identified by "
            + "by the index number used in the displayed person list or "
            + "by a specified date\n"
            + "Parameters: [INDEX(must be a positive integer)] "
            + "[" + PREFIX_APPOINTMENT_DATE + "Appointment Date] *At least one parameter specified\n"
            + "Example: " + COMMAND_WORD + " 1 ";
    public static final String MESSAGE_COMPLETE_SUCCESS = "Appointments Completed!";
    public static final String MESSAGE_INVALID_DATE_FORMAT = "Input Date should be in format of dd-MM-yyyy";
    public static final String MESSAGE_INVALID_DATE = "Please input a valid Date";
    private final CompleteDescriptor completeDescriptor;
    public CompleteCommand(CompleteDescriptor completeDescriptor) {
        this.completeDescriptor = completeDescriptor;
    }
    @Override
    public CommandResult execute(Model model) throws CommandException {

        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        Consumer<Index> editByIndex = index -> {
            Person personToEdit = lastShownList.get(index.getZeroBased());
            Person edittedPerson = createPersonWithoutAppointment(personToEdit);

            model.setPerson(personToEdit, edittedPerson);
        };

        Consumer<LocalDate> editByDate = date -> {
            model.clearAppointments(date);
        };

        Optional<Index> index = completeDescriptor.getIndex();
        if (index.isPresent()) {
            if (index.get().getZeroBased() >= lastShownList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
            }
        }

        // if Index present, edit Model by Person Index
        completeDescriptor.getIndex().ifPresent(editByIndex);

        // if Date present, edit Model by Date;
        completeDescriptor.getDate().ifPresent(editByDate);

        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(MESSAGE_COMPLETE_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CompleteCommand)) {
            return false;
        }

        CompleteCommand otherEditCommand = (CompleteCommand) other;
        return completeDescriptor.equals(otherEditCommand.completeDescriptor);
    }

    private static Person createPersonWithoutAppointment(Person personToEdit) {
        return new Person(personToEdit.getName(), personToEdit.getPhone(), personToEdit.getEmail(),
                personToEdit.getAddress(), personToEdit.getNextOfKinName(), personToEdit.getNextOfKinPhone(),
                personToEdit.getFinancialPlans(), personToEdit.getTags(), NullAppointment.getNullAppointment());
    }

    /**
     * Stores details to complete the appointment. Appointments will be completed based on
     * the input field by user.
     */
    public static class CompleteDescriptor {
        private Index index;
        private LocalDate date;

        public CompleteDescriptor() {}

        public void setIndex(Index index) {
            this.index = index;
        }
        public void setDate(LocalDate date) {
            this.date = date;
        }
        public Optional<Index> getIndex() {
            return Optional.ofNullable(index);
        }
        public Optional<LocalDate> getDate() {
            return Optional.ofNullable(date);
        }
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(index, date);
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof CompleteDescriptor)) {
                return false;
            }

            CompleteDescriptor otherCompleteDescriptor = (CompleteDescriptor) other;
            return Objects.equals(index, otherCompleteDescriptor.index)
                    && Objects.equals(date, otherCompleteDescriptor.date);
        }
    }
}
