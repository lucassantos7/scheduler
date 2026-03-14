package com.maverick.scheduler.controller;

import com.maverick.scheduler.dto.UserResponseDTO;
import com.maverick.scheduler.model.User;
import com.maverick.scheduler.model.WorkConfig;
import com.maverick.scheduler.repository.UserRepository;
import com.maverick.scheduler.service.ScheduleService;
import com.maverick.scheduler.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@Tag(name = "Usuários", description = "Gestão de prestadores e clientes")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    @Operation(summary = "Lista todos os usuários", description = "Retorna uma lista de UserResponseDTO")
    public List<UserResponseDTO> listAll() {
        return userService.findAll();
    }

    @PostMapping
    public UserResponseDTO create(@RequestBody User user) {
        return userService.create(user);
    }

    @PostMapping("/{id}/config")
    public UserResponseDTO setupWork(@PathVariable Long id, @RequestBody WorkConfig config) {
        return userService.setWorkConfig(id, config);
    }

    @Autowired
    private ScheduleService scheduleService;

    @GetMapping("/{id}/slots")
    public List<LocalTime> getSlots(@PathVariable Long id, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return scheduleService.getAvailableSlots(id, date);
    }
}
