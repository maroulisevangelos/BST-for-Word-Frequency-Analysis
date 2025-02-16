# BST-for-Word-Frequency-Analysis
This project implements a Binary Search Tree (BST) to efficiently store and manage word frequencies from a text file. It supports operations such as insertion, search, removal, and statistical analysis of word occurrences. The implementation is optimized to avoid unnecessary checks, ensuring the best possible performance.


Features
🌟 Core Functionalities
  Insert (insert(String w))
  
    Adds a word to the BST or increases its frequency if it already exists.
    Updates subtreeSize for all affected nodes.
    Optimized to avoid redundant checks.
    
  Search (search(String w))

    Searches for a word in the BST and returns its corresponding WordFreq.
    If the word’s frequency is above average, it is reinserted at the root to optimize future searches.
  
  Remove (remove(String w))
  
    Removes a word from the BST while maintaining the tree’s structure.
    Updates subtreeSize accordingly.
    
  Load (load(String filename))

    Reads words from a .txt file, processes them, and inserts them into the BST.
    Handles punctuation removal and character validation.
  
📊 Word Frequency Analysis

  Get Total Words (getTotalWords()) → Returns the total number of words stored (O(1)).
  
  Get Distinct Words (getDistinctWords()) → Returns the number of unique words (O(1)).
  
  Get Frequency (getFrequency(String w)) → Returns the frequency of a specific word (O(log N)).
  
  Get Maximum Frequency (getMaximumFrequency()) → Finds the word with the highest frequency (O(N)).
  
  Get Mean Frequency (getMeanFrequency()) → Calculates the average word frequency (O(1)).
  
🔍 Stopword Management

  Add Stopword (addStopWord(String w)) → Inserts a stopword at the front of the list (O(1)).
  
  Remove Stopword (removeStopWord(String w)) → Removes a stopword if it exists (O(N)).
  
🖨️ Output Methods

  Print Alphabetically (printAlphabetically(PrintStream out)) → Prints words in alphabetical order (O(N)).
  
  Print by Frequency (printByFrequency(PrintStream out)) → Prints words sorted by frequency using a post-order traversal (O(3N)).
  
Usage

  Run the program from the command line:
    java BSTMain input.txt
  
  Or set the arguments in Eclipse:
    ..\src\input.txt
