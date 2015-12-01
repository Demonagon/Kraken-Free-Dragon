
call javacc-6.0\bin\javacc.bat grammaire1.jj

move G1.java ..\..\..\src\model\initialisation\
move G1Constants.java ..\..\..\src\model\initialisation\
move ParseException.java ..\..\..\src\model\initialisation\
move G1TokenManager.java ..\..\..\src\model\initialisation\
move SimpleCharStream.java ..\..\..\src\model\initialisation\
move Token.java ..\..\..\src\model\initialisation\
move TokenMgrError.java ..\..\..\src\model\initialisation\

cd ..\..\..\src\

echo Compiling...

javac model\initialisation\*.java

echo Done.

java ..\bin\model\initialisation\G1 < ..\config\operators.cfg

java model\initialisation\Main

cd model\initialisation
move *.class ..\..\..\bin\model\initialisation\
cd ..
move *.class ..\..\bin\model\

cd ..\..\res\model\initialisation
javacc-6.0\bin\javacc.bat grammaire2.jj

move G2.java ..\..\..\src\model\initialisation\
move G2Constants.java ..\..\..\src\model\initialisation\
move ParseException.java ..\..\..\src\model\initialisation\
move G2TokenManager.java ..\..\..\src\model\initialisation\
move SimpleCharStream.java ..\..\..\src\model\initialisation\
move Token.java ..\..\..\src\model\initialisation\
move TokenMgrError.java ..\..\..\src\model\initialisation\

cd ..\..\..\src\

javac model\initialisation\*.java

java model\initialisation\G2 < ..\config\rules.cfg

cd model\initialisation
move *.class ..\..\..\bin\model\initialisation\
cd ..
move *.class ..\..\bin\model\


