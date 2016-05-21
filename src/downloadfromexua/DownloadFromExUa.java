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

    public static final long COUNT_BYTES_IN_MEGABYTE = 1024 * 1024;

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

            for (URL fileListURL : fileList) {
                System.out.printf("%s%n", fileListURL);

                URLConnection conn = fileListURL.openConnection();

                String mime = conn.getContentType();
                String nameFile = URLDecoder.decode(new File(conn.getURL().getFile()).getName(), "utf-8");

                System.out.printf("Downloading: (%s) %s [%.1fMB]\n", mime, nameFile, Float.valueOf(conn.getContentLengthLong() / COUNT_BYTES_IN_MEGABYTE));

                InputStream fileIS = conn.getInputStream();

                // write the inputStream to a FileOutputStream
                OutputStream outputStream = new FileOutputStream(new File(ds.PATH_DOWNLOAD + "/" + nameFile));

                int read = 0;
                byte[] bytes = new byte[1024];

                while ((read = fileIS.read(bytes)) != -1) {
                    outputStream.write(bytes, 0, read);
                }

                fileIS.close();
                outputStream.close();

                System.out.println("Downloaded");
            }

        } catch (IOException ex) {
            Logger.getLogger(DownloadFromExUa.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
