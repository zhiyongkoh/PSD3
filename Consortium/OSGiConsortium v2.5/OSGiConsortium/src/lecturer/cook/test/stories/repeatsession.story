Narrative:
In order to repeat a session
As a Lecturer
I want to create multiple session at a fix interval

Scenario: Create repeated session
Given a list of session
When the use select a session with a session id 2
AND the system prompt to enter number of repeat for the session and the use enter 8
Then the system create all the session