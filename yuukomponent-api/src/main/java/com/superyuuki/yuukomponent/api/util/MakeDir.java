package com.superyuuki.yuukomponent.api.util;

import java.io.File;
import java.nio.file.Path;

public interface MakeDir {

    Path makeDir(Path path);

    default Path makeDir(File file) {
        return makeDir(file.toPath());
    }

}
