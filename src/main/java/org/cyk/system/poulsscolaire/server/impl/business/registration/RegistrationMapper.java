package org.cyk.system.poulsscolaire.server.impl.business.registration;

import ci.gouv.dgbf.extension.server.business.IdentifiableMapper;
import org.cyk.system.poulsscolaire.server.api.registration.RegistrationDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.Registration;
import org.mapstruct.Mapper;

/**
 * Cette interface représente le mapping entre {@link Registration} et {@link RegistrationDto}.
 *
 * @author Christian
 *
 */
@Mapper
public interface RegistrationMapper extends IdentifiableMapper<Registration, RegistrationDto> {

}
