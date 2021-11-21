package com.example.notesStorage.auth;

import com.example.notesStorage.enums.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotEmpty;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping(value = "/users")
@PreAuthorize("hasAuthority('ADMIN')") //When admin exists
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public Object userList(Model model) {
        System.out.println(" UserController userList");
        model.addAttribute("users", userService.findAll());
        return "UserList";
    }

//    @RequestMapping("/error")
//    @HeadersSecurityMarker
//    public Object error(HttpServletRequest httpServletRequest){
//        if (httpServletRequest.getAttribute("username").equals("username")){
//            httpServletRequest.getHeaders(HttpServletRequest.BASIC_AUTH);
//        }
//        return httpServletRequest.getHeader(httpServletRequest.getHeaderNames().nextElement());
//    }

    @NotEmpty
    @PostMapping
    public Object userSave(
            @RequestParam String userName,
            @RequestParam Map<String, String> form,
            @RequestParam("userId") User user
    ) {
        System.out.println(" UserController userSave ");
        user.setUsername(userName);
        Set<String> roles = Arrays.stream(Role.values()).map(Role::name).collect(Collectors.toSet());
        user.getRoles().clear();
        for (String key : form.keySet()) {
            if (roles.contains(key)) {
                user.getRoles().add(Role.valueOf(key));
            }
        }
        userService.save(user);
        return "redirect:/users";
    }

    @NotEmpty
    @GetMapping("{user}")
    public Object userEditForm(@PathVariable User user, Model model) {
        System.out.println(" UserController userEditForm ");
        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());
        return "userEdit";
    }
}
