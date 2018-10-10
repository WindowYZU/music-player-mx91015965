/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lendle.courses.wp.mp3downloadersample;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

/**
 *
 * @author lendle
 */
public class FileDownloader {
    static void downloadFile(final URL url, final File destinationFile, final FileDownloaderCallback callback){
        Thread t=new Thread(){
            public void run(){
                byte [] buffer=new byte[4096];
                long bytesDownloaded=0;
                try(InputStream input=url.openStream(); OutputStream output=new FileOutputStream(destinationFile)){
                    int bytesRead=input.read(buffer);
                    while(bytesRead!=-1){
                        bytesDownloaded+=bytesRead;
                        output.write(buffer, 0, bytesRead);
                        callback.totalBytesDownloaded(bytesDownloaded, false, false);
                        bytesRead=input.read(buffer);
                    }
                    callback.totalBytesDownloaded(bytesDownloaded, true, false);
                }catch(Exception e){
                    callback.totalBytesDownloaded(bytesDownloaded, false, true);
                }
            }
        };
        t.start();
    }
}
