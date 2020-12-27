# Projeto de Sistemas Distribuidos - UFU

# Alunos:
  - Kim Ruan Lopes. nº 11421BCC029.
  - Luiz Felipe Gonçalves Barbosa Viana. nº 11611ECP021  
  - Paulo José Carmona Teixeira. nº 11611ECP018
  - Windysson de Souza dezanete. nº 11511ECP018
  
 # Comando antes dos servidores e clientes
   mvn package
  
 # Execução dos Servidores
  java -cp target\Servidor-1.0-SNAPSHOT-jar-with-dependencies.jar com.projetosd.grpc.server.CrudServer s1
  
  java -cp target\Servidor-1.0-SNAPSHOT-jar-with-dependencies.jar com.projetosd.grpc.server.CrudServer s2
  
  java -cp target\Servidor-1.0-SNAPSHOT-jar-with-dependencies.jar com.projetosd.grpc.server.CrudServer s3
 
 # Execução dos Clientes
  java -cp target\Servidor-1.0-SNAPSHOT-jar-with-dependencies.jar com.projetosd.grpc.client.CrudClient p1
  
  java -cp target\Servidor-1.0-SNAPSHOT-jar-with-dependencies.jar com.projetosd.grpc.client.CrudClient p2
  
  java -cp target\Servidor-1.0-SNAPSHOT-jar-with-dependencies.jar com.projetosd.grpc.client.CrudClient p3
