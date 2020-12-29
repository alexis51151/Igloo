Introduction:
-------------

This project is part of our Software Engineering formation at [Télécom SudParis](https://www.telecom-sudparis.eu/) engineering school. </br>
It simulates a hotel infrastructure with several use cases: give customers badges, activate their badges while staying in the hotel, create a new key on a badge each time there is a new occupant...


Groupe:
- [LE GLAUNEC ALexis](https://github.com/alexis51151)
- [CANTO Guillem](https://github.com/guillemollix)

Copyright (C) 2016-2020
Contact: alexis.le_glaunec@telecom-sudparis.eu

================================================================================

Software prerequisites:
-----------------------
	1. JAVA Version >= 9.0
	   (https://openjdk.java.net/install/index.html)
	2. Maven Version >= 3.0.4
	   (http://maven.apache.org/)

Shell variables to set in your ~/.bashrc file:
----------------------------------------------
	1. if JAVA is not installed from an archive file,

~~~
$ export JAVA_HOME=<the root directory of your Java installation>
$ export CLASSPATH=$JAVA_HOME/lib
~~~


Compilation and installation:
-----------------------------

To compile and install the modules, execute the following command.

~~~
$ (cd Code; mvn install)
~~~

Use the following options if you do not want to compile and execute
the JUnit tests and do not want to generate the JavaDoc files.
Then, the build process is much more rapid.

~~~
(cd Code; mvn install -Dmaven.test.skip=true -DskipTests -Dmaven.javadoc.skip=true -Dcheckstyle.skip)
~~~

In Eclipse:
-----------

To load the project in Eclipse, either use the maven plugin (File >
Import > Maven > Existing maven project), or create the Eclipse project
using the following command and then create a Java project in Eclipse:

~~~
(cd Code; mvn eclipse:clean eclipse:eclipse)
~~~
