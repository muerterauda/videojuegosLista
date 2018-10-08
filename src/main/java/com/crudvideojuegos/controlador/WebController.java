package com.crudvideojuegos.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.ServletRequest;
import java.util.Locale;

@Controller
public class WebController {
  /*  @RequestMapping(value = "/", method = {RequestMethod.GET, RequestMethod.POST} )
    public ModelAndView  getLogin() {
        ModelAndView modelAndView = new ModelAndView("index");
        return modelAndView;
    }
    */
    @RequestMapping(value = "/loginHtml", method = {RequestMethod.GET, RequestMethod.POST} )
    public ModelAndView  getLogin2() {
        ModelAndView modelAndView = new ModelAndView("login.html");
        return modelAndView;
    }
    @RequestMapping(value = "/homeAdmin", method = {RequestMethod.GET, RequestMethod.POST} )
    public ModelAndView  getHomeAdmin() {
        ModelAndView modelAndView = new ModelAndView("homeAdmin.html");
        return modelAndView;
    }
    @RequestMapping(value = "/addAdmin", method = {RequestMethod.GET, RequestMethod.POST} )
    public ModelAndView  getAdd() {
        ModelAndView modelAndView = new ModelAndView("addAdmin.html");
        return modelAndView;
    }
    @RequestMapping(value = "/editAdmin", method = {RequestMethod.GET, RequestMethod.POST} )
    public ModelAndView  getEdit() {
        ModelAndView modelAndView = new ModelAndView("editAdmin.html");
        return modelAndView;
    }
    @RequestMapping(value = "/videojuegos", method = {RequestMethod.GET, RequestMethod.POST} )
    public ModelAndView  getVideojuegosUser() {
        ModelAndView modelAndView = new ModelAndView("videojuegos.html");
        return modelAndView;
    }
    @RequestMapping(value = "/tablaAdmin", method = {RequestMethod.GET, RequestMethod.POST} )
    public ModelAndView  getTablaAdmin() {
        ModelAndView modelAndView = new ModelAndView("tablaAdmin.html");
        return modelAndView;
    }
}
