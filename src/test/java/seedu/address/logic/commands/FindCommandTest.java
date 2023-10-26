package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.CARL;
import static seedu.address.testutil.TypicalPersons.ELLE;
import static seedu.address.testutil.TypicalPersons.FIONA;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.model.person.predicates.CombinedPredicate;
import seedu.address.model.person.predicates.FinancialPlanContainsKeywordsPredicate;
import seedu.address.model.person.predicates.NameContainsKeywordsPredicate;
import seedu.address.model.person.predicates.TagContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        Predicate<Person> firstPredicate =
                new NameContainsKeywordsPredicate(Collections.singletonList("first"))
                        .or(new TagContainsKeywordsPredicate(Collections.singletonList("firstTag")));
        Predicate<Person> secondPredicate =
                new NameContainsKeywordsPredicate(Collections.singletonList("second"))
                        .or(new TagContainsKeywordsPredicate(Collections.singletonList("firstTag")));
        Predicate<Person> firstPredicateAgain =
                new TagContainsKeywordsPredicate(Collections.singletonList("firstTag"))
                        .or(new NameContainsKeywordsPredicate(Collections.singletonList("first")));

        FindCommand findFirstCommand = new FindCommand(firstPredicate);
        FindCommand findSecondCommand = new FindCommand(secondPredicate);
        FindCommand findFirstCommandAgain = new FindCommand(firstPredicateAgain);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindCommand findFirstCommandCopy = new FindCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // same values, composed differently -> returns true
        assertFalse(findFirstCommand.equals(findFirstCommandAgain));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noPersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        NameContainsKeywordsPredicate namePredicate = new NameContainsKeywordsPredicate(List.of());
        FinancialPlanContainsKeywordsPredicate financialPlanPredicate =
                new FinancialPlanContainsKeywordsPredicate(List.of());
        TagContainsKeywordsPredicate tagPredicate = new TagContainsKeywordsPredicate(List.of());
        CombinedPredicate combinedPredicate = new CombinedPredicate(financialPlanPredicate,
                namePredicate, tagPredicate);
        FindCommand command = new FindCommand(combinedPredicate);
        expectedModel.updateFilteredPersonList(combinedPredicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }
    @Test
    public void execute_multipleKeywords_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
        NameContainsKeywordsPredicate predicate = prepareNamePredicate("Kurz Elle Kunz");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CARL, ELLE, FIONA), model.getFilteredPersonList());
    }

    @Test
    public void toStringMethod() {
        Predicate<Person> predicate = new NameContainsKeywordsPredicate(Arrays.asList("keyword"))
                .or(new TagContainsKeywordsPredicate(Collections.singletonList("tag")));
        FindCommand findCommand = new FindCommand(predicate);
        String expected = FindCommand.class.getCanonicalName() + "{predicate=" + predicate + "}";
        assertEquals(expected, findCommand.toString());
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private NameContainsKeywordsPredicate prepareNamePredicate(String userInput) {
        return new NameContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
