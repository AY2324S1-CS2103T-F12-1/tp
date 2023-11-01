package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APPOINTMENT_DATE;

import java.time.LocalDate;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CompleteCommand;
import seedu.address.logic.commands.CompleteCommand.CompleteDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parse input arguments and creates a new CompleteCommand object
 */
public class CompleteCommandParser implements Parser<CompleteCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the CompleteCommand
     * and returns an CompleteCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public CompleteCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_APPOINTMENT_DATE);

        CompleteDescriptor completeDescriptor = new CompleteDescriptor();

        Index index;
        if (!argMultimap.getPreamble().trim().isEmpty()) {
            try {
                index = ParserUtil.parseIndex(argMultimap.getPreamble());
                completeDescriptor.setIndex(index);
            } catch (ParseException pe) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        CompleteCommand.MESSAGE_USAGE), pe);
            }
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_APPOINTMENT_DATE);

        if (argMultimap.getValue(PREFIX_APPOINTMENT_DATE).isPresent()) {
            LocalDate date = ParserUtil.parseDate(argMultimap.getValue(PREFIX_APPOINTMENT_DATE).get());
            completeDescriptor.setDate(date);
        }

        if (!completeDescriptor.isAnyFieldEdited()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    CompleteCommand.MESSAGE_USAGE));
        }

        return new CompleteCommand(completeDescriptor);
    }
}
