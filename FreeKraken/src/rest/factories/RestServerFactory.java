package rest.factories;

import rest.api.RestServer;
import rest.controller.Formule;
import rest.controller.Transform;
import rest.impl.JettyRestServer;

/**
 * {@link RestServer} factory
 * @author Julien Prudhomme
 *
 */
public class RestServerFactory {
	public static RestServer createDefaultRestServer() {
		return createJettyRestServer();
	}
	
	public static JettyRestServer createJettyRestServer() {
		return new JettyRestServer();
	}
	
	/**
	 * Create and start a {@link RestServer} in a new {@link Thread}
	 * @param classes Classes that handle rest request.
	 * @return a started {@link RestServer}
	 */
	public static RestServer createAndStartRestServer(String impl, Class<?> ...classes) {
		final RestServer serv;
		switch(impl) {
		case "jetty": serv = createJettyRestServer(); break;
		default: throw new RuntimeException(impl + "doesn't exist !");
		}
		
		serv.initialize(classes);
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					serv.start(8080);
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					serv.stop();
				}
			}
		}).start();
		return serv;
	}
	
	public static RestServer createAndStartDefaultRestServer() {
		return createAndStartRestServer("jetty", //rest controllers classes
				Formule.class, Transform.class);
	}
}
