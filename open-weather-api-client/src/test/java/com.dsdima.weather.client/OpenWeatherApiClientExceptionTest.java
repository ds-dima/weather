package com.dsdima.weather.client;

import com.dsdima.weather.client.service.exception.OpenWeatherApiException;
import com.dsdima.weather.client.service.exception.OpenWeatherApiNotFoundException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withBadRequest;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withServerError;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;

/**
 * @author dsshevchenko
 * @since <pre>1/22/2018</pre>
 */
public class OpenWeatherApiClientExceptionTest extends OpenWeatherApiAbstractTest {

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Test
    public void serverErrorFailedTest() throws Exception {
        mockServer.expect(requestTo(HTTP_API_OPENWEATHERMAP_FULL_URL + "&id=596826"))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withServerError());
        expectedEx.expect(OpenWeatherApiException.class);
        expectedEx.expectMessage("Ошибка работы провайдера погоды. (Internal Server Error)");
        client.getWeatherByCityId(596826);
    }

    @Test
    public void badRequestErrorFailedTest() throws Exception {
        mockServer.expect(requestTo(HTTP_API_OPENWEATHERMAP_FULL_URL + "&id=596826"))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withBadRequest());
        expectedEx.expect(OpenWeatherApiException.class);
        expectedEx.expectMessage("Неправильные параметры запроса. (Bad Request)");
        client.getWeatherByCityId(596826);
    }

    @Test
    public void notFoundErrorFailedTest() throws Exception {
        mockServer.expect(requestTo(HTTP_API_OPENWEATHERMAP_FULL_URL + "&q=111111111fdsfds"))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withStatus(HttpStatus.NOT_FOUND));
        expectedEx.expect(OpenWeatherApiNotFoundException.class);
        expectedEx.expectMessage("По данному запросу ничего не найдено");
        client.getWeatherByCityName("111111111fdsfds");
    }
}
