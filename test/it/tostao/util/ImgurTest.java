package it.tostao.util;

import com.github.scribejava.apis.ImgurApi;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.oauth.OAuth20Service;
import org.junit.Test;
import play.libs.ws.WS;
import play.libs.ws.WSClient;
import play.libs.ws.WSResponse;

import javax.inject.Inject;
import java.util.Scanner;
import java.util.concurrent.CompletionStage;

/**
 * Created by Slawomir Leski <slawomir.leski@idnow.de> on 12.01.17.
 */
public class ImgurTest {

		@Inject
		protected WSClient wsClient;

		private static final String NETWORK_NAME = "Imgur";
		private static final String PROTECTED_RESOURCE_URL = "https://api.imgur.com/3/account/me";

		@Test
		public void shouldLoginToImgur() throws Exception {


			final String apiKey = "TO_SET";
			final String apiSecret = "TO_SET";
			final OAuth20Service service = new ServiceBuilder()
					.apiKey(apiKey)
					.apiSecret(apiSecret)
					.build(ImgurApi.instance());
			final Scanner in = new Scanner(System.in);

			System.out.println("=== " + NETWORK_NAME + "'s OAuth Workflow ===");
			System.out.println();

			// Obtain the Authorization URL
			System.out.println("Fetching the Authorization URL...");
			final String authorizationUrl = service.getAuthorizationUrl();
			System.out.println("Got the Authorization URL!");
			System.out.println("Now go and authorize ScribeJava here:");
			System.out.println(authorizationUrl);

			String correctUrl = authorizationUrl.substring(0, authorizationUrl.lastIndexOf("&"));


			WSClient ws = WS.newClient(-1);
			CompletionStage<WSResponse> wsResponseCompletionStage = ws.url(correctUrl).get();

			WSResponse wsResponse = wsResponseCompletionStage.toCompletableFuture().get();
			System.out.println(wsResponse.getStatus());
			System.out.println(wsResponse.getBody());

//			test(wsResponseCompletionStage1);
//			wsResponseCompletionStage1.thenAccept(wsResponse1 -> {
//				System.out.println("==========================");
//				System.out.println(wsResponse1.getStatus());
//				System.out.println(wsResponse1.asJson());
//				if(wsResponse1.getStatus() == 200){
//					System.out.println("Lauft!!!!!!");
//				}
//			});


//			CompletionStage<WSResponse> wsResponseCompletionStage = wsClient.url(correctUrl).get();
//			wsResponseCompletionStage.thenAccept(wsResponse -> {
//				if (wsResponse.getStatus() == 200) {
//					System.out.println(wsResponse.getBodyAsStream());
//				}
//			});
//			System.out.println("And paste the authorization code here");
//			System.out.print(">>");
//			final String code = in.nextLine();
//			System.out.println();
//
//			// Trade the Request Token and Verifier for the Access Token
//			System.out.println("Trading the Request Token for an Access Token...");
//			final OAuth2AccessToken accessToken = service.getAccessToken(code);
//			System.out.println("Got the Access Token!");
//			System.out.println("(if your curious it looks like this: " + accessToken
//					+ ", 'rawResponse'='" + accessToken.getRawResponse() + "')");
//			System.out.println();

		}

}
