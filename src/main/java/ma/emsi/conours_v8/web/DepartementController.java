package ma.emsi.conours_v8.web;

import lombok.AllArgsConstructor;
import ma.emsi.conours_v8.entities.Concours;
import ma.emsi.conours_v8.entities.Departement;
import ma.emsi.conours_v8.repositories.DepartementRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@AllArgsConstructor
public class DepartementController {
    private DepartementRepository departementRepository;

    @GetMapping("/Departement")
    private String Departement(Model model,
                               @RequestParam(name = "page", defaultValue = "0") int p,
                               @RequestParam(name ="size", defaultValue = "4") int s,
                               @RequestParam(name ="Keyword",defaultValue = "")String kw){
        Page<Departement> departementPage =departementRepository.findByNomContains(kw,PageRequest.of(0,4));
        model.addAttribute("listDepartement",departementPage.getContent());
        model.addAttribute("pages",new int[departementPage.getTotalPages()]);
        model.addAttribute("currentPage",p);
        model.addAttribute("Keyword",kw);
        return "departement";
    }
    @GetMapping("/deleteDeparetement")
    public String delete(@RequestParam(name = "id") Long id,String Keyword,int page){
        departementRepository.deleteById(id);
        return "redirect:/Departement?page="+page+"& Keyword= "+Keyword;
    }
    @GetMapping("/formDepartement")
    public String formDepartement(Model model){
        model.addAttribute("departement",new Departement());
        return "formDepartement";
    }
    @PostMapping("/saveDepartement")
    public String saveDepartement(Departement departement){
        departementRepository.save(departement);
        return"redirect:/Departement";
    }
    @GetMapping("/editDepartement")
    public String editDepartement(@RequestParam(name = "id") Long id, Model model){
        Departement departement=departementRepository.findById(id).get();
        model.addAttribute("departement",departement);
        return "editDepartement";
    }

}
