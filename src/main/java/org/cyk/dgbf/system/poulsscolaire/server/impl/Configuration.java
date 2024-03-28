package org.cyk.dgbf.system.poulsscolaire.server.impl;

import io.quarkus.runtime.annotations.StaticInitSafe;
import io.smallrye.config.ConfigMapping;

/**
 * Cette interface représente la configuration du processus.
 *
 * @author Christian
 *
 */
@StaticInitSafe
@ConfigMapping(prefix = "cyk.system.poulsscolaire")
public interface Configuration {
  

}
