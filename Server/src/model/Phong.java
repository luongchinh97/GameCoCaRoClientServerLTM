package model;

import java.io.Serializable;
import java.util.ArrayList;

public class Phong implements Serializable {

    public static final long serialVersionUID = 2L;
    private int idPhong;
    private int soLuongNguoiChoi;
    private ArrayList<NguoiDung> dsNguoiChoi;
    private int soLuongTienCuoc;
    private int tinhTrang;//dc nguoi choi tao hay chua co nguoi choi nao tao

    public Phong() {
    }

    public Phong(int idPhong, int soLuongNguoiChoi, int soLuongTienCuoc, int tinhTrang) {
        this.idPhong = idPhong;
        this.soLuongNguoiChoi = soLuongNguoiChoi;
        this.soLuongTienCuoc = soLuongTienCuoc;
        this.tinhTrang = tinhTrang;
    }

    public int getIdPhong() {
        return idPhong;
    }

    public void setIdPhong(int idPhong) {
        this.idPhong = idPhong;
    }

    public int getSoLuongNguoiChoi() {
        return soLuongNguoiChoi;
    }

    public void setSoLuongNguoiChoi(int soLuongNguoiChoi) {
        this.soLuongNguoiChoi = soLuongNguoiChoi;
    }

    public ArrayList<NguoiDung> getDsNguoiChoi() {
        return dsNguoiChoi;
    }

    public void setDsNguoiChoi(ArrayList<NguoiDung> dsNguoiChoi) {
        this.dsNguoiChoi = dsNguoiChoi;
    }

    public int getSoLuongTienCuoc() {
        return soLuongTienCuoc;
    }

    public void setSoLuongTienCuoc(int soLuongTienCuoc) {
        this.soLuongTienCuoc = soLuongTienCuoc;
    }

    public int getTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(int tinhTrang) {
        this.tinhTrang = tinhTrang;
    }
    
}
