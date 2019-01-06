package controller;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import model.Nguoi;
import model.NguoiDung;
import model.Phong;
import service.BanBeService;
import service.PhongNguoiChoiService;
import service.PhongService;
import service.impl.BanBeServiceImpl;
import service.impl.NguoiDungServiceImpl;
import service.impl.PhongNguoiChoiServiceImpl;
import service.impl.PhongServiceImpl;

public class WorkerThread extends Thread implements Key {

    private Socket socket;
    private NguoiDungServiceImpl nguoiDungService;
    private PhongService phongService;
    private BanBeService banBeService;
    private PhongNguoiChoiService phongNguoiChoiService;

    public WorkerThread(Socket socket) {
        this.socket = socket;
        nguoiDungService = new NguoiDungServiceImpl();
        phongService = new PhongServiceImpl();
        banBeService = new BanBeServiceImpl();
        phongNguoiChoiService = new PhongNguoiChoiServiceImpl();
    }

    public void run() {
        try {
            ObjectOutputStream os = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream is = new ObjectInputStream(socket.getInputStream());
            while (true) {
                String nhan = (String) is.readObject(); // Receive data from client
                String gui;
                if (nhan.equals(UPDATE)) {
                    System.out.println("---------------------------------");
                    System.out.println("Nhận đc yêu cầu cập nhật dữ liệu từ Client: " + socket);
                    NguoiDung nd = (NguoiDung) is.readObject();
                    nguoiDungService.edit(nd);
                    gui = "Cập nhật thông tin thành công";
                    os.writeObject(gui);
                    System.out.println(gui);

                } else if (nhan.equals(LOGIN)) {
                    System.out.println("---------------------------------");
                    System.out.println("Nhận được yêu cầu login từ Client: " + socket);
                    System.out.println("Đợi client gửi dữ liêu login...");
                    os.writeObject("Server nhận được yêu cầu đăng nhập từ Client");
                    NguoiDung nguoiDung = (NguoiDung) is.readObject();
                    System.out.println("Kiểm tra login người dùng");
                    if (nguoiDungService.checkLogin(nguoiDung)) {
                        System.out.println("Login hợp lệ. Gửi lại cho client...");
                        NguoiDung nd1 = nguoiDungService.getByUsernameNguoiDung(nguoiDung.getUsername());
                        nd1.setIpAddress(socket.getInetAddress().toString().substring(1));
                        nd1.setPort(socket.getPort());
                        nd1.setTinhTrang(1);
                        nguoiDungService.edit(nd1);
                        os.writeObject(nd1);
                    } else {
                        System.out.println("Login không hợp lệ. Gửi lại cho client...");
                        os.writeObject(null);
                    }
                } else if (nhan.equals(DANGKY)) {
                    System.out.println("---------------------------------");
                    System.out.println("Nhận đc yêu cầu đăng ký từ Client");
                    System.out.println("Server đợi Client gửi thông tin. " + socket);
                    gui = "Server nhận đc yêu cầu đăng ký tài khoản";
                    os.writeObject(gui);
                    Object object = is.readObject();
                    if (object instanceof NguoiDung) {
                        NguoiDung ndDangKy = (NguoiDung) (Nguoi) object;
                        System.out.println("Server kiểm tra thông tin,....");
                        if (nguoiDungService.checkUsername(ndDangKy.getUsername())) {
                            System.out.println("Thêm người dung thành công. Gửi trả lời đến CLient,... ");
                            ndDangKy.setLoaiHang(HOC_VIEN);
                            ndDangKy.setAvata(AVA_DEFAULT);
                            ndDangKy.setTien(TIEN_DEFAULT);
                            nguoiDungService.add(ndDangKy);
                            os.writeObject(ndDangKy);
                        } else {
                            System.out.println("Thêm người dùng thất bại. Gửi trả lời đến CLient,... ");
                            os.writeObject(null);
                        }
                    }
                } else if (nhan.equals(DS_PHONG)) {
                    System.out.println("---------------------------------");
                    System.out.println("Nhận đc yêu cầu lấy danh sách phòng từ Client: " + socket);
                    System.out.println("Server đang kiểm tra,....");
                    ArrayList<Phong> list = phongService.getByTT();
                    if (list.isEmpty()) {
                        os.writeObject("Chưa có phòng nào đc tạo");
                        System.out.println("Chưa có phòng nào đc tạo");
                        os.writeObject(null);
                    } else {
                        os.writeObject("Ds phong dc gui ve");
                        os.writeObject(list);
                        System.out.println("Danh sách phòng được gửi về");
                    }

                } else if (nhan.equals(DS_BANBE)) {
                    System.out.println("---------------------------------");
                    System.out.println("Nhận đc yêu cầu lấy danh sách bạn bè từ Client: " + socket);
                    System.out.println("Server đang kiểm tra,....");
                    int idNguoiDung = (int) is.readObject();
                    ArrayList<Nguoi> list = banBeService.getDsBanBe(idNguoiDung);
                    if (list == null) {
                        os.writeObject("Danh sách bạn bè rỗng");
                        System.out.println("Danh sách bạn bè rỗng");
                        os.writeObject(null);
                    } else {
                        os.writeObject("Ds bạn bè dc gui ve");
                        System.out.println("Danh sách bạn bè được gửi về");
                        os.writeObject(list);

                    }

                } else if (nhan.equals(TOP_SERVER)) {
                    System.out.println("---------------------------------");
                    System.out.println("Nhận đc yêu cầu lấy danh sách top-server từ Client: " + socket);
                    System.out.println("Server đang kiểm tra,....");
                    ArrayList<Nguoi> list = nguoiDungService.getTop10();
                    if (list == null) {
                        os.writeObject("Xảy ra lỗi!~~");
                        System.out.println("Danh sách không được gửi về. Xảy ra lỗi");
                        os.writeObject(null);
                    } else {
                        os.writeObject("Ds top-server dc gui ve");
                        System.out.println("Danh sách được gửi về thành công");
                        os.writeObject(list);

                    }

                } else if (nhan.equals(DANG_XUAT)) {
                    System.out.println("---------------------------------");
                    System.out.println("Nhận đc yêu cầu đăng xuất từ Client: " + socket);
                    System.out.println("Server đang thực hiện,....");
                    Object obj = is.readObject();
                    NguoiDung nd = (NguoiDung) obj;
                    nd.setTinhTrang(0);
                    nguoiDungService.edit(nd);
                    gui = "Đăng xuất thành công";
                    os.writeObject(gui);
                    System.out.println(gui);
                } else if (nhan.equals(SEARCH_P)) {
                    System.out.println("---------------------------------");
                    System.out.println("Nhận đc yêu cầu tìm phòng từ Client: " + socket);
                    Object obj = is.readObject();
                    int maP = (Integer) obj;
                    Phong phong = phongService.getById(maP);
                    if (phong != null && phong.getTinhTrang() == 1) {
                        os.writeObject(phong);
                    } else {
                        os.writeObject(null);
                    }

                } else if (nhan.equals(TAO_P)) {
                    System.out.println("---------------------------------");
                    System.out.println("Nhận đc yêu cầu tạo phòng từ Client: " + socket);
                    System.out.println("Server đang kiểm tra,...");
                    Integer soTien = (Integer) is.readObject();
                    NguoiDung nd = (NguoiDung) is.readObject();
                    //thuc hien yeu cau....
                    Phong phong = phongService.newPhong();
                    if (phong != null) {
                        phong.setTinhTrang(1);
                        phong.setSoLuongTienCuoc(soTien);
                        ArrayList<NguoiDung> list = new ArrayList<>();
                        nd.setRole(1);
                        list.add(nd);
                        phong.setDsNguoiChoi(list);
                        phongService.edit(phong);
                        os.writeObject(phong);
                        gui = "Tạo phòng thành công";
                        System.out.println(gui);
                        os.writeObject(gui);
                    } else {
                        os.writeObject(null);
                        gui = "Lỗi đã quá tải. Mời tạo lại sau";
                        System.out.println(gui);
                        os.writeObject(gui);
                    }
                } else if (nhan.equals(XOA_NGUOI_DUNG)) {
                    System.out.println("---------------------------------");
                    System.out.println("Nhận đc yêu cầu người dùng thoát phòng từ Client: " + socket);
                    System.out.println("Server đang thực hiện xóa người dùng khỏi danh sách người dùng trong phòng,...");
                    Nguoi nguoi = (Nguoi) is.readObject();
                    Phong phong = (Phong) is.readObject();
                    System.out.println(nguoi.getIdNguoiDung());
                    System.out.println(phong.getIdPhong());
                    phongNguoiChoiService.delete(nguoi.getIdNguoiDung());
                    gui = "Xóa thành công";
                    System.out.println(gui);
                    os.writeObject(gui);
                    phong.setDsNguoiChoi(phongNguoiChoiService.dsNguoiChoiTrongPhong(phong.getIdPhong()));
                    if (phong.getDsNguoiChoi().size() == 1) {
                        NguoiDung ncConLai = phongNguoiChoiService.dsNguoiChoiTrongPhong(phong.getIdPhong()).get(0);
                        ncConLai.setRole(1);
                        phongNguoiChoiService.edit(phong.getIdPhong(), ncConLai);
                        phong.setSoLuongNguoiChoi(1);
                        phong.setDsNguoiChoi(phongNguoiChoiService.dsNguoiChoiTrongPhong(phong.getIdPhong()));
                        phongService.edit(phong);
                        Socket s = new Socket(ncConLai.getIpAddress(), 21656);
                        System.out.println("Server gửi lại dữ liệu đến cho Client: " + s);
                        ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
                        ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
                        oos.writeObject("XOA");
                        oos.writeObject(phong);
                        System.out.println("Gửi yêu câu thay đổi TC");
                    } else if (phong.getDsNguoiChoi().size() == 0) {
                        phongService.edit(phong);
                    }
                } else if (nhan.equals(GET_DL)) {
                    System.out.println("---------------------------------");
                    System.out.println("Nhận đc yêu cầu lấy dl phòng từ Client: " + socket);
                    System.out.println("Server đang kiểm tra,...");
                    int IdPhong = (int) is.readObject();
                    Phong phong = phongService.getById(IdPhong);
                    gui = "Phòng được gửi về";
                    os.writeObject(gui);
                    os.writeObject(phong);

                } else if (nhan.equals(VAO_P)) {
                    System.out.println("---------------------------------");
                    System.out.println("Nhận đc yêu cầu vào phòng từ Client: " + socket);
                    System.out.println("Server đang kiểm tra,....");
                    int maP = (int) is.readObject();
                    NguoiDung nd = (NguoiDung) is.readObject();
                    nd.setRole(0);
                    Phong p = phongService.getById(maP);
                    ArrayList<NguoiDung> list = p.getDsNguoiChoi();
                    list.add(nd);
                    p.setDsNguoiChoi(list);
                    p.setSoLuongNguoiChoi(p.getDsNguoiChoi().size());
                    phongService.edit(p);
                    gui = "vào phòng OK";
                    os.writeObject(gui);
                    System.out.println(gui);
                    System.out.println(p.getDsNguoiChoi().get(0).getIpAddress());
                    Socket s = new Socket(p.getDsNguoiChoi().get(0).getIpAddress(), 21656);
                    ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
                    ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
                    System.out.println("Server gui yeu cau den Client: " + s);
                    oos.writeObject(UPDATE_P_ND);
                    oos.writeObject(p);
                } else if (nhan.equals("XOABAN")) {
                    System.out.println("---------------------------------");
                    System.out.println("Nhận đc yêu cầu xóa bạn bè từ Client: " + socket);
                    System.out.println("Server đang kiểm tra,....");
                    int idNguoiDung = (int) is.readObject();
                    int idBB = (int) is.readObject();
                    banBeService.delete(idNguoiDung, idBB);
                    gui = "Xóa bạn thành công";
                    os.writeObject(gui);
                    System.out.println(gui);
                } else if (nhan.equals("MOIBANBE")) {
                    System.out.println("---------------------------------");
                    System.out.println("Nhận đc yêu cầu mời bạn bè vào phòng từ Client: " + socket);
                    System.out.println("Server đang kiểm tra,....");

                    int idBB = (int) is.readObject();
                    int idP = (int) is.readObject();
                    NguoiDung nd = (NguoiDung) is.readObject();

                    try {
                        Socket s = new Socket(nguoiDungService.getByIdNguoiDung(idBB).getIpAddress(), 21656);
                        ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
                        ObjectInputStream ois = new ObjectInputStream(s.getInputStream());

                        oos.writeObject("CHOI_CUNG_K");
                        oos.writeObject(idP);
                        oos.writeObject(nd);

                        boolean dongY = (boolean) ois.readObject();
                        nhan = (String) ois.readObject();
                        System.out.println(nhan);

                        if (dongY) {
                            Phong p = phongService.getById(idP);
                            ArrayList<NguoiDung> dsNguoiChoi = p.getDsNguoiChoi();
                            dsNguoiChoi.add(nguoiDungService.getByIdNguoiDung(idBB));
                            p.setDsNguoiChoi(dsNguoiChoi);
                            phongService.edit(p);

                            os.writeObject(p);
                            oos.writeObject(p);
                        } else {
                            os.writeObject(null);
                        }
                    } catch (Exception e) {
                        System.err.println("Lỗi khi mời bạn bè");
                    }
                } else if (nhan.equals("MOI_THEM_BAN")) {
                    System.out.println("---------------------------------");
                    System.out.println("Nhận đc yêu cầu thêm bạn bè từ Client: " + socket);
                    System.out.println("Server đang kiểm tra,....");

                    int idBanBe = (int) is.readObject();
                    NguoiDung nguoiDung = (NguoiDung) is.readObject();
                    NguoiDung banBe = nguoiDungService.getByIdNguoiDung(idBanBe);
                    Socket s = new Socket(banBe.getIpAddress(), 21656);
                    ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
                    ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
                    oos.writeObject("THEM_BAN_BE");
                    oos.writeObject(nguoiDung);
                    int kq = (int) ois.readObject();
                    if (kq == 1) {
                        banBeService.add(banBe, nguoiDung);
                        banBeService.add(nguoiDung, banBe);
                        os.writeObject(1);
                        System.out.println("Thêm bạn thành công!");
                    } else {
                        os.writeObject(0);
                        System.out.println("Thêm bạn thất bại");
                    }
                } else if (nhan.equals("THONG_TIN_SAU_TD")) {
                    System.out.println("---------------------------------");
                    System.out.println("Nhận đc yêu cầu cập nhật thông tin người chơi sau trận đấu từ Client: " + socket);
                    System.out.println("Server đang kiểm tra,....");

                    int kq = (int) is.readObject();
                    int idNguoiDung = (int) is.readObject();
                    int mucTien = (int) is.readObject();
                    NguoiDung nd = nguoiDungService.getByIdNguoiDung(idNguoiDung);
                    if (kq == 1) {//người chơi thắng cuộc
                        nd.setSoTranThang(nd.getSoTranThang()+1);
                        nd.setTien(nd.getTien() + mucTien);
                        nd.setDiem(nd.getDiem()+1);
                        nguoiDungService.edit(nd);
                    } else if (kq == 2) {//người chơi thua cuộc
                        nd.setSoTranThua(nd.getSoTranThua()+1);
                        nd.setTien(nd.getTien() - mucTien);
                        nd.setDiem(nd.getDiem()-1);
                        nguoiDungService.edit(nd);
                    }
                    os.writeObject("Cập nhật thông tin sau trận đấu thành công");
                }

            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
