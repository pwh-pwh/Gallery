package com.pwh.action;

import com.pwh.ViewerFrame;
import com.pwh.ViewerService;

public class BigAction implements Action{
    @Override
    public void execute(ViewerService service, ViewerFrame frame) {
        service.zoom(frame,true);
    }
}
