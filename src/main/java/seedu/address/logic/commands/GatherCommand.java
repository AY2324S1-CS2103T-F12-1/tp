package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FINANCIAL_PLAN;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.Model;
import seedu.address.model.person.gatheremail.GatherEmailPrompt;

/**
 * Gathers and lists all persons in address book emails whose details contain a given Financial Plan.
 * Keyword matching is case-insensitive.
 */
public class GatherCommand extends Command {

    public static final String COMMAND_WORD = "gather";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Gathers all emails of person whose details matches "
            + "the desire prompt\n"
            + "Gather by ether Financial Plans or Tags. Not both.\n"
            + "Parameters: "
            + "[" + PREFIX_FINANCIAL_PLAN + "FINANCIAL_PLAN] or "
            + "[" + PREFIX_TAG + "TAG]\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_FINANCIAL_PLAN + "Financial Plan A";

    public static final String MESSAGE_NO_PERSON_FOUND = "0 persons were found with ";

    private final GatherEmailPrompt prompt;

    /**
     * Constructs a new GatherCommand object.
     * @param prompt The user's prompt
     */
    public GatherCommand(GatherEmailPrompt prompt) {
        this.prompt = prompt;
    }

    /**
     * Overrides Command execute method.
     * @param model {@code Model} which the command should operate on.
     * @return The CommandResult depending on the user input
     */
    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        String emails = model.gatherEmails(prompt);
        if (emails.isEmpty()) {
            return new CommandResult(MESSAGE_NO_PERSON_FOUND + prompt.toString());
        }
        return new CommandResult(emails);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof GatherCommand)) {
            return false;
        }

        GatherCommand otherGatherCommand = (GatherCommand) other;
        return prompt.equals(otherGatherCommand.prompt);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("prompt", prompt)
                .toString();
    }
}
