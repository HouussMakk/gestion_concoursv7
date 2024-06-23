package ma.emsi.conours_v8.web;

import lombok.AllArgsConstructor;
import ma.emsi.conours_v8.entities.Inscription;
import ma.emsi.conours_v8.repositories.InscriptionRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller @AllArgsConstructor
public class InscriptionController {
    private InscriptionRepository inscriptionRepository;

    @GetMapping("/Inscription")
    private String Inscription(Model model,
                            @RequestParam(name = "page", defaultValue = "0") int p,
                            @RequestParam(name ="size", defaultValue = "4") int s,
                            @RequestParam(name ="Keyword",defaultValue = "")String kw){
        Page<Inscription> inscriptionPage =inscriptionRepository.findByReference(kw, PageRequest.of(p,s));
        model.addAttribute("listInscription",inscriptionPage.getContent());
        model.addAttribute("pages",new int[inscriptionPage.getTotalPages()]);
        model.addAttribute("currentPage",p);
        model.addAttribute("Keyword",kw);
        return "inscriptions";
    }
    @GetMapping("/deleteInscription")
    public String delete(@RequestParam(name = "id") Long id,String Keyword,int page){
        inscriptionRepository.deleteById(id);
        return "redirect:/Inscription?page="+page+"& Keyword= "+Keyword;
    }
    @PostMapping("/saveInscription")
    public String saveInscription(Inscription inscriptions){
        inscriptionRepository.save(inscriptions);
        return"redirect:/Inscription";
    }
    @GetMapping("/editInscription")
    public String editInscription(@RequestParam(name = "id") Long id, Model model){
        Inscription inscriptions=inscriptionRepository.findById(id).get();
        model.addAttribute("inscriptions",inscriptions);
        return "editInscription";
    }
    @GetMapping("/InscriptionList")
    public List<Inscription> findAll() {
        return inscriptionRepository.findAll();
    }

}
