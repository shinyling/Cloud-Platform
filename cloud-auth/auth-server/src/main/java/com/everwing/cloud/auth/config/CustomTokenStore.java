package com.everwing.cloud.auth.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import java.util.Date;

/**
 * @author DELL shiny
 * @create 2020/3/2
 */

public class CustomTokenStore extends RedisTokenStore {

    private Integer invalidSecond;

    private ClientDetailsService clientDetailsService;

    public CustomTokenStore(RedisConnectionFactory connectionFactory, ClientDetailsService clientDetailsService,Integer invalidSecond) {
        super(connectionFactory);
        this.clientDetailsService = clientDetailsService;
        this.invalidSecond=invalidSecond;
    }

    @Override
    public OAuth2Authentication readAuthentication(OAuth2AccessToken token) {
        OAuth2Authentication result = readAuthentication(token.getValue());
        if (result != null) {
            // 如果token没有失效  更新AccessToken过期时间
            DefaultOAuth2AccessToken oAuth2AccessToken = (DefaultOAuth2AccessToken) token;

            //重新设置过期时间
            int validitySeconds = getAccessTokenValiditySeconds(result.getOAuth2Request());
            if (validitySeconds > 0) {
                oAuth2AccessToken.setExpiration(new Date(System.currentTimeMillis() + (invalidSecond * 1000L)));
            }

            //将重新设置过的过期时间重新存入redis, 此时会覆盖redis中原本的过期时间
            storeAccessToken(token, result);
        }
        return result;
    }

    protected int getAccessTokenValiditySeconds(OAuth2Request clientAuth) {
        if (clientDetailsService != null) {
            ClientDetails client = clientDetailsService.loadClientByClientId(clientAuth.getClientId());
            Integer validity = client.getAccessTokenValiditySeconds();
            if (validity != null) {
                return validity;
            }
        }
        // default 6 min.
        return invalidSecond;
    }
}
