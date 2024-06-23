package ma.emsi.conours_v8.web;

import lombok.AllArgsConstructor;
import ma.emsi.conours_v8.entities.Diplome;
import ma.emsi.conours_v8.entities.Etudiant;
import ma.emsi.conours_v8.repositories.EtudiantRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@AllArgsConstructor
public class EtudiantController {
    private EtudiantRepository etudiantRepository;

    @GetMapping("/Etudiant")
    private String Etudiant(Model model,
                            @RequestParam(name = "page", defaultValue = "0") int p,
                            @RequestParam(name ="size", defaultValue = "4") int s,
                            @RequestParam(name ="Keyword",defaultValue = "")String kw){
        Page<Etudiant> etudiantPage =etudiantRepository.findByNomContains(kw,PageRequest.of(0,4));
        model.addAttribute("listEtudiant",etudiantPage.getContent());
        model.addAttribute("pages",new int[etudiantPage.getTotalPages()]);
        model.addAttribute("currentPage",p);
        model.addAttribute("Keyword",kw);
        return "etudiant";
    }
    @GetMapping("/deleteEtudiant")
    public String delete(@RequestParam(name = "id") Long id,String Keyword,int page){
        etudiantRepository.deleteById(id);
        return "redirect:/Etudiant?page="+page+"& Keyword= "+Keyword;
    }
    @GetMapping("/formEtudiant")
    public String formEtudiant(Model model){
        model.addAttribute("etudiant",new Etudiant());
        return "formEtudiant";
    }
    @PostMapping("/saveEtudiant")
    public String saveEtudiant(Etudiant etudiant){
        etudiantRepository.save(etudiant);
        return"redirect:/Etudiant";
    }
    @GetMapping("/editEtudiant")
    public String editEtudiant(@RequestParam(name = "id") Long id, Model model){
        Etudiant etudiant=etudiantRepository.findById(id).get();
        model.addAttribute("etudiant",etudiant);
        return "editEtudiant";
    }

}
