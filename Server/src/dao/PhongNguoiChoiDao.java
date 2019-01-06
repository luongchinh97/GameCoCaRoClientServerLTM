package dao;

import java.util.ArrayList;
import model.Nguoi;
import model.NguoiDung;

public interface PhongNguoiChoiDao {

    void add(int idPhong, Nguoi nguoi);

    void edit(int idPhong, Nguoi nguoi);

    void delete(int idNguoi);

    void deletePhong(int idPhong);

    NguoiDung getNguoiChoi(int idNguoiChoi);

    ArrayList<NguoiDung> dsNguoiChoiTrongPhong(int idPhong);
}
