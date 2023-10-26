package seedu.address.model.person.predicates;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class FinancialPlanContainsKeywordsPredicateTest {
    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        FinancialPlanContainsKeywordsPredicate firstPredicate =
                new FinancialPlanContainsKeywordsPredicate(firstPredicateKeywordList);
        FinancialPlanContainsKeywordsPredicate secondPredicate =
                new FinancialPlanContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        FinancialPlanContainsKeywordsPredicate firstPredicateCopy =
                new FinancialPlanContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different financial plan -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_financialPlansContainKeywords_returnsTrue() {
        // One keyword
        FinancialPlanContainsKeywordsPredicate predicate =
                new FinancialPlanContainsKeywordsPredicate(Collections.singletonList("Senior"));
        assertTrue(predicate.test(new PersonBuilder().withFinancialPlans("Senior").build()));

        // Multiple keywords in same financial plan
        predicate = new FinancialPlanContainsKeywordsPredicate(Arrays.asList("Senior", "Premium"));
        assertTrue(predicate.test(new PersonBuilder().withFinancialPlans("Senior Premium").build()));

        // Multiple keywords in different financial plans
        predicate = new FinancialPlanContainsKeywordsPredicate(Arrays.asList("Senior", "Premium"));
        assertTrue(predicate.test(new PersonBuilder().withFinancialPlans("Senior", "Premium").build()));

        // Only one matching keyword
        predicate = new FinancialPlanContainsKeywordsPredicate(Arrays.asList("Child", "Premium"));
        assertTrue(predicate.test(new PersonBuilder().withFinancialPlans("Child", "Koopa").build()));

        // Mixed-case keywords
        predicate = new FinancialPlanContainsKeywordsPredicate(Arrays.asList("cHIld", "pREmium"));
        assertTrue(predicate.test(new PersonBuilder().withFinancialPlans("Child Premium").build()));
    }

    @Test
    public void test_financialPlansDoNotContainKeywords_returnsFalse() {
        // Zero keywords
        FinancialPlanContainsKeywordsPredicate predicate =
                new FinancialPlanContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new PersonBuilder().withFinancialPlans("Child").build()));

        // Non-matching keyword
        predicate = new FinancialPlanContainsKeywordsPredicate(Arrays.asList("Senior"));
        assertFalse(predicate.test(new PersonBuilder().withFinancialPlans("Child Premium").build()));

        // Keywords match phone, email and address, but does not match financial plans
        predicate = new FinancialPlanContainsKeywordsPredicate(
                Arrays.asList("12345", "alice@email.com", "Main", "Street"));
        assertFalse(predicate.test(new PersonBuilder().withFinancialPlans("Child").withPhone("12345")
                .withEmail("alice@email.com").withAddress("Main Street").build()));
    }

    @Test
    public void toStringMethod() {
        List<String> keywords = List.of("keyword1", "keyword2");
        FinancialPlanContainsKeywordsPredicate predicate = new FinancialPlanContainsKeywordsPredicate(keywords);

        String expected = FinancialPlanContainsKeywordsPredicate.class.getCanonicalName()
                + "{keywords=" + keywords + "}";
        assertEquals(expected, predicate.toString());
    }
}
