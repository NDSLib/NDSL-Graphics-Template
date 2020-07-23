package com.ndsl.graphics.templates.file;

import com.ndsl.graphics.display.Display;
import com.ndsl.graphics.display.sub.SubWindow;
import com.ndsl.graphics.display.sub.Syncer;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.io.File;

public class FileChooser{
    public Syncer syncer;
    public Display display;
    public SubWindow subWindow;
    public File currentFile;
    public FileChooser(Display display){
        this.display=display;
    }

    public void show(){
        this.subWindow=new SubWindow(display);
        this.syncer=subWindow.syncer;
    }

    @Nullable
    public File getFile(){
        return currentFile;
    }

    public void asyncGetFile(){
        show();
    }

    public static class FileChooserWindow extends SubWindow{
        public String currentPath="";
        public File currentFile;
        public FileChooserWindow(File file){
            this.currentFile=file;
            currentPath=file.getAbsolutePath();
        }

        public FileChooserWindow(String path){
            this(new File(path));
        }

        public FileChooserWindow(){
            this(new File("C:"));
        }
    }
}
