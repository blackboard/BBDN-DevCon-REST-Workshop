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
		
		
		Datasource ds = null;
		Term tm = null;

		
		boolean result = false;
		
		// Initialize Handlers
		DatasourceHandler datasourceHandler = new DatasourceHandler(_hostname);
		TermHandler termHandler = new TermHandler(_hostname);
		
		// Obtain a bearer token
		Authorizer auth = new Authorizer();
		Token token = auth.authorize();
		
		log.debug("token: " + token.toString());
	
		// Datasource object
		if( OPER_ALL || OPER_DATASOURCE) {
			ds = datasourceHandler.createObject(token.getToken());
			log.debug("Create DS: " + ds.toString());
			ds = datasourceHandler.readObject(token.getToken());
			log.debug("Read DS: " + ds.toString());
			ds = datasourceHandler.updateObject(token.getToken());
			log.debug("Update DS: " + ds.toString());
		}
		
		// Term object
		if( OPER_ALL || OPER_TERM) {
			tm = termHandler.createObject(token.getToken(), ds.getId());
			log.debug("Create TM: " + tm.toString());
			tm = termHandler.readObject(token.getToken());
			log.debug("Read TM: " + tm.toString());
			tm = termHandler.updateObject(token.getToken(), ds.getId());
			log.debug("Update TM: " + tm.toString());
		}
		

		
		// Delete objects	
		if( OPER_ALL || OPER_TERM) {
			result = termHandler.deleteObject(token.getToken());
			log.debug("Delete TM: " + result);
		}
		
		if( OPER_ALL || OPER_DATASOURCE) {
			result = datasourceHandler.deleteObject(token.getToken());
			log.debug("Delete DS: " + result);
		}
		

	}

}
