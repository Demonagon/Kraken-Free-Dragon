package rest.controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import controller.Application;
import model.KrakenTree;
import rest.api.ServletPath;

@ServletPath("/api/transform/*")
@Path("/")
public class Transform 
{
	public KrakenTree tree = Application.getInstance().tree;
	
	/* 
	 * R�cup�re expId contenu dans l'URI localhost:8080/api/transform/%expId%, l'affiche et g�n�re l'expression y correspondant.
	 * M�thode de test.
	 */
	@GET
	@Path("/{expId}")
	public void demoMathJaxToJava(@PathParam("expId") String id) {
		System.out.println("MathJax a renvoye l'id :"+ id +".");
		System.out.println("Avec celle-ci, on recupere l'expression : "+ tree.getLocateExpression(tree.getRoot(), id).toString()+".");
		System.out.println("Son equivalent LaTeX est : "+ tree.getLocateExpression(tree.getRoot(), id).generateExpression(id.substring(3))+".");
	}
	
}