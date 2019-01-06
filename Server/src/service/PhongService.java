package service;

import java.util.ArrayList;
import model.Phong;

public interface PhongService {

    void add(Phong phong);

    void edit(Phong phong);

    void delete(int idPhong);//xoa han phong ra khoi danh sach phong cua server

    Phong getById(int idPhong);

    Phong newPhong();

    ArrayList<Phong> getAll();

    ArrayList<Phong> getByTT();

    void deletePhongTheoTT(int idPhong);//sửa lại tình trạng của phong thành off
}
