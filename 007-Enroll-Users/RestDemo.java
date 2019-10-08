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
		
		/* Part Four - Get the Instructor */
		UserService us = new UserService();

		User prof = us.read("externalId:" + RestConstants.USER_PROF_ID);

		/* Part Five - Get the Student */
		User stud = us.read("externalId:" + RestConstants.USER_STUD_ID);

		/* Part Six - Create A Course */
		CourseService cs = new CourseService();
		
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
