package work.icql.springboot.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class User implements Serializable {

    private static final long serialVersionUID = 7360799469210163425L;

    private Long id;

    private String usercode;

    private String username;

    private String password;

    private Short age;

    private String memo;

    private Integer deleted;
}