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
public class VanChuyenDA {
     private Session s;
     public VanChuyenDA(Session ss) {
         s = ss;
     }
   
     public List<VanChuyen> getAll(Integer maxResult) {
        return s.createCriteria(VanChuyen.class).setMaxResults(maxResult == null ? 1000 : maxResult).list();
    }

    public List<VanChuyen> getProPage1(Integer offset, Integer maxResult) {
        Query q = s.createQuery("from VanChuyen where trangThai = 1");
        return q.setFirstResult(offset == null ? 0 : offset).setMaxResults(maxResult == null ? 4 : maxResult).list();
    }

    public Long getNumberProductDetail() {
        try {

            List list = s.createQuery("from VanChuyen  where trangThai = 1").list();
            
            return (long) list.size();
        } catch (Exception e) {
            s.getTransaction().rollback();
        }
        return 0L;
    }
     public void insert(VanChuyen vc){
        s.save(vc);
        
    }
    
    public void update(VanChuyen vc){
        s.merge(vc);
        
    }
    
    public void delete(VanChuyen vc)
    {
        s.update(vc);
        
    }
    
    public VanChuyen getById(int maVc)
    {
        VanChuyen vc = (VanChuyen) s.get(VanChuyen.class, maVc);
        
        return vc;
    }
    
    public List<VanChuyen> search(String tenVc)
    {
        Query q=s.createQuery("select s from VanChuyen s where s.tenVc like :tenVc");
        q.setParameter("tenVc", "%"+ tenVc+"%");
        return q.list();
    }
    
}
