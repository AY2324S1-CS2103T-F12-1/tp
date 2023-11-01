package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Comparator;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.Person;




/**
 * Sorts all persons in the list by lexicographical order barring capitalisation.
 */
public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sorts the current list based currently available "
            + "sort functions.\n"
            + "Currently available sorting variations: \n"
            + "-sort (lexicographical name sort)\n -appointment (earliest timing first)\n"
            + "Parameters: sort [variation]...\n"
            + "Example: sort sort";

    private final Comparator<Person> comparator;

    /**
     * Default constructor for a SortCommand.
     * @param comparator comparator to be used to sort the list.
     */
    public SortCommand(Comparator<Person> comparator) {
        this.comparator = comparator;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.sortFilteredPersonList(comparator);
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof SortCommand)) {
            return false;
        }

        SortCommand otherSortCommand = (SortCommand) other;
        return comparator.equals(otherSortCommand.comparator);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("comparator", comparator)
                .toString();
    }
}
