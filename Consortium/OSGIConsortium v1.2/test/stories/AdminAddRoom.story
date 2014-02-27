Narrative:
In order to assign rooms to timetable slots
As an administrator
I want to add new room

Scenario: Administrator add new room
Given a LectureHall2
And 100 capacity
When LectureHall2 is new
Then LectureHall2 is added to database