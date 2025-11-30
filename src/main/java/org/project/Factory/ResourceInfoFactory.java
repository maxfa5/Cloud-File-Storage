package org.project.Factory;

import org.project.DTO.FileType;
import org.project.DTO.ResourceInfo;
//TODO!!!!!!
public class ResourceInfoFactory {
    public static ResourceInfo createFile(String filename, String path, long byteSize) {
        return new ResourceInfo(path, path, null, byteSize);
    }


    public static ResourceInfo createDirectory(String filename, String path) {
        return new ResourceInfo(path, path, null, 1);

    }
}