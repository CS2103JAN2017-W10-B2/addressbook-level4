package seedu.address.model.label;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.commons.core.UnmodifiableObservableList;
import seedu.address.commons.exceptions.DuplicateDataException;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.CollectionUtil;

/**
 * A list of tags that enforces no nulls and uniqueness between its elements.
 *
 * Supports minimal set of list operations for the app's features.
 *
 * @see Label#equals(Object)
 * @see CollectionUtil#elementsAreUnique(Collection)
 */
public class LABELS implements Iterable<Label> {

    private final ObservableList<Label> internalList = FXCollections.observableArrayList();

    /**
     * Constructs empty TagList.
     */
    public LABELS() {}

    /**
     * Creates a UniqueTagList using given String tags.
     * Enforces no nulls or duplicates.
     */
    public LABELS(String... tags) throws DuplicateTagException, IllegalValueException {
        final List<Label> tagList = new ArrayList<Label>();
        for (String tag : tags) {
            tagList.add(new Label(tag));
        }
        setTags(tagList);
    }

    /**
     * Creates a UniqueTagList using given tags.
     * Enforces no nulls or duplicates.
     */
    public LABELS(Label... tags) throws DuplicateTagException {
        assert !CollectionUtil.isAnyNull((Object[]) tags);
        final List<Label> initialTags = Arrays.asList(tags);
        if (!CollectionUtil.elementsAreUnique(initialTags)) {
            throw new DuplicateTagException();
        }
        internalList.addAll(initialTags);
    }

    /**
     * Creates a UniqueTagList using given tags.
     * Enforces no null or duplicate elements.
     */
    public LABELS(Collection<Label> tags) throws DuplicateTagException {
        this();
        setTags(tags);
    }

    /**
     * Creates a UniqueTagList using given tags.
     * Enforces no nulls.
     */
    public LABELS(Set<Label> tags) {
        assert !CollectionUtil.isAnyNull(tags);
        internalList.addAll(tags);
    }

    /**
     * Creates a copy of the given list.
     * Insulates from changes in source.
     */
    public LABELS(LABELS source) {
        internalList.addAll(source.internalList); // insulate internal list from changes in argument
    }

    /**
     * Returns all tags in this list as a Set.
     * This set is mutable and change-insulated against the internal list.
     */
    public Set<Label> toSet() {
        return new HashSet<>(internalList);
    }

    /**
     * Replaces the Tags in this list with those in the argument tag list.
     */
    public void setLabels(LABELS replacement) {
        this.internalList.setAll(replacement.internalList);
    }

    public void setTags(Collection<Label> tags) throws DuplicateTagException {
        assert !CollectionUtil.isAnyNull(tags);
        if (!CollectionUtil.elementsAreUnique(tags)) {
            throw new DuplicateTagException();
        }
        internalList.setAll(tags);
    }

    /**
     * Ensures every tag in the argument list exists in this object.
     */
    public void mergeFrom(LABELS from) {
        final Set<Label> alreadyInside = this.toSet();
        from.internalList.stream()
                .filter(tag -> !alreadyInside.contains(tag))
                .forEach(internalList::add);
    }

    /**
     * Returns true if the list contains an equivalent Tag as the given argument.
     */
    public boolean contains(Label toCheck) {
        assert toCheck != null;
        return internalList.contains(toCheck);
    }

    /**
     * Adds a Tag to the list.
     *
     * @throws DuplicateTagException if the Tag to add is a duplicate of an existing Tag in the list.
     */
    public void add(Label toAdd) throws DuplicateTagException {
        assert toAdd != null;
        if (contains(toAdd)) {
            throw new DuplicateTagException();
        }
        internalList.add(toAdd);
    }

    @Override
    public Iterator<Label> iterator() {
        return internalList.iterator();
    }

    public UnmodifiableObservableList<Label> asObservableList() {
        return new UnmodifiableObservableList<>(internalList);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof LABELS // instanceof handles nulls
                && this.internalList.equals(
                ((LABELS) other).internalList));
    }

    public boolean equalsOrderInsensitive(LABELS other) {
        return this == other || new HashSet<>(this.internalList).equals(new HashSet<>(other.internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Signals that an operation would have violated the 'no duplicates' property of the list.
     */
    public static class DuplicateTagException extends DuplicateDataException {
        protected DuplicateTagException() {
            super("Operation would result in duplicate tags");
        }
    }

}
