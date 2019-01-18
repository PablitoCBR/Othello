package com.Othello.Game.Helpers;

public class FieldsStatusCloner {
    public static FieldsStatusCloner instance = null;

    private FieldsStatusCloner(){}

    public static FieldsStatusCloner getInstanceFieldsStatusCloner(){
        if(instance == null)
            instance = new FieldsStatusCloner();
        return instance;
    }

    public byte[][] cloneFieldsStatus(byte[][] fieldsStatus){
        byte[][] cloned = new byte[8][8];
        for(int i=0; i<8; i++){
            for(int j=0; j<8; j++){
                cloned[i][j] = fieldsStatus[i][j];
            }
        }
        return  cloned;
    }
}
