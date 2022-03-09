package ru.gb.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.gb.api.security.dto.UserDto;
import ru.gb.entity.security.AccountStatus;
import ru.gb.entity.security.AccountUser;
import ru.gb.service.UserService;
import ru.gb.services.MailService;

import java.util.Random;

import javax.validation.Valid;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;
    private final MailService mailService;
    private Integer cod;

    @GetMapping("/login")
    public String showLoginForm() {
        return "auth/login-form";
    }

    @GetMapping("/registration")
    public String showRegistrationForm(Model model) {
        model.addAttribute("userDto", new UserDto());
        return "auth/registration-form";
    }

    @PostMapping("/register")
    public String handleRegistration(@Valid UserDto userDto, BindingResult bindingResult, Model model) {

        String username = userDto.getUsername();
        log.info("Process registration form for: " + username);
        if (bindingResult.hasErrors()) {
            return "auth/registration-form";
        }
        try {
            userService.findByUsername(username);
            model.addAttribute("user", userDto);
            model.addAttribute("registrationError", "Пользователь с таким именем уже существует");
            log.info("Username {} already exists", username);
            return "auth/registration-form";
        } catch (UsernameNotFoundException ignored) {
        }

        userService.register(userDto);
        log.info("Successfully created user with username: {}", username);
        model.addAttribute("username", username);
        // todo ДЗ 11 - добавить подтверждение email перед конечной активацией
        // todo сделать так чтобы аккаунт был создан но находился в статусе NOT_ACTIVE и enable=false до тех пор пока не введет на сайте пароль из мейла
        cod = new Random().nextInt(55555);
        mailService.sendMail(userDto.getEmail(), "Подтверждение регистрации на GB shop", "Код для подтверждения регистрации: " + cod);
        AccountUser user = userService.findByUsername(username);
        user.setStatus(AccountStatus.NOT_ACTIVE);
        user.setEnabled(false);
        userService.update(userDto);
        return "auth/registration-confirmation";
    }

    @PostMapping("/confirm")
    public String handleConfirm(@RequestParam String incode, @RequestParam String username,
                                     Model model, UserDto userDto) {
        if (Integer.parseInt(incode) == cod) {
            AccountUser user = userService.findByUsername(username);
            user.setStatus(AccountStatus.ACTIVE);
            user.setEnabled(true);
            userService.update(userDto);
        } else {
            model.addAttribute("username", username);
            return "auth/registration-confirmation";
        }
        return "redirect:/product/all";
    }

}
