package lk.sparkx.ncms.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;


@Getter @Setter @NoArgsConstructor
public class Hospital {
    private  String id;
    private  String name;
    private String district;
    private int locationX;
    private int locationY;
    private Date buildDate;

}
