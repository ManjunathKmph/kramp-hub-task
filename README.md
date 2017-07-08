# Kramp Hub Task Application

# Technical Points:
  * Programming Language - Java
  * Framework Used for REST Api - Spring Boot
  * Build Tool - Maven

# Prerequisites:
  * Install Java 8
  * Install Maven build tool

# Steps to run this application from browser:
  1. Clone the respository using this url (https://github.com/ManjunathKmph/kramp-hub-task.git).
  2. Run mvn spring-boot:run command from the project folder using command prompt/terminal.
  3. Hit the url (http://localhost:8888/task/search?q=java) in the browser to get the result for your search criteria against apple itune and google books api.
  4. Browser will ask for basic authentication credentials (Use Username: manajunath and Password: manjunath123).

# Api Usage:
  * In order to get the result for your specific term related to both music and album use q=britney or q=java to get the results.
  * In order to limit the response user limit=5 or limit=10.

# Api Url Examples:
  * http://localhost:8888/task/search?q=java -- Will fetch the results of 5 books and 5 albums. Default limit is 5.
  * http://localhost:8888/task/search?q=java&limit=10 -- Will fetch the results of 10 books and 10 albums.
  
# Steps to run this application from code:
  * Please refer the Example test class (src/test/java/com/manju/kramphub/task/KrampHubTaskRestTest.java) from this project.
