/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package downloadfromexua;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import static java.lang.Math.ceil;
import static java.lang.Math.round;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Igor Gayvan
 */
public class DownloadFromExUa {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws MalformedURLException {
        URL playlistUrl = new URL("http://www.ex.ua/playlist/17427869.m3u");
        List<URL> fileList = new ArrayList<>();

        List<DownloadFile> downloadFileList = new ArrayList<>();

        DataSource ds = new DataSource(downloadFileList);
        ShowData.ShowListAlreadyDownloadFiles(downloadFileList);

        try (Scanner scanner = new Scanner(playlistUrl.openStream())) {
            while (scanner.hasNextLine()) {
                URL fileUrl = new URL(scanner.nextLine());
                fileList.add(fileUrl);
            }

            for (URL fileDownloadURL : fileList) {
                System.out.printf("%s%n", fileDownloadURL);

                DownloadFile df = new DownloadFile(fileDownloadURL);
                df.loadFile(ds,downloadFileList);

                downloadFileList.add(df);
            }

        } catch (IOException ex) {
            Logger.getLogger(DownloadFromExUa.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
