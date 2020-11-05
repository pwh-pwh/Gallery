package com.pwh.action;

import com.pwh.ViewerFrame;
import com.pwh.ViewerService;

public interface Action {
    void execute(ViewerService service, ViewerFrame frame);
}
