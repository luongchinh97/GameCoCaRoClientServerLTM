package dao.impl;

import dao.PhongNguoiChoiDao;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Nguoi;
import model.NguoiDung;

public class PhongNguoiChoiImpl extends RootDao implements PhongNguoiChoiDao {

    private NguoiDungDaoImpl nguoiDAO = new NguoiDungDaoImpl();

    @Override
    public void add(int idPhong, Nguoi nguoi) {
        String sql = "INSERT INTO phong_nguoidung(idPhong, idNguoiChoi, role) VALUES(?,?,?);";
        try {
            if (getJDBCConnection() != null) {
                ppst = getJDBCConnection().prepareStatement(sql);
                ppst.setInt(1, idPhong);
                ppst.setInt(2, nguoi.getIdNguoiDung());
                ppst.setInt(3, nguoi.getRole());
                ppst.executeUpdate();
                ppst.close();
                getJDBCConnection().close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public void edit(int idPhong, Nguoi nguoi) {
        String sql = "UPDATE phong_nguoidung SET role = ? WHERE idPhong = ? AND idNguoiChoi = ?;";
        try {
                ppst = getJDBCConnection().prepareStatement(sql);
                ppst.setInt(1, nguoi.getRole());
                ppst.setInt(2, idPhong);
                ppst.setInt(3, nguoi.getIdNguoiDung());
                ppst.executeUpdate();
                ppst.close();
                getJDBCConnection().close();
        } catch (SQLException ex) {
            Logger.getLogger(PhongNguoiChoiImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(int idNguoi) {
        String sql = "DELETE FROM phong_nguoidung WHERE idNguoiChoi  = ?;";
        try {
                ppst = getJDBCConnection().prepareStatement(sql);
                ppst.setInt(1, idNguoi);
                ppst.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public NguoiDung getNguoiChoi(int idNguoiChoi) {
        String sql = " SELECT * FROM phong_nguoidung WHERE idNguoiChoi = ?;";
        try {
                ppst = getJDBCConnection().prepareStatement(sql);
                ppst.setInt(1, idNguoiChoi);
                rs = ppst.executeQuery();
                while (rs.next()) {
                    NguoiDung nguoi = nguoiDAO.getByIdNguoiDung(rs.getInt("idNguoiChoi"));
                    nguoi.setRole(rs.getInt("role"));
                    ppst.close();
                    rs.close();
                    getJDBCConnection().close();
                    return nguoi;
                }
        } catch (SQLException ex) {
            Logger.getLogger(PhongNguoiChoiImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public ArrayList<NguoiDung> dsNguoiChoiTrongPhong(int idPhong) {
        ArrayList<NguoiDung> list = new ArrayList<NguoiDung>();
        String sql = "SELECT * FROM phong_nguoidung WHERE idPhong = ?;";
        try {
            ppst = getJDBCConnection().prepareStatement(sql);
            ppst.setInt(1, idPhong);
            rs = ppst.executeQuery();
            while (rs.next()) {
                NguoiDung nguoi = nguoiDAO.getByIdNguoiDung(rs.getInt("idNguoiChoi"));
                nguoi.setRole(rs.getInt("role"));
                list.add(nguoi);
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
    public void deletePhong(int idPhong) {
        String sql = "DELETE FROM phong_nguoidung WHERE idPhong  = ?;";
        try {
            ppst = getJDBCConnection().prepareStatement(sql);
            ppst.setInt(1, idPhong);
            ppst.executeUpdate();
            ppst.close();
            getJDBCConnection().close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

}
