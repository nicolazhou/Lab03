package it.polito.tdp.spellchecker.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Dictionary {

	List<String> dictionary;
	
	
	public Dictionary() {
		
		this.dictionary = new ArrayList<String>();
		//this.dictionary = new LinkedList<String>();
	}
	
	
	public void loadDictionary(String language) {
		
		this.dictionary.clear();
		
		try {
			FileReader fr = new FileReader(language+".txt");
			BufferedReader br = new BufferedReader(fr); 
			
			String word;
			while((word = br.readLine()) != null) {
				this.dictionary.add(word);
			}
			
			br.close();
			
			} catch (IOException e){
				System.out.println("Errore nella lettura del file");
			}
		
	}
	
	
	public List<RichWord> spellCheckText(List<String> inputTextList) {
		
		List<RichWord> list = new ArrayList<RichWord>();
		
		for(String input : inputTextList) {
			
			if(this.dictionary.contains(input)) {
				list.add(new RichWord(input, true));
			} else {
				list.add(new RichWord(input, false));
			}
			
		}
		
		return list;
	}
	
	
	public List<RichWord> spellCheckTextLinear(List<String> inputTextList) {
		
		List<RichWord> list = new ArrayList<RichWord>();
		
		for(String input : inputTextList) {
			
			boolean check = false;
			for(int i=0; i < this.dictionary.size(); i++) {
				
				if(this.dictionary.get(i).compareTo(input)==0) {
					list.add(new RichWord(input, true));
					check = true;
				}
			}
			
			if(!check) {
				list.add(new RichWord(input, false));
			}
		}
		
		return list;
	}
	
	
	public List<RichWord> spellCheckTextDichotomic(List<String> inputTextList) {
		
		List<RichWord> list = new ArrayList<RichWord>();
		Collections.sort(this.dictionary);
		
		/*for(String parola : this.dictionary) {
			System.out.println(parola);
		}*/
		
		//System.out.println(this.dictionary.size()/2);
		
		for(String input : inputTextList) {
			
			boolean check = false;
			
			if(input.compareTo(this.dictionary.get(this.dictionary.size()/2))==0) {
				list.add(new RichWord(input, true));
			} else if(input.compareTo(this.dictionary.get(this.dictionary.size()/2)) < 0) {
				
				//System.out.println(">0");
				//boolean check = false;
				for(int i=0; i < this.dictionary.size()/2; i++) {
					
					if(input.compareTo(this.dictionary.get(i))==0) {
						list.add(new RichWord(input, true));
						check = true;
					}
				}
				
				if(!check) {
					list.add(new RichWord(input, false));
				}
				
			} else if(input.compareTo(this.dictionary.get(this.dictionary.size()/2)) > 0) {
				
				//System.out.println("<0");
				//boolean check = false;
				for(int i=this.dictionary.size()/2; i < this.dictionary.size(); i++) {
					
					if(input.compareTo(this.dictionary.get(i))==0) {
						list.add(new RichWord(input, true));
						check = true;
					}
				}
				
				if(!check) {
					list.add(new RichWord(input, false));
				}
				
			}
			
		}
		
		return list;
	}
	
	
}
