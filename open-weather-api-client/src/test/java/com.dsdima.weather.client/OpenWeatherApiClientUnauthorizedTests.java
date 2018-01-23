package com.dsdima.weather.client;

import com.dsdima.weather.client.service.exception.OpenWeatherApiException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.TestPropertySource;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withUnauthorizedRequest;

/**
 * @author dsshevchenko
 * @since <pre>1/22/2018</pre>
 */
@TestPropertySource(properties = {
        "openweather.api.appid = 46f7323cb5c5febda15f2a2baaf4ed64wrong",
})
public class OpenWeatherApiClientUnauthorizedTests extends OpenWeatherApiAbstractTest {

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Test
    public void unauthorizedErrorFailedTest() throws Exception {
        mockServer.expect(requestTo(HTTP_API_OPENWEATHERMAP_BASE_URL + APP_ID + "wrong" + "&" + UNITS_METRIC + "&id=596826"))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withUnauthorizedRequest());
        expectedEx.expect(OpenWeatherApiException.class);
        expectedEx.expectMessage("Ошибка авторизации, уточните корректность 'appid' (Unauthorized)");
        client.getWeatherByCityId(596826);
    }


}
