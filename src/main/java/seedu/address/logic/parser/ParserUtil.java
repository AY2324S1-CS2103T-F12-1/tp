package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.commands.CompleteCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.financialplan.FinancialPlan;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.NextOfKinName;
import seedu.address.model.person.NextOfKinPhone;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods used for parsing and validating strings in the various *Parser classes.
 */
public class ParserUtil {
    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
        return Index.fromOneBased(Integer.parseInt(trimmedIndex));
    }

    /**
     * Parses a {@code String name} into a {@code Name}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static Name parseName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!Name.isValidName(trimmedName)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }
        return new Name(trimmedName);
    }

    /**
     * Parses a {@code String phone} into a {@code Phone}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code phone} is invalid.
     */
    public static Phone parsePhone(String phone) throws ParseException {
        requireNonNull(phone);
        String trimmedPhone = phone.trim();
        if (!Phone.isValidPhone(trimmedPhone)) {
            throw new ParseException(Phone.MESSAGE_CONSTRAINTS);
        }
        return new Phone(trimmedPhone);
    }

    /**
     * Parses a {@code String address} into an {@code Address}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code address} is invalid.
     */
    public static Address parseAddress(String address) throws ParseException {
        requireNonNull(address);
        String trimmedAddress = address.trim();
        if (!Address.isValidAddress(trimmedAddress)) {
            throw new ParseException(Address.MESSAGE_CONSTRAINTS);
        }
        return new Address(trimmedAddress);
    }

    /**
     * Parses a {@code String email} into an {@code Email}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code email} is invalid.
     */
    public static Email parseEmail(String email) throws ParseException {
        requireNonNull(email);
        String trimmedEmail = email.trim();
        if (!Email.isValidEmail(trimmedEmail)) {
            throw new ParseException(Email.MESSAGE_CONSTRAINTS);
        }
        return new Email(trimmedEmail);
    }
    /**
     * Parses a {@code String nokName} into a {@code NextOfKinName}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code nokName} is invalid.
     */
    public static NextOfKinName parseNextOfKinName(String nokName) throws ParseException {
        requireNonNull(nokName);
        String trimmedNokName = nokName.trim();
        if (!NextOfKinName.isValidName(trimmedNokName)) {
            throw new ParseException(NextOfKinName.MESSAGE_CONSTRAINTS);
        }
        return new NextOfKinName(trimmedNokName);
    }
    /**
     * Parses a {@code String nokPhone} into an {@code NextOfKinPhone}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code nokPhone} is invalid.
     */
    public static NextOfKinPhone parseNextOfKinPhone(String nokPhone) throws ParseException {
        requireNonNull(nokPhone);
        String trimmedNokPhone = nokPhone.trim();
        if (!NextOfKinPhone.isValidPhone(trimmedNokPhone)) {
            throw new ParseException(NextOfKinPhone.MESSAGE_CONSTRAINTS);
        }
        return new NextOfKinPhone(trimmedNokPhone);
    }

    /**
     * Parses a {@code String financialPlan} into a {@code FinancialPlan}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code tag} is invalid.
     */
    public static FinancialPlan parseFinancialPlan(String financialPlan) throws ParseException {
        requireNonNull(financialPlan);
        String trimmedFinancialPlan = financialPlan.trim();
        if (!FinancialPlan.isValidFinancialPlanName(trimmedFinancialPlan)) {
            throw new ParseException(FinancialPlan.MESSAGE_CONSTRAINTS);
        }
        return new FinancialPlan(trimmedFinancialPlan);
    }

    /**
     * Parses {@code Collection<String> financialPlans} into a {@code Set<FinancialPlan>}.
     */
    public static Set<FinancialPlan> parseFinancialPlans(Collection<String> financialPlans) throws ParseException {
        requireNonNull(financialPlans);
        final Set<FinancialPlan> financialPlanSet = new HashSet<>();
        for (String financialPlanName : financialPlans) {
            financialPlanSet.add(parseFinancialPlan(financialPlanName));
        }
        return financialPlanSet;
    }

    /**
     * Parses a {@code String aptName} and {@code String aptDateString} into an {@code Appointment}.
     *
     * @param appointmentName The name of the appointment.
     * @param appointmentDateString The appointment date and time.
     * @return The Appointment.
     * @throws ParseException If the given {@code aptName} or {@code aptDateString}.
     */
    public static Appointment parseAppointment(String appointmentName, String appointmentDateString)
            throws ParseException {
        requireNonNull(appointmentName);
        requireNonNull(appointmentDateString);

        String trimmedAppointmentName = appointmentName.trim();

        if (!Appointment.isValidDesc(appointmentName)) {
            throw new ParseException(Appointment.MESSAGE_DESC_CONSTRAINTS);
        }

        if (!Appointment.isValidDateFormat(appointmentDateString)) {
            throw new ParseException(Appointment.MESSAGE_DATE_CONSTRAINTS);
        }

        LocalDateTime appointmentDate;
        try {
            appointmentDate = Appointment.parseAppointmentDate(appointmentDateString);
        } catch (DateTimeParseException e) {
            throw new ParseException(Appointment.MESSAGE_INVALID_DATE);
        }

        return new Appointment(trimmedAppointmentName, appointmentDate);
    }

    /**
     * Parses a {@code String date} into a {@code LocalDate}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code date} is invalid.
     */
    public static LocalDate parseDate(String date) throws ParseException {
        requireNonNull(date);
        String trimmedDate = date.trim();

        String dateValidation = "\\d{2}-\\d{2}-\\d{4}";
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");


        if (!trimmedDate.matches(dateValidation)) {
            throw new ParseException(CompleteCommand.MESSAGE_INVALID_DATE_FORMAT);
        }

        LocalDate appointmentDate;
        try {
            appointmentDate = LocalDate.parse(date, dateFormatter);
        } catch (DateTimeParseException e) {
            throw new ParseException(CompleteCommand.MESSAGE_INVALID_DATE);
        }

        return appointmentDate;
    }

    /**
     * Parses a {@code String tag} into a {@code Tag}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code tag} is invalid.
     */
    public static Tag parseTag(String tag) throws ParseException {
        requireNonNull(tag);
        String trimmedTag = tag.trim();
        if (!Tag.isValidTagName(trimmedTag)) {
            throw new ParseException(Tag.MESSAGE_CONSTRAINTS);
        }
        return new Tag(trimmedTag);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>}.
     */
    public static Set<Tag> parseTags(Collection<String> tags) throws ParseException {
        requireNonNull(tags);
        final Set<Tag> tagSet = new HashSet<>();
        for (String tagName : tags) {
            tagSet.add(parseTag(tagName));
        }
        return tagSet;
    }
    /**
     * Validates if a {@code String name} is a valid {@code Name}.
     *
     * @param input String to validate.
     * @throws ParseException if the given string is invalid.
     */
    public static void validateName(String input) throws ParseException {
        if (input.isEmpty() || !Name.isValidName(input)) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, Name.MESSAGE_CONSTRAINTS));
        }
    }
    /**
     * Validates if a list of {@code String names} are valid for {@code Name} objects.
     *
     * @param inputs List of names to validate.
     * @throws ParseException if any of the given names are invalid.
     */
    public static void validateNames(List<String> inputs) throws ParseException {
        for (String name : inputs) {
            validateName(name);
        }
    }
    /**
     * Validates if a {@code String financial plan} is a valid name for a {@code FinancialPlan}.
     *
     * @param input String to validate.
     * @throws ParseException if the given string is invalid.
     */
    public static void validateFinancialPlan(String input) throws ParseException {
        if (input.isEmpty() || !FinancialPlan.isValidFinancialPlanName(input)) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FinancialPlan.MESSAGE_CONSTRAINTS));
        }
    }
    /**
     * Validates if a list of {@code String financial plans} are valid as {@code FinancialPlan} names.
     *
     * @param inputs List of financial plan names to validate.
     * @throws ParseException if any of the given names are invalid.
     */
    public static void validateFinancialPlans(List<String> inputs) throws ParseException {
        for (String financialPlan : inputs) {
            validateFinancialPlan(financialPlan);
        }
    }

    /**
     * Validates if a {@code String tag} is a valid name for a {@code Tag}.
     *
     * @param input String to validate.
     * @throws ParseException if the given string is invalid.
     */
    public static void validateTag(String input) throws ParseException {
        if (input.isEmpty() || !Tag.isValidTagName(input)) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, Tag.MESSAGE_CONSTRAINTS));
        }
    }
    /**
     * Validates if a list of {@code String tags} are valid as {@code Tag} names.
     *
     * @param inputs List of tag names to validate.
     * @throws ParseException if any of the given names are invalid.
     */
    public static void validateTags(List<String> inputs) throws ParseException {
        for (String tag : inputs) {
            validateTag(tag);
        }
    }
}
