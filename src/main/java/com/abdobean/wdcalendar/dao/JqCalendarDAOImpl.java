package com.abdobean.wdcalendar.dao;

import com.abdobean.wdcalendar.model.Jqcalendar;
import java.util.List;



import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class JqCalendarDAOImpl implements JqCalendarDAO {
	private SessionFactory sessionFactory;

	public JqCalendarDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Transactional
	public List<Jqcalendar> list() {
		@SuppressWarnings("unchecked")
		List<Jqcalendar> listUser = (List<Jqcalendar>) sessionFactory.getCurrentSession()
				.createCriteria(Jqcalendar.class)
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();

		return listUser;
	}

}
