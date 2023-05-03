/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ptit.DataAccess;

import ptit.Entities.*;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author nguye
 */
public class SanPhamDA {

    private Session s;

    public SanPhamDA(Session ss) {
        s = ss;
    }

    public List<SanPham> getAll(Integer maxResult) {
        return s.createCriteria(SanPham.class).setMaxResults(maxResult == null ? 1000 : maxResult).list();
    }

    public List<SanPham> getProPage1(Integer offset, Integer maxResult) {
        Query q = s.createQuery("from SanPham where trangThai = 1");
        return q.setFirstResult(offset == null ? 0 : offset).setMaxResults(maxResult == null ? 10 : maxResult).list();
    }

    public Long getNumberProductDetail() {
        try {

            List list = s.createQuery("from SanPham  where trangThai = 1").list();

            return (long) list.size();
        } catch (Exception e) {
   
        }
        return 0L;
    }

    public void insert(SanPham sp) {
        s.save(sp);
    }

    public void update(SanPham sp) {
        s.merge(sp);
    }

    public void delete(SanPham sp) {
        s.update(sp);
    }

    public SanPham getById(int maSp) {
        SanPham sp = (SanPham) s.get(SanPham.class, maSp);
        return sp;
    }

    public List<SanPham> search(String tenSp) {
        Query q = s.createQuery("select sp from SanPham sp where sp.tenSp like :tenSp");
        q.setParameter("tenSp", "%" + tenSp + "%");
        return q.list();
    }
}
