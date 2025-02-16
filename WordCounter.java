import java.io.PrintStream;

public interface WordCounter {
	
	/**
	 * Inserts w at a new TreeNode of BST 
	 * or increases the number of appearances of TreeNode's item which has as key string w
	 * @param w, string to insert as a TreeNode
	 */
	void insert(String w);
	
	
	/**
	 * Searches for a TreeNode which has as key string w
	 * and if this TreeNode's appearances are more than MeanFrequency 
	 * insert this key as a new treeNode in the root
	 * @param w, string to find in the BST
	 * @return the WordFreq of this TreeNode
	 */
	WordFreq search(String w);
	
	
	/**
	 * Removes the TreeNode which contains w
	 * @param w, string to remove from BST
	 */
	void remove(String w);
	
	
	/**
     * @return size of the tree (==distinct words of the BST)
     */
	void load(String filename);
	
	
	/**
     * @return total words of BST
     */
	int getTotalWords();
	
	
	/**
     * Reads words the .txt file contains, changes them if there is need,
     * removes these, which can't be inserted in the BST and
     * insert the correct words to lower class in the BST 
     * @param filename, the name of the file
     */
	int getDistinctWords();
	
	
	/**
     * Searches for w using search
     * @param w, string to return its frequency
     * @return frequency if there is w, 0 if there is not
     */
	int getFrequency(String w);
	
	/**
     * @return WordFreq with maximum frequency 
     */
	WordFreq getMaximumFrequency();
	
	/**
     * @return the mean frequency of all TreeNodes of BST if there are TreeNode in BST,
     * 0 if there are not
     */
	double getMeanFrequency();
	
	/**
     * Adds a stopWord in the stopWords linked-list
     * @param w, stopWord to add
     */
	void addStopWord(String w); 
	
	/**
     * Removes w from stopWords linked-list
     * Prints error message if list is empty or there is not w in list
     * @param w, stopWord to remove
     */
	void removeStopWord(String w); 
	
	/**
     * Prints alphabetically BST using stream and recursiveInOrder
     * *If BST is empty prints error message
     * @param stream
     */
	void print‘reeAlphabetically(PrintStream stream);
	
	/**
     * Print BST in an ascending way 
     * comparing the number of appearances of every TreeNode
     * Uses stream and recursivePostOrder
     * If BST is empty prints error message
     * @param stream, PrintStream to print BST
     */
	void printTreeByFrequency(PrintStream stream);
} 
