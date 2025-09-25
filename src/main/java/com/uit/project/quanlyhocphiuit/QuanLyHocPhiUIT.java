/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.uit.project.quanlyhocphiuit;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;

/**
 *
 * @author thebl
 */
public class QuanLyHocPhiUIT {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AppFrame frame = null;
            try {
                frame = new AppFrame();
            } catch (SQLException ex) {
                Logger.getLogger(QuanLyHocPhiUIT.class.getName()).log(Level.SEVERE, null, ex);
            }
            frame.setVisible(true);
        });
    }
}
