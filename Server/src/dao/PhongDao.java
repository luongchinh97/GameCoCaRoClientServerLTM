package dao;

import java.util.ArrayList;
import model.Phong;

public interface PhongDao {

    void add(Phong phong);

    void edit(Phong phong);

    void delete(int idPhong);

    Phong getById(int idPhong);

    Phong newPhong();
    
    ArrayList<Phong> getAll();

    ArrayList<Phong> getByTT();

}
