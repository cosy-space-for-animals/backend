package com.memopet.memopet.domain.oauth2.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SocialUser {
    public String id;
    public String email;
    public String name;
    public String mobile;
    public String mobile_e164;

    /** 구글용 */
    public String verified_email;
    public String given_name;
    public String family_name;
    public String picture;
}
