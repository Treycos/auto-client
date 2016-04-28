/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.autoclient.GUI;

import cz.autoclient.GUI.dialogs.DoNotAskAgainPanel;
import cz.autoclient.settings.Settings;
import java.awt.Component;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;

/**
 *
 * @author Jakub
 */
public class Dialogs {
    public static void dialogErrorAsync(final String message, final String title, final Component parentComponent) {
        new Thread("AsyncErrorDialog") {
          @Override
          public void run() {
            JOptionPane.showMessageDialog(
                parentComponent,
                makeTextPane(message),
                title,
                JOptionPane.ERROR_MESSAGE
            );
          }
        }.start();
    }
    public static void dialogErrorAsync(final String message) {
      dialogErrorAsync(message, "Error", null);
    }
    public static void dialogErrorAsync(final String message, final Component parentComponent) {
      dialogErrorAsync(message, "Error", parentComponent);
    }
    public static void dialogErrorAsync(final String message, final String title) {
      dialogErrorAsync(message, title, null);
    }
    public static void dialogInfoAsync(final String message, final String title, final Component parentComponent) {
        new Thread("AsyncErrorDialog") {
          @Override
          public void run() {
            JOptionPane.showMessageDialog(
                parentComponent,
                makeTextPane(message),
                title,
                JOptionPane.INFORMATION_MESSAGE
            );
          }
        }.start();
    }
    public static void dialogInfoAsync(final String message) {
      dialogInfoAsync(message, "Error", null);
    }
    public static void dialogInfoAsync(final String message, final Component parentComponent) {
      dialogInfoAsync(message, "Error", parentComponent);
    }
    public static void dialogInfoAsync(final String message, final String title) {
      dialogInfoAsync(message, title, null);
    }
    /**
     * 
     * @param message Text to display, ca be simple HTML
     * @param title Window title
     * @param parentComponent This is probably not needed, it's the parent window
     * @param name Name of setting that determines whether to show the dialog
     * @param settings Settings map to contain the setting
     * @param defaultSetting Default value of the checkbox - true means 'do not show again'
     */
    public static void dialogInfoOnceAsync(
            final String message,
            final String title,
            final Component parentComponent,
            String name,
            Settings settings,
            boolean defaultSetting
    )
    {
        if(settings.getBoolean(name, false)) {
          return;
        }
        new Thread("AsyncErrorDialog") {
          @Override
          public void run() {
            DoNotAskAgainPanel panel = new DoNotAskAgainPanel(makeTextPane(message));
            panel.setDontAskMeAgain(defaultSetting);
            JOptionPane.showMessageDialog (
                parentComponent,
                panel,
                title,
                JOptionPane.INFORMATION_MESSAGE
            );
            settings.setSetting(name, panel.dontAskMeAgain());
          }
        }.start();
    }
    public static void dialogInfoOnceAsync(final String message, final String name, Settings settings) {
      dialogInfoOnceAsync(message, "Info", null, name, settings, false);
    }
    public static void dialogInfoOnceAsync(final String message, final String title, final String name, Settings settings) {
      dialogInfoOnceAsync(message, title, null, name, settings, false);
    }
    
   /**
     * 
     * @param message Text to display, ca be simple HTML
     * @param title Window title
     * @param parentComponent This is probably not needed, it's the parent window
     * @param name Name of setting that determines whether to show the dialog
     * @param settings Settings map to contain the setting
     * @param defaultSetting Default value of the checkbox - true means 'do not show again'
     */
    public static void dialogInfoOnce(
            final String message,
            final String title,
            final Component parentComponent,
            final String name,
            final Settings settings,
            final boolean defaultSetting
    )
    {
        if(settings.getBoolean(name, false)) {
          return;
        }
        Thread t = new Thread(
            ()->dialogInfoOnceShowDialog(message, title, parentComponent, name, settings, defaultSetting)
        );
        t.start();
    }
    /**
     * This method is used to show dialog from both sync and async version.
     * @param message
     * @param title
     * @param parentComponent
     * @param name
     * @param settings
     * @param defaultSetting 
     */
    protected static void dialogInfoOnceShowDialog(
            final String message,
            final String title,
            final Component parentComponent,
            String name,
            Settings settings,
            boolean defaultSetting
    ) {
        DoNotAskAgainPanel panel = new DoNotAskAgainPanel(makeTextPane(message));
        panel.setDontAskMeAgain(defaultSetting);
        JOptionPane.showMessageDialog (
            parentComponent,
            panel,
            title,
            JOptionPane.INFORMATION_MESSAGE
        );
        System.out.println("Setting dialog "+name+" show status to: "+(panel.dontAskMeAgain()?"do not show":"show"));
        settings.setSetting(name, panel.dontAskMeAgain());
    }
    public static void dialogInfoOnce(final String message, final String name, Settings settings) {
      dialogInfoOnce(message, "Info", null, name, settings, false);
    }
    public static void dialogInfoOnce(final String message, final String title, final String name, Settings settings) {
      dialogInfoOnce(message, title, null, name, settings, false);
    }
    
    public static JTextPane makeTextPane(String text) {
      JTextPane f = new JTextPane();
      f.setContentType("text/html"); // let the text pane know this is what you want
      f.setText("<html>"+text+"</html>"); // showing off
      f.setEditable(false); // as before
      f.setBackground(null); // this is the same as a JLabel
      f.setBorder(null); // remove the border
      return f;
    }
    
    
}
