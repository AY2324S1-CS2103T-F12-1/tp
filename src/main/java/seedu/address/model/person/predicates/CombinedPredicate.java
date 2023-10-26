package seedu.address.model.person.predicates;

import java.util.Objects;
import java.util.function.Predicate;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.person.Person;

/**
 * Tests that at least one of a {@code Person}'s {@code FinancialPlan}, {@code Name} or {@code Tag} matches any
 * of the keywords given. Note that there is no requirement that all 3 predicates must be present in the constructor.
 */
public class CombinedPredicate implements Predicate<Person> {
    public final FinancialPlanContainsKeywordsPredicate financialPlanContainsKeywordsPredicate;
    public final NameContainsKeywordsPredicate nameContainsKeywordsPredicate;
    public final TagContainsKeywordsPredicate tagContainsKeywordsPredicate;

    /**
     * Creates a combined predicate containing up to one of a financial plan predicate, name predicate and tag
     * predicate each.
     *
     * @param financialPlanContainsKeywordsPredicate Financial plan predicate.
     * @param nameContainsKeywordsPredicate Name predicate.
     * @param tagContainsKeywordsPredicate Tag predicate.
     */
    public CombinedPredicate(FinancialPlanContainsKeywordsPredicate financialPlanContainsKeywordsPredicate,
                             NameContainsKeywordsPredicate nameContainsKeywordsPredicate,
                             TagContainsKeywordsPredicate tagContainsKeywordsPredicate) {
        this.financialPlanContainsKeywordsPredicate = financialPlanContainsKeywordsPredicate;
        this.nameContainsKeywordsPredicate = nameContainsKeywordsPredicate;
        this.tagContainsKeywordsPredicate = tagContainsKeywordsPredicate;
    }

    @Override
    public boolean test(Person person) {
        boolean result = false;
        if (financialPlanContainsKeywordsPredicate != null) {
            result = result || financialPlanContainsKeywordsPredicate.test(person);
        }
        if (nameContainsKeywordsPredicate != null) {
            result = result || nameContainsKeywordsPredicate.test(person);
        }
        if (tagContainsKeywordsPredicate != null) {
            result = result || tagContainsKeywordsPredicate.test(person);
        }
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CombinedPredicate)) {
            return false;
        }

        CombinedPredicate otherNameContainsKeywordsPredicate =
                (CombinedPredicate) other;
        return Objects.equals(financialPlanContainsKeywordsPredicate,
                otherNameContainsKeywordsPredicate.financialPlanContainsKeywordsPredicate)
                && Objects.equals(nameContainsKeywordsPredicate,
                otherNameContainsKeywordsPredicate.nameContainsKeywordsPredicate)
                && Objects.equals(tagContainsKeywordsPredicate,
                otherNameContainsKeywordsPredicate.tagContainsKeywordsPredicate);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("financialPlanContainsKeywordsPredicate", financialPlanContainsKeywordsPredicate)
                .add("nameContainsKeywordsPredicate", nameContainsKeywordsPredicate)
                .add("tagContainsKeywordsPredicate", tagContainsKeywordsPredicate)
                .toString();
    }
}
