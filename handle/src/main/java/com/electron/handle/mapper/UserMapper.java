package com.electron.handle.mapper;

import com.electron.handle.dto.UserResponse;
import com.electron.handle.entity.UserEntity;
import org.mapstruct.*;
import ru.electron.domain.User;
import ru.electron.domain.valueobject.*;

import java.util.UUID;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface UserMapper {

    @Mapping(source = "id", target = "id", qualifiedByName = "toId")
    @Mapping(source = "name", target = "name", qualifiedByName = "toName")
    @Mapping(source = "surname", target = "surname", qualifiedByName = "toSurname")
    @Mapping(source = "bio", target = "bio", qualifiedByName = "toBio")
    @Mapping(source = "nickName", target = "nickName", qualifiedByName = "toNickname")
    @Mapping(source = "email", target = "email", qualifiedByName = "toEmail")
    @Mapping(source = "numberPhone", target = "numberPhone", qualifiedByName = "toNumberPhone")
    User toUser(UserEntity userEntity);

    @Named("toId")
    default UserId toId(UUID id) {
        return id != null ? new UserId(id) : null;
    }

    @Named("toName")
    default UserName toName(String name) {
        return name != null ? new UserName(name) : null;
    }

    @Named("toSurname")
    default UserSurname toSurname(String surname) {
        return surname != null ? new UserSurname(surname) : null;
    }

    @Named("toBio")
    default UserBio toBio(String bio) {
        return bio != null ? new UserBio(bio) : null;
    }

    @Named("toNickname")
    default UserNickname toNickname(String nickname) {
        return nickname != null ? new UserNickname(nickname) : null;
    }

    @Named("toEmail")
    default UserEmail toEmail(String email) {
        return email != null ? new UserEmail(email) : null;
    }

    @Named("toNumberPhone")
    default UserNumberPhone toNumberPhone(String numberphone) {
        return numberphone != null ? new UserNumberPhone(numberphone) : null;
    }

    UserResponse toUserResponse(UserEntity userEntity);

    @Mapping(source = "id", target = "id", qualifiedByName = "uuidToString")
    @Mapping(source = "name", target = "name", qualifiedByName = "userNameToString")
    @Mapping(source = "surname", target = "surname", qualifiedByName = "userSurnameToString")
    @Mapping(source = "bio", target = "bio", qualifiedByName = "userBioToString")
    @Mapping(source = "nickName", target = "nickName", qualifiedByName = "userNicknameToString")
    @Mapping(source = "email", target = "email", qualifiedByName = "userEmailToString")
    @Mapping(source = "numberPhone", target = "numberPhone", qualifiedByName = "userNumberPhoneToString")
    UserEntity toUserEntity(User user);

    @Named("uuidToString")
    default UUID uuidToString(UserId id) {
        return id != null ? id.id() : null;
    }

    @Named("userNameToString")
    default String userNameToString(UserName name) {
        return name != null ? name.name() : null;
    }

    @Named("userSurnameToString")
    default String userSurnameToString(UserSurname surname) {
        return surname != null ? surname.surname() : null;
    }

    @Named("userBioToString")
    default String userBioToString(UserBio bio) {
        return bio != null ? bio.bio() : null;
    }

    @Named("userNicknameToString")
    default String userNicknameToString(UserNickname nickname) {
        return nickname != null ? nickname.nickname() : null;
    }

    @Named("userEmailToString")
    default String userEmailToString(UserEmail email) {
        return email != null ? email.email() : null;
    }

    @Named("userNumberPhoneToString")
    default String userNumberPhoneToString(UserNumberPhone numberPhone) {
        return numberPhone != null ? numberPhone.numberPhone() : null;
    }
}
