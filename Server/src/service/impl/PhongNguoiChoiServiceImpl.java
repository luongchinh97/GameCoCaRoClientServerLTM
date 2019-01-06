package service.impl;

import dao.PhongNguoiChoiDao;
import dao.impl.PhongDaoImpl;
import dao.impl.PhongNguoiChoiImpl;
import java.util.ArrayList;
import model.Nguoi;
import model.NguoiDung;
import service.PhongNguoiChoiService;

public class PhongNguoiChoiServiceImpl implements PhongNguoiChoiService {

    private PhongNguoiChoiDao phongNguoiChoiDao = new PhongNguoiChoiImpl();
    private PhongDaoImpl phongDao = new PhongDaoImpl();

    @Override
    public void add(int idPhong, Nguoi nguoi) {
        phongNguoiChoiDao.add(idPhong, nguoi);
    }

    @Override
    public void edit(int idPhong, Nguoi nguoi) {
        phongNguoiChoiDao.edit(idPhong, nguoi);

    }

    @Override
    public void delete(int idNguoi) {
        phongNguoiChoiDao.delete(idNguoi);
    }

    @Override
    public NguoiDung getNguoiChoi(int idNguoiChoi) {
        return phongNguoiChoiDao.getNguoiChoi(idNguoiChoi);
    }

    @Override
    public ArrayList<NguoiDung> dsNguoiChoiTrongPhong(int idPhong) {
        return phongNguoiChoiDao.dsNguoiChoiTrongPhong(idPhong);
    }

}
