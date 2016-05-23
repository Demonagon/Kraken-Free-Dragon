package rest.controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import controller.Application;
import rest.api.ServletPath;

@ServletPath("/api/formule/*")
@Path("/")
public class Formule 
{
	
	/*
	 * R�cup�re l'arbre g�n�r� dans l'Application, et g�n�re l'expression LaTeX y correspondant.
	 * Cette expression sera ensuite envoyee dans l'URI localhost:8080/api/formule/.
	 */
	@GET
	@Path("/")
	public String getFormule(){
		return "{\"latex\":\""+Application.getInstance().latex+"\"}";
	}
	
}
