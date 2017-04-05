package seedu.address.logic.parser;

import java.util.regex.Pattern;

import seedu.address.logic.parser.ArgumentTokenizer.Prefix;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple
 * commands
 */
public class CliSyntax {

    //@@author A0135795R
    /* Prefix definitions */
    public static final String KEYWORD_ONLY_DEADLINE = " due:";
    public static final Prefix PREFIX_ONLY_DEADLINE = new Prefix(KEYWORD_ONLY_DEADLINE);
    public static final Prefix PREFIX_DEADLINE = new Prefix(" till:");
    public static final Prefix PREFIX_REMARKS = new Prefix(" remark:");
    public static final Prefix PREFIX_START_TIME = new Prefix(" from:");
    public static final Prefix PREFIX_LABELS = new Prefix(" #");
    public static final Prefix PREFIX_ISCOMPLETED = new Prefix("c/");
    //@@author

    /* Patterns definitions */
    public static final Pattern KEYWORDS_ARGS_FORMAT = Pattern.compile("(?<keywords>\\S+(?:\\s+\\S+)*)"); // one
                                                                                                          // or
                                                                                                          // more
                                                                                                          // keywords
                                                                                                          // separated
                                                                                                          // by
                                                                                                          // whitespace

}
