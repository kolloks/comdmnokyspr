package com.dmnoky.prd.controller;

import com.dmnoky.prd.model.Authority;
import com.dmnoky.prd.model.User;
import com.dmnoky.prd.service.AuthorityService;
import com.dmnoky.prd.service.UserService;
import com.dmnoky.prd.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;

@Controller
public class UserController {
    private UserService userService;
    private AuthorityService authorityService;
    private UserValidator userValidator;

    @Autowired @Qualifier(value = "userService")
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired @Qualifier(value = "authorityService")
    public void setAuthorityService(AuthorityService authorityService) {
        this.authorityService = authorityService;
    }

    @Autowired
    public void setUserValidator(UserValidator userValidator) {
        this.userValidator = userValidator;
    }

    @GetMapping(value = "/registration")
    public String registration(Model model) {
        model.addAttribute("userForm", new User());
        return "registration";
    }

    @PostMapping(value = "/registration")
    public String registration(@ModelAttribute("userForm") User user,
                               BindingResult bindingResult, Model model) {
        this.userValidator.validate(user, bindingResult);
        if (bindingResult.hasErrors()) {
            return "registration";
        }
        if (this.userService.addUser(user)) {
            this.authorityService.addAuthority(new Authority(user.getUsername(), "ROLE_USER"));
            return "redirect:/login?goodReg";
        }
        return "registration";
    }

    @GetMapping(value = "/login")
    public String login(@RequestParam(name = "error", required = false) String error,
                        @RequestParam(name = "logout", required = false) String logout,
                        @RequestParam(name = "goodReg", required = false) String goodReg,
                        Model model) {
        if (error != null) model.addAttribute("message", "Логин или пароль некорректны.");
        if (logout != null) model.addAttribute("message", "Вы вышли из системы.");
        if (goodReg != null) model.addAttribute("message", "Вы прошли регистрацию.");
        return "/login";
    }

    @GetMapping(value = "/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout";
    }

    @GetMapping(value = "/admin")
    public String admin(Model model) {
        return "adminAcc";
    }

    @GetMapping(value = "/user")
    public String user(Model model) {
        Object object = SecurityContextHolder
                .getContext().getAuthentication().getPrincipal();
        if (object instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) object;
            String userName = userDetails.getUsername();
            User user = this.userService.getUserByName(userName);
            model.addAttribute("user", user);
            return "userAcc";
        }
        return "redirect:/login?error";
    }

    @GetMapping(value = "/userAttr")
    public String userAttr(Model model, Principal principal) {
        model.addAttribute("username", "Гость");
        if (principal != null) {
        String username = principal.getName();
        model.addAttribute("username", username);
        }
        return "userAttr";
    }
}
