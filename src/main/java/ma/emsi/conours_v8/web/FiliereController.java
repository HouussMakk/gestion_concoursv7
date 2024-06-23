package ma.emsi.conours_v8.web;

import lombok.AllArgsConstructor;
import ma.emsi.conours_v8.entities.Etudiant;
import ma.emsi.conours_v8.entities.Filiere;
import ma.emsi.conours_v8.repositories.FiliereRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller @AllArgsConstructor
public class FiliereController {
    private FiliereRepository filiereRepository;
    @GetMapping("/Filiere")
    private String Filiere(Model model,
                            @RequestParam(name = "page", defaultValue = "0") int p,
                            @RequestParam(name ="size", defaultValue = "4") int s,
                            @RequestParam(name ="Keyword",defaultValue = "")String kw){
        Page<Filiere> filierePage =filiereRepository.findByNomContains(kw, PageRequest.of(0,4));
        model.addAttribute("listFiliere",filierePage.getContent());
        model.addAttribute("pages",new int[filierePage.getTotalPages()]);
        model.addAttribute("currentPage",p);
        model.addAttribute("Keyword",kw);
        return "filiere";
    }
    @GetMapping("/deleteFiliere")
    public String delete(@RequestParam(name = "id") Long id,String Keyword,int page){
        filiereRepository.deleteById(id);
        return "redirect:/Filiere?page="+page+"& Keyword= "+Keyword;
    }
    @GetMapping("/formFiliere")
    public String formFiliere(Model model){
        model.addAttribute("filiere",new Filiere());
        return "formFiliere";
    }
    @PostMapping("/saveFiliere")
    public String saveFiliere(Filiere filiere){
        filiereRepository.save(filiere);
        return"redirect:/Filiere";
    }
    @GetMapping("/editFiliere")
    public String editFiliere(@RequestParam(name = "id") Long id, Model model){
        Filiere filiere=filiereRepository.findById(id).get();
        model.addAttribute("filiere",filiere);
        return "editFiliere";
    }

}
