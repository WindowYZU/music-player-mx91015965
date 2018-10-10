/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lendle.courses.wp.mp3downloadersample;

import com.sun.media.jfxmedia.MetadataParser;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.media.CannotRealizeException;
import javax.media.Manager;
import javax.media.NoPlayerException;
import javax.media.Player;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.ProgressMonitor;

/**
 *
 * @author lendle
 */
public class NewMain {

    static Player p = null;
    static ProgressMonitor progress=null;

    private static void playFile(File tempFile) {
        progress.setProgress(100);
        try {
            if (p != null) {
                p.stop();
                p = null;
            }
            p = Manager.createRealizedPlayer(tempFile.toURI().toURL());
            p.start();
        } catch (Exception ex) {
            Logger.getLogger(NewMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        JFrame frame = new JFrame();
        frame.setSize(500, 500);

        frame.setLayout(new FlowLayout());
        JButton button1 = new JButton("Play Wav File");
        final double FILE_SIZE = 9580594d;
        
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                try {
                    final File tempFile = File.createTempFile("tmp", ".wav");
                    progress = new ProgressMonitor(frame, "downloading......", "a2002011001-e02.wav", 0, 100);
                    FileDownloader.downloadFile(new URL("http://www.music.helsinki.fi/tmt/opetus/uusmedia/esim/a2002011001-e02.wav"), 
                            tempFile, new FileDownloaderCallback() {
                        @Override
                        public void totalBytesDownloaded(long bytes, boolean finished, boolean failed) {
                           //implement this
                           ////////////////
                        }
                        protected double FILE_SIZE;
                    });

                } catch (Exception ex) {
                    Logger.getLogger(NewMain.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        frame.add(button1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

}
