package model.initialisation;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import model.Configuration;
import model.RulesConfiguration;


public class Main {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		
		Configuration.rules = new RulesConfiguration();
		
		String line = null;
		BufferedReader buff =  new BufferedReader(new FileReader("src/model/initialisation/G1.txt"));
		
		

		ArrayList<Specification> list = new ArrayList<Specification>();
		
		String[] splitLine;
		
		String specification;

		while( (line = buff.readLine() ) != null)
		{
			splitLine = line.split(" ");

			specification = splitLine[0];
			
			if(specification.equals("Unaire"))
				list.add(new SpecificationUnaire(splitLine[1], splitLine[2], splitLine[3]));
			
			if(specification.equals("Primaire"))
				list.add(new SpecificationPrimaire(splitLine[1], splitLine[2]));
			
			if(specification.equals("Binaire"))
				list.add(new SpecificationBinaire(splitLine[1], splitLine[2], Integer.parseInt(splitLine[3])));

		}

		buff.close();
		
		GenerGrammarRules r = new GenerGrammarRules(list);
		r.generateGrammar();
		/*
		BufferedReader regle_buff =  new BufferedReader(new FileReader("src/model/initialisation/test.rules"));
		
		G2.readRule(regle_buff);*/
		
		System.out.println(Configuration.rules.getRules());

	}

}
