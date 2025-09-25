package dao;

import java.sql.SQLException;
import java.util.List;

/**
 * Giao diện chung cho các lớp Data Access Object (DAO).
 * Tách biệt logic truy cập dữ liệu ra khỏi logic nghiệp vụ.
 *
 * @param <T> Kiểu dữ liệu của đối tượng (ví dụ: SinhVien, HocPhi).
 */
public interface IDAO<T> {

    /**
     * Thêm một đối tượng mới vào cơ sở dữ liệu.
     * @param t Đối tượng cần thêm.
     * @return true nếu thêm thành công, ngược lại là false.
     * @throws SQLException Nếu có lỗi xảy ra khi tương tác với cơ sở dữ liệu.
     */
    boolean add(T t) throws SQLException;

    /**
     * Cập nhật thông tin của một đối tượng trong cơ sở dữ liệu.
     * @param t Đối tượng cần cập nhật.
     * @return true nếu cập nhật thành công, ngược lại là false.
     * @throws SQLException Nếu có lỗi xảy ra khi tương tác với cơ sở dữ liệu.
     */
    boolean update(T t) throws SQLException;

    /**
     * Xóa một đối tượng khỏi cơ sở dữ liệu dựa trên ID.
     * @param id ID của đối tượng cần xóa.
     * @return true nếu xóa thành công, ngược lại là false.
     * @throws SQLException Nếu có lỗi xảy ra khi tương tác với cơ sở dữ liệu.
     */
    boolean delete(String id) throws SQLException;

    /**
     * Lấy một đối tượng từ cơ sở dữ liệu dựa trên ID.
     * @param id ID của đối tượng cần lấy.
     * @return Đối tượng nếu tìm thấy, ngược lại là null.
     * @throws SQLException Nếu có lỗi xảy ra khi tương tác với cơ sở dữ liệu.
     */
    T getById(String id) throws SQLException;

    /**
     * Lấy tất cả các đối tượng từ cơ sở dữ liệu.
     * @return Danh sách các đối tượng.
     * @throws SQLException Nếu có lỗi xảy ra khi tương tác với cơ sở dữ liệu.
     */
    List<T> getAll() throws SQLException;
}
