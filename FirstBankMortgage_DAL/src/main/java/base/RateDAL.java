package base;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import domain.RateDomainModel;
import util.HibernateUtil;

public class RateDAL {


	public static RateDomainModel getRate(int GivenCreditScore) {
		
			Session session = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = null;
			RateDomainModel  tblRateGet = null;		
			
			try {
				tx = session.beginTransaction();	
										
				Query query = session.createQuery("from RateDomainModel  where GivenCreditScore = : ");
				query.setParameter("GivenCreditScore", GivenCreditScore);
				
				List<?> list = query.list();
				tblRateGet = (RateDomainModel)list.get(0);
				
				tx.commit();
			} catch (HibernateException e) {
				if (tx != null)
					tx.rollback();
				e.printStackTrace();
			} finally {
				session.close();
			}
			return tblRateGet;
				
	}
	public static ArrayList<RateDomainModel> getRate() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		RateDomainModel RateGet = null;		
		ArrayList<RateDomainModel> Rates = new ArrayList<RateDomainModel>();
		
		try {
			tx = session.beginTransaction();	
			
			List Rats = session.createQuery("FROM RateDomainModel").list();
			for (Iterator iterator = Rats.iterator(); iterator.hasNext();) {
				RateDomainModel tblRate = (RateDomainModel) iterator.next();
				 Rates.add(tblRate);

			}
			
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return Rates;
	}		
}