# Greenmail-Gui
An gui helper for testing/viewing email messages during development of an application.

## Development Setup
To get a development environment setup for this project, you can either follow the "Local Deployment" steps or use the "Docker Deployment" steps below.

### Prerequisites (Docker Local Deployment)
- [Docker 4.21](https://www.docker.com/)
- Java OpenJDK 19
- [Apache Maven 3.9](https://maven.apache.org/index.html)

### Initial setup (Docker Local Deployment)
- To perform the initial build of the docker containers and the creation of the dev SSL certs, run the following:
```
mvn clean install docker:build docker:start
```
Some notes:
- You should be able to start/stop/restart the docker containers after this initial build using the normal docker tools/command line.
- The app server exposes several volumes into the project base folder, if your IDE of choice compiles classes to "target/classes" then they will automatically be redeployed into the running tomcat docker container.  Similarly, changes made to the project's HTML/CSS/JS files are automatically picked up by the running docker instance.  The app server logs are available under the "logs" folder in the project base folder.  If you wish to make changes to the tomcat server.xml file, a copy of it is under the "conf" folder in the project base and will be copied to the container on the next "docker:build" maven command.
- This app will automatically register itself into the "hex-network" Docker network so the hex app related containers can communicate with it.
- (Optional) Configure your IDE to use the Checkstyle file in the root of the project (checkstyle.xml).  The checkstyle checks can also be run via maven:
```
mvn checkstyle:check
```
- That's it! You should be able to see the application running at "https://localhost:18443/greenmail-gui/".


### Prerequisites (Non-Docker Local Deployment)
- Java OpenJDK 19
- [Apache Tomcat 10.1](https://tomcat.apache.org/)
- [Apache Maven 3.9](https://maven.apache.org/index.html)

### Initial setup (Non-Docker Local Deployment)
- To initialize the project, generate dev SSL keystore, run the following:
```
mvn clean install
```
- Add the project to your Tomcat server.xml for the hex project by adding this context tag after the hex one:
```
<Context docBase="greenmail-gui" path="/greenmail-gui" reloadable="true"/>
```
- (Optional) Configure your IDE to use the Checkstyle file in the root of the project (checkstyle.xml).  The checkstyle checks can also be run via maven:
```
mvn checkstyle:check
```
- That's it! You should be able to see the application running at "https://localhost:8443/greenmail-gui/".
