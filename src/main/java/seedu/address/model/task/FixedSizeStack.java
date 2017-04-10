//@@author A0138831A
package seedu.address.model.task;

import java.util.Stack;

/**
 *
 * @author A0138831A
 *         A fixed size stack to prevent overflowing default size is
 *         11 however you may self defined in declaration.
 *         To self-define the limit, include an integer in the bracket when instantiating
 *         example: FixedSizeStack<T>(size);
 *
 * @param <T>
 */
public class FixedSizeStack<T> extends Stack<T> {

    private int maximumSize = 11;

    public FixedSizeStack() {
        super();
    }

    public FixedSizeStack(int size) {
        super();
        this.maximumSize = size;
    }

    @Override
    public T push(T object) {
        while (this.size() >= maximumSize) {
            this.remove(0);
        }
        return super.push(object);
    }

}
