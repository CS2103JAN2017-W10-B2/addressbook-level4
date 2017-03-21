package seedu.address.logic.parser;

import java.util.regex.Pattern;

import seedu.address.logic.parser.ArgumentTokenizer.Prefix;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions */
    public static final Prefix PREFIX_DEADLINE = new Prefix(" to ");
    public static final Prefix PREFIX_REMARKS = new Prefix(" remark: ");
    public static final Prefix PREFIX_START_TIME = new Prefix(" from ");
    public static final Prefix PREFIX_LABELS = new Prefix(" label: ");
    public static final Prefix PREFIX_ISCOMPLETED = new Prefix("c/");

    /* Patterns definitions */
    public static final Pattern KEYWORDS_ARGS_FORMAT =
            Pattern.compile("(?<keywords>\\S+(?:\\s+\\S+)*)"); // one or more keywords separated by whitespace

}
