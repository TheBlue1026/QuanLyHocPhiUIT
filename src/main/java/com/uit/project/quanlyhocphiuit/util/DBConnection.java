package com.uit.project.quanlyhocphiuit.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBConnection {

    private static DBConnection instance;
    private Connection connection;

    // Cấu hình thông tin database cho SQL Server
    // Tên server của bạn có thể là "localhost" hoặc một địa chỉ IP khác
    private static final String SERVER_NAME = "localhost"; 
    // Tên database bạn đã tạo ở bước trước
    private static final String DATABASE_NAME = "QL_HOCPHI_UIT"; 
    // Tên người dùng CSDL (thường là "sa" nếu bạn dùng SQL Server Authentication)
    private static final String USER = "sa"; 
    // Mật khẩu của người dùng CSDL
    private static final String PASSWORD = "123456"; 
    // Cấu hình URL kết nối
    private static final String URL = "jdbc:sqlserver://" + SERVER_NAME + ";databaseName=" + DATABASE_NAME + ";encrypt=true;trustServerCertificate=true";

    private DBConnection() {
        try {
            // Đăng ký driver
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            this.connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Kết nối Database thành công!");
        } catch (ClassNotFoundException e) {
            System.err.println("Không tìm thấy JDBC Driver cho SQL Server.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Lỗi kết nối Database!");
            e.printStackTrace();
        }
    }

    public static DBConnection getInstance() {
        if (instance == null) {
            synchronized (DBConnection.class) {
                if (instance == null) {
                    instance = new DBConnection();
                }
            }
        }
        return instance;
    }

    public Connection getConnection() {
        try {
            if (this.connection == null || this.connection.isClosed()) {
                 System.out.println("Kết nối bị đóng, đang thử kết nối lại...");
                 // Tái kết nối nếu cần
                 this.connection = DriverManager.getConnection(URL, USER, PASSWORD);
            }
        } catch (SQLException e) {
            System.err.println("Lỗi kiểm tra hoặc tái kết nối Database!");
            e.printStackTrace();
        }
        return this.connection;
    }
}


