import java.io.PrintStream;
import java.util.Comparator;

/**
 * Single-link List. Uses {@link Node} for list nodes.
 */
public class List<T> implements ListInterface<T> {

    private Node<T> head = null;
    private Node<T> tail = null;
    private int size;

    /**
     * Default constructor
     */
    public List() {
    }

    /**
     * Determine whether list is empty
     *
     * @return true if list is empty
     */
    @Override
    public boolean isEmpty() {
        return head == null;
    }

    /**
     * Inserts the data at the front of the list
     *
     * @param data the inserted data
     */
    @Override
    public void insertAtFront(T data) {
        Node<T> n = new Node<>(data);

        if (isEmpty()) {
            head = n;
            tail = n;
        } else {
            n.setNext(head);
            head = n;
        }
        size++;
    }

    /**
     * Inserts the data at the end of the list
     *
     * @param data the inserted item
     */
    @Override
    public void insertAtBack(T data) {
        Node<T> n = new Node<>(data);

        if (isEmpty()) {
            head = n;
            tail = n;
        } else {
            tail.setNext(n);
            tail = n;
        }
        size++;
    }

    /**
     * Returns and removes the data from the list head
     *
     * @return the data contained in the list head
     * @throws EmptyListException if the list is empty
     */
    @Override
    public T removeFromFront() throws EmptyListException{
    	if (isEmpty()) {
        	throw new EmptyListException();
        }

        T data = head.getData();

        if (head == tail) {
            head = tail = null;  
        }else {
            head = head.getNext();
        }
        size--;
        return data;
    }

    /**
     * Returns and removes the data from the list tail
     *
     * @return the data contained in the list tail
     * @throws EmptyListException if the list is empty
     */
    @Override
    public T removeFromBack() throws EmptyListException {
    	if (isEmpty()) {
        	throw new EmptyListException();
        }
    	
        T data = tail.getData();

        if (head == tail) {
            head = tail = null;
        }else {
            Node<T> iterator = head;
            while (iterator.getNext() != tail)
                iterator = iterator.getNext();

            iterator.setNext(null);
            tail = iterator;
        }
        size--;
        return data;
    }
    
    /**
     * Removes without returning the data from the list 
     *
     * prints error message if there is not data in list
     */
    public void remove (T data) {
    	Node current = head;
    	Node prev = head;
    	int flag = 0;
    	while (current!=null && current.getData() != data) {
    		flag = 1;
    		prev = current;
    		current = current.getNext();
    	}
    	if (current==null) {
    		System.out.println("There is not stopword: " + data + ".");
    	}else if (flag == 0) {
    		try {
				this.removeFromFront();
			} catch (EmptyListException e) {
				System.out.print("");
			}
    	}else {
    		prev.setNext(current.getNext());
    	}
    }
    
    /**
     * Searhes for data in the list
     * 
     * @param data
     * @return	true if there is data in list and false if there is not
     */
    public boolean search(T data) {
    	Node<T> iterator = head;
        while (iterator != null) {
        	if (iterator.getData().equals(data)) {
        		return true;
        	}
            iterator = iterator.getNext();
        }
        return false;
    }
    
    public Node<T> getHead(){//return the head of the list
    	return head;
    }
    
    public Node<T> getTail(){//return the tail of the list
    	return tail;
    }
    
    public int getSize() {//return the size of the list
    	return size;
    }
    
    public void setHead(Node h) {//set head of the list
    	this.head = h;
    }
    
    public void setTail(Node t) {//set tail of the list
    	this.tail = t;
    }
}
