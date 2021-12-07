package com.superyuuki.yuukomponent.api.util;

import java.nio.file.Path;

public class MakeDirImpl implements MakeDir {



    @Override
    public Path madeDir(Path path) {
        boolean ignored = path.toFile().mkdir();

        return path;
    }
}
