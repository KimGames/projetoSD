package com.projetosd.grpc.resources;

public enum ActionsEnum {
    SET(0), GET(1), TESTANDSET(2), DEL(3);

    private final int valor;

    ActionsEnum(int _valor){
        valor = _valor;
    }

    public int getValor(){
        return valor;
    }

}
