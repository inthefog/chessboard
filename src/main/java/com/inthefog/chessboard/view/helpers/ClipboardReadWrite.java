package com.inthefog.chessboard.view.helpers;

import com.inthefog.chessboard.exceptions.ClipboardException;

import java.awt.*;
import java.awt.datatransfer.*;

/**
 * Created by pavel on 30/08/2016.
 */
public class ClipboardReadWrite {
    /**
     *
     * @param s
     */
    public static void writeText(String s) {
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        Transferable transferable = new StringSelection(s);
        clipboard.setContents(transferable, null);
    }

    /**
     *
     * @return
     * @throws ClipboardException
     */
    public static String readText() throws ClipboardException {
        try {
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            return (String) clipboard.getData(DataFlavor.stringFlavor);
        } catch (Exception e) {
            throw new ClipboardException("Failed to read text from the clipboard.", e);
        }
    }
}
