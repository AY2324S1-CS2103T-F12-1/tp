package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FINANCIAL_PLAN;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.ParserUtil.validateFinancialPlan;
import static seedu.address.logic.parser.ParserUtil.validateTag;

import seedu.address.logic.commands.GatherCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.gatheremail.GatherEmailByFinancialPlan;
import seedu.address.model.person.gatheremail.GatherEmailByTag;

/**
 * Parses input arguments and creates a new GatherCommand object
 */
public class GatherCommandParser implements Parser<GatherCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the GatherCommand
     * and returns a GatherCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public GatherCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_FINANCIAL_PLAN, PREFIX_TAG);
        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_FINANCIAL_PLAN, PREFIX_TAG);

        if (argMultimap.getValue(PREFIX_FINANCIAL_PLAN).isPresent() && argMultimap.getValue(PREFIX_TAG).isPresent()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, GatherCommand.MESSAGE_USAGE));
        }

        if (argMultimap.getValue(PREFIX_FINANCIAL_PLAN).isPresent()) {
            // Parse Financial Plan
            String financialPlanArgs = trimmedArgs.replace(PREFIX_FINANCIAL_PLAN.getPrefix(), "").trim();
            validateFinancialPlan(financialPlanArgs);
            return new GatherCommand(new GatherEmailByFinancialPlan(financialPlanArgs));
        } else if (argMultimap.getValue(PREFIX_TAG).isPresent()) {
            // Parse Tag
            String tagArgs = trimmedArgs.replace(PREFIX_TAG.getPrefix(), "").trim();
            validateTag(tagArgs);
            return new GatherCommand(new GatherEmailByTag(tagArgs));
        }

        throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, GatherCommand.MESSAGE_USAGE));
    }
}
