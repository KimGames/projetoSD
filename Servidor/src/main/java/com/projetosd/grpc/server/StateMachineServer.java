package com.projetosd.grpc.server;

import org.apache.ratis.proto.RaftProtos;
import org.apache.ratis.protocol.Message;
import org.apache.ratis.statemachine.TransactionContext;
import org.apache.ratis.statemachine.impl.BaseStateMachine;

import java.math.BigInteger;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;

public class StateMachineServer extends BaseStateMachine {

    private final HashMap<BigInteger, byte[]> dataBase;
    private final Map<String, String> key2values = new ConcurrentHashMap<>();

    public StateMachineServer(HashMap<BigInteger, byte[]> _dataBase) {
        this.dataBase = _dataBase;
    }

    @Override
    public CompletableFuture<Message> query(Message request) {

        /*Recupero minhas chaves*/
        final String[] opKey = request.getContent().toString(Charset.defaultCharset()).split(":");
        final String result = key2values.get(opKey[1]);

        /*Printo o meu resultado*/
        System.out.println(opKey[0] + ":" + opKey[1] + " = " + result);

        return CompletableFuture.completedFuture(Message.valueOf(result));
    }

    @Override
    public CompletableFuture<Message> applyTransaction(TransactionContext trx) {

        final RaftProtos.LogEntryProto entry = trx.getLogEntry();
        final String[] opKeyValue = entry.getStateMachineLogEntry().getLogData().toString(Charset.defaultCharset()).split(":");

        System.out.println(opKeyValue[0] + "-" + opKeyValue[1]);
        String result = "";

        switch (opKeyValue[0]) {
            case "set":
                /*Adiciono no Banco de dados RATIS*/
                result += opKeyValue[0]+ ":"+ key2values.put(opKeyValue[1], opKeyValue[2]);

                /*Adiciono no Banco de dados Padrão*/
                dataBase.put(BigInteger.valueOf(Long.parseLong(opKeyValue[1])), opKeyValue[2].getBytes());
                break;

            case "testandset":
                /*Adiciono no Banco de dados RATIS*/
                result += opKeyValue[0]+ ":"+ key2values.put(opKeyValue[1], opKeyValue[2]);

                /*Adiciono no Banco de dados Padrão*/
                dataBase.replace(BigInteger.valueOf(Long.parseLong(opKeyValue[1])), opKeyValue[2].getBytes());
                break;

            case "del":
                /*Adiciono no Banco de dados RATIS*/
                result += opKeyValue[0]+ ":"+ key2values.remove(opKeyValue[1]);

                /*Removo no Banco de dados Padrão*/
                dataBase.remove(BigInteger.valueOf(Long.parseLong(opKeyValue[1])));
                break;
        }

        final CompletableFuture<Message> f = CompletableFuture.completedFuture(Message.valueOf(result));
        final RaftProtos.RaftPeerRole role = trx.getServerRole();

        return f;
    }


}
