package service.impl;

import dao.BanBeDao;
import dao.impl.BanBeDaoImpl;
import java.util.ArrayList;
import model.Nguoi;
import model.NguoiDung;
import service.BanBeService;

public class BanBeServiceImpl implements BanBeService{
    private BanBeDao banBeDao = new BanBeDaoImpl();
    
    @Override
    public void add(Nguoi nguoi, NguoiDung nguoiDung) {
        banBeDao.add(nguoi, nguoiDung);
    }

    @Override
    public void delete(int idNguoiDung, int idBB) {
        banBeDao.delete(idNguoiDung, idBB);
    }

    @Override
    public Nguoi xemThongTin(int idNguoi) {
        return banBeDao.xemThongTin(idNguoi);
    }

    @Override
    public ArrayList<Nguoi> getDsBanBe(int idNguoiDung) {
        return banBeDao.getDsBanBe(idNguoiDung);
    }
    
}
