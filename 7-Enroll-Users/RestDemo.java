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

		/*Duration duration = new Duration();
	  Availability availability = new Availability(true);

	  LocalDateTime start = LocalDateTime.now();
	  LocalDateTime end = start.plusMonths(3);

	  duration.setType("DateRange");
	  duration.setStart(start + "Z");
	  duration.setEnd(end + "Z");

	  availability.setDuration(duration);
	  availability.setAvailable("Yes");

		Term tempTm = new Term();

	  tempTm.setExternalId(RestConstants.TERM_ID);
	  tempTm.setName(RestConstants.TERM_NAME);
	  tempTm.setDescription(RestConstants.TERM_DESCRIPTION);
	  tempTm.setDataSourceId(datasource.getId());
	  tempTm.setAvailability(availability);

	  Term term = ts.create(tempTm);

	  log.info("Term: " + term.toString());*/

		/* Part Four - Create an Instructor */
		Term term = ts.read("externalId:" + RestConstants.TERM_ID);

		UserService us = new UserService();

		/* Availability availability = new Availability();
		availability.setAvailable("Yes");

		Contact contact = new Contact();
		contact.setEmail(RestConstants.USER_PROF_EMAIL);

		Name name = new Name();
		name.setGiven(RestConstants.USER_PROF_GIVEN);
		name.setFamily(RestConstants.USER_PROF_FAMILY);

		User tempPr = new User();
		tempPr.setExternalId(RestConstants.USER_PROF_ID);
		tempPr.setDataSourceId(datasource.getId());
		tempPr.setUserName(RestConstants.USER_PROF_NAME);
		tempPr.setPassword(RestConstants.USER_PROF_PASS);
		tempPr.setAvailability(availability);
		tempPr.setContact(contact);
		tempPr.setName(name);

		User prof = us.create(tempPr);

		log.info("Professor:" + prof.toString()); */

		/* Part Five - Create the Student */
		User prof = us.read("externalId:" + RestConstants.USER_PROF_ID);

		/*Availability availability = new Availability();
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

		log.info("Student:" + stud.toString());*/

		/* Part Six - Create A Course */
		User stud = us.read("externalId:" + RestConstants.USER_STUD_ID);

		CourseService cs = new CourseService();

		/*Duration duration = new Duration();
	  Availability availability = new Availability(true);

	  LocalDateTime start = LocalDateTime.now();
	  LocalDateTime end = start.plusMonths(3);

	  duration.setType("DateRange");
	  duration.setStart(start + "Z");
	  duration.setEnd(end + "Z");

	  availability.setDuration(duration);
	  availability.setAvailable("Yes");

		Course tempCs = new Course();
		tempCs.setExternalId(RestConstants.COURSE_ID);
		tempCs.setCourseId(RestConstants.COURSE_ID);
		tempCs.setName(RestConstants.COURSE_NAME);
		tempCs.setDescription(RestConstants.COURSE_DESCRIPTION);
		tempCs.setDataSourceId(datasource.getId());
		tempCs.setTermId(term.getId());
		tempCs.setAvailability(availability);

		Course course = cs.create(tempCs);

		log.info("Course:" + course.toString());*/

		/* Part Seven - Enroll Users */
		Course course = cs.read("externalId:" + RestConstants.COURSE_ID);

		MembershipService ms = new MembershipService();

		Availability availability = new Availability();
		availability.setAvailable("Yes");

		Membership tempPM = new Membership();
		tempPM.setDataSourceId(datasource.getId());
		tempPM.setAvailability(availability);
		tempPM.setCourseRoleId("Instructor");

		Membership pMembership = ms.create(tempPM, prof.getId(), course.getId());

		log.info("Professor Membership:" + pMembership.toString());

		Membership tempSM = new Membership();
		tempSM.setDataSourceId(datasource.getId());
		tempSM.setAvailability(availability);
		tempSM.setCourseRoleId("Student");

		Membership sMembership = ms.create(tempSM, stud.getId(), course.getId());

		log.info("Student Membership:" + sMembership.toString());

	}
}
