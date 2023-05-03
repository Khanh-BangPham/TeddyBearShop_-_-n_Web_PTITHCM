/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ptit.controller;

import ptit.DataAccess.*;
import ptit.Entities.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.request;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author ADMIN
 */
@Controller
@Transactional

public class SanPhamController {
	@Autowired
	SessionFactory factory;
    @RequestMapping(value = "/showsanpham")
    public String getSanPham(Model model, Integer offset, Integer maxResult) {
    	Session s = factory.getCurrentSession();
        ShoppingDA sp = new ShoppingDA(s);
        // hien thi san pham

        List<SanPham> list = sp.getProPage1(offset, maxResult);
        model.addAttribute("sanpham", list);

        model.addAttribute("offset", offset);
        s = factory.getCurrentSession();
        model.addAttribute("numberProductDetail", sp.getNumberProductDetail());
        // hien thi danh muc
        List<NhomSanPham> l = sp.getNhom();
        model.addAttribute("nhomsanpham", l);
        // hien thi quang cao
        List<QuangCao> ls = sp.getAllQuangCao();
        model.addAttribute("quangcao", ls);
        return "home";
    }

    @RequestMapping(value = "/detailsanpham")
    public String detailsanpham(int maSp, Model model) {
    	Session s = factory.getCurrentSession();
        ShoppingDA sp = new ShoppingDA(s);
        model.addAttribute("sanpham", sp.getById(maSp));

        // hien thi danh muc
        model.addAttribute("donHang", new DonHang());

        List<NhomSanPham> l = sp.getNhom();
        model.addAttribute("nhomsanpham", l);
        return "details";
    }

    @RequestMapping(value = "/detailquangcao")
    public String detailquangcao(int maQc, Model model) {
    	Session s = factory.getCurrentSession();
        ShoppingDA sp = new ShoppingDA(s);
        model.addAttribute("quangcao", sp.getByIdQuangCao(maQc));

        // hien thi danh muc
        List<NhomSanPham> l = sp.getNhom();
        model.addAttribute("nhomsanpham", l);
        return "bannerdetails";
    }

    @RequestMapping(value = "/searchsanpham")
    public String searchsanpham(String tensp, Model model) {
    	Session s = factory.getCurrentSession();
        ShoppingDA sp = new ShoppingDA(s);
        List<SanPham> list = sp.search(tensp);
        model.addAttribute("sanpham", list);

        // hien thi danh muc
        List<NhomSanPham> l = sp.getNhom();
        model.addAttribute("nhomsanpham", l);

        List<QuangCao> ls = sp.getAllQuangCao();
        model.addAttribute("quangcao", ls);
        return "home";
    }

    @RequestMapping(value = "/searchbynhomsp")
    public String getByNhomSp(int maNhomSp, Model model) {
    	Session s = factory.getCurrentSession();
        ShoppingDA sp = new ShoppingDA(s);
        List<SanPham> list = sp.getByNhomSp(maNhomSp);
        model.addAttribute("sanpham", list);

        // hien thi danh muc
        List<NhomSanPham> l = sp.getNhom();
        model.addAttribute("nhomsanpham", l);

        //Hien thi quang cáo
        List<QuangCao> ls = sp.getAllQuangCao();
        model.addAttribute("quangcao", ls);

        return "home";
    }

    @RequestMapping(value = "/loginuser")
    public String dangnhap(Model model) {
    	Session s = factory.getCurrentSession();
        ShoppingDA sp = new ShoppingDA(s);
        KhachHang kh = new KhachHang();
        model.addAttribute("khachHang", kh);

        List<NhomSanPham> l = sp.getNhom();
        model.addAttribute("nhomsanpham", l);
        return "login";
    }

    @RequestMapping(value = "/dangnhap")
    public String Login(Integer offset, Integer maxResult, HttpServletRequest request, HttpServletResponse response, KhachHang kh, ModelMap mm, Model model) {
    	Session s = factory.getCurrentSession();
        ShoppingDA da = new ShoppingDA(s);
        KhachHang k = da.Login(kh.getTenTk(), kh.getMatKhau());
        if (k != null) {
            HttpSession session = request.getSession();
            session.setAttribute("ten", k.getTenKh());
            session.setAttribute("email", k.getEmail());
            session.setAttribute("diachi", k.getDiaChi());
            session.setAttribute("sodienthoai", k.getSoDienThoai());
            session.setAttribute("ma", k.getMaKh());

            if (session.getAttribute("myCartItems") != null) {
                model.addAttribute("donHang", new DonHang());

                List<NhomSanPham> ls = da.getNhom();
                model.addAttribute("nhomsanpham", ls);

                List<VanChuyen> list = da.getVanChuyen();
                model.addAttribute("vanchuyen", list);

                List<ThanhToan> l = da.getThanhToan();
                model.addAttribute("thanhtoan", l);
                return "checkout";
            }


            List<SanPham> list = da.getProPage1(offset, maxResult);
            model.addAttribute("sanpham", list);

            model.addAttribute("offset", offset);
            model.addAttribute("numberProductDetail", da.getNumberProductDetail());
            // hien thi danh muc
            List<NhomSanPham> l = da.getNhom();
            model.addAttribute("nhomsanpham", l);
            // hien thi quang cao
            List<QuangCao> ls = da.getAllQuangCao();
            model.addAttribute("quangcao", ls);
            return "home";
        } else {
            mm.put("msg", "Tài khoản hoặc mật khẩu không đúng");
            return "login";
        }

    }

    @RequestMapping(value = "/dangKyKhachHang")
    public String savebook(HttpServletRequest request, KhachHang kh, BindingResult result, ModelMap mm, String email, String tenKh, String tenTk, String matKhau, String diaChi, Integer soDienThoai, Model model) {
    	Session s = factory.getCurrentSession();
        ShoppingDA da = new ShoppingDA(s);

        if (tenTk.length() < 6) {
            mm.put("msgchecktk", "Độ dài tài khoản > 6");
            return "login";
        }

        if (matKhau.length() < 6) {
            mm.put("msgcheckmk", "Độ dài mật khẩu > 6");
            return "login";
        }

        KhachHang ks = da.checkTaiKhoan(kh.getTenTk());
        if (ks != null) {
            HttpSession session = request.getSession();
            session.setAttribute("ten", ks.getTenKh());

            if (session.getAttribute("ten") != null) {
                session.invalidate();
                mm.put("msgcheck1", "Tài khoản đã có người đăng ký");
                return "login";
            }

        }

        KhachHang k = da.checkEmail(kh.getEmail());
        if (k != null) {
            HttpSession session = request.getSession();
            session.setAttribute("ten", k.getTenKh());

            if (session.getAttribute("ten") != null) {
                session.invalidate();
                mm.put("msgcheck", "Email đã có người đăng ký");
                return "login";
            }
        }
        kh.setTenKh(tenKh);
        kh.setTenTk(tenTk);
        kh.setMatKhau(matKhau);
        kh.setDiaChi(diaChi);
        kh.setSoDienThoai(soDienThoai);
        kh.setEmail(email);
        kh.setTrangThai(Boolean.TRUE);
        da.insertUser(kh);
        model.addAttribute("khachHang", kh);

        mm.put("msgsussessdk", "Bạn đã đăng ký thành công");
        return "login";
    }

    @RequestMapping(value = "/dangXuatKhachHang")
    public String Logout(Integer offset, Integer maxResult, HttpServletRequest request, HttpServletResponse response, KhachHang kh, ModelMap mm, Model model) {

        HttpSession session = request.getSession();
        session.invalidate();

    	Session s = factory.getCurrentSession();
        ShoppingDA sp = new ShoppingDA(s);
        List<SanPham> list = sp.getProPage1(offset, maxResult);
        model.addAttribute("sanpham", list);

        model.addAttribute("offset", offset);
        model.addAttribute("numberProductDetail", sp.getNumberProductDetail());
        // hien thi danh muc
        List<NhomSanPham> l = sp.getNhom();
        model.addAttribute("nhomsanpham", l);

        List<QuangCao> ls = sp.getAllQuangCao();
        model.addAttribute("quangcao", ls);

        return "home";
    }

    @RequestMapping(value = "/uathich")
    public String UaThich(HttpSession session,Integer offset, Integer maxResult, int maKh, int maSp, Model model) {
    	Session s = factory.getCurrentSession();
        ShoppingDA da = new ShoppingDA(s);

        UaThich ut = new UaThich();
        KhachHang kh = new KhachHang(maKh);
        ut.setMaKh(kh);
        SanPham sp = new SanPham(maSp);
        ut.setMaSp(sp);
        da.insertUaThich(ut);
        session.setAttribute("sanphamuathich", da.getUaThichByKhachHang(maKh));
        
        List<SanPham> list = da.getProPage1(offset, maxResult);
        model.addAttribute("sanpham", list);
        
        // hien thi danh muc
        model.addAttribute("offset", offset);
        model.addAttribute("numberProductDetail", da.getNumberProductDetail());

        List<NhomSanPham> l = da.getNhom();
        model.addAttribute("nhomsanpham", l);
        // hien thi quang cao
        List<QuangCao> ls = da.getAllQuangCao();
        model.addAttribute("quangcao", ls);
        return "home";

    }

    @RequestMapping(value = "/danhsachuathich")
    public String danhsachuathich(int maKh, Model model) {
    	Session s = factory.getCurrentSession();
        ShoppingDA sp = new ShoppingDA(s);
        
        model.addAttribute("sanphamuathich", sp.getUaThichByKhachHang(maKh));
        
        // hien thi danh muc
        List<NhomSanPham> l = sp.getNhom();
        model.addAttribute("nhomsanpham", l);
        return "likelist";
    }

    @RequestMapping(value = "/removelikelist")
    public String removelikelist(HttpServletRequest request,int maKh, int maUt, Model model) {
    	Session s = factory.getCurrentSession();
        ShoppingDA sp = new ShoppingDA(s);
        sp.delete(maUt);

        
        List<NhomSanPham> l = sp.getNhom();
        model.addAttribute("nhomsanpham", l);
         
        model.addAttribute("sanphamuathich", sp.getUaThichByKhachHang(maKh));

        return "likelist";
    }

    @RequestMapping(value = "/thongtinkhachhang")
    public String thongtinkhachhang(int maKh, Model model) {
    	Session s = factory.getCurrentSession();
        ShoppingDA sp = new ShoppingDA(s);
        model.addAttribute("khachHang", sp.getMaKhachHang(maKh));

        // hien thi danh muc
        List<NhomSanPham> l = sp.getNhom();
        model.addAttribute("nhomsanpham", l);
        return "userdetails";
    }

    @RequestMapping(value = "capnhapkhachhang")
    public String capnhapkhachhang(int maKh, Model model) {
    	Session s = factory.getCurrentSession();
        ShoppingDA sp = new ShoppingDA(s);
        model.addAttribute("khachHang", sp.getMaKhachHang(maKh));

        // hien thi danh muc
        List<NhomSanPham> l = sp.getNhom();
        model.addAttribute("nhomsanpham", l);
        return "updateuser";
    }

    @RequestMapping(value = "/donhangkhachhang")
    public String donhangkhachhang(int maKh, Model model) {
    	Session s = factory.getCurrentSession();
        ShoppingDA sp = new ShoppingDA(s);
        List<DonHang> list = sp.getByKhachHang(maKh);
        model.addAttribute("donhang", list);

        // hien thi danh muc
        List<NhomSanPham> l = sp.getNhom();
        model.addAttribute("nhomsanpham", l);

        return "orderbyuser";
    }

    @RequestMapping(value = "/chitietdonhang")
    public String chitietdonhang(int maDh, Model model) {
    	Session s = factory.getCurrentSession();
        ShoppingDA sp = new ShoppingDA(s);
        List<ChiTietDonHang> list = sp.getByDonHang(maDh);
        model.addAttribute("chitietdonhang", list);

        // hien thi danh muc
        List<NhomSanPham> l = sp.getNhom();
        model.addAttribute("nhomsanpham", l);

        return "detailsorder";
    }

    @RequestMapping(value = "/luukhachhang")
    public String luukhachhang(int maKh, KhachHang kh, BindingResult result, ModelMap mm, String tenKh, String tenTk, String matKhau, String diaChi, Integer soDienThoai, String email, Model model) {
    	Session s = factory.getCurrentSession();
        ShoppingDA sp = new ShoppingDA(s);
        KhachHang k = new KhachHang(maKh);
        kh.setTenKh(tenKh);
        kh.setTenTk(tenTk);
        kh.setMatKhau(matKhau);
        kh.setDiaChi(diaChi);
        kh.setSoDienThoai(soDienThoai);
        kh.setEmail(email);
        kh.setTrangThai(Boolean.TRUE);
        sp.updateKhachHang(kh);

        List<NhomSanPham> l = sp.getNhom();
        model.addAttribute("nhomsanpham", l);

        return "userdetails";
    }

    @RequestMapping(value = "/checkedvanchuyen")
    public String checkedvanchuyen(HttpServletRequest request, int maVc, Model model) {
    	Session s = factory.getCurrentSession();
        ShoppingDA sp = new ShoppingDA(s);
        HttpSession session = request.getSession();
        session.setAttribute("mavanchuyen", sp.getMaVanChuyen(maVc));
        List<VanChuyen> list = sp.getVanChuyen();
        model.addAttribute("vanchuyen", list);

        // hien thi danh muc
        List<ThanhToan> l = sp.getThanhToan();
        model.addAttribute("thanhtoan", l);
        // hien thi danh muc
        return "redirect:/checkout.htm";
    }

    @RequestMapping(value = "/checkedthanhtoan")
    public String checkedthanhtoan(HttpServletRequest request, int maTt, Model model) {
    	Session s = factory.getCurrentSession();
        ShoppingDA sp = new ShoppingDA(s);
        HttpSession session = request.getSession();
        session.setAttribute("mathanhtoan", sp.getMaThanhToan(maTt));

        List<VanChuyen> list = sp.getVanChuyen();
        model.addAttribute("vanchuyen", list);

        // hien thi danh muc
        List<ThanhToan> l = sp.getThanhToan();
        model.addAttribute("thanhtoan", l);
        // hien thi danh muc
        return "redirect:/checkout.htm";
    }

    @RequestMapping(value = "/add")
    public String viewAdd(Integer offset, Integer maxResult, ModelMap mm, HttpSession session, int maSp, Model model) {
        HashMap<Integer, GioHang> cartItems = (HashMap<Integer, GioHang>) session.getAttribute("myCartItems");
        if (cartItems == null) {
            cartItems = new HashMap<>();
        }

    	Session s = factory.getCurrentSession();
        ShoppingDA sp = new ShoppingDA(s);
        SanPham sanpham = sp.getById(maSp);

        if (sanpham != null) {
            if (cartItems.containsKey(maSp)) {
                GioHang item = cartItems.get(maSp);
                item.setSanPham(sanpham);
                item.setSoLuong(item.getSoLuong() + 1);
                cartItems.put(maSp, item);
            } else {
                GioHang item = new GioHang();
                item.setSanPham(sanpham);
                item.setSoLuong(1);
                cartItems.put(maSp, item);
            }
        }
        session.setAttribute("myCartItems", cartItems);
        session.setAttribute("myCartTotal", totalPrice(cartItems));
        session.setAttribute("myCartNum", cartItems.size());

        List<SanPham> list = sp.getProPage1(offset, maxResult);
        model.addAttribute("sanpham", list);

        model.addAttribute("offset", offset);
        model.addAttribute("numberProductDetail", sp.getNumberProductDetail());
        // hien thi danh muc
        List<NhomSanPham> l = sp.getNhom();
        model.addAttribute("nhomsanpham", l);

        List<QuangCao> ls = sp.getAllQuangCao();
        model.addAttribute("quangcao", ls);
        return "home";
    }

    @RequestMapping(value = "/addlikelisttocart")
    public String addlikelisttocart(int maKh, int maUt, ModelMap mm, HttpSession session, int maSp, Model model) {
        HashMap<Integer, GioHang> cartItems = (HashMap<Integer, GioHang>) session.getAttribute("myCartItems");
        if (cartItems == null) {
            cartItems = new HashMap<>();
        }

    	Session s = factory.getCurrentSession();
        ShoppingDA sp = new ShoppingDA(s);
        SanPham sanpham = sp.getById(maSp);

        if (sanpham != null) {
            if (cartItems.containsKey(maSp)) {
                GioHang item = cartItems.get(maSp);
                item.setSanPham(sanpham);
                item.setSoLuong(item.getSoLuong() + 1);
                cartItems.put(maSp, item);
            } else {
                GioHang item = new GioHang();
                item.setSanPham(sanpham);
                item.setSoLuong(1);
                cartItems.put(maSp, item);
            }
        }
        session.setAttribute("myCartItems", cartItems);
        session.setAttribute("myCartTotal", totalPrice(cartItems));
        session.setAttribute("myCartNum", cartItems.size());

        sp.delete(maUt);

        List<NhomSanPham> l = sp.getNhom();
        model.addAttribute("nhomsanpham", l);

        model.addAttribute("sanphamuathich", sp.getUaThichByKhachHang(maKh));

        return "likelist";
    }

    @RequestMapping(value = "/sub")
    public String viewSub(ModelMap mm, HttpSession session, int maSp, int soLuong, Model model) {
        HashMap<Integer, GioHang> cartItems = (HashMap<Integer, GioHang>) session.getAttribute("myCartItems");
        if (cartItems == null) {
            cartItems = new HashMap<>();
        }

    	Session s = factory.getCurrentSession();
        ShoppingDA sp = new ShoppingDA(s);
        SanPham sanpham = sp.getById(maSp);

        if (sanpham != null) {
            if (cartItems.containsKey(maSp)) {
                GioHang item = cartItems.get(maSp);
                item.setSanPham(sanpham);
                item.setSoLuong(item.getSoLuong() + soLuong);
                cartItems.put(maSp, item);
            } else {
                GioHang item = new GioHang();
                item.setSanPham(sanpham);
                item.setSoLuong(soLuong);
                cartItems.put(maSp, item);
            }
        }

        session.setAttribute("myCartItems", cartItems);
        session.setAttribute("myCartTotal", totalPrice(cartItems));
        session.setAttribute("myCartNum", cartItems.size());

        List<NhomSanPham> ls = sp.getNhom();
        model.addAttribute("nhomsanpham", ls);

        List<VanChuyen> list = sp.getVanChuyen();
        model.addAttribute("vanchuyen", list);
        // hien thi danh muc
        List<ThanhToan> l = sp.getThanhToan();
        model.addAttribute("thanhtoan", l);
        model.addAttribute("donHang", new DonHang());
        return "checkout";
    }

    @RequestMapping(value = "/giam")
    public String viewGiam(Integer offset, Integer maxResult, ModelMap mm, HttpSession session, int maSp, Model model) {
        HashMap<Integer, GioHang> cartItems = (HashMap<Integer, GioHang>) session.getAttribute("myCartItems");
        if (cartItems == null) {
            cartItems = new HashMap<>();
        }

    	Session s = factory.getCurrentSession();
        ShoppingDA sp = new ShoppingDA(s);
        SanPham sanpham = sp.getById(maSp);

        if (sanpham != null) {
            if (cartItems.containsKey(maSp)) {
                GioHang item = cartItems.get(maSp);
                item.setSanPham(sanpham);
                item.setSoLuong(item.getSoLuong() - 1);
                cartItems.put(maSp, item);
                if (item.getSoLuong() == 0) {
                    cartItems.remove(maSp);
                    session.setAttribute("myCartItems", cartItems.remove(cartItems));
                    session.setAttribute("myCartTotal", 0);
                    session.setAttribute("myCartNum", 0);
                }
            }
        }

        List<NhomSanPham> ls = sp.getNhom();
        model.addAttribute("nhomsanpham", ls);

        List<VanChuyen> list = sp.getVanChuyen();
        model.addAttribute("vanchuyen", list);
        // hien thi danh muc
        List<ThanhToan> l = sp.getThanhToan();
        model.addAttribute("thanhtoan", l);
        model.addAttribute("donHang", new DonHang());
        return "checkout";
    }

    @RequestMapping(value = "/tang")
    public String viewTang(Integer offset, Integer maxResult, ModelMap mm, HttpSession session, int maSp, Model model) {
        HashMap<Integer, GioHang> cartItems = (HashMap<Integer, GioHang>) session.getAttribute("myCartItems");
        if (cartItems == null) {
            cartItems = new HashMap<>();
        }

    	Session s = factory.getCurrentSession();
        ShoppingDA sp = new ShoppingDA(s);
        SanPham sanpham = sp.getById(maSp);

        if (sanpham != null) {
            if (cartItems.containsKey(maSp)) {
                GioHang item = cartItems.get(maSp);
                item.setSanPham(sanpham);
                item.setSoLuong(item.getSoLuong() + 1);
                cartItems.put(maSp, item);
            }
        }
        session.setAttribute("myCartItems", cartItems);
        session.setAttribute("myCartTotal", totalPrice(cartItems));
        session.setAttribute("myCartNum", cartItems.size());

        List<NhomSanPham> ls = sp.getNhom();
        model.addAttribute("nhomsanpham", ls);

        List<VanChuyen> list = sp.getVanChuyen();
        model.addAttribute("vanchuyen", list);
        // hien thi danh muc
        List<ThanhToan> l = sp.getThanhToan();
        model.addAttribute("thanhtoan", l);
        model.addAttribute("donHang", new DonHang());
        return "checkout";
    }

    @RequestMapping(value = "/remove")
    public String viewRemove(ModelMap mm, HttpSession session, int maSp) {
        HashMap<Integer, GioHang> cartItems = (HashMap<Integer, GioHang>) session.getAttribute("myCartItems");
        if (cartItems == null) {
            cartItems = new HashMap<>();
        }
        if (cartItems.containsKey(maSp)) {
            cartItems.remove(maSp);
        }
        session.setAttribute("myCartItems", cartItems.remove(cartItems));
        session.setAttribute("myCartTotal", 0);
        session.setAttribute("myCartNum", 0);
        return "checkout";
    }

    @RequestMapping(value = "/removehome")
    public String viewRemoveHome(Integer offset, Integer maxResult, ModelMap mm, HttpSession session, int maSp, Model model) {
        HashMap<Integer, GioHang> cartItems = (HashMap<Integer, GioHang>) session.getAttribute("myCartItems");
        if (cartItems == null) {
            cartItems = new HashMap<>();
        }
        if (cartItems.containsKey(maSp)) {
            cartItems.remove(maSp);
        }
        session.setAttribute("myCartItems", cartItems);
        session.setAttribute("myCartTotal", totalPrice(cartItems));
        session.setAttribute("myCartNum", cartItems.size());

    	Session s = factory.getCurrentSession();
        ShoppingDA sp = new ShoppingDA(s);
        List<SanPham> list = sp.getProPage1(offset, maxResult);
        model.addAttribute("sanpham", list);

        model.addAttribute("offset", offset);
        model.addAttribute("numberProductDetail", sp.getNumberProductDetail());
        // hien thi danh muc
        List<NhomSanPham> l = sp.getNhom();
        model.addAttribute("nhomsanpham", l);

        List<QuangCao> ls = sp.getAllQuangCao();
        model.addAttribute("quangcao", ls);
        return "home";
    }

    public double totalPrice(HashMap<Integer, GioHang> cartItems) {
        int count = 0;
        for (Map.Entry<Integer, GioHang> list : cartItems.entrySet()) {
            count += list.getValue().getSanPham().getGia() * list.getValue().getSoLuong();
        }
        return count;
    }

    @RequestMapping(value = "checkout")
    public String checkout(Model model) {
    	Session s = factory.getCurrentSession();
        ShoppingDA sp = new ShoppingDA(s);

        List<NhomSanPham> ls = sp.getNhom();
        model.addAttribute("nhomsanpham", ls);

        List<VanChuyen> list = sp.getVanChuyen();
        model.addAttribute("vanchuyen", list);
        // hien thi danh muc
        List<ThanhToan> l = sp.getThanhToan();
        model.addAttribute("thanhtoan", l);
        model.addAttribute("donHang", new DonHang());
        return "checkout";
    }

    @RequestMapping(value = "/muahang")
    public String viewCheckout(Integer offset, Integer maxResult, Model model, ModelMap mm, HttpSession session, String tenKh, String email, String diaChi, String soDienThoai, double tongTien, int maKh, int maTt, int maVc) {
        Map<Integer, GioHang> cartItems = (Map<Integer, GioHang>) session.getAttribute("myCartItems");
        if (cartItems == null) {
            mm.put("msgmuahang", "Bạn cần lựa chọn sản phẩm");
        	Session s = factory.getCurrentSession();
            ShoppingDA sp = new ShoppingDA(s);

            List<NhomSanPham> ls = sp.getNhom();
            model.addAttribute("nhomsanpham", ls);

            List<VanChuyen> list = sp.getVanChuyen();
            model.addAttribute("vanchuyen", list);
            // hien thi danh muc
            List<ThanhToan> l = sp.getThanhToan();
            model.addAttribute("thanhtoan", l);
            model.addAttribute("donHang", new DonHang());
            return "/checkout";
        } else {
        	Session s = factory.getCurrentSession();
            ShoppingDA sp = new ShoppingDA(s);
            DonHang dh = new DonHang();
            dh.setTenKh(tenKh);
            dh.setEmail(email);
            dh.setDiaChi(diaChi);
            dh.setSoDienThoai(soDienThoai);
            dh.setTongTien(tongTien);
            dh.setNgayMua(new Timestamp(new Date().getTime()).toString());
            dh.setTrangThai(Boolean.TRUE);
            KhachHang kh = new KhachHang(maKh);
            dh.setMaKh(kh);
            VanChuyen vc = new VanChuyen(maVc);
            dh.setMaVc(vc);
            ThanhToan tt = new ThanhToan(maTt);
            dh.setMaTt(tt);
            sp.insertDonHang(dh);
            for (Map.Entry<Integer, GioHang> entry : cartItems.entrySet()) {
                ChiTietDonHang ctdh = new ChiTietDonHang();
                ctdh.setMaSp(entry.getValue().getSanPham());
                ctdh.setGiaTien(entry.getValue().getSanPham().getGia());
                ctdh.setSoLuong(entry.getValue().getSoLuong());
                ctdh.setTrangThai(true);
                ctdh.setMaDh(dh);
                sp.insertChiTietDonHang(ctdh);
            }

            mm.put("msgsussess", "Bạn đã mua hàng thành công");

            session.setAttribute("myCartItems", cartItems.remove(cartItems));
            session.setAttribute("myCartTotal", 0);
            session.setAttribute("myCartNum", 0);

            model.addAttribute("donHang", dh);

            List<SanPham> list = sp.getProPage1(offset, maxResult);
            model.addAttribute("sanpham", list);

            model.addAttribute("offset", offset);
            model.addAttribute("numberProductDetail", sp.getNumberProductDetail());
            // hien thi danh muc
            List<NhomSanPham> l = sp.getNhom();
            model.addAttribute("nhomsanpham", l);
            // hien thi quang cao
            List<QuangCao> ls = sp.getAllQuangCao();
            model.addAttribute("quangcao", ls);
            return "sussess";
        }

    }
}
