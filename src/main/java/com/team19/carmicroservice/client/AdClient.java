package com.team19.carmicroservice.client;

import com.team19.carmicroservice.dto.AdDTOSimple;
import com.team19.carmicroservice.dto.AdDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "ad-service")
public interface AdClient {

    @GetMapping(value="api/getAd/{id}", produces = "application/json")
    AdDTOSimple getAdSimple(@PathVariable("id") Long id,@RequestHeader("permissions") String permissions,
                            @RequestHeader("userID") String userId, @RequestHeader("Authorization") String token);

    @GetMapping("/api/ad/car/{car_id}/active")
    AdDTO checkIfCarHasActiveAds(@PathVariable("car_id") Long id, @RequestHeader("permissions") String permissions,
                 @RequestHeader("userID") String userId, @RequestHeader("Authorization") String token);

    @PutMapping(value = "api/changeCanPostComment/{adId}/{userId}")
    Boolean changeUserComment(@PathVariable("adId") Long adId, @PathVariable("userId") Long uId ,@RequestHeader("permissions") String permissions,
                              @RequestHeader("userID") String userId, @RequestHeader("Authorization") String token);

}
