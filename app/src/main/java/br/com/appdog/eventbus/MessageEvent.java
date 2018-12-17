package br.com.appdog.eventbus;

import android.view.View;

public class MessageEvent {

    public String connection;
    public View mView;

    public MessageEvent(final String message, final View view) {
        this.connection = message;
        this.mView = view;
    }
}
