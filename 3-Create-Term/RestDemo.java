package bbdn.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import bbdn.caching.CacheUtil;

import bbdn.rest.common.*;
import bbdn.rest.content.*;
import bbdn.rest.course.*;
import bbdn.rest.datasource.*;
import bbdn.rest.membership.*;
import bbdn.rest.oauth.*;
import bbdn.rest.term.*;
import bbdn.rest.user.*;

import bbdn.rest.RestConfig;
import bbdn.rest.RestConstants;

public class RestDemo {

	private static final Logger log = LoggerFactory.getLogger(RestDemo.class);

	public static boolean DEVMODE = false;

	public static void main(String[] args) {

		log.info("Hello REST Workshop Participant!!!");

		/* Part One - Get Authorization Token */
		Authorizer authorizer = new Authorizer();

	  authorizer.authorize();

		log.info("Token: " + CacheUtil.getValidToken());

		/* Part Two - Create Datasource Key */
	  DatasourceService dss = new DatasourceService();
		/*Datasource tempDs = new Datasource();
	  tempDs.setExternalId(RestConstants.DATASOURCE_ID);
	  tempDs.setDescription(RestConstants.DATASOURCE_DESCRIPTION);

	  Datasource datasource = dss.create(tempDs);

	  log.info("Datasource: " + datasource.toString());*/

		/* Part Three - Create a Term */
		Datasource datasource = dss.read("externalId:" + RestConstants.DATASOURCE_ID);

		TermService ts = new TermService();
		Term tempTm = new Term();

		Duration duration = new Duration();
	  Availability availability = new Availability(true);

	  LocalDateTime start = LocalDateTime.now();
	  LocalDateTime end = start.plusMonths(3);

	  duration.setType("DateRange");
	  duration.setStart(start + "Z");
	  duration.setEnd(end + "Z");

	  availability.setDuration(duration);
	  availability.setAvailable("Yes");

	  tempTm.setExternalId(RestConstants.TERM_ID);
	  tempTm.setName(RestConstants.TERM_NAME);
	  tempTm.setDescription(RestConstants.TERM_DESCRIPTION);
	  tempTm.setDataSourceId(datasource.getId());
	  tempTm.setAvailability(availability);

	  Term term = ts.create(tempTm);

	  log.info("Term: " + term.toString());

	}

}
