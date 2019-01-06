package service.impl;

import dao.NguoiDungDao;
import dao.impl.NguoiDungDaoImpl;
import java.util.ArrayList;
import model.Nguoi;
import model.NguoiDung;
import service.NguoiDungService;

public class NguoiDungServiceImpl implements NguoiDungService {

    private NguoiDungDao nguoiDungDao = new NguoiDungDaoImpl();

    @Override
    public void add(NguoiDung nguoiDung) {
        nguoiDungDao.add(nguoiDung);
    }

    @Override
    public void edit(NguoiDung nguoiDung) {
        nguoiDungDao.edit(nguoiDung);
    }

    @Override
    public void delete(int idNguoiDung) {
        nguoiDungDao.delete(idNguoiDung);
    }

    @Override
    public NguoiDung getByIdNguoiDung(int idNguoiDung) {
        return nguoiDungDao.getByIdNguoiDung(idNguoiDung);
    }

    @Override
    public NguoiDung getByUsernameNguoiDung(String username) {
        return nguoiDungDao.getByUsernameNguoiDung(username);
    }

    @Override
    public ArrayList<NguoiDung> getByNameNguoiDung(String name) {
        return nguoiDungDao.getByNameNguoiDung(name);
    }

    @Override
    public ArrayList<NguoiDung> getAll() {
        return nguoiDungDao.getAll();
    }

    @Override
    public boolean checkLogin(NguoiDung nguoiDung) {
        if (getByUsernameNguoiDung(nguoiDung.getUsername()) != null && getByUsernameNguoiDung(nguoiDung.getUsername()).getPassword().equals(nguoiDung.getPassword())) {
            return true;
        }
        return false;
    }

    @Override
    public boolean checkUsername(String username) {
        if (getByUsernameNguoiDung(username) != null) {
            return false;
        }
        return true;
    }

    @Override
    public ArrayList<Nguoi> getTop10() {
        return nguoiDungDao.getTop10();
    }

    @Override
    public void updateData() {
        ArrayList<NguoiDung> list = getAll();
        for (NguoiDung nguoiDung : list) {
            nguoiDung.setXepHang(list.indexOf(nguoiDung));
            edit(nguoiDung);
        }
    }
}
