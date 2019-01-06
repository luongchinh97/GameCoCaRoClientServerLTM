package service;

import java.util.ArrayList;
import model.Nguoi;
import model.NguoiDung;

public interface BanBeService {

    void add(Nguoi nguoi, NguoiDung nguoiDung);

    void delete(int idNguoiDung, int idBB);

    Nguoi xemThongTin(int idNguoi);

    ArrayList<Nguoi> getDsBanBe(int idNguoiDung);
}
