/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ptit.controller;

import ptit.DataAccess.*;
import ptit.Entities.*;
import java.io.File;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author nguye
 */
@Controller
@RequestMapping(value = "/admin")
@Transactional


public class AdminController {
//nhóm sản phẩm 
	@Autowired
	SessionFactory factory;
    @RequestMapping(value = "/Home")
    public String homeAdmin(Model model, Integer offset, Integer maxResult) {

    	Session s = factory.getCurrentSession();
    	NhomSanPhamDA da = new NhomSanPhamDA(s);
        List<NhomSanPham> listdm = da.getProPage1(offset, maxResult);
        model.addAttribute("getAllnsp", listdm);
        model.addAttribute("offset", offset);
        model.addAttribute("numberProductDetail", da.getNumberProductDetail());
        return "admin/home";
    }
    
    @RequestMapping(value = "/taomoinsp")
    public String createNhomSP(Model model) {
        NhomSanPham nsp = new NhomSanPham();
        model.addAttribute("nhomsanpham", nsp);
        return "admin/themnhomsanpham";
    }

    @RequestMapping(value = "luumoinsp", method = RequestMethod.POST)
    public String saveNhomSP(String tenNhomSp, boolean trangThai, Model model, Integer offset, Integer maxResult) {
        NhomSanPham nsp = new NhomSanPham();
        nsp.setTenNhomSp(tenNhomSp);
        nsp.setTrangThai(trangThai);
    	Session s = factory.getCurrentSession();
    	NhomSanPhamDA da = new NhomSanPhamDA(s);
        da.insert(nsp);
        List<NhomSanPham> listdm = da.getProPage1(offset, maxResult);
        model.addAttribute("getAllnsp", listdm);
        model.addAttribute("offset", offset);
        model.addAttribute("numberProductDetail", da.getNumberProductDetail());
        return "admin/home";
    }

    @RequestMapping(value = "/updatennsp")
    public String updatenhomsp(int maNhomSp, Model model) {
    	Session s = factory.getCurrentSession();
    	NhomSanPhamDA da = new NhomSanPhamDA(s);
        NhomSanPham nsp = da.getById(maNhomSp);
        model.addAttribute("nhomsanpham", nsp);
        return "admin/suanhomsanpham";
    }

    @RequestMapping(value = "suanhomSP", method = RequestMethod.POST)
    public String updatenhomsanpham(int maNhomSp, String tenNhomSp, boolean trangThai, Model model, Integer offset, Integer maxResult) {
        NhomSanPham nsp = new NhomSanPham();
        nsp.setMaNhomSp(maNhomSp);
        nsp.setTenNhomSp(tenNhomSp);
        nsp.setTrangThai(trangThai);
    	Session s = factory.getCurrentSession();
    	NhomSanPhamDA da = new NhomSanPhamDA(s);
        da.update(nsp);
        List<NhomSanPham> listdm = da.getProPage1(offset, maxResult);
        model.addAttribute("getAllnsp", listdm);
        model.addAttribute("offset", offset);
        model.addAttribute("numberProductDetail", da.getNumberProductDetail());
        return "admin/home";
    }

    @RequestMapping(value = "/xoanhomsanpham")
    public String deletenhomSP(int maNhomSp, String tenNhomSp, boolean trangThai, Model model, Integer offset, Integer maxResult) {
    	Session s = factory.getCurrentSession();
    	NhomSanPhamDA da = new NhomSanPhamDA(s);
        NhomSanPham nsp = new NhomSanPham();
        nsp.setMaNhomSp(maNhomSp);
        nsp.setTenNhomSp(tenNhomSp);
        nsp.setTrangThai(false);
        da.delete(nsp);
        List<NhomSanPham> listdm = da.getProPage1(offset, maxResult);
        model.addAttribute("getAllnsp", listdm);
        model.addAttribute("offset", offset);
        model.addAttribute("numberProductDetail", da.getNumberProductDetail());
        return "admin/home";
    }

    @RequestMapping(value = "/timtheotennhomsp")
    public String searchTenNhomSP(String tenNhomSp, Model model) {
    	Session s = factory.getCurrentSession();
    	NhomSanPhamDA da = new NhomSanPhamDA(s);
        List<NhomSanPham> list = da.search(tenNhomSp);
        model.addAttribute("getAllnsp", list);
        return "admin/home";
    }

    @RequestMapping(value = "/chitietnhomSP")
    public String detailNhomSP(int maNhomSp, Model model) {
    	Session s = factory.getCurrentSession();
    	NhomSanPhamDA da = new NhomSanPhamDA(s);
        NhomSanPham nsp = da.getById(maNhomSp);
        model.addAttribute("nsp", nsp);
        return "admin/chitietnhomsanpham";
    }

    /*Phương thức thanh toán*/
    @RequestMapping(value = "/ThanhToan")
    public String getAll(Model model, Integer offset, Integer maxResult) {
    	Session s = factory.getCurrentSession();
    	ThanhToanDA da = new ThanhToanDA(s);
        List<ThanhToan> data = da.getProPage1(offset, maxResult);
        model.addAttribute("getAlltt", data);
        model.addAttribute("offset", offset);
        model.addAttribute("numberProductDetail", da.getNumberProductDetail());
        return "admin/thanhtoan";
    }

    @RequestMapping(value = "/taomoi")
    public String create(Model model) {
        ThanhToan tt = new ThanhToan();
        model.addAttribute("thanhtoan", tt);
        return "admin/themthanhtoan";
    }

    @RequestMapping(value = "luumoi", method = RequestMethod.POST)
    public String saveThanhToan(String tenTt, String mota, boolean trangThai, Model model, Integer offset, Integer maxResult) {
        ThanhToan tt = new ThanhToan();
        tt.setTenTt(tenTt);
        tt.setMota(mota);
        tt.setTrangThai(trangThai);
    	Session s = factory.getCurrentSession();
    	ThanhToanDA da = new ThanhToanDA(s);
        da.insert(tt);
        List<ThanhToan> data = da.getProPage1(offset, maxResult);
        model.addAttribute("getAlltt", data);
        model.addAttribute("offset", offset);
        model.addAttribute("numberProductDetail", da.getNumberProductDetail());
        return "admin/thanhtoan";
    }

    @RequestMapping(value = "/updatethanhtoan")
    public String update(int maTt, Model model) {
    	Session s = factory.getCurrentSession();
    	ThanhToanDA da = new ThanhToanDA(s);
        ThanhToan tt = da.getById(maTt);
        model.addAttribute("thanhtoan", tt);
        return "admin/suathanhtoan";
    }

    @RequestMapping(value = "sua", method = RequestMethod.POST)
    public String saveupdate(int maTt, String tenTt, String mota, boolean trangThai, Model model, Integer offset, Integer maxResult) {
        ThanhToan tt = new ThanhToan();
        tt.setMaTt(maTt);
        tt.setMota(mota);
        tt.setTenTt(tenTt);
        tt.setTrangThai(trangThai);
    	Session s = factory.getCurrentSession();
    	ThanhToanDA da = new ThanhToanDA(s);
        da.update(tt);
        List<ThanhToan> data = da.getProPage1(offset, maxResult);
        model.addAttribute("getAlltt", data);
        model.addAttribute("offset", offset);
        model.addAttribute("numberProductDetail", da.getNumberProductDetail());
        return "admin/thanhtoan";
    }

    @RequestMapping(value = "/chitiet")
    public String detailthanhtoan(int maTt, Model model) {
    	Session s = factory.getCurrentSession();
    	ThanhToanDA da = new ThanhToanDA(s);
        ThanhToan tt = da.getById(maTt);
        model.addAttribute("tt", tt);
        return "admin/chitietthanhtoan";
    }

    @RequestMapping(value = "/xoathanhtoan")
    public String delete(int maTt, String tenTt, String mota, boolean trangThai, Model model, Integer offset, Integer maxResult) {
        ThanhToan tt = new ThanhToan();
    	Session s = factory.getCurrentSession();
    	ThanhToanDA da = new ThanhToanDA(s);
        tt.setMaTt(maTt);
        tt.setMota(mota);
        tt.setTenTt(tenTt);
        tt.setTrangThai(false);
        da.delete(tt);
        List<ThanhToan> data = da.getProPage1(offset, maxResult);
        model.addAttribute("getAlltt", data);
        model.addAttribute("offset", offset);
        model.addAttribute("numberProductDetail", da.getNumberProductDetail());
        return "admin/thanhtoan";
    }

    @RequestMapping(value = "/timtheotentt")
    public String searchtitle(String tenTt, Model model) {
    	Session s = factory.getCurrentSession();
    	ThanhToanDA da = new ThanhToanDA(s);
        List<ThanhToan> list = da.search(tenTt);
        model.addAttribute("getAlltt", list);
        return "admin/thanhtoan";
    }

    /*Phương thức vận chuyển*/
    @RequestMapping(value = "/VanChuyen")
    public String getAllVC(Model model, Integer offset, Integer maxResult) {
    	Session s = factory.getCurrentSession();
    	VanChuyenDA da = new VanChuyenDA(s);
        List<VanChuyen> data = da.getProPage1(offset, maxResult);
        model.addAttribute("getAllvc", data);
        model.addAttribute("offset", offset);
        model.addAttribute("numberProductDetail", da.getNumberProductDetail());
        return "admin/vanchuyen";
    }

    @RequestMapping(value = "/taomoivc")
    public String createVanChuyen(Model model) {
        VanChuyen vc = new VanChuyen();
        model.addAttribute("vanchuyen", vc);
        return "admin/themvchuyen";
    }

    @RequestMapping(value = "luumoivc", method = RequestMethod.POST)
    public String saveVanChuyen(String tenVc, String mota, double gia,boolean trangThai, Model model, Integer offset, Integer maxResult) {
        VanChuyen vc = new VanChuyen();
        vc.setTenVc(tenVc);
        vc.setMota(mota);
        vc.setGia(gia);
        vc.setTrangThai(trangThai);
    	Session s = factory.getCurrentSession();
    	VanChuyenDA da = new VanChuyenDA(s);
        da.insert(vc);
        List<VanChuyen> data = da.getProPage1(offset, maxResult);
        model.addAttribute("getAllvc", data);
        model.addAttribute("offset", offset);
        model.addAttribute("numberProductDetail", da.getNumberProductDetail());
        return "admin/vanchuyen";
    }

    @RequestMapping(value = "/updatevanchuyen")
    public String updatevc(int maVc, Model model) {
    	Session s = factory.getCurrentSession();
    	VanChuyenDA da = new VanChuyenDA(s);
        VanChuyen vc = da.getById(maVc);
        model.addAttribute("vanchuyen", vc);
        return "admin/suavanchuyen";
    }

    @RequestMapping(value = "suavc", method = RequestMethod.POST)
    public String updatevanchuyen(int maVc, String tenVc, String mota,double gia, boolean trangThai, Model model, Integer offset, Integer maxResult) {
        VanChuyen vc = new VanChuyen();
        vc.setMaVc(maVc);
        vc.setTenVc(tenVc);
        vc.setMota(mota);
        vc.setGia(gia);
        vc.setTrangThai(trangThai);
    	Session s = factory.getCurrentSession();
    	VanChuyenDA da = new VanChuyenDA(s);
        List<VanChuyen> data = da.getProPage1(offset, maxResult);
        model.addAttribute("getAllvc", data);
        model.addAttribute("offset", offset);
        model.addAttribute("numberProductDetail", da.getNumberProductDetail());
        da.update(vc);
        return "admin/vanchuyen";
    }

    @RequestMapping(value = "/xoavc")
    public String deletevc(int maVc, String tenVc, String mota,double gia, boolean trangThai, Model model, Integer offset, Integer maxResult) {
        VanChuyen vc = new VanChuyen();
        vc.setMaVc(maVc);
        vc.setTenVc(tenVc);
        vc.setMota(mota);
        vc.setGia(gia);
        vc.setTrangThai(false);
    	Session s = factory.getCurrentSession();
    	VanChuyenDA da = new VanChuyenDA(s);
        List<VanChuyen> data = da.getProPage1(offset, maxResult);
        model.addAttribute("getAllvc", data);
        model.addAttribute("offset", offset);
        model.addAttribute("numberProductDetail", da.getNumberProductDetail());
        da.delete(vc);
        return "admin/vanchuyen";

    }

    @RequestMapping(value = "/timtheotenvc")
    public String searchTenVC(String tenVc, Model model) {
    	Session s = factory.getCurrentSession();
    	VanChuyenDA da = new VanChuyenDA(s);
        List<VanChuyen> list = da.search(tenVc);
        model.addAttribute("getAllvc", list);
        return "admin/vanchuyen";
    }

    @RequestMapping(value = "/chitietvanchuyen")
    public String detailvanchuyen(int maVc, Model model) {
    	Session s = factory.getCurrentSession();
    	VanChuyenDA da = new VanChuyenDA(s);
        VanChuyen vc = da.getById(maVc);
        model.addAttribute("vc", vc);
        return "admin/chitietvc";
    }

    //Tin tức
    @RequestMapping(value = "/TinTuc")
    public String getAllTinTuc(Model model, Integer offset, Integer maxResult) {
    	Session s = factory.getCurrentSession();
    	TinTucDA da = new TinTucDA(s);
        List<QuangCao> data = da.getProPage1(offset, maxResult);
        model.addAttribute("getAllqc", data);
        model.addAttribute("offset", offset);
        model.addAttribute("numberProductDetail", da.getNumberProductDetail());
        return "admin/tintuc";
    }

    @RequestMapping(value = "/taomoitintuc")
    public String createTinTuc(Model model) {
        QuangCao qc = new QuangCao();
        model.addAttribute("quangcao", qc);
        return "admin/themtintuc";
    }

    @RequestMapping(value = "luumoitintuc", method = RequestMethod.POST)
    public String saveTinTuc(String tenQc, String anh, double gia, String nhaSanXuat, String mota, boolean trangThai, Model model, Integer offset, Integer maxResult) {

        QuangCao qc = new QuangCao();
        qc.setTenQc(tenQc);
        qc.setAnh(anh);
        qc.setGia(gia);
        qc.setNhaSanXuat(nhaSanXuat);
        qc.setMota(mota);
        qc.setTrangThai(trangThai);
    	Session s = factory.getCurrentSession();
    	TinTucDA da = new TinTucDA(s);
        da.insert(qc);
        List<QuangCao> data = da.getProPage1(offset, maxResult);
        model.addAttribute("getAllqc", data);
        model.addAttribute("offset", offset);
        model.addAttribute("numberProductDetail", da.getNumberProductDetail());
        return "admin/tintuc";
    }

    @RequestMapping(value = "/updatetintuc")
    public String updatetintuc(int maQc, Model model) {
    	Session s = factory.getCurrentSession();
    	TinTucDA da = new TinTucDA(s);
        QuangCao qc = da.getById(maQc);
        model.addAttribute("tintuc", qc);
        return "admin/suatintuc";
    }

    @RequestMapping(value = "suatintuc", method = RequestMethod.POST)
    public String updatetintuc(int maQc, String tenQc, String anh, double gia, String nhaSanXuat, String mota, boolean trangThai, Model model, Integer offset, Integer maxResult) {
        QuangCao qc = new QuangCao();
        qc.setMaQc(maQc);
        qc.setTenQc(tenQc);
        qc.setAnh(anh);
        qc.setGia(gia);
        qc.setNhaSanXuat(nhaSanXuat);
        qc.setMota(mota);
        qc.setTrangThai(trangThai);
    	Session s = factory.getCurrentSession();
    	TinTucDA da = new TinTucDA(s);
        da.update(qc);
        List<QuangCao> data = da.getProPage1(offset, maxResult);
        model.addAttribute("getAllqc", data);
        model.addAttribute("offset", offset);
        model.addAttribute("numberProductDetail", da.getNumberProductDetail());
        return "admin/tintuc";
    }

    @RequestMapping(value = "/xoatintuc")
    public String deletetintuc(int maQc, String tenQc, String anh, double gia, String nhaSanXuat, String mota, boolean trangThai, Model model, Integer offset, Integer maxResult) {
        QuangCao qc = new QuangCao();
        qc.setMaQc(maQc);
        qc.setTenQc(tenQc);
        qc.setAnh(anh);
        qc.setGia(gia);
        qc.setNhaSanXuat(nhaSanXuat);
        qc.setMota(mota);
        qc.setTrangThai(false);
    	Session s = factory.getCurrentSession();
    	TinTucDA da = new TinTucDA(s);
        da.delete(qc);
        List<QuangCao> data = da.getProPage1(offset, maxResult);
        model.addAttribute("getAllqc", data);
        model.addAttribute("offset", offset);
        model.addAttribute("numberProductDetail", da.getNumberProductDetail());
        return "admin/tintuc";

    }

    @RequestMapping(value = "/timtheotentintuc")
    public String searchTenTinTuc(String tenQc, Model model) {
    	Session s = factory.getCurrentSession();
    	TinTucDA da = new TinTucDA(s);
        List<QuangCao> list = da.search(tenQc);
        model.addAttribute("getAllqc", list);
        return "admin/tintuc";
    }

    @RequestMapping(value = "/chitiettintucs")
    public String detailtintuc(int maQc, Model model) {
    	Session s = factory.getCurrentSession();
    	TinTucDA da = new TinTucDA(s);
        QuangCao qc = da.getById(maQc);
        model.addAttribute("qc", qc);
        return "admin/chitiettintuc";
    }

    @RequestMapping(value = "/uploadanh")
    public ModelAndView upload(HttpServletRequest req, HttpServletResponse res) throws Exception {
        res.setContentType("text/plain");
        if (!(req instanceof MultipartHttpServletRequest)) {
            res.sendError(HttpServletResponse.SC_BAD_REQUEST, "Expected multipart request");
            return null;
        }
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) req;
        MultipartFile file = multipartRequest.getFile("file");
        File destination = new File(req.getServletContext().getRealPath("images") + "\\" + file.getOriginalFilename());
        file.transferTo(destination);
        res.getWriter().write("http://localhost:8080/TeddyBearShop/images/" + file.getOriginalFilename());
        res.flushBuffer();
        return null;
    }

    //nhà cung cấp
    @RequestMapping(value = "/NhaCungCap")
    public String getAllNCC(Model model, Integer offset, Integer maxResult) {
    	Session s = factory.getCurrentSession();
    	NhaCungCapDA da = new NhaCungCapDA(s);
        List<NhaCungCap> data = da.getProPage1(offset, maxResult);
        model.addAttribute("getAllncc", data);
        model.addAttribute("offset", offset);
        model.addAttribute("numberProductDetail", da.getNumberProductDetail());
        return "admin/nhacungcap";
    }

    @RequestMapping(value = "/taomoincc")
    public String createNhaCC(Model model, Integer offset, Integer maxResult) {
        NhaCungCap ncc = new NhaCungCap();
    	Session s = factory.getCurrentSession();
    	NhaCungCapDA da = new NhaCungCapDA(s);
        model.addAttribute("nhacungcap", ncc);
        model.addAttribute("offset", offset);
        model.addAttribute("numberProductDetail", da.getNumberProductDetail());
        return "admin/themnccap";
    }

    @RequestMapping(value = "luumoincc", method = RequestMethod.POST)
    public String saveNhaCC(String tenNcc, boolean trangThai, Model model, Integer offset, Integer maxResult) {
        NhaCungCap ncc = new NhaCungCap();
        ncc.setTenNcc(tenNcc);
        ncc.setTrangThai(trangThai);
    	Session s = factory.getCurrentSession();
    	NhaCungCapDA da = new NhaCungCapDA(s);
        da.insert(ncc);
        List<NhaCungCap> data = da.getProPage1(offset, maxResult);
        model.addAttribute("getAllncc", data);
        return "admin/nhacungcap";
    }

    @RequestMapping(value = "/updatencc")
    public String updatenhacc(int maNcc, Model model, Integer offset, Integer maxResult) {
    	Session s = factory.getCurrentSession();
    	NhaCungCapDA da = new NhaCungCapDA(s);
        NhaCungCap ncc = da.getById(maNcc);
        model.addAttribute("nhacungcap", ncc);
        model.addAttribute("offset", offset);
        model.addAttribute("numberProductDetail", da.getNumberProductDetail());
        return "admin/suanhacc";
    }

    @RequestMapping(value = "suancc", method = RequestMethod.POST)
    public String updatenhacungcap(int maNcc, String tenNcc, boolean trangThai, Model model, Integer offset, Integer maxResult) {
        NhaCungCap ncc = new NhaCungCap();
        ncc.setMaNcc(maNcc);
        ncc.setTenNcc(tenNcc);
        ncc.setTrangThai(trangThai);
    	Session s = factory.getCurrentSession();
    	NhaCungCapDA da = new NhaCungCapDA(s);
        da.update(ncc);
        List<NhaCungCap> data = da.getProPage1(offset, maxResult);
        model.addAttribute("getAllncc", data);
        return "admin/nhacungcap";
    }

    @RequestMapping(value = "/xoanhacc")
    public String deletenhacungcap(int maNcc, String tenNcc, boolean trangThai, Model model, Integer offset, Integer maxResult) {
        NhaCungCap ncc = new NhaCungCap();
        ncc.setMaNcc(maNcc);
        ncc.setTenNcc(tenNcc);
        ncc.setTrangThai(false);
    	Session s = factory.getCurrentSession();
    	NhaCungCapDA da = new NhaCungCapDA(s);
        da.delete(ncc);
        List<NhaCungCap> data = da.getProPage1(offset, maxResult);
        model.addAttribute("getAllncc", data);
        return "admin/nhacungcap";

    }

    @RequestMapping(value = "/timtheotennhacungcap")
    public String searchTenNCC(String tenNcc, Model model) {
    	Session s = factory.getCurrentSession();
    	NhaCungCapDA da = new NhaCungCapDA(s);
        List<NhaCungCap> list = da.search(tenNcc);
        model.addAttribute("getAllncc", list);
        return "admin/nhacungcap";
    }

    @RequestMapping(value = "/chitietnhacc")
    public String detailNhaCC(int maNcc, Model model) {
    	Session s = factory.getCurrentSession();
    	NhaCungCapDA da = new NhaCungCapDA(s);
        NhaCungCap ncc = da.getById(maNcc);
        model.addAttribute("ncc", ncc);
        return "admin/chitietnhacungcap";
    }

    //khách hàng
    @RequestMapping(value = "/KhachHang")
    public String getAllKhachHang(Model model, Integer offset, Integer maxResult) {
    	Session s = factory.getCurrentSession();
    	KhachHangDA da = new KhachHangDA(s);
        List<KhachHang> data = da.getProPage1(offset, maxResult);
        model.addAttribute("getAllkh", data);
        model.addAttribute("offset", offset);
        model.addAttribute("numberProductDetail", da.getNumberProductDetail());
        return "admin/khachhang";
    }

    @RequestMapping(value = "/chitietkh")
    public String detailKhachHang(int maKh, Model model) {
    	Session s = factory.getCurrentSession();
    	KhachHangDA da = new KhachHangDA(s);
        KhachHang kh = da.getById(maKh);
        model.addAttribute("kh", kh);
        return "admin/chitietkhachhang";
    }

    @RequestMapping(value = "/timtenkhachhang")
    public String searchKhachHang(String tenKh, Model model) {
    	Session s = factory.getCurrentSession();
    	KhachHangDA da = new KhachHangDA(s);
        List<KhachHang> list = da.search(tenKh);
        model.addAttribute("getAllkh", list);
        return "admin/khachhang";
    }

    //Sản phẩm
    //Load sản phẩm
    @RequestMapping(value = "/SanPham")
    public String getSanPham(Model model, Integer offset, Integer maxResult) {
        Session s = factory.getCurrentSession();
        SanPhamDA da = new SanPhamDA(s);
        List<SanPham> list = da.getProPage1(offset, maxResult);
        model.addAttribute("getAllsp", list);
        model.addAttribute("offset", offset);
        model.addAttribute("numberProductDetail", da.getNumberProductDetail());
        return "admin/sanpham";
    }

    //thêm mới sản phẩm
    @RequestMapping(value = "/taomoiSp")
    public String createSanPham(Model model, Integer offset, Integer maxResult) {
        SanPham sp = new SanPham();
        Session s = factory.getCurrentSession();
        model.addAttribute("sanpham", sp);
        model.addAttribute("nsp", new NhomSanPhamDA(s).getProPage1(offset, maxResult));
        model.addAttribute("ncc", new NhaCungCapDA(s).getProPage1(offset, maxResult));
        return "admin/themmoiSp";
    }

    @RequestMapping(value = "luumoisp", method = RequestMethod.POST)
    public String saveSanPham(String tenSp, String anh, double gia, String mota, boolean trangThai, int maNcc, int maNhomSp, Model model, Integer offset, Integer maxResult) {
        SanPham sp = new SanPham();
        sp.setTenSp(tenSp);
        sp.setAnh(anh);
        sp.setGia(gia);
        sp.setMota(mota);
        sp.setTrangThai(trangThai);
        NhaCungCap ncc = new NhaCungCap(maNcc);
        sp.setMaNcc(ncc);
        NhomSanPham nsp = new NhomSanPham(maNhomSp);
        sp.setMaNhomSp(nsp);
        Session s = factory.getCurrentSession();
        SanPhamDA da = new SanPhamDA(s);
        da.insert(sp);

        List<SanPham> list = da.getProPage1(offset, maxResult);
        model.addAttribute("getAllsp", list);
        model.addAttribute("offset", offset);
        model.addAttribute("numberProductDetail", da.getNumberProductDetail());
        return "admin/sanpham";
    }

    //load ảnh sản phẩm
    @RequestMapping(value = "/uploadanhsp")
    public ModelAndView uploadanhsanpham(HttpServletRequest req, HttpServletResponse res) throws Exception {
        res.setContentType("text/plain");
        if (!(req instanceof MultipartHttpServletRequest)) {
            res.sendError(HttpServletResponse.SC_BAD_REQUEST, "Expected multipart request");
            return null;
        }
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) req;
        MultipartFile file = multipartRequest.getFile("file");
        File destination = new File(req.getServletContext().getRealPath("images") + "\\" + file.getOriginalFilename());
        file.transferTo(destination);
        res.getWriter().write("http://localhost:8080/TeddyBearShop/images/" + file.getOriginalFilename());
        res.flushBuffer();
        return null;
    }

    //update sản phẩm
    @RequestMapping(value = "/updatesp")
    public String updateSanPham(int maSp, Model model, Integer offset, Integer maxResult) {
        Session s = factory.getCurrentSession();
        SanPhamDA da = new SanPhamDA(s);
        SanPham sp = da.getById(maSp);
        model.addAttribute("sanpham", sp);
        model.addAttribute("nsp", new NhomSanPhamDA(s).getProPage1(offset, maxResult));
        model.addAttribute("ncc", new NhaCungCapDA(s).getProPage1(offset, maxResult));
        return "admin/suaSanpham";
    }

    @RequestMapping(value = "suasp", method = RequestMethod.POST)
    public String SuaSanPham(int maSp, String tenSp, String anh, double gia, String mota, boolean trangThai, int maNcc, int maNhomSp, Model model, Integer offset, Integer maxResult) {
        SanPham sp = new SanPham();
        sp.setMaSp(maSp);
        sp.setTenSp(tenSp);
        sp.setAnh(anh);
        sp.setGia(gia);
        sp.setMota(mota);
        sp.setTrangThai(trangThai);
        NhaCungCap ncc = new NhaCungCap(maNcc);
        sp.setMaNcc(ncc);
        NhomSanPham nsp = new NhomSanPham(maNhomSp);
        sp.setMaNhomSp(nsp);
        Session s = factory.getCurrentSession();
        SanPhamDA da = new SanPhamDA(s);
        da.update(sp);
        List<SanPham> list = da.getProPage1(offset, maxResult);
        model.addAttribute("getAllsp", list);
        model.addAttribute("offset", offset);
        model.addAttribute("numberProductDetail", da.getNumberProductDetail());
        return "admin/sanpham";
    }

    //Xóa sản phẩm
    @RequestMapping(value = "/xoasanpham")
    public String deleteSanPham(int maSp, String tenSp, String anh, Double gia, String moTa, Model model, Integer offset, int maNcc, int maNhomSp, Integer maxResult) {
        Session s = factory.getCurrentSession();
        SanPhamDA da = new SanPhamDA(s);
        SanPham sp = new SanPham();
        sp.setMaSp(maSp);
        sp.setTenSp(tenSp);
        sp.setAnh(anh);
        sp.setGia(gia);
        sp.setMota(moTa);
        sp.setTrangThai(false);
        NhaCungCap ncc = new NhaCungCap(maNcc);
        sp.setMaNcc(ncc);
        NhomSanPham nsp = new NhomSanPham(maNhomSp);
        sp.setMaNhomSp(nsp);

        da.delete(sp);

        List<SanPham> list = da.getProPage1(offset, maxResult);
        model.addAttribute("getAllsp", list);
        model.addAttribute("offset", offset);
        model.addAttribute("numberProductDetail", da.getNumberProductDetail());
        return "admin/sanpham";

    }

    //chi tiết sản phẩm
    @RequestMapping(value = "/chitietsp")
    public String detailSanPham(int maSp, Model model) {
        Session s = factory.getCurrentSession();
        SanPhamDA da = new SanPhamDA(s);
        SanPham sp = da.getById(maSp);
        model.addAttribute("sp", sp);
        return "admin/chitietsanpham";
    }

    //tìm kiếm theo tên sản phẩm
    @RequestMapping(value = "/timtensanpham")
    public String searchSanPham(String tenSp, Model model) {
        Session s = factory.getCurrentSession();
        SanPhamDA da = new SanPhamDA(s);
        List<SanPham> list = da.search(tenSp);
        model.addAttribute("getAllsp", list);
        return "admin/sanpham";
    }

    //Hóa đơn
    @RequestMapping(value = "/HoaDon")
    public String getHoaDon(Model model, Integer offset, Integer maxResult) {
        Session s = factory.getCurrentSession();
        DonHangDA da = new DonHangDA(s);
        List<DonHang> list = da.getProPage1(offset, maxResult);
        model.addAttribute("getAllhd", list);
        model.addAttribute("offset", offset);
        model.addAttribute("numberProductDetail", da.getNumberProductDetail());
        return "admin/hoadon";
    }

    //tìm kiếm theo tên hóa đơn
    @RequestMapping(value = "/timhoadon")
    public String searchDonHabg(String tenKh, Model model) {
        Session s = factory.getCurrentSession();
        DonHangDA da = new DonHangDA(s);
        List<DonHang> list = da.search(tenKh);
        model.addAttribute("getAllhd", list);
        return "admin/hoadon";
    }

    //Chi tiết hóa đơn
    @RequestMapping(value = "/ChiTietHoaDon")
    public String getDonHang(Model model, Integer offset, Integer maxResult) {
        Session s = factory.getCurrentSession();
        ChiTietDonHangDA da = new ChiTietDonHangDA(s);
        List<ChiTietDonHang> list = da.getProPage1(offset, maxResult);
        model.addAttribute("getAlldh", list);
        model.addAttribute("offset", offset);
        model.addAttribute("numberProductDetail", da.getNumberProductDetail());
        return "admin/chitietdonhang";
    }

    //đăng ký
    @RequestMapping(value = "/Dangky")
    public String getDangKy(Model model) {
        Session s = factory.getCurrentSession();
        QuangTriDA da = new QuangTriDA(s);
        List<QuanTri> list = da.getAll();
        model.addAttribute("getAllqt", list);
        return "admin/dangky";
    }

    //THÊM MỚI ĐĂNG KÝ
    @RequestMapping(value = "/taomoiquantri")
    public String createQuanTri(Model model) {
        QuanTri qt = new QuanTri();
        model.addAttribute("quantri", qt);
        return "admin/themquantri";
    }

    @RequestMapping(value = "luumoiquantri", method = RequestMethod.POST)
    public String saveQuanTri(String tenQt, String taiKhoan, String matKhau, boolean trangThai, Model model) {

        QuanTri qt = new QuanTri();
        qt.setTenQt(tenQt);
        qt.setMatKhau(matKhau);
        qt.setTaiKhoan(taiKhoan);
        qt.setTrangThai(trangThai);
        Session s = factory.getCurrentSession();
        QuangTriDA da = new QuangTriDA(s);
        da.insert(qt);
        List<QuanTri> data = da.getAll();
        model.addAttribute("getAllqt", data);
        return "admin/dangky";
    }
//cập nhật đăng ký

    @RequestMapping(value = "/updatequantri")
    public String updateQuanTri(int maQt, Model model) {
        Session s = factory.getCurrentSession();
        QuangTriDA da = new QuangTriDA(s);
        QuanTri qt = da.getById(maQt);
        model.addAttribute("quantri", qt);
        return "admin/suaquantri";
    }

    @RequestMapping(value = "suaquantri", method = RequestMethod.POST)
    public String updateQuanTri(int maQt, String tenQt, String taiKhoan, String matKhau, boolean trangThai, Model model) {
        QuanTri qt = new QuanTri();
        qt.setMaQt(maQt);
        qt.setTenQt(tenQt);
        qt.setMatKhau(matKhau);
        qt.setTaiKhoan(taiKhoan);
        qt.setTrangThai(trangThai);
        Session s = factory.getCurrentSession();
        QuangTriDA da = new QuangTriDA(s);
        da.update(qt);
        List<QuanTri> data = da.getAll();
        model.addAttribute("getAllqt", data);
        return "admin/dangky";
    }

    //xóa đăng ký
    @RequestMapping(value = "/xoaquantri")
    public String deleteQuanTri(int id, Model model) {
        Session s = factory.getCurrentSession();
        QuangTriDA da = new QuangTriDA(s);
        da.delete(id);
        List<QuanTri> data = da.getAll();
        model.addAttribute("getAllqt", data);
        return "admin/dangky";

    }

    @RequestMapping(value = "/login")
    public String login() {
        return "admin/login";

    }

    @RequestMapping(value = "/DangNhap", method = RequestMethod.POST)
    public String getDangNhap(Integer offset, Integer maxResult, ModelMap mm, HttpServletRequest request, QuanTri qt, Model model, String taiKhoan, String matKhau) {
        Session s = factory.getCurrentSession();
        QuangTriDA da = new QuangTriDA(s);
        QuanTri qts = da.loginQuanTri(qt.getTaiKhoan(), qt.getMatKhau());
        if (qts != null) {
            HttpSession session = request.getSession();
            session.setAttribute("name", qts.getTenQt());
            NhomSanPhamDA nda = new NhomSanPhamDA(s);
            List<NhomSanPham> listdm = nda.getProPage1(offset, maxResult);
            model.addAttribute("getAllnsp", listdm);
            model.addAttribute("offset", offset);
            model.addAttribute("numberProductDetail", nda.getNumberProductDetail());
            mm.put("msgSussess", "Ban da dang nhap thanh cong");
            return "admin/home";
        } else {
            mm.put("msgError", "Tai khoan hoac mat khau khong dung");
            return "admin/login";

        }
    }

    @RequestMapping(value = "/dangxuat")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.invalidate();
        return "admin/login";

    }

}
