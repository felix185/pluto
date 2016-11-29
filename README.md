# pluto

started on: 04-10-2016
Programming language: Java
https://gitter.im/aswe-pluto/Lobby?utm_source=share-link&utm_medium=link&utm_campaign=share-link


## 1. Technologies
### Database
We use the serverless relational database SQLite 3.

### Backend
The backend is to be developed in JavaEE with the use of the jersey library and an Apache Tomcat instance.
The backend communicates with the frontend over http, it is supposed to be a RESTful web service. 

### Frontend
The CSS framework is Semantic UI. The JS framework is React.


## 2. Contributors:
- Alexander Schmalz
- Alexandra Hauser
- Dominik Hirt
- Felix Riess
- Felix Seidel

## 3. Branching
We use the 'develop' branch as our main branch for development. The master branch holds production-ready code.

## 4. Run Tests
You need the junit.jar, Version 4.10, which you can find here: https://github.com/junit-team/junit4/wiki/Download-and-Install
Also, you will need the latest TestRecipies.class, which you can find here:
Create the following folder structure in the "Backend"-Folder: build/classes/dhbw/pluto/recipes.
Paste the TestRecipies in the newly created folder.
Open the Commandline, navigate pluto's "Backend" folder and enter the following command
    java -cp .;<PATH_TO_JUNIT>;"WebContent/WEB-INF/lib/aopalliance-repackaged-2.5.0-b05.jar";"WebContent/WEB-INF/lib/asm-debug-all-
    5.0.4.jar";"WebContent/WEB-INF/lib/hk2-api-2.5.0-b05.jar";"WebContent/WEB-INF/lib/hk2-locator-2.5.0-        
    b05.jar";"WebContent/WEB-INF/lib/hk2-utils-2.5.0-b05.jar";"WebContent/WEB-INF/lib/javassist-3.20.0-GA.jar";"WebContent/WEB-
    INF/lib/javax.annotation-api-1.2.jar";"WebContent/WEB-INF/lib/javax.inject-2.5.0-b05.jar";"WebContent/WEB-
    INF/lib/javax.mail.jar";"WebContent/WEB-INF/lib/javax.servlet-api-3.0.1.jar";"WebContent/WEB-INF/lib/javax.ws.rs-api-
    2.0.1.jar";"WebContent/WEB-INF/lib/jaxb-api-2.2.7.jar";"WebContent/WEB-INF/lib/jersey-client.jar";"WebContent/WEB-INF/lib/jersey-
    common.jar";"WebContent/WEB-INF/lib/jersey-container-servlet-core.jar";"WebContent/WEB-INF/lib/jersey-container-
    servlet.jar";"WebContent/WEB-INF/lib/jersey-guava-2.23.2.jar";"WebContent/WEB-INF/lib/jersey-media-jaxb.jar";"WebContent/WEB-
    INF/lib/jersey-server.jar";"WebContent/WEB-INF/lib/org.json.jar";"WebContent/WEB-INF/lib/org.osgi.core-4.2.0.jar";"WebContent/WEB-
    INF/lib/osgi-resource-locator-1.0.1.jar";"WebContent/WEB-INF/lib/persistence-api-1.0.jar";"WebContent/WEB-INF/lib/sqlite-jdbc-
    3.14.2.1.jar";"WebContent/WEB-INF/lib/validation-api-1.1.0.Final.jar";"build/classes/" org.junit.runner.JUnitCore 
    dhbw.pluto.recipes.TestRecipes

This command is also availabe as a txt-File for better usage here:
Make sure to replace the path to the junit jar file that you just downloaded.
