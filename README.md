## Museum of Biological History Project
## a QA Fundamental project by Thomas Marsh

This is the README for the solo fundamental project assigned by QA
It is a full-stack  CRUD Application that incorporates Front-End, Back-End and Database technologies, outlined below.

## Museum of Biological History Specimen App

## Contents

1. Resources
2. Brief
3. Kanban Board
4. Database Structure
5. Back-End
6. Front-End
7. Testing
9. Future Improvements
10. Author

## Resources

* Presentation [here] (https://docs.google.com/presentation/d/1nlJfxkTSXOafTHWEPHYNivG7cQQnfOEuYarV581kwyE/edit?usp=sharing)
* Jira [here] (https://qatestsite2.atlassian.net/secure/RapidBoard.jspa?rapidView=5&projectKey=QP&selectedIssue=QP-10&atlOrigin=eyJpIjoiNmM3MGMzYTNmMzFmNDBkNzg5OTk3MjU1OTVmMTliN2UiLCJwIjoiaiJ9)
* Back End Repo [https://github.com/TMarshQA/QAProjectMuseum.git]
* Front End Repo [https://github.com/TMarshQA/QAProjectMuseumFE.git]

# Brief

To create a CRUD application with the utilisation of supporting tools, methodologies and technologies that encapsulate all core modules covered during training.

This project will involve concepts from all core training modules; more specifically, this will involve:

- Project Management
- Databases
- Java SE
- Spring Boot
- Front-End Development
- Automated Testing

## Approach

In order to complete the task, I have decided to create an app for a fictional museum, based on the Natural History Museum
The app will allow the user to do the following:
* Create Records in the database 
* Read the records from the database, including creates ones
* Update existing records in the database
* Delete any records from the database.

The front-end will feature a clean, minimalistic aesthetic, based around usability and fast navigation

## Kanban Board

My board for this project was created in Jira and can be accessed here (https://qatestsite2.atlassian.net/secure/RapidBoard.jspa?rapidView=5&projectKey=QP&selectedIssue=QP-10&atlOrigin=eyJpIjoiNmM3MGMzYTNmMzFmNDBkNzg5OTk3MjU1OTVmMTliN2UiLCJwIjoiaiJ9)
 
## Database Structure

The project can be run using a MySQL database, or an h2 database by editing the code in the application.properties file (switching between "test" and "prod")
The h2 database is lightweight, temporary and best used for running tests.

# Back-End

The back-end is powered by Java using the Spring Boot Framework. This allows rapid and simple deployment of an integration structure between the database and the front-end. The back-end has the DB logic as well as the HTTP requests and allows the front-end to access the DB and work with the data there.
The back-end also incorporates use of MockMVC, Mockito and Selenium for testing

## Front-End

The front-end is powered by HTML5, CSS and JS, using Bootstrap for aesthetics. I also made use of Axios API to deal with requests.
I started out with a nature-themed webpage, but switched to a clean-cut page after deciding the natural theme was too distracting.
I decied that a quick-to-navigate page with minimalist aesthetics would be better for staff-use than a themed page.


## Testing

Total coverage came to 85.8%, which was expected. There are some in-progress features in the code that are not being used or tested, so this level of coverage is satisfactory.

I used MockMVC for Integration Testing, Mockito for Unit Testing and Selenium for Automated Testing. All tests can run independent of each other.


## Future Improvements

There are a number of improvements I would like to implement: 

* A user logon page/welcome page
* A last-edited-by column
* A warning modal activated by the delete button
* Filter by / search by functionality

## Author

I'd like to acknowledge Jordan Harrison and the other QA Trainers, plus 'Team 4' for the company/entertainment.

Project by Thomas Marsh






