package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.model.person.Remark;

class RemarkCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    @Test
    void execute_addRemarkUnfilteredList_success() {
        Person personToRemark = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Remark remark = new Remark("Hello World");
        Person personAfterRemark = new Person(personToRemark.getName(), personToRemark.getPhone(),
                personToRemark.getEmail(), personToRemark.getAddress(), remark, personToRemark.getTags());

        RemarkCommand remarkCommand = new RemarkCommand(INDEX_FIRST_PERSON, remark);

        String expectedMessage = String.format(RemarkCommand.MESSAGE_ADD_REMARK_SUCCESS,
                Messages.format(personAfterRemark));

        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel.setPerson(personAfterRemark, personAfterRemark);

        assertCommandSuccess(remarkCommand, model, expectedMessage, expectedModel);
    }
}