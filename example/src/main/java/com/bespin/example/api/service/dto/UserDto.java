package com.bespin.example.api.service.dto;

import com.bespin.example.api.persistence.domain.User;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import java.sql.Timestamp;
import java.util.List;

/**
 * Project : group.example
 * Class : com.bespin.example.api.service.dto.UserDto
 * Version : 0.0.1
 * Created by josihyeon on 2019-05-23.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserDto {

    @Setter
    @Getter
    public static class Create {

        @NotEmpty
        private String userId;

        @NotEmpty
        private String userEmail;

        @NotEmpty
        private String userName;

        private String department;

        private Boolean isActive;

        private Boolean isUse;

        //@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")

        public User toEntity() {
            return User.builder()
                    .userId(userId)
                    .userEmail(userEmail)
                    .userName(userName)
                    .isActive(isActive)
                    .isUse(isUse)
                    .build();
        }
    }

    @Setter
    @Getter
    public static class Update {

        @NotEmpty
        private String userEmail;
        @NotEmpty
        private String userName;
        private String department;
        private Boolean isActive;
        private Boolean isUse;

        public User apply(User user) {
            return user.update(userEmail, userName, department, isActive, isUse);
        }
    }

    @Getter
    public static class Response {

        private String userId;
        @NotEmpty
        private String userEmail;
        @NotEmpty
        private String userName;
        private String department;
        private Timestamp createTime;
        private Timestamp updateTime;
        private Boolean isActive = true;
        private Boolean isUse = true;

        @Builder
        private Response(String userId, String userEmail, String userName, String department, Timestamp createTime, Timestamp updateTime, Boolean isActive, Boolean isUse) {
            this.userId = userId;
            this.userEmail = userEmail;
            this.userName = userName;
            this.department = department;
            this.createTime = createTime;
            this.updateTime = updateTime;
            this.isActive = isActive;
            this.isUse = isUse;
        }

        public static Response of(User user) {
            return Response.builder()
                    .userId(user.getUserId())
                    .userEmail(user.getUserEmail())
                    .userName(user.getUserName())
                    .department(user.getDepartment())
                    .createTime(user.getCreateTime())
                    .updateTime(user.getUpdateTime())
                    .isActive(user.getIsActive())
                    .isUse(user.getIsUse())
                    .build();
        }
    }

    @Getter
    public static class ResponseOne {

        private Response user;

        public ResponseOne(Response user) {
            this.user = user;
        }
    }

    @Getter
    public static class ResponseList {

        private List<Response> users;

        public ResponseList(List<Response> users) {
            this.users = users;
        }
    }
}
