package uz.pdp.clickuplesson8tasks.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class ApiResponse {

    private String message;
    private boolean isSuccess;
}
