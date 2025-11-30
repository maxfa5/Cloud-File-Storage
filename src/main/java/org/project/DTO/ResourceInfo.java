package org.project.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResourceInfo {
    private String filename;
    private String path;
    private FileType type;
    private long Bytesize; 
}
