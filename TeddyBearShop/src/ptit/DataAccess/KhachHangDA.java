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
public class KhachHangDA {
     private Session s;
     public KhachHangDA(Session ss) {
         s = ss;
     }
    
    public List<KhachHang>getAll(Integer maxResult){
              return s.createCriteria(KhachHang.class).setMaxResults(maxResult == null ? 1000 : maxResult).list();
    }

    public List<KhachHang> getProPage1(Integer offset, Integer maxResult) {
        Query q = s.createQuery("from KhachHang where trangThai = 1");
        return q.setFirstResult(offset == null ? 0 : offset).setMaxResults(maxResult == null ? 4 : maxResult).list();
    }

    public Long getNumberProductDetail() {
        try {

            List list = s.createQuery("from KhachHang  where trangThai = 1").list();
            return (long) list.size();
        } catch (Exception e) {
        }
        return 0L;
    }
     public void insert(KhachHang kh){
        s.save(kh);
    }
    
    public void update(KhachHang kh){
        s.merge(kh);
    }
    
    public void delete(int id)
    {
        KhachHang kh = (KhachHang) s.get(KhachHang.class, id);
        s.delete(kh);
    }
    
    public KhachHang getById(int maKh)
    {
        KhachHang kh = (KhachHang) s.get(KhachHang.class, maKh);
        return kh;
    }
    
    public List<KhachHang> search(String tenKh)
    {
        Query q=s.createQuery("select kh from KhachHang kh where kh.tenKh like :tenKh");
        q.setParameter("tenKh", "%"+ tenKh+"%");
        return q.list();
    }
    
}
