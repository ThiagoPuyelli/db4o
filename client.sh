mkdir -p salidaCliente

javac -cp "db4o-8.0.249.16098-all-java5.jar" -d salidaCliente src/View/*.java src/Main.java src/utils/*.java src/entidades/*.java

java -cp "salidaCliente:db4o-8.0.249.16098-all-java5.jar" Main
