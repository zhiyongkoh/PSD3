Narrative:
In order to add a session to a course
As a Lecture
I want to add my session to a course

Scenario: Adding session to a course
Given a set of detail of a session
When the system list all the course, enter OS3 as course code which has a course id 3
AND the system list all the tutor, user select "cylon" with a tutor id 1
AND the select "WEEKLY" for session type and select 1 for compulsory
AND the system will auto generate a session id which is 2 and create the session
Then session is created