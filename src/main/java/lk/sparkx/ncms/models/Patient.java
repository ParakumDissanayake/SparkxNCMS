package lk.sparkx.ncms.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.sql.Date;

@Getter @Setter @NoArgsConstructor
public class Patient {
    private String id;
    private String serialNo;
    private String firstName;
    private String lastName;
    private String district;
    private int locationX;
    private int locationY;
    private SeverityLevel severityLevel;
    private Gender gender;
    private String contact;
    private String email;
    private int age;
    private Date admitDate;
    private String admittedBy;
    private Date dischargeDate;
    private String dischargedBy;
}
