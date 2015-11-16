./compile.sh
cat essais.config | java G1
javac Main.java
java Main
./compile2.sh
java G2 < test.rules
