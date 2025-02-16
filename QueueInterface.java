


/**
 * interface Queue declares the basic methods a queue must support, i.e.
 * @param <T>
 */
public interface QueueInterface<T> {

    /**
     * Insert a new item into the queue.
     * @param item the item to insert.
     */
    void enqueue(T item);


    /**
     * Return and remove the least recently inserted item
     * from the queue.
     * @return the least recently inserted item in the queue.
     * @throws EmptyQueueException if the queue is empty.
     */
    T dequeue() throws EmptyQueueException;

    /**
     * Test if the queue is logically empty.
     * @return true if empty, false otherwise.
     */
    boolean isEmpty();
}
