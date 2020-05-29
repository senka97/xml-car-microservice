package com.team19.carmicroservice.service.impl;

import com.team19.carmicroservice.client.AdClient;
import com.team19.carmicroservice.dto.AdDTOSimple;
import com.team19.carmicroservice.dto.CommentDTO;
import com.team19.carmicroservice.model.Car;
import com.team19.carmicroservice.model.Comment;
import com.team19.carmicroservice.repository.CarRepository;
import com.team19.carmicroservice.repository.CommentRepository;
import com.team19.carmicroservice.security.CustomPrincipal;
import com.team19.carmicroservice.service.CarService;
import com.team19.carmicroservice.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private CarService carService;

    @Autowired
    private AdClient adClient;


    @Override
    public ArrayList<CommentDTO> getCommentsForCar(Long id) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomPrincipal cp = (CustomPrincipal) auth.getPrincipal();

        AdDTOSimple ad = this.adClient.getAdSimple(id,cp.getPermissions(), cp.getUserID(), cp.getToken());

        Car car = carService.getCarById(ad.getCarId());

        if(car != null)
        {
            ArrayList<Comment> comments = commentRepository.findCommentsForCar(car.getId());
            ArrayList<CommentDTO> newComments = new ArrayList<>();
            for(Comment c : comments)
            {
                CommentDTO newC = new CommentDTO();
                newC.setCarId(car.getId());
                newC.setId(c.getId());
                newC.setContent(c.getContent());
                newC.setDateTime(c.getDateTime());
                newC.setFromComment(c.getFromComment());
                newC.setReplayContent(c.getReplayContent());
                newC.setIsReplayed(c.getIsReplayed());

                //poslati zahtev na user service da se pokupe ime i prezime usera koji je okacio komentar
                newComments.add(newC);
            }
            return newComments;
        }
        else return null;
    }
}
