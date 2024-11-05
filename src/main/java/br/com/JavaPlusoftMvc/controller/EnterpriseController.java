package br.com.JavaPlusoftMvc.controller;

import br.com.JavaPlusoftMvc.dto.customer.UpdateCustomerDTO;
import br.com.JavaPlusoftMvc.dto.enterprise.CreateEnterpriseDTO;
import br.com.JavaPlusoftMvc.dto.enterprise.UpdateEnterpriseDTO;
import br.com.JavaPlusoftMvc.model.Customer;
import br.com.JavaPlusoftMvc.model.Enterprise;
import br.com.JavaPlusoftMvc.model.enums.SegmentType;
import br.com.JavaPlusoftMvc.repository.EnterpriseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/enterprise")
public class EnterpriseController {

    @Autowired
    EnterpriseRepository enterpriseRepository;

    @GetMapping("register")
    public String register(CreateEnterpriseDTO enterpriseDTO, Model model){
        model.addAttribute("enterpriseDTO", new CreateEnterpriseDTO("", "", null));
        model.addAttribute("segmentType", SegmentType.values());
        return "enterprise/register";
    }

    @PostMapping("register")
    @Transactional
    public String register(CreateEnterpriseDTO enterpriseDTO, RedirectAttributes redirectAttributes){
        Enterprise enterprise = new Enterprise(enterpriseDTO);
        enterpriseRepository.save(enterprise);
        redirectAttributes.addFlashAttribute("msg", "Empresa registrada!");
        return "redirect:/enterprise/register";
    }

    @GetMapping("list")
    public String list(Model model){
        List<Enterprise> enterprises = enterpriseRepository.findAll();
        model.addAttribute("enterprises", enterprises);
        return "enterprise/list";
    }

    @GetMapping("update/{id}")
    public String update(@PathVariable("id") Long id, Model model){
        Enterprise enterprise = enterpriseRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Empresa não encontrada!"));
        UpdateEnterpriseDTO enterpriseDTO = new UpdateEnterpriseDTO(enterprise.getId(), enterprise.getName(), enterprise.getSegmentType());
        model.addAttribute("enterpriseDTO", enterpriseDTO);
        return "enterprise/update";
    }

    @PostMapping("update")
    @Transactional
    public String update(UpdateEnterpriseDTO enterpriseDTO, RedirectAttributes redirectAttributes){
        Enterprise enterprise = enterpriseRepository.findById(enterpriseDTO.id())
                .orElseThrow(() -> new IllegalArgumentException("Empresa não encontrada!"));

        enterprise.setName(enterpriseDTO.name());
        enterprise.setSegmentType(enterpriseDTO.segmentType());

        enterprise.setUpdatedAt(LocalDateTime.now());

        enterpriseRepository.save(enterprise);

        redirectAttributes.addFlashAttribute("msg", "Empresa atualizada!");
        return "redirect:/enterprise/list";
    }

    @PostMapping("delete")
    @Transactional
    public String delete(Long idEnterprise, RedirectAttributes redirectAttributes){
        enterpriseRepository.deleteById(idEnterprise);
        redirectAttributes.addFlashAttribute("msg", "Empresa deletada!");
        return "redirect:/enterprise/list";
    }

}
