package com.abdobean.wdcalendar.controller;

import com.abdobean.wdcalendar.model.Jqcalendar;
import com.abdobean.wdcalendar.model.Shop;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

    @RequestMapping(value = "/")
    public ModelAndView home() {
        List<Jqcalendar> listUsers = new ArrayList<Jqcalendar>();
        ModelAndView model = new ModelAndView("home");
        model.addObject("userList", listUsers);
        return model;
    }

    @RequestMapping(value = "{name}", method = RequestMethod.GET)
    public @ResponseBody  Shop getShopInJSON(@PathVariable String name) {
        Shop shop = new Shop();
        shop.setName(name);
        shop.setStaffName(new String[]{"abdo", "abdo1"});
        return shop;

    }
}
