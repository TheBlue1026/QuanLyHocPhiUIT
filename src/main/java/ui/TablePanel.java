package ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import styles.UIStyle;

public class TablePanel extends JPanel {
    private JTable table;
    private DefaultTableModel model;

    public TablePanel(String[] columns) {
        setLayout(new BorderLayout());

        // Khởi tạo model và bảng
        model = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // bảng chỉ đọc
            }
        };
        table = new JTable(model);

        // Áp dụng style hiện đại
        UIStyle.applyTableStyle(table);

        // Thêm bảng vào panel với scroll
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createEmptyBorder()); // bỏ viền ngoài
        add(scrollPane, BorderLayout.CENTER);
    }

    // thêm 1 row dữ liệu
    public void addRow(Object[] rowData) {
        model.addRow(rowData);
    }

    // xóa toàn bộ row
    public void clearRows() {
        model.setRowCount(0);
    }

    // get table (nếu cần tùy chỉnh thêm)
    public JTable getTable() {
        return table;
    }

    // get model
    public DefaultTableModel getModel() {
        return model;
    }
}
