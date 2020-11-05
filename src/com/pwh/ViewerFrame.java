package com.pwh;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class ViewerFrame extends JFrame {
    private int width = 800;
    private int height = 600;
    JLabel lable=new JLabel();
    ViewerService service=ViewerService.getInstance();
    // 加给菜单的事件监听器
    ActionListener menuListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            service.menuDo(ViewerFrame.this, e.getActionCommand());
        }
    };
public ViewerFrame(){
    super();
    init();
}

    public void init(){
        KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
        manager.addKeyEventPostProcessor(this.getMyKeyEventHandler());
        this.setTitle("Gallery");
        this.setPreferredSize(new Dimension(width,height));
        createMenuBar();
        JPanel toolBar=createToolPanel();
        this.add(toolBar,BorderLayout.NORTH);
        this.add(new JScrollPane(lable),BorderLayout.CENTER);

        this.setVisible(true);
        this.pack();


    }
    public KeyEventPostProcessor getMyKeyEventHandler(){
    return new KeyEventPostProcessor(){
        @Override
        public boolean postProcessKeyEvent(KeyEvent e) {
            if(e.getKeyCode()==KeyEvent.VK_0){
                System.out.println("======== ======== ========");
            }
            return true;
        }
    };
    }
    public JLabel getLabel(){

        return lable;
    }
    public JPanel createToolPanel(){
        JPanel panel = new JPanel();
        JToolBar toolBar = new JToolBar("工具");
        toolBar.setFloatable(false);
        panel.setLayout(new FlowLayout(FlowLayout.LEFT));
        String[] toolarr = { "com.pwh.action.OpenAction",
                "com.pwh.action.LastAction",
                "com.pwh.action.NextAction",
                "com.pwh.action.BigAction",
                "com.pwh.action.SmallAction" };
        for (int i = 0; i < toolarr.length; i++) {
            ViewerAction action = new ViewerAction(new ImageIcon("img/"
                    + toolarr[i] + ".gif"), toolarr[i], this);
            // 以图标创建一个新的button
            JButton button = new JButton(action);
            // 把button加到工具栏中
            toolBar.add(button);
        }
        panel.add(toolBar);
        return panel;
    }
    public void createMenuBar(){
        JMenuBar menuBar = new JMenuBar();
        String[] menuArr={"文件(F)","工具(T)","帮助(H)"};
        String[][] menuItemArr={
                {"打开(O)","-","退出(X)"},
                {"放大(M)","缩小(O)","-","上一个(X)","下一个(P)"},
                {"帮助主题","关于"}
        };
        for (int i = 0; i < menuArr.length; i++) {
            JMenu menu = new JMenu(menuArr[i]);
            for (int j = 0; j < menuItemArr[i].length; j++) {
                if(menuItemArr[i][j].equals("-")){
                    menu.addSeparator();
                }else{
                    JMenuItem menuItem = new JMenuItem(menuItemArr[i][j]);
                    //
                    //
                    menuItem.addActionListener(menuListener);
                    menu.add(menuItem);

                }

            }
            menuBar.add(menu);
        }
this.setJMenuBar(menuBar);
    }

}
