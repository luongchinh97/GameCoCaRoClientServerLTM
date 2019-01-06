package dao.impl;

import dao.PhongDao;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Phong;

public class PhongDaoImpl extends RootDao implements PhongDao {

    @Override
    public void add(Phong phong) {
        String sql = "INSER INTO phong(`soLuongNguoiChoi`,`soLuongTienCuoc`,`tinhTrang`) VALUES(?,?,?);";
        try {
            ppst = getJDBCConnection().prepareCall(sql);
            ppst.setInt(1, phong.getSoLuongNguoiChoi());
            ppst.setInt(2, phong.getSoLuongTienCuoc());
            ppst.setInt(3, phong.getTinhTrang());
            ppst.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public void edit(Phong phong) {
        String sql = "UPDATE phong SET `soLuongNguoiChoi`=?,`soTienCuoc`=?,`tinhTrang`=? WHERE `idPhong`=?;";
        try {
            ppst = getJDBCConnection().prepareStatement(sql);
            ppst.setInt(1, phong.getSoLuongNguoiChoi());
            ppst.setInt(2, phong.getSoLuongTienCuoc());
            ppst.setInt(3, phong.getTinhTrang());
            ppst.setInt(4, phong.getIdPhong());
            ppst.executeUpdate();
            ppst.close();
            getJDBCConnection().close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public void delete(int idPhong) {
        String sql = "DELETE FROM phong WHERE `idPhong`=?;";
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

    @Override
    public Phong getById(int idPhong) {
        String sql = "SELECT * FROM phong WHERE `idPhong`=?;";
        try {
            ppst = getJDBCConnection().prepareStatement(sql);
            ppst.setInt(1, idPhong);
            rs = ppst.executeQuery();
            while (rs.next()) {
                Phong phong = new Phong();
                phong.setIdPhong(rs.getInt("idPhong"));
                phong.setSoLuongNguoiChoi(rs.getInt("soLuongNguoiChoi"));
                phong.setSoLuongTienCuoc(rs.getInt("soTienCuoc"));
                phong.setTinhTrang(rs.getInt("tinhTrang"));
                ppst.close();
                rs.close();
                getJDBCConnection().close();
                return phong;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<Phong> getAll() {
        String sql = "SELECT * FROM phong;";
        ArrayList<Phong> list = new ArrayList<>();
        try {
            ppst = getJDBCConnection().prepareStatement(sql);
            rs = ppst.executeQuery();
            while (rs.next()) {
                Phong phong = new Phong();
                phong.setIdPhong(rs.getInt("idPhong"));
                phong.setSoLuongNguoiChoi(rs.getInt("soLuongNguoiChoi"));
                phong.setSoLuongTienCuoc(rs.getInt("soTienCuoc"));
                phong.setTinhTrang(rs.getInt("tinhTrang"));
                list.add(phong);
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
    public ArrayList<Phong> getByTT() {
        ArrayList<Phong> list = new ArrayList<>();
        String sql = "SELECT * FROM phong WHERE tinhTrang = 1;";
        try {
            ppst = getJDBCConnection().prepareStatement(sql);
            rs = ppst.executeQuery();
            while (rs.next()) {
                Phong phong = new Phong();
                phong.setIdPhong(rs.getInt("idPhong"));
                phong.setSoLuongNguoiChoi(rs.getInt("soLuongNguoiChoi"));
                phong.setSoLuongTienCuoc(rs.getInt("soTienCuoc"));
                phong.setTinhTrang(rs.getInt("tinhTrang"));
                list.add(phong);
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
    public Phong newPhong() {
        String sql = "SELECT * FROM phong WHERE tinhTrang = 0;";
        try {
            ppst = getJDBCConnection().prepareStatement(sql);
            rs = ppst.executeQuery();
            while (rs.next()) {
                Phong phong = new Phong();
                phong.setIdPhong(rs.getInt("idPhong"));
                phong.setSoLuongNguoiChoi(rs.getInt("soLuongNguoiChoi"));
                phong.setSoLuongTienCuoc(rs.getInt("soTienCuoc"));
                phong.setTinhTrang(rs.getInt("tinhTrang"));
                ppst.close();
                rs.close();
                getJDBCConnection().close();
                return phong;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;

    }
}
