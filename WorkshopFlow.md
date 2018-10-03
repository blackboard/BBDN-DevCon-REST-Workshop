# Workshop Flow


1. Register for Key and Secret
  Visit the Developer Community site to discover the process by which you [register for a key and secret](https://community.blackboard.com/docs/DOC-1579) in the [Developer Portal](https://developer.blackboard.com).

2. Create User with the following entitlements
 * course.calendar-entry.CREATE
 * course.configure-properties.EXECUTE
 * course.content.CREATE
 * course.content.MODIFY
 * course.gradebook.MODIFY
 * course.user-enroll.EXECUTE
 * course.user-role.MODIFY
 * system.announcements.CREATE
 * system.course.CREATE
 * system.course.VIEW
 * system.datasource.manager.VIEW
 * system.enrollment.CREATE
 * system.term.MODIFY
 * system.user.CREATE

3. Register Application with Learn
  Visit the Developer Community site to discover the process by which you [register your application with Blackboard Learn](https://community.blackboard.com/docs/DOC-1580-managing-rest-integrations-in-learn-the-rest-integrations-tool-for-system-administrators).

4. Configure Project [RestConfig.java](src/main/java/bbdn/rest/RestConfig.java)
 * host domain
 * key
 * secret
 * secure

5. Get a token
```java
  Authorizer authorizer = new Authorizer();

  authorizer.authorize();

  log.info("Token: " + CacheUtil.getValidToken());
```
5. Create a datasource
```java
  DatasourceService dss = new DatasourceService();
  Datasource tempDs = new Datasource();

  tempDs.setExternalId(RestConstants.DATASOURCE_ID);
  tempDs.setDescription(RestConstants.DATASOURCE_DESCRIPTION);

  Datasource datasource = dss.create(tempDs);

  log.info("Datasource: " + datasource.toString());
```
6. Create a term
```java
  Datasource datasource = dss.read("externalId:" + RestConstants.DATASOURCE_ID);

  TermService ts = new TermService();
  Term tempTm = new Term();

  Duration duration = new Duration();
  Availability availability = new Availability();

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
```
7. Create an Instructor
```java
  Term term = ts.read("externalId:" + RestConstants.TERM_ID);

  UserService us = new UserService();

  Availability availability = new Availability();
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

  log.info("Professor:" + prof.toString());
```
8. Create a Student
```java
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
```
9. Create a course
```java
  User stud = us.read("externalId:" + RestConstants.USER_STUD_ID);

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
```
10. Create memberships
```java
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
```
11. Create a folder
```java
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

  Availability availability = new Availability();
  availability.setAvailable("Yes");

  ContentHandler contentHandler = new ContentHandler();
  contentHandler.setId(RestConstants.CONTENT_FOLDER_HANDLER);

  Content newFolder = new Content(true);
  newFolder.setTitle(RestConstants.CONTENT_FOLDER_TITLE);
  newFolder.setAvailability(availability);
  newFolder.setContentHandler(contentHandler);
  newFolder.setParentId(parentId);
  newFolder.setDescription(RestConstants.CONTENT_FOLDER_DESCRIPTION);

  Content folder = conS.createChildContent(newFolder, course.getId(), parentId);

  log.info("Folder:" + folder.toString());
```
12. Upload an attachment and create an assignment
```java
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

  String xid = "";

  try {
    xid = FileUploadUtil.uploadSingleFile();
  } catch(Exception e) {
    log.error("Exception uploading file");
  }

  log.info("XID:" + xid);

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
  newAssignment.setFileUploadIds(new String[] {xid});
  newAssignment.setAvailability(availability);
  newAssignment.setGrading(grading);
  newAssignment.setScore(score);

  Assignment assignment = aS.create(newAssignment, course.getId());

  log.info("Assignment:" + assignment.toString());
```
13. Create a course calendar event to remind the student of the due date
```java
  CalendarService calS = new CalendarService();

  LocalDateTime now = LocalDateTime.now();
  LocalDateTime start = now.plusMonths(3);
  LocalDateTime end = start.plusHours(1);

  Calendar newCal = new Calendar();
  newCal.setType("Course");
  newCal.setCalendarId(course.getId());
  newCal.setTitle(RestConstants.CALENDAR_TITLE);
  newCal.setDescription(RestConstants.CALENDAR_DESCRIPTION);
  newCal.setStart(start + "Z");
  newCal.setEnd(end + "Z");

  Calendar calendar = calS.create(newCal);

  log.info("Calendar:" + calendar.toString());
```
14. Create a system announcement
```java
  AnnouncementService annS = new AnnouncementService();

  LocalDateTime start = LocalDateTime.now();
  LocalDateTime end = start.plusMonths(3);

  Duration duration = new Duration();
  duration.setType("Restricted");
  duration.setStart(start + "Z");
  duration.setEnd(end + "Z");

  AnnouncementAvailability availability = new AnnouncementAvailability();
  availability.setDuration(duration);

  Announcement newAnn = new Announcement();
  newAnn.setTitle(RestConstants.ANNOUNCEMENT_TITLE);
  newAnn.setBody(RestConstants.ANNOUNCEMENT_BODY);
  newAnn.setShowAtLogin(false);
  newAnn.setShowInCourses(true);
  newAnn.setAvailability(availability);

  log.info("NEWANN:" + newAnn.toString());

  Announcement announcement = annS.create(newAnn);

  log.info("Announcement:" + announcement.toString());
```
15. Get Column
```java
  ColumnService colS = new ColumnService();

  List<Column> columns = colS.readAll(course.getId());

  String columnId = "";

  ListIterator<Column> columnCursor = columns.listIterator();
  while(columnCursor.hasNext()) {
    Column currColumn = columnCursor.next();
    log.info("Current Column:" + currColumn.getName());

    if(currColumn.getName().equals(Rest Constants.ASSIGNMENT_TITLE)) {
      columnId = currColumn.getId();
    }
  }

  log.info("columnId:" + columnId);
```
