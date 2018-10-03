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
		Datasource tempDs = new Datasource();
	  tempDs.setExternalId(RestConstants.DATASOURCE_ID);
	  tempDs.setDescription(RestConstants.DATASOURCE_DESCRIPTION);

	  Datasource datasource = dss.create(tempDs);

	  log.info("Datasource: " + datasource.toString());

	}

}
