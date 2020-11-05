package com.pwh.action;

import com.pwh.ViewerFrame;
import com.pwh.ViewerService;

public class SmallAction implements Action{
    @Override
    public void execute(ViewerService service, ViewerFrame frame) {
        service.zoom(frame,false);
    }
}
