package service;

import java.util.ArrayList;
import model.Nguoi;
import model.NguoiDung;

public interface PhongNguoiChoiService {

    void add(int idPhong, Nguoi nguoi);

    void edit(int idPhong, Nguoi nguoi);

    void delete(int idNguoi);

    NguoiDung getNguoiChoi(int idNguoiChoi);

    ArrayList<NguoiDung> dsNguoiChoiTrongPhong(int idPhong);
}
