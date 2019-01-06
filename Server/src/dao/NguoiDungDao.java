package dao;

import java.util.ArrayList;
import model.Nguoi;
import model.NguoiDung;

public interface NguoiDungDao {

    void add(NguoiDung nguoiDung);

    void edit(NguoiDung nguoiDung);

    void delete(int idNguoiDung);

    NguoiDung getByIdNguoiDung(int idNguoiDung);

    NguoiDung getByUsernameNguoiDung(String username);

    ArrayList<NguoiDung> getByNameNguoiDung(String name);

    ArrayList<NguoiDung> getAll();

    ArrayList<Nguoi> getTop10();

}
