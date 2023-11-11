package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FINANCIAL_PLAN;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NEXT_OF_KIN_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NEXT_OF_KIN_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.financialplan.FinancialPlan;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new EditCommand object
 */
public class EditCommandParser implements Parser<EditCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS,
                        PREFIX_NEXT_OF_KIN_NAME, PREFIX_NEXT_OF_KIN_PHONE, PREFIX_FINANCIAL_PLAN, PREFIX_TAG);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE), pe);
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS,
                PREFIX_NEXT_OF_KIN_NAME, PREFIX_NEXT_OF_KIN_PHONE);

        EditPersonDescriptor editPersonDescriptor = makeEditPersonDescriptor(argMultimap);

        return new EditCommand(index, editPersonDescriptor);
    }

    /**
     * Parses {@code Collection<String> financialPlans} into a {@code Set<FinancialPlan>}
     * if {@code financialPlans} is non-empty.
     * If {@code financialPlans} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<FinancialPlan>} containing zero financial plans.
     */
    private Optional<Set<FinancialPlan>> parseFinancialPlansForEdit(
            Collection<String> financialPlans) throws ParseException {
        assert financialPlans != null;

        if (financialPlans.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> financialPlanSet = financialPlans.size() == 1 && financialPlans.contains("")
                ? Collections.emptySet() : financialPlans;
        return Optional.of(ParserUtil.parseFinancialPlans(financialPlanSet));
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>} if {@code tags} is non-empty.
     * If {@code tags} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<Tag>} containing zero tags.
     */
    private Optional<Set<Tag>> parseTagsForEdit(Collection<String> tags) throws ParseException {
        assert tags != null;

        if (tags.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> tagSet = tags.size() == 1 && tags.contains("") ? Collections.emptySet() : tags;
        return Optional.of(ParserUtil.parseTags(tagSet));
    }

    /**
     * Creates a EditPersonDescriptor with a given ArgumentMultimap that contains the inputs.
     *
     * @param argMultimap Multimap to get values from.
     * @return Descriptor to pass to the EditCommand.
     * @throws ParseException If no fields are edited or there are invalid multimap values.
     */
    private EditPersonDescriptor makeEditPersonDescriptor(ArgumentMultimap argMultimap) throws ParseException {
        EditPersonDescriptor editPersonDescriptor = new EditPersonDescriptor();
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            editPersonDescriptor.setName(ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
            editPersonDescriptor.setPhone(ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get()));
        }
        if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            editPersonDescriptor.setEmail(ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get()));
        }
        if (argMultimap.getValue(PREFIX_ADDRESS).isPresent()) {
            editPersonDescriptor.setAddress(ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get()));
        }
        if (argMultimap.getValue(PREFIX_NEXT_OF_KIN_NAME).isPresent()) {
            editPersonDescriptor.setNextOfKinName(ParserUtil.parseNextOfKinName(argMultimap
                    .getValue(PREFIX_NEXT_OF_KIN_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_NEXT_OF_KIN_PHONE).isPresent()) {
            editPersonDescriptor.setNextOfKinPhone(ParserUtil.parseNextOfKinPhone(argMultimap
                    .getValue(PREFIX_NEXT_OF_KIN_PHONE).get()));
        }
        parseFinancialPlansForEdit(argMultimap.getAllValues(PREFIX_FINANCIAL_PLAN))
                .ifPresent(editPersonDescriptor::setFinancialPlans);
        parseTagsForEdit(argMultimap.getAllValues(PREFIX_TAG)).ifPresent(editPersonDescriptor::setTags);
        if (!editPersonDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
        }
        return editPersonDescriptor;
    }
}
