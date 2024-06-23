package ma.emsi.conours_v8.web;

import lombok.AllArgsConstructor;
import ma.emsi.conours_v8.entities.Departement;
import ma.emsi.conours_v8.entities.Diplome;
import ma.emsi.conours_v8.repositories.DiplomeRepository;
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
public class DiplomeController {
    private DiplomeRepository diplomeRepository;

    @GetMapping("/Diplome")
    private String Diplome(Model model,
                           @RequestParam(name = "page", defaultValue = "0") int p,
                           @RequestParam(name ="size", defaultValue = "4") int s,
                           @RequestParam(name ="Keyword",defaultValue = "")String kw){
        Page<Diplome> diplomePage =diplomeRepository.findByNomContains(kw,PageRequest.of(0,4));
        model.addAttribute("listDiplome",diplomePage.getContent());
        model.addAttribute("pages",new int[diplomePage.getTotalPages()]);
        model.addAttribute("currentPage",p);
        model.addAttribute("Keyword",kw);
        return "diplome";
    }
    @GetMapping("/deleteDiplome")
    public String delete(@RequestParam(name = "id") Long id,String Keyword,int page){
        diplomeRepository.deleteById(id);
        return "redirect:/Diplome?page="+page+"& Keyword= "+Keyword;
    }
    @GetMapping("/formDiplome")
    public String formDiplome(Model model){
        model.addAttribute("diplome",new Diplome());
        return "formDiplome";
    }
    @PostMapping("/saveDiplome")
    public String saveDiplome(Diplome diplome){
        diplomeRepository.save(diplome);
        return"redirect:/Diplome";
    }


    @GetMapping("/editDiplome")
    public String editDiplome(@RequestParam(name = "id") Long id, Model model){
        Diplome diplome=diplomeRepository.findById(id).get();
        model.addAttribute("diplome",diplome);
        return "editDiplome";
    }
}
