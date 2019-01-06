package service;

import java.util.ArrayList;
import model.Nguoi;
import model.NguoiDung;

public interface NguoiDungService {

    void add(NguoiDung nguoiDung);

    void edit(NguoiDung nguoiDung);

    void delete(int idNguoiDung);

    NguoiDung getByIdNguoiDung(int idNguoiDung);

    NguoiDung getByUsernameNguoiDung(String username);

    ArrayList<NguoiDung> getByNameNguoiDung(String name);

    ArrayList<NguoiDung> getAll();

    boolean checkLogin(NguoiDung nguoiDung);

    boolean checkUsername(String username);

    ArrayList<Nguoi> getTop10();
    
    void updateData();
}
