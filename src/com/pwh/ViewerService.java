package com.pwh;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ViewerService {
    private static ViewerService service = null;
    private ViewerFileChooser fileChooser=new ViewerFileChooser();
    //放大缩小比例
    private double range =0.2;
    private File currentDirectory = null;
    private List<File> currentFiles = null;
    private File currentFile = null;
    public static ViewerService getInstance(){
        if(service==null){
            service=new ViewerService();
        }

        return service;
    }
    private ViewerService(){

    }
    public void open(ViewerFrame frame){
        if (fileChooser.showOpenDialog(frame)==ViewerFileChooser.APPROVE_OPTION){
            this.currentFile=fileChooser.getSelectedFile();
            String name=this.currentFile.getPath();
            File cd=fileChooser.getCurrentDirectory();
            if(cd!=this.currentDirectory||this.currentDirectory==null){
                FileFilter[] fileFilters = fileChooser.getChoosableFileFilters();
                File[] files = cd.listFiles();
                this.currentFiles=new ArrayList<>();
                for (File file : files) {
                    for (FileFilter filter : fileFilters) {
                        if(filter.accept(file)){
                            this.currentFiles.add(file);
                        }
                    }
                }
            }
            ImageIcon icon = new ImageIcon(name);
            frame.getLabel().setIcon(icon);
        }

    }
    public void zoom(ViewerFrame frame,boolean isEnlarge){
        double enLargeRange = isEnlarge ? 1+range:1-range;
        ImageIcon icon =(ImageIcon)frame.getLabel().getIcon();
        if(icon!=null){
            int width=(int)(icon.getIconWidth()*enLargeRange);
            ImageIcon newIcon = new ImageIcon(icon.getImage().getScaledInstance(width, -1, Image.SCALE_AREA_AVERAGING));
            frame.getLabel().setIcon(newIcon);
        }
    }
    public void last(ViewerFrame frame){
        if(this.currentFiles!=null&&!this.currentFiles.isEmpty()){
            int index=this.currentFiles.indexOf(this.currentFile);
            if(index>0){
                File file = this.currentFiles.get(index - 1);
                ImageIcon icon = new ImageIcon(file.getPath());
                frame.getLabel().setIcon(icon);
                this.currentFile=file;
            }

        }
    }
    public void next(ViewerFrame frame){
        if(this.currentFiles!=null&&!this.currentFiles.isEmpty()){
            int index=this.currentFiles.indexOf(this.currentFile)+1;
            if(index+1<currentFiles.size()){
                File file=this.currentFiles.get(index+1);
                ImageIcon icon = new ImageIcon(file.getPath());
                frame.getLabel().setIcon(icon);
                this.currentFile=file;
            }

        }
    }
    public void menuDo(ViewerFrame frame,String cmd){
        // 打开
        if (cmd.equals("打开(O)")) {
            open(frame);
        }
        // 放大
        if (cmd.equals("放大(M)")) {
            zoom(frame, true);
        }
        // 缩小
        if (cmd.equals("缩小(O)")) {
            zoom(frame, false);
        }
        // 上一个
        if (cmd.equals("上一个(X)")) {
            last(frame);
        }
        // 下一个
        if (cmd.equals("下一个(P)")) {
            next(frame);
        }
        // 退出
        if (cmd.equals("退出(X)")) {
            System.exit(0);
        }
        if(cmd.equals("关于")){
            JOptionPane.showMessageDialog(null,"create by pwh,Please specify!!!");

        }
    }
}

