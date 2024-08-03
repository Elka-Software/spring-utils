package org.elka.spring.security.token;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@Component
@RequiredArgsConstructor
public abstract class AbstractTokenProperties {
    protected final  String secretKey;
    protected final long jwtExpiration;
    protected final long refreshExpiration;
}
