package com.inthefog.chessboard.view.helpers;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;

/**
 * Created by pavel on 30/08/2016.
 */
public class ClipboardReadWrite {
    public static void writeText(String s) {
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        Transferable transferable = new StringSelection(s);
        clipboard.setContents(transferable, null);
    }
}
