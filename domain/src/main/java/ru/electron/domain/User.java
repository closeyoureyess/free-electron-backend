package ru.electron.domain;


import lombok.Builder;
import lombok.Data;
import ru.electron.domain.valueobject.*;

import java.util.UUID;

@Data
@Builder
public class User {

    private UserId id;

    private UserName name;

    private UserSurname surname;

    private UserBio bio;

    private UserNickname nickName;

    private UserEmail email;

    private UserNumberPhone numberPhone;

    public static User create(UUID id,
                              String name,
                              String surname,
                              String bio,
                              String nickname,
                              String email,
                              String numberPhone) {
        User.UserBuilder userBuilder = User.builder();
        if (id != null) userBuilder = userBuilder.id(new UserId(id));
        if (name != null) userBuilder = userBuilder.name(new UserName(name));
        if (surname != null) userBuilder = userBuilder.surname(new UserSurname(surname));
        if (bio != null) userBuilder = userBuilder.bio(new UserBio(bio));
        if (nickname != null) userBuilder = userBuilder.nickName(new UserNickname(nickname));
        if (email != null) userBuilder = userBuilder.email(new UserEmail(email));
        if (numberPhone != null) userBuilder = userBuilder.numberPhone(new UserNumberPhone(numberPhone));
        return userBuilder.build();
    }

    public static User create(UUID id,
                              Object name,
                              Object surname,
                              String bio,
                              String nickname,
                              String email,
                              String numberPhone) {
        User.UserBuilder userBuilder = User.builder();
        if (id != null) userBuilder = userBuilder.id(new UserId(id));
        if (name != null) userBuilder = userBuilder.name(new UserName(extractNameFromObjectAndSet(name)));
        if (surname != null) userBuilder = userBuilder.surname(
                new UserSurname(extractSurnameFromObjectAndSet(surname))
        );
        if (bio != null) userBuilder = userBuilder.bio(new UserBio(bio));
        if (nickname != null) userBuilder = userBuilder.nickName(new UserNickname(nickname));
        if (email != null) userBuilder = userBuilder.email(new UserEmail(email));
        if (numberPhone != null) userBuilder = userBuilder.numberPhone(new UserNumberPhone(numberPhone));
        return userBuilder.build();
    }

    public static String extractNameFromObjectAndSet(Object name) {
        return (String) name;
    }

    public static String extractSurnameFromObjectAndSet(Object surname) {
        return (String) surname;
    }
}
