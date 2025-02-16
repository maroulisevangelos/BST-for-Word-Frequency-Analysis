public class BSTMain{
	public static void main(String[] args) {
		BST bst = new BST(new StringComparator());
		/*bst.insert("my");
		bst.addStopWord("is");
		bst.addStopWord("vagelis");
		bst.insert("my");
		bst.insert("name");
		bst.insert("is");
		bst.insert("my");
		bst.removeStopWord("vagelis");
		bst.insert("vagelis");
		bst.print‘reeAlphabetically(System.out);
		bst.insert("vagelis");
		bst.insert("my");
		bst.insert("is");
		bst.insert("vagelis");
		bst.insert("my");
		bst.print‘reeAlphabetically(System.out);
		bst.print‘reeByFrequency(System.out);
		bst.print‘reeAlphabetically(System.out);
		System.out.println(bst.getTotalWords());
		System.out.println(bst.getDistinctWords());
		System.out.println(bst.getMeanFrequency());
		System.out.println(bst.getTotalWords());
		System.out.println(bst.getMaximumFrequency());*/
		
		bst.printTreeByFrequency(System.out);
		bst.print‘reeAlphabetically(System.out);
		String filename = args[0];//read the name of the file
		bst.removeStopWord("the");
		bst.addStopWord("of");
		bst.removeStopWord("number");
		bst.addStopWord("the");
		bst.removeStopWord("the");
		bst.load(filename);
		bst.removeStopWord("the");
		System.out.println("Total words: "+bst.getTotalWords());
		System.out.println("Distinct words: "+bst.getDistinctWords());
		System.out.println("Mean frequency: "+bst.getMeanFrequency());
		System.out.println("Maximum Frequency: "+bst.getMaximumFrequency());	
		System.out.println("Number appearances of \"cele'brate\": "+bst.getFrequency("cele'brate"));
		bst.print‘reeAlphabetically(System.out);
		bst.printTreeByFrequency(System.out);
		bst.print‘reeAlphabetically(System.out);
		bst.printTreeByFrequency(System.out);
	}
}