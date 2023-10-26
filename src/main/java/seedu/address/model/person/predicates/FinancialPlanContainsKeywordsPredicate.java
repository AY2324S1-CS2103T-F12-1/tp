package seedu.address.model.person.predicates;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.financialplan.FinancialPlan;
import seedu.address.model.person.Person;

/**
 * Tests that at least one of a {@code Person}'s {@code FinancialPlan} matches any of the keywords given.
 */
public class FinancialPlanContainsKeywordsPredicate implements Predicate<Person> {
    private final List<String> keywords;

    public FinancialPlanContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        Stream<FinancialPlan> financialPlanStream = person.getFinancialPlans().stream();
        // We check for each financial plan if it contains a keyword as a substring
        return financialPlanStream.anyMatch(financialPlan -> keywords.stream()
                .anyMatch(keyword -> financialPlan.containsSubstring(keyword)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FinancialPlanContainsKeywordsPredicate)) {
            return false;
        }

        FinancialPlanContainsKeywordsPredicate otherNameContainsKeywordsPredicate =
                (FinancialPlanContainsKeywordsPredicate) other;
        return keywords.equals(otherNameContainsKeywordsPredicate.keywords);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("keywords", keywords).toString();
    }
}
