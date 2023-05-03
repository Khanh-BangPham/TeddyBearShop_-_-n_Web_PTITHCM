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
public class QuangTriDA {
     private Session s;
     public QuangTriDA(Session ss) {
         s = ss;
     }
    public List<QuanTri>getAll(){
              return s.createCriteria(QuanTri.class).list();
    }
     public void insert(QuanTri qt){
        s.save(qt);

    }
    
    public void update(QuanTri qt){
        s.merge(qt);

    }
    
    public void delete(int id)
    {
        QuanTri qt = (QuanTri) s.get(QuanTri.class, id);
        s.delete(qt);

    }
     public QuanTri getById(int maQt)
    {
        QuanTri qt = (QuanTri) s.get(QuanTri.class, maQt);

        return qt;
    }
     
    public QuanTri loginQuanTri(String taiKhoan,String matKhau){
        Query q = s.createQuery(" from QuanTri where taiKhoan = :taiKhoan and matKhau = :matKhau");
       q.setString("matKhau", matKhau);
       q.setString("taiKhoan", taiKhoan);
       QuanTri qt = (QuanTri) q.uniqueResult();
       return qt;
   }
}
