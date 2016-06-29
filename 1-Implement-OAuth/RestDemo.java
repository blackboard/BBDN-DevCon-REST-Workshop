package bbdn.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import bbdn.rest.objects.*;
import bbdn.rest.services.*;


		
public class RestDemo {

	private static final Logger log = LoggerFactory.getLogger(RestDemo.class);
	
	public static boolean DEVMODE = false;
	
	public static void main(String[] args) {

		boolean OPER_ALL = false;
		boolean OPER_DATASOURCE = false;
		boolean OPER_TERM = false;
		boolean OPER_COURSE = false;
		boolean OPER_USER = false;
		boolean OPER_MEMBERSHIP = false;
		boolean OPER_CONTENT = false;
		boolean OPER_GRADES = false;
		
		String _hostname = RestConstants.HOSTNAME;
		
		int operCount = 0;
		boolean nextCommand = false;
		int uriCount = 0;
		boolean nextUri = false;
		
		if(args.length > 0) {
			for(int i = 0; i < args.length; i++ ) {
				log.info("args[" + i + "]: " + args[i]);
				
				if (nextCommand) {
					switch(args[i]) {
					case "datasource":
						OPER_DATASOURCE = true;
						break;
					case "term":
						OPER_TERM = true;
						break;
					case "course":
						OPER_COURSE = true;
						break;
					case "user":
						OPER_USER = true;
						break;
					case "membership":
						OPER_MEMBERSHIP = true;
						break;
					case "content":
						OPER_CONTENT = true;
						break;
					case "grades":
						OPER_GRADES = true;
					case "all":
					default:
						OPER_ALL = true;
					}
					nextCommand = false;
				}
				
				if(nextUri) {
					_hostname = args[i];
					nextUri = false;
				}
				
				if (args[i].equalsIgnoreCase("-c")) {
					nextCommand = true;
					operCount += 1;
				} 
				else if (args[i].equalsIgnoreCase("-t")) {
					nextUri = true;
					uriCount += 1;
				}
				else if (args[i].equalsIgnoreCase("-d")) {
					DEVMODE = true;
				}
			}
			if(operCount == 0) { 	
				OPER_ALL = true;
			}
			log.info(" OPER_ALL: " + OPER_ALL + 
					 " OPER_DATASOURCE: " + OPER_DATASOURCE +
					 " OPER_TERM: " + OPER_TERM +
					 " OPER_COURSE: " + OPER_COURSE +
					 " OPER_USER: " + OPER_USER +
					 " OPER_MEMBERSHIP: " + OPER_MEMBERSHIP +
					 " OPER_CONTENT: " + OPER_CONTENT +
					 " OPER_GRADES: " + OPER_GRADES +
					 " HOSTNAME: " + _hostname + 
					 " operCount: " + operCount + 
					 " uriCount: " + uriCount);
		} else {
			OPER_ALL = true;
		}
	
		Membership mb = null;
		
		boolean result = false;
		
		// Obtain a bearer token
		Authorizer auth = new Authorizer();
		Token token = auth.authorize();
		
		log.info("token: " + token.toString());
	
	}

}
