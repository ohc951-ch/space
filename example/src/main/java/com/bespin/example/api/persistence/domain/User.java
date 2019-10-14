package com.bespin.example.api.persistence.domain;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.envers.Audited;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Project : group.example
 * Class : com.bespin.example.api.persistence.domain.User
 * Version : 0.0.1
 * Created by josihyeon on 2019-05-23.
 */
@Entity
@Table(name = "user")
@NoArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "userId", columnDefinition = "BINARY(16)")
    private String userId;

    @Column(name = "userEmail")
    private String userEmail;

    @Column(name = "userName")
    private String userName;

    @Column(name = "department")
    private String department;

    @CreationTimestamp
    @Column(name = "createTime", updatable = false, insertable = true)
    private Timestamp createTime;

    @UpdateTimestamp
    @Column(name = "updateTime", updatable = true, insertable = false)
    private Timestamp updateTime;

    @Column(name = "isActive")
    private Boolean isActive = true;

    @Column(name = "isUse")
    private Boolean isUse = true;

    @Builder
    private User(String userId, String userEmail, String userName, String department, Boolean isActive, Boolean isUse) {

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Calendar cal = Calendar.getInstance();
        String today = null;
        today = formatter.format(cal.getTime());
        Timestamp ts = Timestamp.valueOf(today);

        this.userId = userId;
        this.userEmail = userEmail;
        this.userName = userName;
        this.department = department;
        this.createTime = ts;
        this.isActive = isActive;
        this.isUse = isUse;
    }

    public User update(String userEmail, String userName, String department, Boolean isActive, Boolean isUse) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Calendar cal = Calendar.getInstance();
        String today = null;
        today = formatter.format(cal.getTime());
        Timestamp ts = Timestamp.valueOf(today);

        this.userEmail = userEmail;
        this.userName = userName;
        this.department = department;
        this.updateTime = ts;
        this.isActive = isActive;
        this.isUse = isUse;
        return this;
    }


}