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

		Datasource datasource = dss.read("externalId:" + RestConstants.DATASOURCE_ID);

		/* Part Three - Get Term */
		TermService ts = new TermService();

		Term term = ts.read("externalId:" + RestConstants.TERM_ID);

		/* Part Four - Get Instructor */
		UserService us = new UserService();

		User prof = us.read("externalId:" + RestConstants.USER_PROF_ID);

		/* Part Five - Get the Student */
		User stud = us.read("externalId:" + RestConstants.USER_STUD_ID);

		/* Part Six - Create A Course */
		CourseService cs = new CourseService();

		Duration duration = new Duration();
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

		log.info("Course:" + course.toString());
	}
}
