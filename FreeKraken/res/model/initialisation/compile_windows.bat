.\javacc-6.0\bin\javacc.exe grammaire1.jj

move G1.java ..\..\..\src\model\initialisation\
move G1Constants.java ..\..\..\src\model\initialisation\
move ParseException.java ..\..\..\src\model\initialisation\
move G1TokenManager.java ..\..\..\src\model\initialisation\
move SimpleCharStream.java ..\..\..\src\model\initialisation\
move Token.java ..\..\..\src\model\initialisation\
move TokenMgrError.java ..\..\..\src\model\initialisation\

cd ..\..\..\src\

javac.exe model\initialisation\*.java

java.exe model\initialisation\G1 < ..\config\operators.cfg

java.exe model\initialisation\Main

cd model\initialisation
move *.class ..\..\..\bin\model\initialisation\
cd ..
move *.class ..\..\bin\model\

cd ..\..\res\model\initialisation
.\javacc-6.0\bin\javacc.exe grammaire2.jj

move G2.java ..\..\..\src\model\initialisation\
move G2Constants.java ..\..\..\src\model\initialisation\
move ParseException.java ..\..\..\src\model\initialisation\
move G2TokenManager.java ..\..\..\src\model\initialisation\
move SimpleCharStream.java ..\..\..\src\model\initialisation\
move Token.java ..\..\..\src\model\initialisation\
move TokenMgrError.java ..\..\..\src\model\initialisation\

cd ..\..\..\src\

javac.exe model\initialisation\*.java

java.exe model\initialisation\G2 < ..\config\rules.cfg

cd model\initialisation
move *.class ..\..\..\bin\model\initialisation\
cd ..
move *.class ..\..\bin\model\


