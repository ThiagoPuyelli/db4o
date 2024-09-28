mkdir -p salida

javac -cp "db4o-8.0.249.16098-all-java5.jar" -d salida src/server/Server.java src/utils/*.java src/entidades/*.java

java -cp "salida:db4o-8.0.249.16098-all-java5.jar" server.Server
