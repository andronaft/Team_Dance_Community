package com.zuk.dto.user.profile;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.zuk.model.Status;
import com.zuk.model.UserProfile;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserProfileDto {
    private String username;
    private long userId;
    private String mobile;
    private String social;
    private String about;
    private String imgUrl;//TODO
    private int rating;
    private int level;
    private Status status;

    public UserProfile toUserProfile(){
        UserProfile userProfile = new UserProfile();
        userProfile.setUserId(userId);
        userProfile.setMobile(mobile);
        userProfile.setSocial(social);
        userProfile.setAbout(about);
        userProfile.setImgUrl(imgUrl);
        userProfile.setLevel(level);
        userProfile.setRating(rating);
        return userProfile;
    }

    public static UserProfileDto fromUserProfile(UserProfile userProfile){//TODO nullpoint


    UserProfileDto userProfileDto = new UserProfileDto();

        userProfileDto.setUserId(userProfile.getUserId());
        userProfileDto.setMobile(userProfile.getMobile());
        userProfileDto.setSocial(userProfile.getSocial());
        userProfileDto.setAbout(userProfile.getAbout());
        userProfileDto.setImgUrl(userProfile.getImgUrl());
        userProfileDto.setLevel(userProfile.getLevel());
        userProfileDto.setRating(userProfile.getRating());

        return userProfileDto;

    }
}
