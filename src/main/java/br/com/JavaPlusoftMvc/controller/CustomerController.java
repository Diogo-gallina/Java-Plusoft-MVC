package br.com.JavaPlusoftMvc.controller;

import br.com.JavaPlusoftMvc.dto.customer.CreateCustomerDTO;
import br.com.JavaPlusoftMvc.dto.customer.UpdateCustomerDTO;
import br.com.JavaPlusoftMvc.model.Customer;
import br.com.JavaPlusoftMvc.model.enums.SegmentType;
import br.com.JavaPlusoftMvc.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    CustomerRepository customerRepository;

    @GetMapping("register")
    public String register(CreateCustomerDTO customerDTO, Model model){
        model.addAttribute("customerDTO", new CreateCustomerDTO("", "", ""));
        return "customer/register";
    }

    @PostMapping("register")
    @Transactional
    public String register(CreateCustomerDTO customerDTO,
                           RedirectAttributes redirectAttributes){
        Customer customer = new Customer(customerDTO);
        customerRepository.save(customer);
        redirectAttributes.addFlashAttribute("msg", "Usuário registrado!");
        return "redirect:/customer/register";
    }

    @GetMapping("list")
    public String list(Model model){
        List<Customer> customers = customerRepository.findAll();
        model.addAttribute("customers", customers);
        return "customer/list";
    }

    @GetMapping("update/{id}")
    public String update(@PathVariable("id") Long id, Model model){
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado!"));
        UpdateCustomerDTO customerDTO = new UpdateCustomerDTO(customer.getId(), customer.getName(), customer.getEmail(), customer.getPassword());
        model.addAttribute("customerDTO", customerDTO);
        model.addAttribute("type", SegmentType.values());
        return "customer/update";
    }

    @PostMapping("update")
    @Transactional
    public String update(UpdateCustomerDTO customerDTO, RedirectAttributes redirectAttributes){
        Customer customer = customerRepository.findById(customerDTO.id())
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado!"));

        customer.setName(customerDTO.name());
        customer.setEmail(customerDTO.email());
        customer.setPassword(customerDTO.password());

        customer.setUpdatedAt(LocalDateTime.now());

        customerRepository.save(customer);

        redirectAttributes.addFlashAttribute("msg", "Usuário atualizado!");
        return "redirect:/customer/list";
    }

    @PostMapping("delete")
    @Transactional
    public String delete(Long idCustomer, RedirectAttributes redirectAttributes){
        customerRepository.deleteById(idCustomer);
        redirectAttributes.addFlashAttribute("msg", "Usuário deletado!");
        return "redirect:/customer/list";
    }

}
