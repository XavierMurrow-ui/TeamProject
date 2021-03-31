package GUI;

import javax.swing.filechooser.FileFilter;
import java.io.File;

public class FileTypeFilter extends FileFilter {
    private final String extension;
    private final String description;

    public FileTypeFilter(String e, String d){
        this.extension = e;
        this.description = d;
    }
    @Override
    public boolean accept(File f) {
        if(f.isDirectory()){
            return true;
        }
        return f.getName().endsWith(extension);
    }

    @Override
    public String getDescription() {
        return description + String.format(" (*%s)",extension);
    }
}
