package ru.marchenko.easy_appointment.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.marchenko.easy_appointment.domain.dto.EntrepreneurCreateDto;
import ru.marchenko.easy_appointment.domain.dto.EntrepreneurDto;
import ru.marchenko.easy_appointment.exceptions.UserAlreadyExistsException;
import ru.marchenko.easy_appointment.services.EntrepreneurService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class AdminController {

    private final EntrepreneurService entrepreneurService;

    @GetMapping("/admin")
    public String getAdminPage(Model model, EntrepreneurCreateDto entrepreneurCreateDto){
        List<EntrepreneurDto> entrepreneurs = entrepreneurService.getAll();
        model.addAttribute("entrepreneurs", entrepreneurs);
        model.addAttribute("entrepreneurCreateDto", entrepreneurCreateDto);
        return "admin";
    }

    @PostMapping("/admin")
    public String add(
            @Valid @ModelAttribute("entrepreneurCreateDto") EntrepreneurCreateDto entrepreneurCreateDto, BindingResult bindingResult) throws UserAlreadyExistsException {
        if (bindingResult.hasErrors()) {
            return "/admin";
        }
        entrepreneurService.create(entrepreneurCreateDto);
        return "redirect:/admin";
    }

    @GetMapping("/admin/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        entrepreneurService.deleteById(id);
        return "redirect:/admin";
    }

    @GetMapping("/admin/edit/{id}")
    public String update(@PathVariable("id") Long id, Model model) {
        EntrepreneurDto dto = entrepreneurService.getById(id);
        model.addAttribute("dto", dto);
        return "edit";
    }

    @PostMapping("/admin/edit")
    public String update(@Valid @ModelAttribute("dto") EntrepreneurDto dto, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return "edit";
        }
        entrepreneurService.update(dto);
        return "redirect:/admin";
    }
}
