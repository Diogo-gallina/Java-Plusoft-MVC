package br.com.JavaPlusoftMvc.controller;

import br.com.JavaPlusoftMvc.dto.employe.CreateEmployeeDTO;
import br.com.JavaPlusoftMvc.dto.employe.UpdateEmployeeDTO;
import br.com.JavaPlusoftMvc.model.Employee;
import br.com.JavaPlusoftMvc.model.enums.SegmentType;
import br.com.JavaPlusoftMvc.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    EmployeeRepository employeeRepository;

    @GetMapping("register")
    public String register(CreateEmployeeDTO employeeDTO, Model model){
        model.addAttribute("employeeDTO", new CreateEmployeeDTO("", ""));
        return "employee/register";
    }

    @PostMapping("register")
    @Transactional
    public String register(CreateEmployeeDTO employeeDTO, RedirectAttributes redirectAttributes){
        Employee employee = new Employee(employeeDTO);
        employeeRepository.save(employee);
        redirectAttributes.addFlashAttribute("msg", "Funcionário registrado!");
        return "redirect:/employee/register";
    }

    @GetMapping("list")
    public String list(Model model){
        List<Employee> employees = employeeRepository.findAll();
        model.addAttribute("employees", employees);
        return "employee/list";
    }

    @GetMapping("update/{id}")
    public String update(@PathVariable("id") Long id, Model model){
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Funcionário não encontrado!"));
        UpdateEmployeeDTO employeeDTO = new UpdateEmployeeDTO(employee.getId(), employee.getName(), employee.getEmail());
        model.addAttribute("employeeDTO", employeeDTO);
        model.addAttribute("type", SegmentType.values());
        return "employee/update";
    }

    @PostMapping("update")
    @Transactional
    public String update(UpdateEmployeeDTO employeeDTO, RedirectAttributes redirectAttributes){
        Employee employee = employeeRepository.findById(employeeDTO.id())
                .orElseThrow(() -> new IllegalArgumentException("Funcionário não encontrado!"));

        employee.setName(employeeDTO.name());
        employee.setEmail(employeeDTO.email());

        employee.setUpdatedAt(LocalDateTime.now());

        employeeRepository.save(employee);

        redirectAttributes.addFlashAttribute("msg", "Funcionário atualizado!");
        return "redirect:/employee/list";
    }

    @PostMapping("delete")
    @Transactional
    public String delete(Long idEmployee, RedirectAttributes redirectAttributes){
        employeeRepository.deleteById(idEmployee);
        redirectAttributes.addFlashAttribute("msg", "Funcionário deletado!");
        return "redirect:/employee/list";
    }

}
