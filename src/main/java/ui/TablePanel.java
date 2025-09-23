package ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class TablePanel extends JPanel {
    private JTable table;
    private DefaultTableModel model;

    public TablePanel(String[] columns) {
        setLayout(new BorderLayout());
        model = new DefaultTableModel(columns, 0);
        table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.CENTER);
    }

    public void addRow(Object[] rowData) {
        model.addRow(rowData);
    }
}
