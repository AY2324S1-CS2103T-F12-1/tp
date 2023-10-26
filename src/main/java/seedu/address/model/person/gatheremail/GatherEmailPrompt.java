package seedu.address.model.person.gatheremail;

import seedu.address.model.person.Person;

/**
 * Gathers the email of {@code Person} if {@code Person}'s matches a specific prompt.
 */
public interface GatherEmailPrompt {
    /**
     * Gathers the email of {@code person} if {@code person}'s financial plan or tag names matches a specific prompt.
     */
    public String gatherEmails(Person person);
}
