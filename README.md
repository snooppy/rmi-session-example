Project consists of two modules: client and server. Follow next steps for the simplest way to start the project:
- execute command in parent directory 'mvn package';
- go to server directory and type 'mvn assembly:assembly';
- to start server go to server/target directory and type 'java -jar server-1.0-SNAPSHOT-jar-with-dependencies.jar';
- to start client go to client/target deirectory and execute 'java -jar client-1.0-SNAPSHOT.jar'.

After starting server creates default database with default data. There are 10 users with logins test* and passwords qwerty*, where * is number (from 1 to 10). So, to login you can use any of these numbers. For instance, login: test1 | password: qwerty1.
