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
public class NhaCungCapDA {
    private Session s;
    public NhaCungCapDA(Session ss) {
        s = ss;
    }
   
    public List<NhaCungCap>getAll(Integer maxResult){
              return s.createCriteria(NhaCungCap.class).setMaxResults(maxResult == null ? 1000 : maxResult).list();
    }

    public List<NhaCungCap> getProPage1(Integer offset, Integer maxResult) {
        Query q = s.createQuery("from NhaCungCap where trangThai = 1");
        return q.setFirstResult(offset == null ? 0 : offset).setMaxResults(maxResult == null ? 4 : maxResult).list();
    }

    public Long getNumberProductDetail() {
        try {

            List list = s.createQuery("from NhaCungCap where trangThai = 1").list();
            return (long) list.size();
        } catch (Exception e) {
        }
        return 0L;
    }
    
     public void insert(NhaCungCap ncc){
        s.save(ncc);

    }
    
    public void update(NhaCungCap ncc){
        s.merge(ncc);
    }
    
    public void delete(NhaCungCap ncc)
    {
        s.update(ncc);
    }
    
    public NhaCungCap getById(int maNcc)
    {
        NhaCungCap ncc = (NhaCungCap) s.get(NhaCungCap.class, maNcc);
        return ncc;
    }
    
    public List<NhaCungCap> search(String tenNcc)
    {
        Query q=s.createQuery("select ncc from NhaCungCap ncc where ncc.tenNcc like :tenNcc");
        q.setParameter("tenNcc", "%"+ tenNcc+"%");
        return q.list();
    }
}
