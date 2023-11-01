package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CompleteCommand.MESSAGE_INVALID_DATE;
import static seedu.address.logic.commands.CompleteCommand.MESSAGE_INVALID_DATE_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APPOINTMENT_DATE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.CompleteCommand;
import seedu.address.logic.commands.CompleteCommand.CompleteDescriptor;
class CompleteCommandParserTest {
    public static final String DATE_DESC = " " + PREFIX_APPOINTMENT_DATE;
    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, CompleteCommand.MESSAGE_USAGE);
    private CompleteCommandParser parser = new CompleteCommandParser();
    @Test
    public void parse_missingParts_failure() {
        //no index or date specified
        assertParseFailure(parser, " ", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + NAME_DESC_AMY, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + NAME_DESC_AMY, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        //Invalid date format
        assertParseFailure(parser, DATE_DESC + "01 May 2023", MESSAGE_INVALID_DATE_FORMAT);
        assertParseFailure(parser, DATE_DESC + "01-05", MESSAGE_INVALID_DATE_FORMAT);
        assertParseFailure(parser, DATE_DESC + "05-2023", MESSAGE_INVALID_DATE_FORMAT);
        assertParseFailure(parser, DATE_DESC + "01-05-2023 20:00", MESSAGE_INVALID_DATE_FORMAT);

        //Invalid date
        assertParseFailure(parser, DATE_DESC + "01-13-2023", MESSAGE_INVALID_DATE);
    }

    @Test
    public void parse_duplicateFields_failure() {
        String userInput = DATE_DESC + "01-05-2023" + DATE_DESC + "02-10-2023";
        assertParseFailure(parser, userInput, Messages.getErrorMessageForDuplicatePrefixes(PREFIX_APPOINTMENT_DATE));
    }

    @Test
    public void parse_indexSpecified_success() {
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput = targetIndex.getOneBased() + "";

        CompleteDescriptor completeDescriptor = new CompleteDescriptor();
        completeDescriptor.setIndex(targetIndex);
        CompleteCommand expectedCommand = new CompleteCommand(completeDescriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_dateSpecified_success() {
        String userInput = DATE_DESC + "01-05-2023";

        CompleteDescriptor completeDescriptor = new CompleteDescriptor();
        completeDescriptor.setDate(LocalDate.of(2023, 5, 1));
        CompleteCommand expectedCommand = new CompleteCommand(completeDescriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
