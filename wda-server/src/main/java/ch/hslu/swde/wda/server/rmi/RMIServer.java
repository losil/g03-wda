package ch.hslu.swde.wda.server.rmi;

import ch.hslu.swde.wda.business.control.api.Control;
import ch.hslu.swde.wda.business.control.impl.ControlImpl;
import ch.hslu.swde.wda.business.init.DataParser;
import ch.hslu.swde.wda.business.init.RestClient;
import ch.hslu.swde.wda.domain.Customer;
import ch.hslu.swde.wda.domain.Operator;
import ch.hslu.swde.wda.domain.RestWdaData;
import ch.hslu.swde.wda.domain.WeatherData;
import ch.hslu.swde.wda.server.service.UpdateDaemon;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Properties;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class RMIServer {

	private static int rmiPort = 1099;
	private static String sourceUrl = "http://swde.el.eee.intern:8080/weatherdata-rws-provider/rest/weatherdata";
	private static final Logger LOGGER = LogManager.getLogger(RMIServer.class);

	private static Control control;

	static {
		try {
			control = new ControlImpl();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {

		/*
		 * Reading property file
		 */
		LOGGER.debug("Loading settings from property file");
		loadProperties();

		/*
		 * Creating remote object
		 */
		Control control = null;
		try {
			control = new ControlImpl();
		} catch (RemoteException e) {
			e.printStackTrace();
		}

		/*
		 * Loading initial Data
		 */
		LOGGER.info("Loading initial data");
		initializeData(sourceUrl);
		LOGGER.info("Initial Import to database is finished");

		/*
		 * Statrting daemon, which is updating the database every 15 minutes
		 */

		new UpdateDaemon(sourceUrl).start();

		/*
		 * create the registry
		 */

		try {
			Registry registry = LocateRegistry.createRegistry(rmiPort);
			registry.rebind(Control.RO_NAME, control);

			LOGGER.info("RMI Control started [" + InetAddress.getLocalHost().getHostAddress() + ":" + rmiPort + "]\n");

			LOGGER.info("Press Enter to shut down the server...");
			new Scanner(System.in).nextLine();

			registry.unbind(Control.RO_NAME);

			System.exit(0);

		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}

	}

	private static void initializeData(final String url) {
		ExecutorService executor = Executors.newFixedThreadPool(30);
		for (RestWdaData r : new RestClient(url).getAllWdaData()) {
			WeatherData w = new DataParser().parseRestWdaData(r);

			executor.execute(new Thread(() -> {
				try {
					control.addWeatherData(w);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}));
		}
		executor.shutdown();
		try {
			executor.awaitTermination(15, TimeUnit.MINUTES);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// InitalDaten für Operator & Customer

		Customer customer0 = new Customer(1, "HSLU", "Surstoffi", "21b", 6343, "Rotkreuz", "Hansjörg Diethelm",
				"0417576811", "education");
		Customer customer1 = new Customer(2, "CSS", "Seidenhofstrasse", "6", 6002, "Luzern", "Silvan Loser",
				"058 277 30 65", "enterprise");
		Customer customer2 = new Customer(3, "Leuchter IT Solutions AG", "Winkelriedstrasse", "45", 6003, "Luzern",
				"Timo Herzog", "0412265050", "enterprise");
		Customer customer3 = new Customer(4, "TailorIT AG", "Surentalstrasse", "10", 6210, "Sursee", "Simon Meier",
				"0412551510", "standard");
		Operator operator0 = new Operator(1, "Loser", "Silvan", "ialoser", "test_password0");
		Operator operator1 = new Operator(2, "Meier", "Simon", "iameier", "test_password1");
		Operator operator2 = new Operator(3, "Herzog", "Timo", "ibherzog", "test_password2");
		Operator operator3 = new Operator(4, "Ingold", "Lukas", "iaingold", "test_password3");
		Operator operator4 = new Operator(5, "Admin", "User", "admin.wda", "1234");

		try {
			if (control.findCustomerByID(customer0.getKundenNummer()) != null) {
				control.deleteCustomer(customer0);
				control.addCustomer(customer0);
			}
			if (control.findCustomerByID(customer1.getKundenNummer()) != null) {
				control.deleteCustomer(customer1);
				control.addCustomer(customer1);
			}
			if (control.findCustomerByID(customer2.getKundenNummer()) != null) {
				control.deleteCustomer(customer2);
				control.addCustomer(customer2);
			}
			if (control.findCustomerByID(customer3.getKundenNummer()) != null) {
				control.deleteCustomer(customer3);
				control.addCustomer(customer3);
			}
			if (control.findOperatorByID(operator0.getPersonalnummer()) != null) {
				control.deleteOperator(operator0);
				control.addOperator(operator0);
			}
			if (control.findOperatorByID(operator1.getPersonalnummer()) != null) {
				control.deleteOperator(operator1);
				control.addOperator(operator1);
			}
			if (control.findOperatorByID(operator2.getPersonalnummer()) != null) {
				control.deleteOperator(operator2);
				control.addOperator(operator2);
			}
			if (control.findOperatorByID(operator3.getPersonalnummer()) != null) {
				control.deleteOperator(operator3);
				control.addOperator(operator3);
			}
			if (control.findOperatorByID(operator4.getPersonalnummer()) != null) {
				control.deleteOperator(operator4);
				control.addOperator(operator4);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static void loadProperties() {
		File configFile = new File(RMIServer.class.getClassLoader().getResource("server.properties").getFile());

		try {
			FileReader reader = new FileReader(configFile);
			Properties props = new Properties();
			props.load(reader);

			rmiPort = Integer.valueOf(props.getProperty("rmiPort", "1099"));
			sourceUrl = props.getProperty("sourceUrl");
			LOGGER.debug("RMI-Port: " + props.getProperty("rmiPort"));
			LOGGER.debug("sourceURL: " + props.getProperty("sourceUrl"));
			reader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
