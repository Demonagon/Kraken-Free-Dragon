#!/bin/sh

./javacc-6.0/bin/javacc grammaire1.jj

mv G1.java ../../../src/model/initialisation/
mv G1Constants.java ../../../src/model/initialisation/
mv ParseException.java ../../../src/model/initialisation/
mv G1TokenManager.java ../../../src/model/initialisation/
mv SimpleCharStream.java ../../../src/model/initialisation/
mv Token.java ../../../src/model/initialisation/
mv TokenMgrError.java ../../../src/model/initialisation/

cd ../../../src/

javac model/initialisation/*.java

java model/initialisation/G1 < ../config/operators.cfg

java model/initialisation/Main

cd model/initialisation
mv *.class ../../../bin/model/initialisation/
cd ..
mv *.class ../../bin/model/

cd ../../res/model/initialisation
./javacc-6.0/bin/javacc grammaire2.jj

mv G2.java ../../../src/model/initialisation/
mv G2Constants.java ../../../src/model/initialisation/
mv ParseException.java ../../../src/model/initialisation/
mv G2TokenManager.java ../../../src/model/initialisation/
mv SimpleCharStream.java ../../../src/model/initialisation/
mv Token.java ../../../src/model/initialisation/
mv TokenMgrError.java ../../../src/model/initialisation/

cd ../../../src/

javac model/initialisation/*.java

java model/initialisation/G2 < ../config/rules.cfg

cd model/initialisation
mv *.class ../../../bin/model/initialisation/
cd ..
mv *.class ../../bin/model/


