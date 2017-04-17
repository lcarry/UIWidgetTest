package com.example.lcarry.messagetest;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ${lcarry} on 2017/4/12.
 */

public class Msg {
    public static final int TYPE_RECEIVED = 0;
    public static final int TYPE_SEND = 1;

    private String content;
    private int type;

    public Msg(String content, int type) {
        this.content = content;
        this.type = type;
    }

    public String getContent(){
        return content;
    }

    public int getType(){
        return type;
    }

    public static void getAllMsgs(List<Msg> msgs){
        for (int i = 0; i < 1; i++) {
            Msg msgRcv = new Msg("Hello!",Msg.TYPE_RECEIVED);
            msgs.add(msgRcv);

            Msg msgSend = new Msg("Hello,I'm here.",Msg.TYPE_SEND);
            msgs.add(msgSend);

            Msg msgRcv2 = new Msg("This is Petter.Nice talking to you.",Msg.TYPE_RECEIVED);
            msgs.add(msgRcv2);
        }
    }
}
