package ui;

import javax.swing.plaf.basic.BasicTextFieldUI;
import javax.swing.text.JTextComponent;
import java.awt.*;

public class RoundedTextFieldUI extends BasicTextFieldUI {
    @Override
    protected void paintBackground(Graphics g) {
        JTextComponent comp = getComponent();
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(comp.getBackground());
        g2.fillRoundRect(0, 0, comp.getWidth(), comp.getHeight(), 20, 20);
        g2.dispose();
    }
}


