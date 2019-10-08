package bbdn.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import bbdn.caching.CacheUtil;

import bbdn.rest.announcement.*;
import bbdn.rest.assignment.*;
import bbdn.rest.calendar.*;
import bbdn.rest.column.*;
import bbdn.rest.common.*;
import bbdn.rest.content.*;
import bbdn.rest.course.*;
import bbdn.rest.datasource.*;
import bbdn.rest.membership.*;
import bbdn.rest.oauth.*;
import bbdn.rest.term.*;
import bbdn.rest.user.*;
import bbdn.rest.util.*;

import bbdn.rest.RestConfig;
import bbdn.rest.RestConstants;

public class RestDemo {

	private static final Logger log = LoggerFactory.getLogger(RestDemo.class);

	public static void main(String[] args) {

		log.info("Hello REST Workshop Participant!!!");

		/* Part One - Get Authorization Token */
		Authorizer authorizer = new Authorizer();

	  	authorizer.authorize();

		log.info("Token: " + CacheUtil.getValidToken());

		/* Part Two - Get Datasource Key */
	  	DatasourceService dss = new DatasourceService();

		/* Part Three - Get Term */
		Datasource datasource = dss.read("externalId:" + RestConstants.DATASOURCE_ID);

		TermService ts = new TermService();

		/* Part Four - Get  Instructor */
		Term term = ts.read("externalId:" + RestConstants.TERM_ID);

		UserService us = new UserService();

		/* Part Five - Create the Student */
		User prof = us.read("externalId:" + RestConstants.USER_PROF_ID);

		Availability availability = new Availability();
		availability.setAvailable("Yes");

		Contact contact = new Contact();
		contact.setEmail(RestConstants.USER_STUD_EMAIL);

		Name name = new Name();
		name.setGiven(RestConstants.USER_STUD_GIVEN);
		name.setFamily(RestConstants.USER_STUD_FAMILY);

		User tempSt = new User();
		tempSt.setExternalId(RestConstants.USER_STUD_ID);
		tempSt.setDataSourceId(datasource.getId());
		tempSt.setUserName(RestConstants.USER_STUD_NAME);
		tempSt.setPassword(RestConstants.USER_STUD_PASS);
		tempSt.setAvailability(availability);
		tempSt.setContact(contact);
		tempSt.setName(name);

		User stud = us.create(tempSt);

		log.info("Student:" + stud.toString());

	}
}
