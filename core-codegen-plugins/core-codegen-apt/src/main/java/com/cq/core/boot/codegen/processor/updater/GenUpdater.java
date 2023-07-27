package com.cq.core.boot.codegen.processor.updater;

/**
 * @author gim
 */
public @interface GenUpdater {

  String pkgName();

  String sourcePath() default "src/main/java";

  boolean overrideSource() default false;
}
