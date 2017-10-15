package dimatkachenko.com.SendPayment;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProcessingControllerImpl implements ProcessingController {

	private int CONNECT_TIMEOUT = 10 * 1000;
	private int READ_TIMEOUT = 1 * 60 * 1000;
	private String EMPTY = "";
	private String gatewayLink = "https://sandbox-secure.unitedthinkers.com/gates/xurl?";

	public String sendPayment(String data) {
		try {
			return getGatewayResponseCode(sendPOSTToGateway(data));
		} catch (Exception e) {
			return EMPTY;
		}
	}

	private String sendPOSTToGateway(String data) throws IOException {

		HttpURLConnection conn = null;
		InputStream stream = null;
		URL urlGatewayLink = new URL(gatewayLink);
		OutputStreamWriter writer = null;
		String streamAsString;

		conn = (HttpURLConnection) urlGatewayLink.openConnection();
		conn.setDoInput(true);
		conn.setDoOutput(true);
		conn.setUseCaches(false);
		conn.setConnectTimeout(CONNECT_TIMEOUT);
		conn.setReadTimeout(READ_TIMEOUT);
		conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		conn.setRequestMethod("POST");

		writer = new OutputStreamWriter(conn.getOutputStream());
		writer.write(data);
		writer.flush();
		writer.close();

		if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
			stream = conn.getInputStream();
		} else {
			stream = conn.getErrorStream();
		}

		if (stream == null) {
			return EMPTY;
		}

		streamAsString = stream2String(stream);
		
		conn.disconnect();
		
		return streamAsString;
	}

	private String stream2String(InputStream is) throws IOException {
		StringBuilder sb = new StringBuilder(8192);
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		String line = null;
		while ((line = br.readLine()) != null) {
			sb.append(line);
		}
		return sb.toString();
	}

	private String getGatewayResponseCode(String response) {

		String gatewayResponseCode = EMPTY;

		if (response.equals(EMPTY)) {
			return gatewayResponseCode;
		}

		String[] responseParameter;
		List<String> listResponseParameters = Arrays.asList(response.split("\\&", 0));
		Map<String, String> mapResponseParameters = new HashMap<String, String>();

		for (String item : listResponseParameters) {
			responseParameter = item.split("\\=", 0);
			if (responseParameter.length > 1) {
				mapResponseParameters.put(responseParameter[0], responseParameter[1]);
			}
		}

		if (mapResponseParameters.get("responseType").equals("sale")) {
			gatewayResponseCode = mapResponseParameters.get("responseCode");
		}

		return gatewayResponseCode;
	}

}
