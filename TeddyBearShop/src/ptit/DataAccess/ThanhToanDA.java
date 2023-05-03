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
public class ThanhToanDA {
     private Session s;
     public ThanhToanDA(Session ss) {
         s = ss;
     }
  
     public List<ThanhToan> getAll(Integer maxResult) {
        return s.createCriteria(ThanhToan.class).setMaxResults(maxResult == null ? 1000 : maxResult).list();
    }

    public List<ThanhToan> getProPage1(Integer offset, Integer maxResult) {
        Query q = s.createQuery("from ThanhToan where trangThai = 1");
        return q.setFirstResult(offset == null ? 0 : offset).setMaxResults(maxResult == null ? 4 : maxResult).list();
    }

    public Long getNumberProductDetail() {
        try {

            List list = s.createQuery("from ThanhToan  where trangThai = 1").list();
            
            return (long) list.size();
        } catch (Exception e) {
            s.getTransaction().rollback();
        }
        return 0L;
    }
     public void insert(ThanhToan sp){
        s.save(sp);
        
    }
    
    public void update(ThanhToan sp){
        s.merge(sp);
        
    }
    
    public void delete(ThanhToan tt)
    {
        s.update(tt);
        
    }
    
    public ThanhToan getById(int maSp)
    {
        ThanhToan sp = (ThanhToan) s.get(ThanhToan.class, maSp);
        
        return sp;
    }
    
    public List<ThanhToan> search(String tenTt)
    {
        Query q=s.createQuery("select tt from ThanhToan tt where tt.tenTt like :tenTt");
        q.setParameter("tenTt", "%"+ tenTt+"%");
        return q.list();
    }
    
}
