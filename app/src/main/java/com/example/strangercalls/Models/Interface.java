package com.example.strangercalls.Models;


import android.webkit.JavascriptInterface;

import com.example.strangercalls.Activities.NewCallingActivity;

public class Interface {
    NewCallingActivity callActivity;

    public Interface(NewCallingActivity callActivity){
        this.callActivity= callActivity;
    }
    @JavascriptInterface
    public void onPeerConnected(){
        callActivity.onPeerConnected();
    }


}
