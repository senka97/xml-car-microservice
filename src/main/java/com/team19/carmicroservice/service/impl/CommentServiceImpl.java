package com.team19.carmicroservice.service.impl;

import com.team19.carmicroservice.client.AdClient;
import com.team19.carmicroservice.client.UserClient;
import com.team19.carmicroservice.dto.AdDTOSimple;
import com.team19.carmicroservice.dto.CommentDTO;
import com.team19.carmicroservice.dto.NewCommentDTO;
import com.team19.carmicroservice.dto.NewReplyDTO;
import com.team19.carmicroservice.enums.CommentStatus;
import com.team19.carmicroservice.enums.ReplyStatus;
import com.team19.carmicroservice.model.Car;
import com.team19.carmicroservice.model.Comment;
import com.team19.carmicroservice.repository.CommentRepository;
import com.team19.carmicroservice.security.CustomPrincipal;
import com.team19.carmicroservice.service.CarService;
import com.team19.carmicroservice.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
                if(c.getCommentStatus().equals(CommentStatus.APPROVED))
                {
                    CommentDTO newC = new CommentDTO();
                    newC.setCarId(car.getId());
                    newC.setId(c.getId());
                    newC.setContent(c.getContent());
                    newC.setDateTime(c.getDateTime());
                    newC.setFromComment(c.getFromComment());

                    if(c.getReplyStatus().equals(ReplyStatus.APPROVED))
                    {
                        newC.setReplyContent(c.getReplyContent());
                        newC.setIsReplied(true);
                    }
                    else if(c.getReplyStatus().equals(ReplyStatus.POSTED))
                    {
                        newC.setReplyContent(null); // da se ne bi prikazao odgovor
                        newC.setIsReplied(true); // ako je odgovor ceka odobravanje ne treba da kaci opet
                    }
                    else{ // ako nije odobren komentar, i ako je kacen pre i ako nije treba dozvoliti da se odgovori opet
                        newC.setIsReplied(false);
                    }
                    newComments.add(newC);
                }


            }
            // komentari odlaze u user-service da se popuni ime i prezime korisnika koji je ostavio komentar
            ArrayList<CommentDTO> returnedComments = userClient.getCommentCreator(newComments,cp.getPermissions(), cp.getUserID(), cp.getToken());

            return returnedComments;
        }
        else return null;
    }

    @Override
    public Boolean createComment(NewCommentDTO comment) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomPrincipal cp = (CustomPrincipal) auth.getPrincipal();

        //dobavljanje ad-a iz ad-service zbog carId
        AdDTOSimple ad = this.adClient.getAdSimple(comment.getAdId(),cp.getPermissions(), cp.getUserID(), cp.getToken());

        if(ad != null)
        {
            Car car = carService.getCarById(ad.getCarId());
            if(car != null) {
                Comment com = new Comment();

                com.setCar(car);
                com.setDateTime(LocalDateTime.now());
                com.setContent(comment.getContent());
                com.setFromComment(comment.getFromComment());
                com.setCommentStatus(CommentStatus.POSTED);
                com.setReplyContent(null);
                com.setReplyStatus(ReplyStatus.NOT_POSTED);

                //promenim da korisnik vise ne moze komentarisati ovaj oglas
                if(this.adClient.changeUserComment(ad.getId(),comment.getFromComment(),cp.getPermissions(), cp.getUserID(), cp.getToken()))
                {
                    commentRepository.save(com);
                    return true;
                }
                else return false;
            }
            else return false;
        }
        else return false;
    }

    @Override
    public Boolean replyComment(Long id, NewReplyDTO reply) {

        Comment comment = commentRepository.findById(id).orElse(null);

        if(comment != null)
        {
            comment.setReplyContent(reply.getReplyContent());
            comment.setReplyStatus(ReplyStatus.POSTED);
            commentRepository.save(comment);
            return true;
        }
        else return false;
    }


}
