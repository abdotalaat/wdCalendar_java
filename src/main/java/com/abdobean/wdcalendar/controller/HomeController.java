package com.abdobean.wdcalendar.controller;

import com.abdobean.wdcalendar.dao.JqCalendarDAO;
import com.abdobean.wdcalendar.model.Jqcalendar;
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
            res = "{\"events\":[[43654,\"project plan review\",\"06\\/28\\/2015 08:29\",\"12\\/31\\/1969 19:47\",1,0,0,11,1,null,\"\"],[26624,\"annual report\",\"06\\/24\\/2015 21:22\",\"12\\/31\\/1969 18:13\",0,0,0,4,1,null,\"\"],[60514,\"annual report\",\"06\\/22\\/2015 16:46\",\"12\\/31\\/1969 19:24\",0,0,0,10,1,null,\"\"],[27239,\"team meeting\",\"06\\/26\\/2015 13:33\",\"12\\/31\\/1969 19:03\",0,0,0,9,1,null,\"\"],[58668,\"go to dinner\",\"06\\/22\\/2015 15:51\",\"12\\/31\\/1969 18:38\",0,0,0,0,1,null,\"\"],[94706,\"go to dinner\",\"06\\/23\\/2015 19:50\",\"12\\/31\\/1969 19:02\",1,0,0,5,1,null,\"\"],[40357,\"remote meeting\",\"06\\/25\\/2015 22:36\",\"12\\/31\\/1969 19:46\",0,0,0,2,1,null,\"\"],[83950,\"team meeting\",\"06\\/27\\/2015 14:45\",\"12\\/31\\/1969 19:36\",1,0,0,10,1,null,\"\"],[15887,\"team meeting\",\"06\\/23\\/2015 04:55\",\"12\\/31\\/1969 18:06\",1,0,0,4,1,null,\"\"],[21811,\"annual report\",\"06\\/23\\/2015 22:04\",\"12\\/31\\/1969 19:43\",1,0,0,0,1,null,\"\"],[97469,\"go to dinner\",\"06\\/26\\/2015 19:58\",\"12\\/31\\/1969 19:12\",1,0,0,12,1,null,\"\"],[67546,\"go to dinner\",\"06\\/26\\/2015 10:59\",\"12\\/31\\/1969 18:03\",0,0,0,10,1,null,\"\"],[54663,\"team meeting\",\"06\\/24\\/2015 02:41\",\"12\\/31\\/1969 18:44\",0,0,0,13,1,null,\"\"],[16351,\"team meeting\",\"06\\/24\\/2015 10:47\",\"12\\/31\\/1969 18:41\",1,0,0,4,1,null,\"\"],[19818,\"go to dinner\",\"06\\/23\\/2015 14:40\",\"12\\/31\\/1969 19:57\",1,0,0,10,1,null,\"\"],[66368,\"project plan review\",\"06\\/27\\/2015 05:41\",\"12\\/31\\/1969 18:35\",1,0,0,9,1,null,\"\"],[39600,\"remote meeting\",\"06\\/23\\/2015 09:18\",\"12\\/31\\/1969 19:35\",0,0,0,10,1,null,\"\"],[81285,\"go to dinner\",\"06\\/26\\/2015 11:55\",\"12\\/31\\/1969 18:32\",0,0,0,8,1,null,\"\"],[85751,\"team meeting\",\"06\\/22\\/2015 05:02\",\"12\\/31\\/1969 18:53\",0,0,0,3,1,null,\"\"],[87447,\"remote meeting\",\"06\\/24\\/2015 05:07\",\"12\\/31\\/1969 18:45\",0,0,0,9,1,null,\"\"]],\"issort\":true,\"start\":\"06\\/22\\/2015 00:00\",\"end\":\"06\\/28\\/2015 23:59\",\"error\":null}";
        } else if (method.equals("add")) {
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
            jqCalendarDAO.add(jqcalendar);
            res = "{\"IsSuccess\":true,\"Msg\":\"add success\",\"Data\":1920149217}";


        }

        return res;

    }

    public String list(String date, String viewType) {
        String[] dateTimes = null;
        String res = "";

        if (viewType.equals("week")) {
            dateTimes = utilities.getWeekRange(date);
        } else if (viewType.equals("day")) {
            dateTimes = utilities.getDayRange(date);
        } else {
            dateTimes = utilities.getmonthRange(date);
        }




        return res;
    }
}
