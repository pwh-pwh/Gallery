package com.pwh;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.io.File;

public class ViewerFileChooser extends JFileChooser {
    /**
     * 使用用户默认路径创建一个ImageFileChooser
     *
     * @return void
     */
    public ViewerFileChooser() {
        super();
        setAcceptAllFileFilterUsed(false);
        addFilter();
    }

    /**
     * 使用自定义的路径路径创建一个ViewerFileChooser
     *
     * @param currentDirectoryPath
     *            String 自定义路径
     * @return void
     */
    public ViewerFileChooser(String currentDirectoryPath) {
        super(currentDirectoryPath);
        setAcceptAllFileFilterUsed(false);
        addFilter();
    }

    /**
     * 增加文件过滤器
     *
     * @return void
     */
    private void addFilter() {
        this.addChoosableFileFilter(new MyFileFilter(new String[] { ".BMP" },
                "BMP (*.BMP)"));
        this.addChoosableFileFilter(new MyFileFilter(new String[] { ".JPG",
                ".JPEG", ".JPE", ".JFIF" },
                "JPEG (*.JPG;*.JPEG;*.JPE;*.JFIF)"));
        this.addChoosableFileFilter(new MyFileFilter(new String[] { ".GIF" },
                "GIF (*.GIF)"));
        this.addChoosableFileFilter(new MyFileFilter(new String[] { ".TIF",
                ".TIFF" }, "TIFF (*.TIF;*.TIFF)"));
        this.addChoosableFileFilter(new MyFileFilter(new String[] { ".PNG" },
                "PNG (*.PNG)"));
        this.addChoosableFileFilter(new MyFileFilter(new String[] { ".ICO" },
                "ICO (*.ICO)"));
        this.addChoosableFileFilter(new MyFileFilter(new String[] { ".BMP",
                ".JPG", ".JPEG", ".JPE", ".JFIF", ".GIF", ".TIF", ".TIFF",
                ".PNG", ".ICO" }, "所有图形文件"));
    }

    class MyFileFilter extends FileFilter {
        String[] suffarr;
        String decription;
        public MyFileFilter(){
            super();
        }
        public MyFileFilter(String[] suffarr, String decription) {
            super();
            this.suffarr = suffarr;
            this.decription = decription;
        }
        /**
         * 判断文件是否属于图片类型。
         * @param f
         * @return
         */
        @Override
        public boolean accept(File f) {
            for (String s : suffarr) {
                if(f.getName().toUpperCase().endsWith(s)){
                    return true;
                }

            }


            return f.isDirectory();
        }

        /**
         * 获取过滤器的描述
         * @return
         */
        @Override
        public String getDescription() {

            return this.decription;
        }
    }
}
