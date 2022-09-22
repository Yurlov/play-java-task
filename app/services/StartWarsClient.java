package services;

import com.fasterxml.jackson.databind.JsonNode;
import com.typesafe.config.Config;
import play.libs.ws.WSClient;
import play.libs.ws.WSResponse;
import javax.inject.Inject;
import java.util.concurrent.CompletionStage;

public class StartWarsClient {

    private final WSClient ws;
    private final String apiUrl;

    @Inject
    public StartWarsClient(WSClient ws, Config config) {
        this.ws = ws;
        this.apiUrl = config.getString("api-url");
    }

    /**
     * get planet example
     *
     * @return JSON Node that can be converted to Planet class
     */
    public CompletionStage<JsonNode> getFirstPlanet() {
        return ws.url(apiUrl + "planets/1")
                .get()
                .thenApply(WSResponse::asJson);
    }

    /**
     * get planet by id
     *
     * @param id String
     * @return JSON Node that can be converted to Planet class
     */
    public CompletionStage<JsonNode> getPlanetById(String id) {
        return ws.url(apiUrl + "planets/" + id)
                .get()
                .thenApply(WSResponse::asJson);
    }

    /**
     * get person
     *
     * @param url String
     * @return JSON Node that can be converted to Person class
     */
    public CompletionStage<JsonNode> getPerson(String url) {
        return ws.url(url)
                .get()
                .thenApply(WSResponse::asJson);
    }
}
