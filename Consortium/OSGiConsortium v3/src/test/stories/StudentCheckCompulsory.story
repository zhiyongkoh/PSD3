Narrative:
In order to not fail the course,
As a student
I want to check that I have signed up for all compulsory sessions.
					 
Scenario: Check for all compulsory sessions
Given a list of timetable slots which belong to student 51
When all the compulsory timetable slots are booked
Then student will not fail the course is true