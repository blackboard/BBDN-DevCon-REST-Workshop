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
import bbdn.rest.attachment.*;
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

		/* Part Six - Get Course */
		CourseService cs = new CourseService();

		Course course = cs.read("externalId:" + RestConstants.COURSE_ID);

		/* Part Seven - Enroll Users */

		/* Part Eight - Get Folder */
		ContentService conS = new ContentService();
		String parentId = "";

		List<Content> topLevel = conS.readAll(course.getId());

		log.info("Top Level:" + topLevel.toString());

		ListIterator<Content> contentCursor = topLevel.listIterator();
		while(contentCursor.hasNext()) {
			Content currContent = contentCursor.next();
			log.info("Current Cursor:" + currContent.getTitle());

			if(currContent.getTitle().equals("Content") && currContent.getContentHandler().getId().equals("resource/x-bb-folder")) {
				parentId = currContent.getId();
			}
		}

		log.info("ParentId:" + parentId);

		/* Part 9 - Create an assignment */
		List<Content> myFolders = conS.readAllChildContent(course.getId(), parentId);

		log.info("My Folder:" + myFolders.toString());

		String folderId = "";

		ListIterator<Content> folderCursor = myFolders.listIterator();
		while(folderCursor.hasNext()) {
			Content currContent = folderCursor.next();
			log.info("Current Folder:" + currContent.getTitle());

			if(currContent.getTitle().equals(RestConstants.CONTENT_FOLDER_TITLE)) {
				folderId = currContent.getId();
			}
		}

		log.info("folderId:" + folderId);

		// Create the Assignment
		AssignmentService aS = new AssignmentService();

		LocalDateTime start = LocalDateTime.now();
	  	LocalDateTime end = start.plusMonths(3);

		AdaptiveRelease adaptiveRelease = new AdaptiveRelease();
		adaptiveRelease.setStart(start + "Z");
		adaptiveRelease.setEnd(end + "Z");

		Availability availability = new Availability(false,true);
		availability.setAvailable("Yes");
		availability.setAdaptiveRelease(adaptiveRelease);

		Grading grading = new Grading();
		grading.setDue(end + "Z");
		grading.setAttemptsAllowed(10);
		grading.setIsUnlimitedAttemptsAllowed(false);

		Score score = new Score();
		score.setPossible(100);

		NewAssignment newAssignment = new NewAssignment();
		newAssignment.setParentId(folderId);
		newAssignment.setTitle(RestConstants.ASSIGNMENT_TITLE);
		newAssignment.setInstructions(RestConstants.ASSIGNMENT_INSTRUCTIONS);
		newAssignment.setDescription(RestConstants.ASSIGNMENT_DESCRIPTION);
		newAssignment.setAvailability(availability);
		newAssignment.setGrading(grading);
		newAssignment.setScore(score);

		Assignment assignment = aS.create(newAssignment, course.getId());

		log.info("Assignment:" + assignment.toString());

	}
}
