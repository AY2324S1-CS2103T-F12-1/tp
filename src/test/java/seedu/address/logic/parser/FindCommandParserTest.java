package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindCommand;
import seedu.address.model.person.predicates.CombinedPredicate;
import seedu.address.model.person.predicates.FinancialPlanContainsKeywordsPredicate;
import seedu.address.model.person.predicates.NameContainsKeywordsPredicate;
import seedu.address.model.person.predicates.TagContainsKeywordsPredicate;

public class FindCommandParserTest {

    private FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidArg_throwsParseException() {
        assertParseFailure(parser, " doo doo ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedFindCommand =
                new FindCommand(new CombinedPredicate(
                        new FinancialPlanContainsKeywordsPredicate(List.of()),
                        new NameContainsKeywordsPredicate(Arrays.asList("Alice", "Bob")),
                        new TagContainsKeywordsPredicate(List.of())));
        String rawFindCommand = " " + PREFIX_NAME + "Alice " + PREFIX_NAME + "Bob";
        assertParseSuccess(parser, rawFindCommand, expectedFindCommand);

        // multiple whitespaces between keywords
        rawFindCommand = " " + PREFIX_NAME + " \n Alice \n " + PREFIX_NAME + " \t Bob \t";
        assertParseSuccess(parser, rawFindCommand, expectedFindCommand);
    }
}
