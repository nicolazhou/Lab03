package it.polito.tdp.spellchecker.model;


public class RichWord {

	String word;
	boolean correct;
	
	
	public RichWord(String word, boolean correct) {
		this.word = word;
		this.correct = correct;
	}


	
	public String getWord() {
		return word;
	}


	public boolean isCorrect() {
		return correct;
	}
	
	
}
