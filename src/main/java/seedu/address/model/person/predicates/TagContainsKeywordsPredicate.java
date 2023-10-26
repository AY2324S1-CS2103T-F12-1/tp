package seedu.address.model.person.predicates;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;

/**
 * Tests that at least one of a {@code Person}'s {@code Tag} matches any of the keywords given.
 */
public class TagContainsKeywordsPredicate implements Predicate<Person> {
    private final List<String> keywords;

    public TagContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        Stream<Tag> tagStream = person.getTags().stream();
        // We check for each tag if it contains a keyword as a substring
        return tagStream.anyMatch(tag -> keywords.stream()
                .anyMatch(keyword -> tag.containsSubstring(keyword)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof TagContainsKeywordsPredicate)) {
            return false;
        }

        TagContainsKeywordsPredicate otherNameContainsKeywordsPredicate = (TagContainsKeywordsPredicate) other;
        return keywords.equals(otherNameContainsKeywordsPredicate.keywords);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("keywords", keywords).toString();
    }
}
