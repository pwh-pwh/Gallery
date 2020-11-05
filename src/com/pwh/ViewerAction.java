package com.pwh;

import com.pwh.action.Action;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class ViewerAction extends AbstractAction{
    private Action action;
    private String actionName="";
    private ViewerFrame frame=null;

    public ViewerAction(){
        super();
    }
    public ViewerAction(ImageIcon icon, String actionName, ViewerFrame frame) {
        // 调用父构造器
        super("", icon);
        this.actionName = actionName;
        this.frame = frame;
    }
private Action getAction(String actionName){
    try{
        Action action = (Action)Class.forName(actionName).newInstance();
         this.action=action;
    }catch(Exception e){
        e.printStackTrace();
        return null;
    }
    return action;
}

public void actionPerformed(ActionEvent e){
    ViewerService service = ViewerService.getInstance();
    Action action=getAction(this.actionName);
    action.execute(service,frame);


}
}
