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
public class ChiTietDonHangDA {
     private Session s;
     public ChiTietDonHangDA(Session ss) {
         s = ss;
     }
     public List<ChiTietDonHang>getAll(Integer maxResult){
              return s.createCriteria(ChiTietDonHang.class).setMaxResults(maxResult == null ? 1000 : maxResult).list();
    }

    public List<ChiTietDonHang> getProPage1(Integer offset, Integer maxResult) {
        Query q = s.createQuery("from ChiTietDonHang");
        return q.setFirstResult(offset == null ? 0 : offset).setMaxResults(maxResult == null ? 4 : maxResult).list();
    }

    public Long getNumberProductDetail() {
        try {

            List list = s.createQuery("from ChiTietDonHang").list();
            return (long) list.size();
        } catch (Exception e) {
        }
        return 0L;
    }
}
