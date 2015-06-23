package com.abdobean.wdcalendar.dao;

import com.abdobean.wdcalendar.model.Jqcalendar;
import java.io.Serializable;
import java.util.List;



import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class JqCalendarDAOImpl implements JqCalendarDAO {
	private SessionFactory sessionFactory;

        @Autowired
	public JqCalendarDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Transactional
	public List<Jqcalendar> list(DateTime start,DateTime end) {
		@SuppressWarnings("unchecked")
		List<Jqcalendar> listUser = (List<Jqcalendar>) sessionFactory.getCurrentSession()
				.createCriteria(Jqcalendar.class)
				.add(Restrictions.between("startTime", start, end)).list();

		return listUser;
	}

        @Transactional
    public int add(Jqcalendar jqcalendar) {
            boolean b = true;
            sessionFactory.getCurrentSession().save(jqcalendar);
        return jqcalendar.getId();
    }

}
