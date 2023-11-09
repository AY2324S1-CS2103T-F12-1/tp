package seedu.address.model.financialplan;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Financial Plan in the address book.
 * Guarantees: immutable; name is valid as declared in {@link #isValidFinancialPlanName(String)}
 */
public class FinancialPlan {
    public static final String MESSAGE_CONSTRAINTS = "Financial plan names should be alphanumeric or space characters, "
                                                        + "and it should not be blank";
    public static final String VALIDATION_REGEX = "^[a-zA-Z0-9\\s]+";

    public final String financialPlanName;

    /**
     * Constructs a {@code financialPlan}.
     *
     * @param financialPlanName A valid financial plan name.
     */
    public FinancialPlan(String financialPlanName) {
        requireNonNull(financialPlanName);
        checkArgument(isValidFinancialPlanName(financialPlanName), MESSAGE_CONSTRAINTS);
        this.financialPlanName = financialPlanName;
    }

    /**
     * Returns true if a given string is a valid financial plan name.
     */
    public static boolean isValidFinancialPlanName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns true if the given string is a substring of {@code financialPlanName}
     */
    public boolean containsSubstring(String substring) {
        return financialPlanName.toLowerCase().contains(substring.toLowerCase());
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FinancialPlan)) {
            return false;
        }

        FinancialPlan otherFinancialPlan = (FinancialPlan) other;
        return financialPlanName.equals(otherFinancialPlan.financialPlanName);
    }

    @Override
    public int hashCode() {
        return financialPlanName.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return '[' + financialPlanName + ']';
    }

}
