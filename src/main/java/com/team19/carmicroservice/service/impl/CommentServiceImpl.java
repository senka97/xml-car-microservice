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
import java.util.List;

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

        //dobavljanje ad-a iz ad-service zbog carId
        AdDTOSimple ad = this.adClient.getAdSimple(id);

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
            ArrayList<CommentDTO> returnedComments = userClient.getCommentCreator(newComments);

            return returnedComments;
        }
        else return null;
    }

    @Override
    public Boolean createComment(NewCommentDTO comment) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomPrincipal cp = (CustomPrincipal) auth.getPrincipal();

        //dobavljanje ad-a iz ad-service zbog carId
        AdDTOSimple ad = this.adClient.getAdSimple(comment.getAdId());

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
            if (comment.getCommentStatus().equals(CommentStatus.APPROVED)) {
                comment.setReplyContent(reply.getReplyContent());
                comment.setReplyStatus(ReplyStatus.POSTED);
                commentRepository.save(comment);
                return true;
            }
            else return false;
        }
        else return false;
    }

    @Override
    public ArrayList<CommentDTO> getAllPostedComments() {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomPrincipal cp = (CustomPrincipal) auth.getPrincipal();

        ArrayList<Comment> comments = commentRepository.findAllPosetdComments();
        ArrayList<CommentDTO> commentDTOS = new ArrayList<>();

        for (Comment c : comments) {
            commentDTOS.add(new CommentDTO(c));
        }

        ArrayList<CommentDTO> returnedComments = userClient.getCommentCreator(commentDTOS);
        return returnedComments;

    }

    @Override
    public List<Comment> getAllPostedReplies() {
        return commentRepository.findAllPostedReplies();
    }

    @Override
    public boolean approveComment(Long id) {
        Comment comment = commentRepository.getOne(id);
        if (comment != null && comment.getCommentStatus().equals(CommentStatus.POSTED)) {
            comment.setCommentStatus(CommentStatus.APPROVED);
            commentRepository.save(comment);
            return true;
        }
        return false;
    }

    @Override
    public boolean rejectComment(Long id) {
        Comment comment = commentRepository.getOne(id);
        if (comment != null && comment.getCommentStatus().equals(CommentStatus.POSTED)) {
            comment.setCommentStatus(CommentStatus.REJECTED);
            commentRepository.save(comment);
            return true;
        }
        return false;
    }

    @Override
    public boolean approveReply(Long id) {
        Comment comment = commentRepository.getOne(id);
        if (comment != null && comment.getReplyStatus().equals(ReplyStatus.POSTED)) {
            comment.setReplyStatus(ReplyStatus.APPROVED);
            commentRepository.save(comment);
            return true;
        }
        return false;
    }

    @Override
    public boolean rejectReply(Long id) {
        Comment comment = commentRepository.getOne(id);
        if (comment != null && comment.getReplyStatus().equals(ReplyStatus.POSTED)) {
            comment.setReplyStatus(ReplyStatus.REJECTED);
            commentRepository.save(comment);
            return true;
        }
        return false;
    }

    @Override
    public void hideCommentRequestsForBlockedAndRemovedClient(Long id) {
        //proveravam zahteve za komentare koje je ostavio korisnik pre, a koji sada treba da se obrise ili blokira
        ArrayList<Comment> allClientComments = commentRepository.findAllByFromComment(id);
        if (!allClientComments.isEmpty()) {
            for (Comment c : allClientComments) {
                if (c.getCommentStatus().equals(CommentStatus.POSTED) && c.getReplyStatus().equals(ReplyStatus.NOT_POSTED)) {
                    c.setCommentStatus(CommentStatus.REJECTED);
                }
                if (c.getReplyStatus().equals(ReplyStatus.POSTED)) {
                    c.setReplyStatus(ReplyStatus.REJECTED);
                }
                commentRepository.save(c);
            }
        }

        //proveravam zahteve za komentare za oglase(tacnije automobile) koji pripadaju korisniku,a koji sada treba da se obrise ili blokira
        ArrayList<Comment> allCommentsOnClientCars = commentRepository.findAllByCar_OwnerId(id);
        if (!allCommentsOnClientCars.isEmpty()) {
            for (Comment c : allCommentsOnClientCars) {
                if (c.getCommentStatus().equals(CommentStatus.POSTED) && c.getReplyStatus().equals(ReplyStatus.NOT_POSTED)) {
                    c.setCommentStatus(CommentStatus.REJECTED);
                }
                if (c.getReplyStatus().equals(ReplyStatus.POSTED)) {
                    c.setReplyStatus(ReplyStatus.REJECTED);
                }
                commentRepository.save(c);
            }
        }

    }

}
