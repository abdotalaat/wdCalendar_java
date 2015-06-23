package com.abdobean.wdcalendar.dao;

import com.abdobean.wdcalendar.model.Jqcalendar;
import java.util.List;

public interface JqCalendarDAO {
	public List<Jqcalendar> list();
       public int  add(Jqcalendar jqcalendar);
}
