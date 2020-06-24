package com.team19.carmicroservice.soap;

import com.rent_a_car.car_service.soap.*;
import com.team19.carmicroservice.dto.NewReplyDTO;
import com.team19.carmicroservice.service.impl.CommentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.ArrayList;

@Endpoint
public class CommentEndpoint {

    @Autowired
    private CommentServiceImpl commentService;

    private static final String NAMESPACE_URI = "http://www.rent-a-car.com/car-service/soap";

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "CommentsRequest")
    @ResponsePayload
    public CommentsResponse getComments(@RequestPayload CommentsRequest request) {

        CommentsResponse response = new CommentsResponse();
        ArrayList<CommentSOAP> commentArrayList = commentService.getCommentsForCarSOAP(request.getId());

        if( commentArrayList != null)
        {
            for(CommentSOAP comm : commentArrayList)
            {
                response.getCommentSOAP().add(comm);
            }
        }
        return response;

    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "SendReplyRequest")
    @ResponsePayload
    public SendReplyResponse sendReply(@RequestPayload SendReplyRequest request) {

        SendReplyResponse response = new SendReplyResponse();

        NewReplyDTO dto = new NewReplyDTO(request.getReplyContent());

        response.setFlag(this.commentService.replyComment(request.getCommentId(), dto));

        return response;
    }
}
