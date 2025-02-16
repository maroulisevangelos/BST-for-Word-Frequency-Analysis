import java.util.*;
import java.io.*;

public class BST implements WordCounter { 
	
	private class TreeNode {
		WordFreq item; 
		// pointer to left subtree
		TreeNode left; 
		// pointer to right subtree
		TreeNode right; 
		// The BST node's parent
	    // (useful for performing rotations (swaps) during root insertion)
	    TreeNode parent;
		//number of nodes in subtree starting at this node
		int subtreeSize;
		
		 /**
	     * Default TreeNode constructor
	     */
	    TreeNode(){
	    }

	    /**
	     * TreeNode constructor accepting word
	     * @param word
	     */
	    TreeNode(String word){
	        this.item = new WordFreq(word);
	    }

	    /**
	     * @return left subtree
	     */
	    TreeNode getLeft() {
	        return left;
	    }

	    /**
	     * @param left Set left subtree
	     */
	    void setLeft(TreeNode left) {
	        this.left = left;
	    }

	    /**
	     * @return right subtree
	     */
	    TreeNode getRight() {
	        return right;
	    }

	    /**
	     * @param right Set right subtree
	     */
	    void setRight(TreeNode right) {
	        this.right = right;
	    }
	    
	    /** 
	     * @return the BST node's parent
	     */
	    TreeNode getParent() {
			return parent;
		}

	    /**
	     * 
	     * @param parent Set the BST node's parent
	     */
		void setParent(TreeNode parent) {
			this.parent = parent;
		}
		
		/**
		 * Print TeeNode's item using stream
		 * @param stream
		 */
		void visit(PrintStream stream) {
	        stream.print(this.item + " ");
	    }
		
		/**
		 * @return the subtreeSize of this TreeNode
		 */
		int getsubtreeSize() {
			return subtreeSize;
		}
		
		/**
		 * Set as TreeNode's subtreeSize int s
		 * @param s
		 */
		void setsubtreeSize(int s) {
			subtreeSize = s;
		}
	}; 
	
	
	
	private TreeNode head; //root of the tree
	private List<String> stopWords; // list of stopWords 
	private Comparator comparator;// The comparator for the items
	private int tw;//number of total words
	private int maxap;//the max num of all TreeNodes of the BST
	private TreeNode maxNode;//TreeNode which contains the maxap
	
	/**
     * The number of nodes in the tree.
     */
	private int size;
	
	public BST (Comparator comparator) {//Constructor
		this.comparator = comparator;
		this.head = null;
		this.size = 0;
		this.stopWords = new List<>();
		this.tw = 0;
	}
	
	/**
     * @return size of the tree (==distinct words of the BST)
     */
	public int getDistinctWords() {
		return this.size;
	}
	
	/**
	 * Inserts w at a new TreeNode of BST 
	 * or increases the number of appearances of TreeNode's item which has as key string w
	 * @param w, string to insert as a TreeNode
	 */
	@Override
	public void insert(String w) {
		
		if (head == null) {//if BST is Empty insert a TreeNode in the head
			++size;//increase size
			++tw;//increase total words
            head = new TreeNode(w);
            head.setsubtreeSize(0);//subtreeSize of head = 0
            return;
		}
        TreeNode current = head;
        while (true) {
            if (current.item.key().equals(w)) {//if there is a TreeNode whose item's key == w 
            	current.item.addAppearance();//increase item'a appearance
            	++tw;
                return;
            }
            if (comparator.compare(current.item.key(), w) < 0) {//find in which direction string w should be
                if (current.getRight() == null) {//if current is a leaf
                    current.setRight(new TreeNode(w));//create a new TreeNode using as key string w
                    ++size;
                    ++tw;
                    this.upSubtreeSize(current.getRight());//update the subtreeSize of every node 
                    return;
                } else {
                    current = current.getRight();//go to the Right TreeNode
                }
            } else {
                if (current.getLeft() == null) {//if current is a leaf
                    current.setLeft(new TreeNode(w));//create a new TreeNode using as key string w
                    ++size;
                    ++tw;
                    this.upSubtreeSize(current.getLeft());//update the subtreeSize of every node 
                    return;
                } else {
                    current = current.getLeft();//go to the Left TreeNode
                }
            }
        }
	}
	
	/**
	 * Searches for a TreeNode which has as key string w
	 * and if this TreeNode's appearances are more than MeanFrequency 
	 * insert this key as a new treeNode in the root
	 * @param w, string to find in the BST
	 * @return the WordFreq of this TreeNode
	 */
	@Override
	public WordFreq search(String w) {
		
		TreeNode current = head;
        while (true) {
            if (current == null)//if current is a leaf
                return null;

            if (current.item.key().equals(w)) {//checks if this is the TreeNode we look for
            	if (current.item.num()>this.getMeanFrequency()) {//check if TreeNode's appearances are more than MeanFrequency
            		String k = current.item.key();//store the key
            		int n = current.item.num();//store the num
            		remove(current.item.key());//remove this TreeNode
            		rootInsert(k,n);//insert this key as a new treeNode in the root
            	}
                return current.item;//return TreeNode's item
            }
            
            if (comparator.compare(current.item.key(), w) < 0)//find in which direction string w should be
                current = current.getRight();
            else
                current = current.getLeft();
        }
	}
	
	/**
	 * Removes the TreeNode which contains w
	 * @param w, string to remove from BST
	 */
	@Override
	public void remove(String w) {
		
		// find node to delete and its parent
        TreeNode current = head;
        TreeNode parent = null;
        
        while (true) {
            if (current == null)
                return;

            if (current.item.key().equals(w)) {
            	--size;//reduce the size
            	tw = tw - current.item.num();//remove from tw current's appearances
            	this.upSubtreeSize(parent);//update the subtreeSize of every node until parent
                break;
            }
            parent = current;
            if (comparator.compare(current.item.key(), w) < 0) {
                current = current.getRight();
            }else {
                current = current.getLeft();
            }
        }

        // node to replace with
        TreeNode replace = null;

        // only right
        if (current.getLeft() == null)
            replace = current.getRight();
        else if (current.getRight() == null)
            replace = current.getLeft();
        else {
            // find left most child of current right subtree!
            TreeNode findCurrent = current.getRight();

            while (true) {
                if (findCurrent.getLeft() != null)
                    findCurrent = findCurrent.getLeft();
                else
                    break;
            }

            // only has zero or one child (there is no left child!!!)
            remove(findCurrent.item.key());

            findCurrent.setLeft(current.getLeft());
            findCurrent.setRight(current.getRight());

            replace = findCurrent;
        }
        // replace parents reference

        if (parent == null) { //root
            head = replace;
        } else {
            if (parent.getLeft().equals(current))
                parent.setLeft(replace);
            
            if (parent.getRight().equals(current))
                parent.setRight(replace);
        }
    }
	
	/*
	* Recursively adds an element in the root of tree by doing rotations.
	*/
	private TreeNode rootInsert(TreeNode head, String element, TreeNode parent) {
    	
        if (head == null) {
        	//the BST specified by root is empty (it has 0 elements) -
        	//initialize node with element and insert it at the BST
        	//(do not make any rotations)
            TreeNode node = new TreeNode(element);
            node.setParent(parent);
            return node;
        }
        
        //BST not empty
        //find subtree where we're going to insert element
        int result = comparator.compare(element, head.item.key());
        
        if (result == 0) {
        	//element equal to root
        	//do not insert element in the BST
        	return head;
        }
        
        if (result < 0) {
        	//element smaller than root
        	//(1) insert element at the left subtree of root (recursively)
        	TreeNode leftSubtreeRoot = this.rootInsert(head.getLeft(), element, head);
        	//(2) update root's left subtree
            head.setLeft(leftSubtreeRoot);
            //(3) perform a rotation at the opposite (right)
            head = this.rotateRight(head);
            
        } 
        else {
        	//element bigger than root
        	//(1) insert element at the right subtree of root (recursively)
        	TreeNode rightSubtreeRoot = this.rootInsert(head.getRight(), element, head);
        	//(2) update root's right subtree
        	head.setRight(rightSubtreeRoot);
        	//(3) perform a rotation at the opposite (left)
        	head = this.rotateLeft(head);
        }
        
        //after each rotation, return the updated BST
        return head;
    }
	
	/**
	 * Inserts element as a new treeNode in the root
	 * and adds appearances TreeNode had before
	 * @param element, string to insert in the root
	 * @param n, number of appearances the TreeNode had before
	 */
	public void rootInsert(String element,int n){
		head = rootInsert(head, element, null);
		tw = tw + n;//increase tw
		head.item.addIntAppearance(n-1);//add the appearances of item (-1 because after rootInsert head.item.num() == 1)
		++size;
		this.upSubtreeSize(null);
	}
	
	
	/**
     * Performs a left rotation.
     * @param pivot The node to rotate.
     */
    private TreeNode rotateLeft(TreeNode pivot) {
    	
        TreeNode parent = pivot.getParent();
        TreeNode child = pivot.getRight();
        
        //update pivot's parent's child with pivot's right child
        if (parent == null) {
            head = child;
        } 
        else if (parent.getLeft() == pivot) {
            parent.setLeft(child);
        } 
        else {
            parent.setRight(child);
        }
        
        //update pivot's right child's parent with pivot's parent
        child.setParent(pivot.getParent());
        //update pivot's parent with child
        pivot.setParent(child);
        //during update, child has 3 children (1 right, 1 initial left + 1 left (pivot))
        //BST spec violation
        //pivot takes child's initial left child as its right child
        pivot.setRight(child.getLeft());
        //if child's left child exists, update it with its new parent (pivot)
        if (child.getLeft() != null) {
        	child.getLeft().setParent(pivot);
        }
        //update child's new left child (pivot)
        child.setLeft(pivot);
        //return the new BST root after rotation
        return child;
    }
    
    /**
     * Performs a right rotation.
     * @param pivot The node to rotate.
     */
    private TreeNode rotateRight(TreeNode pivot) {
    	
        TreeNode parent = pivot.getParent();
        TreeNode child = pivot.getLeft();
        
        if (parent == null) {
            head = child;
        } 
        else if (parent.getLeft() == pivot) {
            parent.setLeft(child);
        } 
        else {
            parent.setRight(child);
        }
        
        child.setParent(pivot.getParent());
        pivot.setParent(child);
        pivot.setLeft(child.getRight());
        if (child.getRight() != null) {
        	child.getRight().setParent(pivot);
        }
        child.setRight(pivot);
        return child;
    }
    
    /**
     * Updates the subtreeSize of every TreeNode
     */
    private void upSubtreeSize(TreeNode n) {
    	List l1 = new List();
    	this.BreadthFirst(n, l1);//store all the TreeNodes until n in a linked-list
    	Node l1head = l1.getHead();//store the head of the list
    	TreeNode t;
        Node l2head;
        while (l1head!=null) {//until the end of l1
        	List l2 = new List();
        	this.recursivePostOrder((TreeNode)l1head.getData(), l2);//store all TreeNodes of l1head's subtree
        	int s = l2.getSize() -1;//subtreeSize = size of the list - 1
        	t = (TreeNode)l1head.getData();
        	t.setsubtreeSize(s);
        	l1head = l1head.getNext();//go to the next node of the list
        }
    }
    
    /**
     * Using PreOrder traversal finds maxap and maxNode
     * @param node the head of the BST
     */
    private void recursivePreOrder(TreeNode node) {
    	
        if (node == null)
            return ;
        if (node.item.num()>maxap) {//if this TreeNode's appearances > maxap
        	maxap = node.item.num();//update maxap
        	maxNode = node;//update maxNode
        }
        
        recursivePreOrder(node.getLeft());
        recursivePreOrder(node.getRight());
        
    }
    
    /**
     * @return total words of BST
     */
    @Override
    public int getTotalWords() {
    	return tw;
    }
    
    
    /**
     * Searches for w using search
     * @param w, string to return its frequency
     * @return frequency if there is w, 0 if there is not
     */
    @Override
    public int getFrequency(String w) {
    	WordFreq it = this.search(w);
    	if (it != null) {
    		return it.num();
    	}
    	return 0;
    }
    
    
    /**
     * @return WordFreq with maximum frequency 
     */
    @Override
    public WordFreq getMaximumFrequency() {
    	maxap = -1;
    	maxNode = null;
    	recursivePreOrder(head);//call recursivePreOrder 
    	return maxNode.item;
    }
    
    
    /**
     * @return the mean frequency of all TreeNodes of BST if there are TreeNode in BST,
     * 0 if there are not
     */
    @Override
    public double getMeanFrequency() {
    	if (this.getDistinctWords()!=0) {
    		return (double)this.getTotalWords()/this.getDistinctWords();
    	}else {
    		return 0;
    	}
    }
    
    
    /**
     * Adds a stopWord in the stopWords linked-list
     * @param w, stopWord to add
     */
    @Override
    public void addStopWord(String w) {
    	stopWords.insertAtFront(w);//call insertAtFront
    }
    
    
    /**
     * Removes w from stopWords linked-list
     * Prints error message if list is empty or there is not w in list
     * @param w, stopWord to remove
     */
    @Override
    public void removeStopWord(String w) {
    	if (stopWords.isEmpty()) {
    		System.out.println("List stopWords is empty!");
    	}else {
    		stopWords.remove(w);//call remove
    	}
    }
    
    
    /**
     * Prints alphabetically BST using stream and recursiveInOrder
     * If BST is empty prints error message
     * @param stream
     */
    @Override
    public void print‘reeAlphabetically(PrintStream stream) {
    	if (head==null) {//if BST is empty
    		System.out.println("BST is empty!");
    	}else {
    		recursiveInOrder(head,stream);//call recursiveInOrder
    		stream.println();//change line
    	}
    }
    
    
    /**
     * Using InOrder traversal to print BST
     * @param node, the head of the BST
     * @param stream, PrintStream to print BST
     */
    private void recursiveInOrder(TreeNode node,PrintStream stream) {
        if (node == null)
            return;

        recursiveInOrder(node.getLeft(), stream);
        node.visit(stream);//call visit
        recursiveInOrder(node.getRight(),stream);
    }
    
    /**
     * Print BST in an ascending way 
     * comparing the number of appearances of every TreeNode
     * Uses stream and recursivePostOrder
     * If BST is empty prints error message
     * @param stream, PrintStream to print BST
     */
    @Override
    public void printTreeByFrequency(PrintStream stream) {
    	if (head==null) {//if BST is empty
    		System.out.println("BST is empty!");
    	}else {
    		List l = new List();
    		this.recursivePostOrder(head, l);//store all the TreeNodes in a linked-list
    		Node lhead = l.getHead();//store head of the list
    		Node tail = l.getTail();//store tail of the list
    		if (lhead == null || lhead == tail)//sort the linked-list
    			return;

    		Node newHead = null;
    		Node newTail = null;
        
    		while (lhead != null) {
    			// get next item
    			Node tmp = lhead;

    			// move head
    			lhead = lhead.getNext();

    			// move swap to new-sorted list
    			if (newHead == null) {
    				newHead = newTail = tmp;
    				tmp.setNext(null);
    			} else {
    				Node prev = null;
    				Node iterator = newHead;

    				// iterate newList until we get to a point where our data is smaller or reach the end
    				while (iterator != null && this.TreeNodeComparator((TreeNode)iterator.getData(), (TreeNode)tmp.getData()) <= 0) {
    					prev = iterator;
    					iterator = iterator.getNext();
    				}

    				// reached the point where we should place the node

    				// if prev == null then its the head of the list
    				if (prev == null)
                    newHead = tmp;
    				else
    					prev.setNext(tmp);

    				// if iterator == null then its the tail of the list
    				tmp.setNext(iterator);
    				if(iterator == null)
    					newTail = tmp;
    			}
    		}
    		l.setHead(newHead);
    		l.setTail(newTail);
        
     

    		Node current = l.getHead();
    		TreeNode t ;
    		while (current != null) {//until the end of linked-list
    			t = (TreeNode)current.getData();
    			t.visit(stream);//call visit
    			current = current.next;//go to the next Node
    		}
    		stream.println();//change line
    	}
    }
   
    
    /**
     * Compares two TreeNodes using their nums
     * @param t1, first TreeNode to compare
     * @param t2, second TreeNode to compare
     * @return difference between the appearances of the two TreeNodes
     */
    private int TreeNodeComparator(TreeNode t1, TreeNode t2){
        	int it1 = t1.item.num();
        	int it2 = t2.item.num();
            return it1 - it2;
    }
    
    
    /**
     * Using PostOrder traversal to store TreeNodes of BST in a linked-list
     * @param node, TreeNode to start
     * @param l, linked-list to insert the TreeNodes
     */
    private void recursivePostOrder(TreeNode node,List l) {
        if (node == null)
            return;

        recursivePostOrder(node.getLeft(),l);
        recursivePostOrder(node.getRight(),l);
        l.insertAtFront(node);
    }
    
    
    /**
     * Using BreadthFirst traversal to store TreeNodes of BST in a linked-list
     * @param stop,TreeNode to stop
     * @param l, linked-list to insert the TreeNodes
     */
    public void BreadthFirst(TreeNode stop,List l) {
        QueueInterface queue = new ArrayQueue();

        if (head == null)
            return;

        queue.enqueue(head);

        try {
            while (!queue.isEmpty()) {
                TreeNode current = (TreeNode) queue.dequeue();

                if (current.getLeft() != null)
                    queue.enqueue(current.getLeft());

                if (current.getRight() != null)
                    queue.enqueue(current.getRight());

                l.insertAtFront(current);
                if (current.equals(stop)) {
                	break;
                }
            }
        } catch (EmptyQueueException ex) {
            return;
        }
    }
   
    /**
     * Reads words the .txt file contains, changes them if there is need,
     * removes these, which can't be inserted in the BST and
     * insert the correct words to lower class in the BST 
     * @param filename, the name of the file
     */
    public void load(String filename) {
    	BufferedReader reader;
    	String line;
    	String word = "";
    	boolean flag = false;
    	Scanner s;
    	String ci;
    	try {
    		reader = new BufferedReader(new FileReader(new File(filename)));//open txt file
            line = reader.readLine();//read the first line         
            while (line != null) {
            	int i = 0; 
            	s = new Scanner(line).useDelimiter("\\s");//read every word
            	while (s.hasNext()) {//while there is a word in the line
            		flag = false;
            		String c = s.next();
            		i = 0;
            		while(i<c.length() && !flag) {//check for digits inside the word 
            			if ( Character.isDigit(c.charAt(i))) {
            				flag = true;
            			}
            			++i;
            		}
            		i = 0;
            		ci = c; 
            		while(i<c.length() && !flag) {//if there are not digits remove every punctuation until the first letter
            			if ( Character.isLetter(c.charAt(i))) {//when you find a letter
            				break;
            			}else {
            				ci = ci.substring(1);//remove first char
            			}
            			++i;
            		}
            		c = ci;
            		i = c.length()-1;
            		while(i>0 && !flag) {//if there are not digits remove every punctuation until the first letter from behind
            			if ( Character.isLetter(c.charAt(i))) {//when you find a letter
            				break;
            			}else {
            				ci = ci.substring(0,ci.length()-1);//remove last char
            			}
            			--i;
            		}
            		c = ci;
            		i = 0;
            		while(i<c.length() && !flag) {//if there are not digits check for punctuation inside the word
            			if (!(Character.isLetter(c.charAt(i))) && !(String.valueOf(c.charAt(i)).equals("'"))) {
            				flag = true;
            				break;
            			}
            			++i;
            		}
            		c = c.toLowerCase();//make the capital letters small
            		if (stopWords.search(c)) {//check if this word is a stop word
            			flag = true;
            		}
            		if (flag == false) {//if the word is correct
            			this.insert(c);//insert it in the BST
            		}
            		
            	}
            	s.close();
            	line = reader.readLine();//read next line
            }
            reader.close();
    	}
    	
    	catch (IOException e) {//exception on reading file
            System.out.println	("Error reading line ...");
		}
    }
}