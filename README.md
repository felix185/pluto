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
- You need the junit.jar, Version 4.10, which you can find here: https://github.com/junit-team/junit4/wiki/Download-and-Install
- Compile the current source files (in particular, make sure that the compiled .class-Files are located at build/classes/...). Make sure the "test"-Folder is tagged as a ressource folder. You may need to include the junit library into the buildpath in order to compile the test correctly.
- Move the current Pluto.db in your Backend-Folder.
- Open the Commandline, navigate pluto's "Backend" folder and enter the command that you can download here: https://github.com/felix185/pluto/releases/tag/v0.2 Make sure to replace the `PATH_TO_JUNIT` to the jar file that you just downloaded.

## 5. Auto-Deployment
The project is automatically deployed only if you push a tagged commit. That exludes merge commits that are created by GitHub.
