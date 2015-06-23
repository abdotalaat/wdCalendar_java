package com.abdobean.wdcalendar.dao;

import com.abdobean.wdcalendar.model.Jqcalendar;
import java.util.List;
import org.joda.time.DateTime;

public interface JqCalendarDAO {
	public List<Jqcalendar> list(DateTime start,DateTime end);
       public int  add(Jqcalendar jqcalendar);
}
