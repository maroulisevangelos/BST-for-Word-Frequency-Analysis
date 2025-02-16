
/**
 * Implementation of Queue using Array structure
 *
 * @param <T>
 */
public class ArrayQueue<T> implements QueueInterface<T> {
  

    private T[] queueContents; // the array to hold the queue data

    private int size; // number of enqueued items

    private int front; // array position of the front of the queue
    private int back; // array position of the back of the queue

    private static final int DEFAULT_CAPACITY = 2; // the default queue capacity
    private static final int AUTOGROW_SIZE = 2; // the autogrow size after reaching full capacity

    /**
     * Initialize the queue
     */
    public ArrayQueue() {
        queueContents = (T[]) new Object[DEFAULT_CAPACITY];
        front = 0;
        back = -1;
        size = 0;
    }

    /**
     * Insert a new item into the queue.
     *
     * @param item the item to insert.
     */
    @Override
    public void enqueue(T item) {
        if (size == queueContents.length)
            growQueue();

        back = positionAfter(back);
        queueContents[back] = item;

        size += 1;
    }

    /**
     * Return and remove the least recently inserted item
     * from the queue.
     *
     * @return the least recently inserted item in the queue.
     * @throws EmptyQueueException if the queue is empty.
     */
    @Override
    public T dequeue() throws EmptyQueueException {
        if (isEmpty())
            throw new EmptyQueueException();

        T element = queueContents[front];
        front = positionAfter(front);
        size -= 1;

        return element;
    }

    /**
     * Test if the queue is logically empty.
     *
     * @return true if empty, false otherwise.
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns the next position in queue, after "current"
     *
     * @param current The current position
     * @return the position in queue after current
     */
    private int positionAfter(int current) {
        return (current + 1) % queueContents.length;

        // Alternative
        // if(current + 1 == queueContents.length)
        //     return 0;
        // return current + 1;
    }

    /**
     * Increases the maximum capacity of the queue base on AUTOGROW_SIZE
     */
    private void growQueue() {
        T[] newContents = (T[]) new Object[queueContents.length + AUTOGROW_SIZE];

        int current = front;

        for (int i = 0; i < size; ++i) {
            newContents[i] = queueContents[current];
            current = positionAfter(current);
        }
        queueContents = newContents;
        front = 0;
        back = size - 1;
    }
}
