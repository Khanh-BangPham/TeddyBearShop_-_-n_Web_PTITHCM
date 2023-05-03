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
public class TinTucDA {
    private Session s;
    public TinTucDA(Session ss) {
        s = ss;
    }
    
      public List<QuangCao> getAll(Integer maxResult) {
        return s.createCriteria(QuangCao.class).setMaxResults(maxResult == null ? 1000 : maxResult).list();
    }

    public List<QuangCao> getProPage1(Integer offset, Integer maxResult) {
        Query q = s.createQuery("from QuangCao where trangThai = 1");
        return q.setFirstResult(offset == null ? 0 : offset).setMaxResults(maxResult == null ? 4 : maxResult).list();
    }

    public Long getNumberProductDetail() {
        try {

            List list = s.createQuery("from QuangCao  where trangThai = 1").list();
            
            return (long) list.size();
        } catch (Exception e) {
            s.getTransaction().rollback();
        }
        return 0L;
    }
     public void insert(QuangCao qc){
        s.save(qc);
        
    }
    
    public void update(QuangCao qc){
        s.merge(qc);
        
    }
    
    public void delete(QuangCao qc)
    {
        s.update(qc);
        
    }
    
    public QuangCao getById(int maQc)
    {
        QuangCao qc = (QuangCao) s.get(QuangCao.class, maQc);
        
        return qc;
    }
    
    public List<QuangCao> search(String tenQc)
    {
        Query q=s.createQuery("select qc from QuangCao qc where qc.tenQc like :tenQc");
        q.setParameter("tenQc", "%"+ tenQc+"%");
        return q.list();
    }
}
