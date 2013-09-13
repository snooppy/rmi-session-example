Project consist of client and server. Follow next steps for the simplest way to start the project:
- execute command in parent directory 'mvn package';
- go to server directory and type 'mvn assembly:assembly';
- to start server go to server/target directory and type 'java -jar server-1.0-SNAPSHOT-jar-with-dependencies.jar';
- to start client go to client/target deirectory and execute 'java -jar client-1.0-SNAPSHOT.jar'.
