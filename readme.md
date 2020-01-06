Groupe:
- Nom1 Prenom1 (to be adapted)
- Nom2 Prenom2 (to be adapted)

Copyright (C) 2016-2020
Contact: Denis.Conan[at]telecom-sudparis.eu

Structure du projet:
$ tree
.
├── AUTHORS              // vos noms et prénoms
├── Code                 // le code du logiciel dans un module Maven
├── LICENSE              // la lisence (GPL)
├── Modelisation         // le dossier de modélisation avec sources + PDF
├── readme.md            // ce fichier
└── Suivi
    └── readme.md        // les messages échangés lors du suivi entre séances

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
$
export JAVA_HOME=<the root directory of your Java installation>
export CLASSPATH=$JAVA_HOME/lib

Before using the Maven module:
------------------------------
Modify the line 
 <artifactId>csc4102-prenom1Nom1-prenom2Nom2</artifactId>
in the file Code/pom.xml

Compilation and installation:
-----------------------------
	To compile and install the modules, execute the following command.
$
(cd Code; mvn install)
$
	Use the following options if you do not want to compile and execute
        the JUnit tests and do not want to generate the JavaDoc files.
        Then, the build process is much more rapid.
$
(cd Code; mvn install -Dmaven.test.skip=true -DskipTests -Dmaven.javadoc.skip=true -Dcheckstyle.skip)
$

In Eclipse:
-----------
	To load the project in Eclipse, either use the maven plugin (File >
	Import > Maven > Existing maven project), or create the Eclipse project
	using the following command and then create a Java project in Eclipse:
$
(cd Code; mvn eclipse:clean eclipse:eclipse)
$
