package dao.impl;

import controller.Key;
import dao.NguoiDungDao;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Nguoi;
import model.NguoiDung;

public class NguoiDungDaoImpl extends RootDao implements NguoiDungDao, Key {

    public void add(NguoiDung nguoiDung) {
        String sql = "INSERT INTO nguoidung(username,password,name,avata,loaiHang,xepHang,soTranThang,soTranThua,diem,tien,tinhTrang,ipAddress,port) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?);";
        String sql2 = "SELECT COUNT(*) AS soHang FROM nguoidung";
        int soHang = 1;
        try {
            ppst = getJDBCConnection().prepareStatement(sql2);
            rs = ppst.executeQuery();
            while (rs.next()) {
                soHang = rs.getInt("soHang") + 1;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        try {
            ppst = getJDBCConnection().prepareStatement(sql);
            ppst.setString(1, nguoiDung.getUsername());
            ppst.setString(2, nguoiDung.getPassword());
            ppst.setString(3, nguoiDung.getName());
            ppst.setString(4, AVA_DEFAULT);
            ppst.setString(5, HOC_VIEN);
            ppst.setInt(6, soHang);
            ppst.setInt(7, 0);
            ppst.setInt(8, 0);
            ppst.setInt(9, 0);
            ppst.setInt(10, TIEN_DEFAULT);
            ppst.setInt(11, 0);
            ppst.setString(12, nguoiDung.getIpAddress());
            ppst.setInt(13, nguoiDung.getPort());
            ppst.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();

        }

    }

    public void edit(NguoiDung nguoiDung) {
        String sql = "UPDATE nguoidung SET `username`=?,`password`=?,`name`=?,`avata`=?,`loaiHang`=?,`xepHang`=?,`soTranThang`=?,`soTranThua`=? ,`diem`=?, `tien`=?, `tinhTrang`=?, ipAddress = ?, port = ? WHERE `idNguoiDung`=?;";
        try {
            ppst = getJDBCConnection().prepareStatement(sql);
            ppst.setString(1, nguoiDung.getUsername());
            ppst.setString(2, nguoiDung.getPassword());
            ppst.setString(3, nguoiDung.getName());
            ppst.setString(4, nguoiDung.getAvata());
            ppst.setString(5, nguoiDung.getLoaiHang());
            ppst.setInt(6, nguoiDung.getXepHang());
            ppst.setInt(7, nguoiDung.getSoTranThang());
            ppst.setInt(8, nguoiDung.getSoTranThua());
            ppst.setInt(9, nguoiDung.getDiem());
            ppst.setInt(10, nguoiDung.getTien());
            ppst.setInt(11, nguoiDung.getTinhTrang());
            ppst.setString(12, nguoiDung.getIpAddress());
            ppst.setInt(13, nguoiDung.getPort());
            ppst.setInt(14, nguoiDung.getIdNguoiDung());
            ppst.executeUpdate();
            ppst.close();
            getJDBCConnection().close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    public void delete(int idNguoiDung) {
        String sql = "DELETE FROM nguoidung WHERE `idNguoiDung`=?;";
        try {
            ppst = getJDBCConnection().prepareStatement(sql);
            ppst.setInt(1, idNguoiDung);
            ppst.executeUpdate();
            ppst.close();
            getJDBCConnection().close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    public NguoiDung getByIdNguoiDung(int idNguoiDung) {
        String sql = "SELECT *FROM nguoidung WhERE `idNguoiDung`=?;";
        try {
            ppst = getJDBCConnection().prepareStatement(sql);
            ppst.setInt(1, idNguoiDung);
            rs = ppst.executeQuery();
            while (rs.next()) {
                NguoiDung nguoiDung = new NguoiDung();
                nguoiDung.setIdNguoiDung(idNguoiDung);
                nguoiDung.setUsername(rs.getString("username"));
                nguoiDung.setPassword(rs.getString("password"));
                nguoiDung.setName(rs.getString("name"));
                nguoiDung.setAvata(rs.getString("avata"));
                nguoiDung.setLoaiHang(rs.getString("loaiHang"));
                nguoiDung.setXepHang(rs.getInt("xepHang"));
                nguoiDung.setSoTranThua(rs.getInt("soTranThua"));
                nguoiDung.setSoTranThang(rs.getInt("soTranThang"));
                nguoiDung.setDiem(rs.getInt("diem"));
                nguoiDung.setTien(rs.getInt("tien"));
                nguoiDung.setTinhTrang(rs.getInt("tinhTrang"));
                nguoiDung.setIpAddress(rs.getString("ipAddress"));
                nguoiDung.setPort(rs.getInt("port"));
                ppst.close();
                rs.close();
                getJDBCConnection().close();
                return nguoiDung;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return null;
    }

    public NguoiDung getByUsernameNguoiDung(String username) {
        String sql = "SELECT * FROM nguoidung WHERE `username` = ?;";
        try {
            ppst = getJDBCConnection().prepareStatement(sql);
            ppst.setString(1, username);
            rs = ppst.executeQuery();
            while (rs.next()) {
                NguoiDung nguoiDung = new NguoiDung();
                nguoiDung.setIdNguoiDung(rs.getInt("idNguoiDung"));
                nguoiDung.setUsername(rs.getString("username"));
                nguoiDung.setPassword(rs.getString("password"));
                nguoiDung.setName(rs.getString("name"));
                nguoiDung.setAvata(rs.getString("avata"));
                nguoiDung.setLoaiHang(rs.getString("loaiHang"));
                nguoiDung.setXepHang(rs.getInt("xepHang"));
                nguoiDung.setSoTranThua(rs.getInt("soTranThua"));
                nguoiDung.setSoTranThang(rs.getInt("soTranThang"));
                nguoiDung.setDiem(rs.getInt("diem"));
                nguoiDung.setTien(rs.getInt("tien"));
                nguoiDung.setTinhTrang(rs.getInt("tinhTrang"));
                nguoiDung.setIpAddress(rs.getString("ipAddress"));
                nguoiDung.setPort(rs.getInt("port"));
                ppst.close();
                rs.close();
                getJDBCConnection().close();
                return nguoiDung;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return null;
    }

    public ArrayList<NguoiDung> getByNameNguoiDung(String name) {
        ArrayList<NguoiDung> list = new ArrayList<>();
        String sql = "SELECT *FROM nguoidung WHERE `name` LIKE ?;";
        try {
            ppst = getJDBCConnection().prepareStatement(sql);
            ppst.setString(1, "%" + name + "%");
            rs = ppst.executeQuery();
            while (rs.next()) {
                NguoiDung nguoiDung = new NguoiDung();
                nguoiDung.setIdNguoiDung(rs.getInt("idNguoiDung"));
                nguoiDung.setUsername(rs.getString("username"));
                nguoiDung.setPassword(rs.getString("password"));
                nguoiDung.setName(rs.getString("name"));
                nguoiDung.setAvata(rs.getString("avata"));
                nguoiDung.setLoaiHang(rs.getString("loaiHang"));
                nguoiDung.setXepHang(rs.getInt("xepHang"));
                nguoiDung.setSoTranThua(rs.getInt("soTranThua"));
                nguoiDung.setSoTranThang(rs.getInt("soTranThang"));
                nguoiDung.setDiem(rs.getInt("diem"));
                nguoiDung.setTien(rs.getInt("tien"));
                nguoiDung.setTinhTrang(rs.getInt("tinhTrang"));
                nguoiDung.setIpAddress(rs.getString("ipAddress"));
                nguoiDung.setPort(rs.getInt("port"));
                list.add(nguoiDung);
            }
            ppst.close();
            rs.close();
            getJDBCConnection().close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return list;
    }

    public ArrayList<NguoiDung> getAll() {
        ArrayList<NguoiDung> list = new ArrayList<>();
        String sql = "SELECT * FROM nguoidung ORDER BY diem DESC, soTranThang DESC;";
        try {
            ppst = getJDBCConnection().prepareStatement(sql);
            rs = ppst.executeQuery();
            while (rs.next()) {
                NguoiDung nguoiDung = new NguoiDung();
                nguoiDung.setIdNguoiDung(rs.getInt("idNguoiDung"));
                nguoiDung.setUsername(rs.getString("username"));
                nguoiDung.setPassword(rs.getString("password"));
                nguoiDung.setName(rs.getString("name"));
                nguoiDung.setAvata(rs.getString("avata"));
                nguoiDung.setLoaiHang(rs.getString("loaiHang"));
                nguoiDung.setXepHang(rs.getInt("xepHang"));
                nguoiDung.setSoTranThua(rs.getInt("soTranThua"));
                nguoiDung.setSoTranThang(rs.getInt("soTranThang"));
                nguoiDung.setDiem(rs.getInt("diem"));
                nguoiDung.setTien(rs.getInt("tien"));
                nguoiDung.setTinhTrang(rs.getInt("tinhTrang"));
                nguoiDung.setIpAddress(rs.getString("ipAddress"));
                nguoiDung.setPort(rs.getInt("port"));
                list.add(nguoiDung);
            }
            ppst.close();
            rs.close();
            getJDBCConnection().close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return list;
    }

    @Override
    public ArrayList<Nguoi> getTop10() {
        ArrayList<Nguoi> list = new ArrayList<>();
        String sql = "SELECT * FROM nguoidung ORDER BY xepHang LIMIT 10;";
        try {
            if (getJDBCConnection() != null) {
                ppst = getJDBCConnection().prepareStatement(sql);
                rs = ppst.executeQuery();
                while (rs.next()) {
                    Nguoi nguoiDung = new Nguoi();
                    nguoiDung.setIdNguoiDung(rs.getInt("idNguoiDung"));
                    nguoiDung.setName(rs.getString("name"));
                    nguoiDung.setAvata(rs.getString("avata"));
                    nguoiDung.setLoaiHang(rs.getString("loaiHang"));
                    nguoiDung.setXepHang(rs.getInt("xepHang"));
                    nguoiDung.setSoTranThua(rs.getInt("soTranThua"));
                    nguoiDung.setSoTranThang(rs.getInt("soTranThang"));
                    nguoiDung.setDiem(rs.getInt("diem"));
                    nguoiDung.setTinhTrang(rs.getInt("tinhTrang"));
                    nguoiDung.setIpAddress(rs.getString("ipAddress"));
                    nguoiDung.setPort(rs.getInt("port"));
                    list.add(nguoiDung);
                }
                ppst.close();
                rs.close();
                getJDBCConnection().close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }
}
