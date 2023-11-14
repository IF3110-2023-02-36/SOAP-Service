package models;

import com.google.protobuf.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class loggingModel {
    private String ip;
    private Timestamp timestamp;
    private String endpoint;
    private String methodAccesed;
    private String caller;

    
}
