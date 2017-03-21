package seedu.address.logic.parser;

import java.util.regex.Pattern;

import seedu.address.logic.parser.ArgumentTokenizer.Prefix;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions */
    public static final Prefix PREFIX_DEADLINE = new Prefix("d/");
    public static final Prefix PREFIX_REMARKS = new Prefix("r/");
    public static final Prefix PREFIX_NOT_IN_USE = new Prefix("n/");
    public static final Prefix PREFIX_LABELS = new Prefix("l/");
    public static final Prefix PREFIX_ISCOMPLETED = new Prefix("c/");
    public static final Prefix PREFIX_PERIOD = new Prefix("p/");

    /* Patterns definitions */
    public static final Pattern KEYWORDS_ARGS_FORMAT =
            Pattern.compile("(?<keywords>\\S+(?:\\s+\\S+)*)"); // one or more keywords separated by whitespace

}
