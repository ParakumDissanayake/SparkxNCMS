package lk.sparkx.ncms.payload;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class ApiResponse<E>{
    private  String success;
    private  String response;
}
