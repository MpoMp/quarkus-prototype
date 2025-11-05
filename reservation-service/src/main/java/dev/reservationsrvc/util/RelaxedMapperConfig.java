package dev.reservationsrvc.util;

import org.mapstruct.MapperConfig;
import org.mapstruct.ReportingPolicy;

/**
 * Allows for unmapped fields when one knows it's safe to do so.
 */
@MapperConfig(
        unmappedTargetPolicy = ReportingPolicy.WARN,
        unmappedSourcePolicy = ReportingPolicy.WARN
)
public class RelaxedMapperConfig {
}
