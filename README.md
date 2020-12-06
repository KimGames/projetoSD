# Parte 01 do Projeto de Sistemas Distribuidos - UFU

# Alunos:
  - Kim Ruan Lopes.
  - nº 11421BCC029.
  
  - Luiz Felipe Gonçalves Barbosa Viana
  - nº 11611ECP021  
  
  - Paulo José Carmona Teixeira
  - nº 11611ECP018
  
  - Windysson de Souza dezanete
  - nº 11511ECP018
  
 # Execução do Servidor
 mvn package exec:java -Dexec.mainClass=com.projetosd.grpc.server.CrudServer
 
 # Execução do Cliente
 mvn package exec:java -Dexec.mainClass=com.projetosd.grpc.client.CrudClient
