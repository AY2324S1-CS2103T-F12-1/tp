package seedu.address.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.ConfirmOverrideCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.person.Person;

/**
 * Controller for the override appointment command confirmation page.
 */
public class OverrideWindow extends UiPart<Stage> {

    public static final String CONFIRM_OVERRIDE_MESSAGE = "There is an appointment found under this persons name"
            + "are you sure you want to override this appointment? \n";

    private static String message;
    private static Appointment appointment = null;
    private static Person personToEdit = null;

    private static final Logger logger = LogsCenter.getLogger(OverrideWindow.class);
    private static final String FXML = "OverrideWindow.fxml";
    private CommandExecutor commandExecutor;

    @FXML
    private Label overrideMessage;
    @FXML
    private Button overrideButton;
    @FXML
    private Button cancelButton;

    /**
     * Creates a new OverrideWindow.
     *
     * @param root Stage to use as the root of the OverrideWindow.
     */
    public OverrideWindow(Stage root) {
        super(FXML, root);
        root.initStyle(StageStyle.UNDECORATED);
        root.initModality(Modality.APPLICATION_MODAL);
        overrideMessage.setText(CONFIRM_OVERRIDE_MESSAGE);
    }

    /**
     * Creates a new OverrideWindow.
     */
    public OverrideWindow(CommandExecutor commandExecutor, Appointment appointment, Person personToEdit) {
        this(new Stage());
        this.commandExecutor = commandExecutor;
        this.appointment = appointment;
        this.personToEdit = personToEdit;
        overrideMessage.setText(CONFIRM_OVERRIDE_MESSAGE + personToEdit.getAppointment().toString());
    }

    /**
     * Shows the clear window.
     * @throws IllegalStateException
     *     <ul>
     *         <li>
     *             if this method is called on a thread other than the JavaFX Application Thread.
     *         </li>
     *         <li>
     *             if this method is called during animation or layout processing.
     *         </li>
     *         <li>
     *             if this method is called on the primary stage.
     *         </li>
     *         <li>
     *             if {@code dialogStage} is already showing.
     *         </li>
     *     </ul>
     */
    public void show() {
        logger.fine("Showing confirm override window.");
        getRoot().show();
        getRoot().centerOnScreen();
    }

    /**
     * Returns true if the override window is currently being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Hides the override window.
     */
    public void hide() {
        getRoot().hide();
    }

    /**
     * Focuses on the override window.
     */
    public void focus() {
        getRoot().requestFocus();
    }
    /**
     * Executes changing of the appointment.
     */
    @FXML
    private void confirmOverride() {
        Command overrideCommand = new ConfirmOverrideCommand(this.appointment, this.personToEdit);
        try {
            commandExecutor.execute(overrideCommand);
        } catch (CommandException e) {
            // nothing to do in this window if there's an error.
        }
        hide();
    }
    /**
     * Cancels the override command.
     */
    @FXML
    private void cancel() {
        hide();
    }
    /**
     * Represents a function that can execute commands.
     */
    @FunctionalInterface
    public interface CommandExecutor {
        /**
         * Executes the command and returns the result.
         *
         * @see seedu.address.logic.Logic#execute(Command)
         */
        CommandResult execute(Command command) throws CommandException;
    }
}
