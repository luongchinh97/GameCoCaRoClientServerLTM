package dao.impl;

import dao.BanBeDao;
import dao.NguoiDungDao;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Nguoi;
import model.NguoiDung;

public class BanBeDaoImpl extends RootDao implements BanBeDao {

    private NguoiDungDao nguoiDungDao = new NguoiDungDaoImpl();

    @Override
    public void add(Nguoi nguoi, NguoiDung nguoiDung) {
        String sql = "INSERT INTO banbe(idBanBe, idNguoiDung) VALUES(?,?);";
        try {
            if (getJDBCConnection() != null) {
                ppst = getJDBCConnection().prepareStatement(sql);
                ppst.setInt(1, nguoi.getIdNguoiDung());
                ppst.setInt(2, nguoiDung.getIdNguoiDung());
                ppst.executeUpdate();
                ppst.close();
                getJDBCConnection().close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void delete(int idNguoiDung, int idBB) {
        String sql = "DELETE FROM banbe WHERE idNguoiDung = ? AND idBanBe =?;";
        try {
            if (getJDBCConnection() != null) {
                ppst = getJDBCConnection().prepareStatement(sql);
                ppst.setInt(1, idNguoiDung);
                ppst.setInt(2, idBB);
                ppst.executeUpdate();
                ppst.close();
                getJDBCConnection().close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public Nguoi xemThongTin(int idNguoi) {
        String sql = "SELECT * FROM nguoidung WHERE idBanBe = ?;";
        try {
            if (getJDBCConnection() != null) {
                ppst = getJDBCConnection().prepareStatement(sql);
                ppst.setInt(1, idNguoi);
                rs = ppst.executeQuery();
                while (rs.next()) {
                    Nguoi nguoi = new NguoiDung();
                    nguoi.setIdNguoiDung(idNguoi);
                    nguoi.setName(rs.getString("name"));
                    nguoi.setAvata(rs.getString("avata"));
                    nguoi.setSoTranThang(rs.getInt("soTranThang"));
                    nguoi.setSoTranThua(rs.getInt("soTranThua"));
                    nguoi.setDiem(rs.getInt("diem"));
                    nguoi.setLoaiHang(rs.getString("loaiHang"));
                    nguoi.setXepHang(rs.getInt("xepHang"));
                    nguoi.setTinhTrang(rs.getInt("tinhTrang"));
                    ppst.close();
                    rs.close();
                    getJDBCConnection().close();
                    return nguoi;
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return null;
    }

    @Override
    public ArrayList<Nguoi> getDsBanBe(int idNguoiDung) {
        ArrayList<Nguoi> list = new ArrayList<>();
        String sql = "SELECT * FROM banbe WHERE idNguoiDung = ?;";
        try {
            if (getJDBCConnection() != null) {
                ppst = getJDBCConnection().prepareStatement(sql);
                ppst.setInt(1, idNguoiDung);
                rs = ppst.executeQuery();
                while (rs.next()) {
                    Nguoi nguoi = nguoiDungDao.getByIdNguoiDung(rs.getInt("idBanBe"));
                    list.add(nguoi);
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
