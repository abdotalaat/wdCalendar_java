package com.abdobean.wdcalendar.controller;

import com.abdobean.wdcalendar.dao.JqCalendarDAO;
import com.abdobean.wdcalendar.model.Jqcalendar;
import com.abdobean.wdcalendar.model.Lists;
import com.abdobean.wdcalendar.model.Shop;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

    @Autowired
    JqCalendarDAO jqCalendarDAO;
    @Autowired
    private HttpServletRequest context;

    @RequestMapping(value = "/")
    public ModelAndView home() {
        List<Jqcalendar> listUsers = new ArrayList<Jqcalendar>();
        ModelAndView model = new ModelAndView("home");
        model.addObject("userList", listUsers);
        return model;
    }

    @RequestMapping(value = "/calendar/rest", method = RequestMethod.POST)
    public @ResponseBody
    String getShopInJSON(@RequestParam("method") String method, @RequestParam(value = "showdate", required = false) String showdate, @RequestParam(value = "viewtype", required = false) String viewtype) {
        String res = "";
        if (method.equals("list")) {
            System.out.println(showdate);
            System.out.println(viewtype);
            res =list(showdate, viewtype);
            
        } else if (method.equals("add")) {
            res = addCalendar();
        }

        return res;

    }

    public String list(String date, String viewType) {
        DateTime[] dateTimes = null;
        String res = "";

        if (viewType.equals("week")) {
            dateTimes = utilities.getWeekRange(date);
            
        } else if (viewType.equals("day")) {
           // dateTimes = utilities.getDayRange(date);
        } else {
            //dateTimes = utilities.getmonthRange(date);
        }


        List<Jqcalendar> jqcalendars = jqCalendarDAO.list(dateTimes[0], dateTimes[1]);
        System.out.println(jqcalendars.size());
      JSONArray jSONArray = new JSONArray();
        for (Jqcalendar jqcalendar : jqcalendars) {
            
            JSONArray array = new JSONArray();
            array.add(jqcalendar.getId());
            array.add(jqcalendar.getSubject());
            array.add(utilities.convertDateTimeToString(jqcalendar.getStartTime()));
            array.add(utilities.convertDateTimeToString(jqcalendar.getEndTime()));
            array.add(jqcalendar.getIsAllDayEvent());
            array.add(0);
            array.add(0);
            array.add(jqcalendar.getColor());
            array.add(jqcalendar.getLocation());
            jSONArray.add(array);
        }
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("events", jSONArray);
        jSONObject.put("issort", true);
        jSONObject.put("start", utilities.convertDateTimeToString(dateTimes[0]));
        jSONObject.put("end", utilities.convertDateTimeToString(dateTimes[1]));
        jSONObject.put("error", null);
        
        
        
       
        return jSONObject.toJSONString();
    }

    
    public String addCalendar() {
        Jqcalendar jqcalendar = new Jqcalendar();
        // System.out.println(context.getParameter("CalendarStartTime"));
        DateTime CalendarStartTime = utilities.getdateDateTime(context.getParameter("CalendarStartTime"));
        DateTime CalendarEndTime = utilities.getdateDateTime(context.getParameter("CalendarEndTime"));
        short IsAllDayEvent = Short.parseShort(context.getParameter("IsAllDayEvent"));
        String CalendarTitle = context.getParameter("CalendarTitle");
        jqcalendar.setStartTime(CalendarStartTime);
        jqcalendar.setEndTime(CalendarEndTime);
        jqcalendar.setIsAllDayEvent(IsAllDayEvent);
        jqcalendar.setSubject(CalendarTitle);

        //  System.out.print(json.get("CalendarStartTime"));
        int id = jqCalendarDAO.add(jqcalendar);
        if (id > 0) {
            return "{\"IsSuccess\":true,\"Msg\":\"add success\",\"Data\":" + id + "}";
        } else {
            return "{\"IsSuccess\":true,\"Msg\":\"add faild\"";
        }
    }
}
