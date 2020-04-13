package com.zuk.dto.user;

import com.zuk.model.UserProfile;
import lombok.Data;

@Data
public class UserProfileDto {
    private long userId;
    private String mobile;
    private String social;
    private String about;
    private String imgUrl;//TODO
    private int rating;
    private int level;

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

    public static UserProfileDto fromUserProfile(UserProfile userProfile){

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
