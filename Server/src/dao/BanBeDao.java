package dao;

import java.util.ArrayList;
import model.Nguoi;
import model.NguoiDung;

public interface BanBeDao {

    void add(Nguoi nguoi, NguoiDung nguoiDung);//them ban be vao danh sach ban be cua NguoiDung

    void delete(int idNguoiDung, int idBB);//xoa ban be vao danh sach ban be cua NguoiDung

    Nguoi xemThongTin(int idNguoi);//xem thong tin

    ArrayList<Nguoi> getDsBanBe(int idNguoiDung);
}
