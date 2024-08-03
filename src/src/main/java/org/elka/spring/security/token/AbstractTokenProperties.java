package org.elka.spring.security.token;

import lombok.Data;
import lombok.RequiredArgsConstructor;
@Data
@RequiredArgsConstructor
public abstract class AbstractTokenProperties {
    protected final  String secretKey;
    protected final long jwtExpiration;
    protected final long refreshExpiration;
}
