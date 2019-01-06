package service.impl;

import dao.PhongDao;
import dao.PhongNguoiChoiDao;
import dao.impl.PhongDaoImpl;
import dao.impl.PhongNguoiChoiImpl;
import java.util.ArrayList;
import model.Nguoi;
import model.NguoiDung;
import model.Phong;
import service.PhongService;

public class PhongServiceImpl implements PhongService {

    private PhongDao phongDAO = new PhongDaoImpl();
    private PhongNguoiChoiDao phongNguoiChoiDao = new PhongNguoiChoiImpl();

    @Override
    public void add(Phong phong) {
        phongDAO.add(phong);
    }

    @Override
    public void edit(Phong phong) {
        phongNguoiChoiDao.deletePhong(phong.getIdPhong());
        if (phong.getDsNguoiChoi().size() == 0) {
            phong.setTinhTrang(0);
            phong.setSoLuongNguoiChoi(0);
            phong.setSoLuongTienCuoc(0);
            phongDAO.edit(phong);
        }else{
            for (Nguoi nguoi : phong.getDsNguoiChoi()) {
                phongNguoiChoiDao.add(phong.getIdPhong(), nguoi);
            }
            phong.setTinhTrang(1);
            phongDAO.edit(phong);
                    
        }
    }

    @Override
    public void delete(int idPhong) {
        phongDAO.delete(idPhong);
    }

    @Override
    public Phong getById(int idPhong) {
        Phong phong = phongDAO.getById(idPhong);
        phong.setDsNguoiChoi(phongNguoiChoiDao.dsNguoiChoiTrongPhong(idPhong));
        phong.setSoLuongNguoiChoi(phong.getDsNguoiChoi().size());
        return phong;
    }

    @Override
    public ArrayList<Phong> getAll() {
        return phongDAO.getAll();
    }

    @Override
    public ArrayList<Phong> getByTT() {
        ArrayList<Phong> list = phongDAO.getByTT();
        for (Phong phong : list) {
            phong.setDsNguoiChoi(phongNguoiChoiDao.dsNguoiChoiTrongPhong(phong.getIdPhong()));
            phong.setSoLuongNguoiChoi(phong.getDsNguoiChoi().size());
        }
        return list;
    }

    @Override
    public Phong newPhong() {
        return phongDAO.newPhong();
    }

    @Override
    public void deletePhongTheoTT(int idPhong) {
        Phong phong = getById(idPhong);
        phong.setTinhTrang(0);
        phong.setSoLuongNguoiChoi(0);
        phong.setSoLuongTienCuoc(0);
        phongDAO.edit(phong);
    }

}
