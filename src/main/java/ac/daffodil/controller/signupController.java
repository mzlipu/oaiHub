package ac.daffodil.controller;

import ac.daffodil.dao.RoleDao;
import ac.daffodil.dao.UserDao;
import ac.daffodil.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Muiduzzaman Lipu on 03-Apr-18.
 */
@Controller
public class signupController {

    @Autowired
    UserDao userDao;

    @Autowired
    RoleDao roleDao;

    @RequestMapping(value = { "/signup" }, method = RequestMethod.GET)
    public ModelAndView signup(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("newUser", new User());
        modelAndView.addObject("roles", roleDao.getAll());
        modelAndView.setViewName("fragments/signup");
        return modelAndView;
    }

    @RequestMapping(value="/saveUser", method = RequestMethod.POST)
    public String saveUser(User user, RedirectAttributes redirectAttributes, HttpServletRequest request) {
        try {
            user.setActive(1);
            user.setRoleId(6);
            userDao.save(user);
            redirectAttributes.addFlashAttribute("message", "User Saved SuccessFully...");
            redirectAttributes.addFlashAttribute("alertClass", "alert-success");
            return "redirect:/signup";
        }catch (Exception e){
            redirectAttributes.addFlashAttribute("message", "Please Cheack and input Correct Data.");
            redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
            return "redirect:/signup";

        }
    }



}