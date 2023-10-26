package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.SortCommand;
import seedu.address.model.person.SortByAppointmentComparator;
import seedu.address.model.person.SortByNameComparator;

public class SortCommandParserTest {

    private SortCommandParser parser = new SortCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsSortByNameCommand() {
        // no leading and trailing whitespaces
        SortCommand expectedSortCommand =
                new SortCommand(new SortByNameComparator());
        assertParseSuccess(parser, "name", expectedSortCommand);
    }

    @Test
    public void parse_validArgs_returnsSortByAppointmentCommand() {
        // no leading and trailing whitespaces
        SortCommand expectedSortCommand =
                new SortCommand(new SortByAppointmentComparator());
        assertParseSuccess(parser, "appointment", expectedSortCommand);
    }

    @Test
    public void parse_invalidArg_throwsParseException() {
        assertParseFailure(parser, "sorter", String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
    }

}
