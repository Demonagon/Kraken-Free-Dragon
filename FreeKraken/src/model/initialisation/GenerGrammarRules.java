import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GenerGrammarRules{

	public List<SpecificationUnaire> listLeftUnaryOperators; 
	public List<SpecificationUnaire> listRightUnaryOperators; 
	public List<SpecificationUnaire> listUnaryOperators; 
	public List<SpecificationPrimaire> listPrimaryOperators;
	public List<SpecificationBinaire> listBinaryOperators;
	HashMap<String, String> map;

	public GenerGrammarRules(List<Specification> listOperators)
	{
		listLeftUnaryOperators = new ArrayList<SpecificationUnaire>(); 
		listRightUnaryOperators = new ArrayList<SpecificationUnaire>(); 
		listUnaryOperators = new ArrayList<SpecificationUnaire>(); 
		listPrimaryOperators = new ArrayList<SpecificationPrimaire>();
		listBinaryOperators = new ArrayList<SpecificationBinaire>();
		map = new HashMap<String, String>(); // Clef : Le symbole, Valeur : Le nom du premier opérateur où le symbole a été vu


		sort(listOperators);
	}

	//Permet de trier la liste des operateurs Binaires en fonction de leur priorité ( + petit au + grand )
	private void addBinary(SpecificationBinaire b)
	{
		//Si la liste est vide
		if(listBinaryOperators.isEmpty()) {
			listBinaryOperators.add(b);
			return;
		}

		//Cas classique
		for(int i = 0 ; i < listBinaryOperators.size(); i++)
		{
			if(listBinaryOperators.get(i).getPriority() >= b.getPriority())
			{
				listBinaryOperators.add(i, b);
				return;
			}
		}

		//Si l'élément doit être ajouté en dernier
		listBinaryOperators.add(b);
	}

	private void sort(List<Specification> listOperators)
	{
		for(Specification s : listOperators)
		{
			if( s instanceof SpecificationPrimaire)
			{
				listPrimaryOperators.add((SpecificationPrimaire) s);
			}

			else if(s instanceof SpecificationUnaire)
			{
				SpecificationUnaire u = (SpecificationUnaire) s;
				if(u.isLeftSymbolEmpty())
					listRightUnaryOperators.add(u);
				else if(u.isRightSymbolEmpty())
					listLeftUnaryOperators.add(u);
				else
					listUnaryOperators.add(u);			

			}

			else if( s instanceof SpecificationBinaire)
			{
				addBinary((SpecificationBinaire) s);
			}
			else
				System.out.println("Erreur");
		}
	}
	private String writeABinaryRule(SpecificationBinaire b, boolean isFirstTerm, PrintWriter writer, int compteur)
	{
		if(!isFirstTerm)
			writer.println("\t|");

		String value = map.get(b.getSymbol());
		
		String nextSymbol = "Terme"+ (compteur-1);
		return "\t\tsymbol = <"+value+">\n\t\t"+nextSymbol+"()";

		//return nextSymbol+"()\n\t\tsymbol = <"+value+">\n\t\t"+nextSymbol+"()";
	}

	private String writeAnUnaryRule(SpecificationUnaire u, boolean isFirstTerm, PrintWriter writer, int compteur, Boolean isListRightUnaryOperators, Boolean isListLeftUnaryOperators)
	{
		String total = "\t\t";

		String value, nextSymbol;
		if(!isListRightUnaryOperators){
			if(u != listLeftUnaryOperators.get(0) )
				writer.println("\t|");

			value = map.get(u.getLeftSymbol());
			nextSymbol = "Terme"+compteur;
			return "\t\t"+"symbol = <"+value+">\n\t\t"+nextSymbol+"()";
		}	
		else if(!isListLeftUnaryOperators)
		{
			if(u != listRightUnaryOperators.get(0) )
				writer.println("\t|");
			
			value = map.get(u.getRightSymbol());
			return "\t\tsymbol = <"+value+">\n\t\tUnaireDroitBis()";
		}

		if(u != listUnaryOperators.get(0))
			writer.println("\t|");
		value = map.get(u.getLeftSymbol());
		total = "\t\tsymbolLeft = <"+value+">\n";
		total += "\t\tTerme0()\n";
		value = map.get(u.getRightSymbol());
		total += "\t\tsymbolRight = <"+value+">\n";

		return total;
	}

	private String writeAPrimaryRule(SpecificationPrimaire p, PrintWriter writer)
	{
		writer.println("\t|");
		String value = map.get(p.getRegEx());
		return "\t\tsymbolLeft = <"+value+">";			
	}

	private void writeARule(Specification s, Boolean isListRightUnaryOperators, Boolean isListLeftUnaryOperators, PrintWriter writer, int compteur, boolean isFirstTerm){
		String total = "";

		if( s instanceof SpecificationBinaire){			
			total = writeABinaryRule((SpecificationBinaire) s, isFirstTerm, writer,compteur);
		}

		if( s instanceof SpecificationUnaire){
			total = writeAnUnaryRule((SpecificationUnaire)s, isFirstTerm, writer, compteur, isListRightUnaryOperators, isListLeftUnaryOperators);
		}

		if( s instanceof SpecificationPrimaire){
			total = writeAPrimaryRule((SpecificationPrimaire) s, writer);
		}
		writer.println("\t(");
		writer.println(total);
		writer.println("\t)");
	}


	private int writeAllBinaryRules(PrintWriter writer, int compteur, int currentIndex)
	{

		int currentPriority = listBinaryOperators.get(currentIndex).getPriority();

		boolean isFirstTerm = true;
		writer.println("void Terme"+ compteur +"() :\n{");
		writer.println("}\n{");
		compteur++;

		String nextSymbol = "\t\tTerme"+ compteur;
		writer.println(nextSymbol+"()\n\t\tTerme"+(compteur-1)+"Bis()\n}");
		
		SpecificationBinaire b;
		
		writer.println("void Terme"+ (compteur-1) +"Bis() :\n{");
		writer.println("\tToken symbol;");
		writer.println("}\n{");

		for(currentIndex = currentIndex; currentIndex < listBinaryOperators.size() ; currentIndex++)
		{
			b = listBinaryOperators.get(currentIndex);

			if(b.getPriority() < currentPriority)
				continue;
			if(b.getPriority() > currentPriority)					
				break;
			writeARule(b, false, false, writer, compteur, isFirstTerm);	
			writer.println("\t|");
		}

		writer.println("\t(");
		writer.println("\t\tEpsilon()");
		writer.println("\t)");

		writer.println("}");
		return currentIndex;
	}

	private void writeAllRules(PrintWriter writer) {

		int compteur = 0;
		int currentIndex = 0;

		writer.println("void Axiome() :\n{");
		writer.println("}");

		writer.println("{");
		writer.println("\tTerme0()");
		writer.println("\t<EOF>");
		writer.println("}");

		if(listBinaryOperators == null){
			writer.println("void Terme0() :\n{");
			writer.println("}\n{");
			writer.println("\tTerme"+ compteur++ +"();");
		}

		else 
		{
			while(listBinaryOperators.size() - (currentIndex + 1) >= 0 ) //TODO : simplifier
			{
				currentIndex = writeAllBinaryRules(writer, compteur, currentIndex);
				compteur++;
			}
		}
		
		writer.println("void Terme"+compteur+"() :\n{"); //UnaireGauche
		writer.println("\tToken symbol;");
		writer.println("}");

		writer.println("{");

		if(! listLeftUnaryOperators.isEmpty())
		{
			for(SpecificationUnaire u : listLeftUnaryOperators)
				writeARule(u, false, true, writer, compteur, false);

			writer.println("\t|\n\t(");
		}

		writer.println("\t\tUnaireDroit()");
		writer.println("\t)");
		writer.println("}");

		writer.println("void UnaireDroit() :\n{");
		writer.println("}\n{");
		writer.println("\tPrimaire()");
		writer.println("\tUnaireDroitBis()");
		writer.println("}");

		
		writer.println("void UnaireDroitBis() :\n{");
		writer.println("\tToken symbol;");
		writer.println("}\n{");

		if(! listRightUnaryOperators.isEmpty())
		{
			for(SpecificationUnaire u : listRightUnaryOperators)
				writeARule(u, true, false, writer, 0, false);

			writer.println("\t|\n\t(");
		}

		writer.println("\t\tEpsilon()");
		writer.println("\t)");
		writer.println("}");

		writer.println("void Primaire() :\n{");
		writer.println("\tToken symbolLeft;");
		writer.println("\tToken symbolRight;");
		writer.println("}");

		writer.println("{");

		if(! listUnaryOperators.isEmpty())
			for(SpecificationUnaire u : listUnaryOperators)
				writeARule(u, true, true, writer, 0, false);

		for(SpecificationPrimaire p : listPrimaryOperators)
			writeARule(p, false, false, writer, 0, false);

		writer.println("}");

	}

	private void writePrimaryToken(PrintWriter writer)
	{
		for(SpecificationPrimaire p : listPrimaryOperators)
		{

			if(!map.containsKey(p.getRegEx())){
				map.put(p.getRegEx(), p.getName());

				String part = " < "+p.getName()+" : "+ p.getRegEx().substring(1) +" >";

				
				if(listBinaryOperators == null && listLeftUnaryOperators == null && listRightUnaryOperators == null && listUnaryOperators == null && p == listPrimaryOperators.get(0) && ! part.equals(""))
					writer.println(" "+part);

				else if(!part.equals(""))
					writer.println("|"+part);

			}
		}
	}


	private void writeUnaryToken(PrintWriter writer)
	{
		for(SpecificationUnaire u : listUnaryOperators)
		{
			String leftPart = "";
			String rightPart = "";

			if(!map.containsKey(u.getLeftSymbol()))
			{
				leftPart = " < "+u.getName()+"_LEFT : "+ u.getLeftSymbol() +" >";
				map.put(u.getLeftSymbol(), u.getName()+"_LEFT");
			}
			if(!map.containsKey(u.getRightSymbol()))
			{
				rightPart = " < "+u.getName()+"_RIGHT : "+ u.getRightSymbol() +" >";
				map.put(u.getRightSymbol(), u.getName()+"_RIGHT");
			}

			if(listBinaryOperators == null && listLeftUnaryOperators == null && listRightUnaryOperators == null && u == listUnaryOperators.get(0) && ! leftPart.equals(""))
				writer.println(" "+leftPart);

			else if(!leftPart.equals(""))
				writer.println("|"+leftPart);

			if(!rightPart.equals(""))
				writer.println("|"+rightPart);
		}
	}

	private void writeLeftUnaryToken(PrintWriter writer)
	{
		for(SpecificationUnaire u : listLeftUnaryOperators)
		{
			String leftPart = "";

			if(!map.containsKey(u.getLeftSymbol()))
			{
				leftPart = " < "+u.getName()+"_LEFT : "+ u.getLeftSymbol() +" >";
				map.put(u.getLeftSymbol(), u.getName()+"_LEFT");
			}


			if(listBinaryOperators == null && u == listLeftUnaryOperators.get(0) && !leftPart.equals(""))
				writer.println(" "+leftPart);
			else if(!leftPart.equals(""))
				writer.println("|"+leftPart);

		}
	}

	private void writeRightUnaryToken(PrintWriter writer)
	{
		String rightPart = "";

		for(SpecificationUnaire u : listRightUnaryOperators)
		{

			if(!map.containsKey(u.getRightSymbol()))
			{
				rightPart = " < "+u.getName()+"_RIGHT : "+ u.getLeftSymbol() +" >";
				map.put(u.getLeftSymbol(), u.getName()+"_RIGHT");
			}

			if(listBinaryOperators == null && listLeftUnaryOperators == null && u == listRightUnaryOperators.get(0) && !rightPart.equals(""))
				writer.println(" "+rightPart);
			else if(!rightPart.equals(""))
				writer.println("|"+rightPart);

		}
	}

	private void writeBinaryToken(PrintWriter writer)
	{
		for(SpecificationBinaire b : listBinaryOperators)
		{
			String part = "";
			
			if(!map.containsKey(b.getSymbol()))
			{
				part = " < "+b.getName()+"_OP : "+ b.getSymbol() +" >";
				map.put(b.getSymbol(), b.getName()+"_OP");
			}

			if(b == listBinaryOperators.get(0) && !part.equals("") )
				writer.println(" "+part);
			else if(!part.equals("")) 
				writer.println("|"+part);
		}
	}

	private void writeAllToken(PrintWriter writer)
	{
		writer.println("TOKEN :\n{");

		if(listBinaryOperators != null)
			writeBinaryToken(writer);

		if(listLeftUnaryOperators != null)
			writeLeftUnaryToken(writer);

		if(listRightUnaryOperators != null)
			writeRightUnaryToken(writer);

		if(listUnaryOperators != null)
			writeUnaryToken(writer);

		if(listPrimaryOperators != null)
			writePrimaryToken(writer);

		writer.println("}");
	}

	public void generateGrammar() throws FileNotFoundException, UnsupportedEncodingException
	{
		File file = new File("grammaire2.jj");
		try {
			//file.mkdirs();
			file.createNewFile();
			System.out.println("TEST1");
		} catch (IOException e1) {
			System.out.println("doizqkdoqid");
			e1.printStackTrace();
		}

		PrintWriter writer;
		try {
			writer = new PrintWriter(file);
			System.out.println("TEST2");
		} catch (FileNotFoundException e) {
			System.out.println("BLABLABLABLA");
			e.printStackTrace();
			return;
		}
		writer.println("PARSER_BEGIN(G2)");
		writer.println("import java.util.ArrayList;");
		writer.println("public class G2 {");
		writer.println("\tpublic static void main(String args[]) throws ParseException {");
		writer.println("\t\tG2 parser = new G2(System.in);");
		writer.println("\t\tparser.Axiome();");
		writer.println("\t}");
		writer.println("}");
		writer.println("\nPARSER_END(G2)\n");

		writer.println("SKIP :\n{");
		writer.println("  \" \"\n| \"\\t\"");	 // les caractères espace " " et \t 
		writer.println("| \"\\n\"");			// le caractère \n
		writer.println("| \"\\r\"\n}");			// le caractère \r


		writeAllToken(writer);

		writer.println("\nvoid Epsilon() : { } { {System.out.println(\"Epsilon\");} }\n");//défini une transition epsilon

		writeAllRules(writer);

		writer.close();

	}


}
