package com.vote2go.votingsystem.Controller;

import com.vote2go.votingsystem.entity.Candidate;
import com.vote2go.votingsystem.entity.Citizen;
import com.vote2go.votingsystem.repositories.CandidateRepo;
import com.vote2go.votingsystem.repositories.CitizensRepo;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class VotingController {

 final Logger logger = Logger.getLogger(VotingController.class);


    @Autowired
    CitizensRepo citizensRepo;

    @Autowired
    CandidateRepo candidateRepo;

    @RequestMapping("/")
    public String goToVote(){
        logger.info("Returning vote.html file");
        return "vote.html";
    }


    @RequestMapping("/dologin")
    public String doLogin(@RequestParam String name, Model model, HttpSession httpSession){

        logger.info("Getting citizen from database");

        Citizen citizen = citizensRepo.findByName(name);
        logger.info("putting citizen into session");
        httpSession.setAttribute("citizen", citizen);

        if (!citizen.getHasVoted()){
            logger.info("puttin candidates into model");
            List<Candidate> candidates = candidateRepo.findAll();
            model.addAttribute("candidates", candidates);

            return "/performVoted.html";

        } else {
            return "/alreadyVoted.html";
        }
    }

    @RequestMapping("/voteFor")
    public String voteFor(@RequestParam Long id, HttpSession session){
        logger.info("voting");

        Citizen citizen = ((Citizen) session.getAttribute("citizen"));

        if (!citizen.getHasVoted()) {

            citizen.setHasVoted(true);

            Candidate c = candidateRepo.findById((long) id);
            logger.info("voting for candidate " + c.getName());
            c.setCountOfVotes(c.getCountOfVotes() + 1);

            candidateRepo.save(c);
            citizensRepo.save(citizen);

            return "voted.html";
        }
        return "alreadyVoted.html";
    }
}
