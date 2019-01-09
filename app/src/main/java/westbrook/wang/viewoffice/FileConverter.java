package westbrook.wang.viewoffice;

import java.io.File;

public class FileConverter {

    final static FileConverter fileConverter = new FileConverter();


    public static FileConverter get() {
        return fileConverter;
    }


    public String getHTMLpath(File file) {
        return "";
    }


}
