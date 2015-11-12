import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class Main {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		
		String line = null;
		BufferedReader buff =  new BufferedReader(new FileReader("G1.txt"));

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

	}

}
