package peaksoft.api;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.entity.User;
import peaksoft.service.UserService;

/**
 * @author :ЛОКИ Kelsivbekov
 * @created 27.02.2023
 */
@Controller
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserApi {
    private final UserService userService;

    @GetMapping()
    public String getAllUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "getAllUsers";
    }


    @GetMapping("/new")
    public String createUser(Model model) {
        model.addAttribute("user", new User());
        return "newUser";
    }

    @PostMapping("/save")
    public String saveUser(@ModelAttribute("user") User user) {
        userService.saveUser(user);
        return "redirect:/api/users";
    }


    @GetMapping("/{id}/getUser")
    public String getUserToUpdate(@PathVariable Long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "updateUser";
    }

    @PostMapping("/{id}/update")
    public String updateUser(@PathVariable Long id,
                             @ModelAttribute("user") User user) {
        userService.updateUser(id,user);
        return "redirect:/api/users";
    }
//@GetMapping("/{email}/getUser")
//public String getUserToUpdate(@PathVariable String email, Model model) {
//    model.addAttribute("user", userService.findUserByEmail(email));
//    return "updateUser";
//}
//
//    @PostMapping("/{id}/update")
//    public String updateUser(@PathVariable Long id,
//                             @ModelAttribute("user") User user) {
//        return "redirect:/api/users";
//    }


    @GetMapping("/{id}/delete")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return "redirect:/api/users";
    }

}
