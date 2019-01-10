package westbrook.wang.viewoffice;


import android.os.Environment;
import android.util.Log;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.converter.PicturesManager;
import org.apache.poi.hwpf.converter.WordToHtmlConverter;
import org.apache.poi.hwpf.usermodel.Picture;
import org.apache.poi.hwpf.usermodel.PictureType;
import org.w3c.dom.Document;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URI;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import static westbrook.wang.viewoffice.Constant.APPLICATION_NAME;

public class FileConverter {

    final static FileConverter fileConverter = new FileConverter();

    String tempFolderPath;


    public static FileConverter get() {
        return fileConverter;
    }


    public String getHTMLpath(File file) {
        createTempFolder();
        String fileName = file.getName();
        String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
        Log.d("FileConverter", "suffix is ".concat(suffix));
        switch (suffix) {

            case "txt": {
                return getHtmlFromTxt(file);
            }

            case "doc": {
                return getHtmlFromDoc(file);
            }

            default:
                return "";
        }

    }


    private String getHtmlFromTxt(File file) {

        String fileName = file.getName().substring(0, file.getName().indexOf("."));
        File htmlFile = new File(tempFolderPath.concat("/").concat(fileName).concat(".html"));
        InputStreamReader read = null;
        BufferedWriter bw = null;
        FileOutputStream fos = null;
        OutputStreamWriter osw = null;

        try {
            read = new InputStreamReader(new FileInputStream(file));
            // 考虑到编码格式
            BufferedReader bufferedReader = new BufferedReader(read);
            // 写文件
            fos = new FileOutputStream(htmlFile);
            osw = new OutputStreamWriter(fos, "UTF-8");
            bw = new BufferedWriter(osw);
            String lineTxt = null;
            while ((lineTxt = bufferedReader.readLine()) != null) {
                bw.write(lineTxt + "</br>");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bw != null && osw != null && fos != null && read != null) {
                try {
                    bw.close();
                    osw.close();
                    fos.close();
                    read.close();
                } catch (Exception e) {

                }
            }

        }

        if (htmlFile.exists()) {
            return htmlFile.getAbsolutePath();
        } else {
            return "";
        }
    }


    private String getHtmlFromDoc(File file) {
        final String fileName = file.getName().substring(0, file.getName().indexOf("."));
        final File htmlFile = new File(tempFolderPath.concat("/").concat(fileName).concat(".html"));
        try {
            HWPFDocument wordDocument = new HWPFDocument(new FileInputStream(file));
            Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
            WordToHtmlConverter wordToHtmlConverter = new WordToHtmlConverter(document);

            wordToHtmlConverter.setPicturesManager(new PicturesManager() {
                @Override
                public String savePicture(byte[] content, PictureType pictureType, String name, float width, float height) {
                    try {
                        FileOutputStream fos = new FileOutputStream(tempFolderPath.concat("/") + name);
                        fos.write(content);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return name;
                }
            });

            wordToHtmlConverter.processDocument(wordDocument);
            Document htmlDocument = wordToHtmlConverter.getDocument();
            DOMSource domSource = new DOMSource(htmlDocument);
            StreamResult streamResult = new StreamResult(htmlFile);
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer serializer = tf.newTransformer();
            serializer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            serializer.setOutputProperty(OutputKeys.INDENT, "yes");
            serializer.setOutputProperty(OutputKeys.METHOD, "html");
            serializer.transform(domSource, streamResult);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (htmlFile.exists()) {
            return htmlFile.getAbsolutePath();
        } else {
            return "";
        }
    }


    private void createTempFolder() {
        tempFolderPath = Environment.getDataDirectory().getAbsolutePath().concat("/data/").concat(APPLICATION_NAME).concat("/officeViewTemp");
        File file = new File(tempFolderPath);
        if (!file.exists()) {
            file.mkdir();
        }
    }

}
