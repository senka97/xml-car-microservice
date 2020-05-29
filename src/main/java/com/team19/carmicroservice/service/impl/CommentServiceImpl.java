package com.team19.carmicroservice.service.impl;

import com.team19.carmicroservice.client.AdClient;
import com.team19.carmicroservice.client.UserClient;
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

import java.lang.reflect.Array;
import java.util.ArrayList;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private CarService carService;

    @Autowired
    private AdClient adClient;

    @Autowired
    private UserClient userClient;


    @Override
    public ArrayList<CommentDTO> getCommentsForCar(Long id) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomPrincipal cp = (CustomPrincipal) auth.getPrincipal();

        //dobavljanje ad-a iz ad-service zbog carId
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
                newC.setReplyContent(c.getReplyContent());
                newC.setIsReplied(c.getIsReplied());

                newComments.add(newC);
            }
            // komentari odlaze u user-service da se popuni ime i prezime korisnika koji je ostavio komentar
            ArrayList<CommentDTO> returnedComments = userClient.getCommentCreator(newComments,cp.getPermissions(), cp.getUserID(), cp.getToken());

            return returnedComments;
        }
        else return null;
    }
}
