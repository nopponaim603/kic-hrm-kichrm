package com.kic.hrm.server;

import java.io.InputStream;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class GeoLocationServiceImpl {

	public static String getAddressWithLatLong(String latLong) {
		// TODO Auto-generated method stub
		return getAddress(latLong);
	}

	private static String getAddress(String latLong) {
		try {
			String combile = "http://maps.googleapis.com/maps/api/geocode/xml?latlng="
					+ latLong + "&sensor=true";
			// System.out.println("Address COmbile : " + combile);
			URL mapsUrl = new URL(combile);
			InputStream openStream = mapsUrl.openStream();
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(openStream);

			NodeList formattedAddress = doc
					.getElementsByTagName("formatted_address");
			// System.out.println("Formatted : " +
			// formattedAddress.getLength());

			Element formattedAddressElement = (Element) formattedAddress
					.item(0);
			return formattedAddressElement.getTextContent();
		} catch (Exception e) {
			System.out.println("Error : " + e);
			return null;
		}
	}

	public static double findDistance(double[] gps1, double[] gps2) {
		double R = 6371;
		double latitudeDistance1 = gps1[0]; // a1
		double latitudeDistance2 = gps2[0]; // a2

		double longitudeDistance1 = gps1[1]; // b1
		double longitudeDistance2 = gps2[1]; // b2

		double latitudeDistanceRad = Math.toRadians(latitudeDistance1
				- latitudeDistance2);
		double longitudeDistanceRad = Math.toRadians(longitudeDistance1
				- longitudeDistance2);

		double a = Math.sin(latitudeDistanceRad / 2)
				* Math.sin(latitudeDistanceRad / 2)
				+ Math.cos(Math.toRadians(latitudeDistance1))
				* Math.cos(Math.toRadians(latitudeDistance2))
				* Math.sin(longitudeDistanceRad / 2)
				* Math.sin(longitudeDistanceRad / 2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		double d = R * c;

		return d;
	}

	/*
	 * public static MapsEngine BuildMapAPIbyTOKEN(String token) {
	 * 
	 * HttpTransport httpTransport = new NetHttpTransport(); JacksonFactory
	 * jsonFactory = new JacksonFactory();
	 * 
	 * // This request initializer will ensure the API key is sent with every //
	 * HTTP request. MapsEngineRequestInitializer apiKeyInitializer = new
	 * MapsEngineRequestInitializer( PUBLIC_API_KEY);
	 * 
	 * GoogleCredential credential = new GoogleCredential()
	 * .setAccessToken(token); MapsEngine service = new
	 * MapsEngine.Builder(httpTransport, jsonFactory,
	 * credential).setMapsEngineRequestInitializer(apiKeyInitializer)
	 * .setApplicationName(APPLICATION_NAME).build();
	 * 
	 * return service; }
	 */
	
	/*
	 * static final String SAMPLE_TABLE_ID =
	 * "12421761926155747447-06672618218968397709"; static final String
	 * PUBLIC_API_KEY = "4f36c102c352bcec6c8ee5b40028dc8b6f6602a3";
	 * 
	 * // public static void readFeaturesFromTable(MapsEngine me) throws //
	 * IOException { // Query the table for offices in WA that are within 100km
	 * of Perth.
	 * 
	 * FeaturesListResponse featResp = me .tables() .features()
	 * .list(SAMPLE_TABLE_ID) .setVersion("published") .setWhere(
	 * "State='WA' AND ST_DISTANCE(geometry,ST_POINT(115.8589,-31.9522)) < 100000"
	 * ) .execute();
	 * 
	 * for (Feature feat : featResp.getFeatures()) {
	 * System.out.println("Properties: " + feat.getProperties().toString() +
	 * "\n\t" + "Name: " + feat.getProperties().get("Fcilty_nam") + "\n\t" +
	 * "Geometry Type: " + feat.getGeometry().getType());
	 * 
	 * if (feat.getGeometry() instanceof GeoJsonPoint) { GeoJsonPoint point =
	 * (GeoJsonPoint) feat.getGeometry(); System.out.println("\t" +
	 * "Longitude: " + point.getCoordinates().get(0) + ", " + "Latitude: " +
	 * point.getCoordinates().get(1)); } else {
	 * System.out.println("Only points are expected in this table!"); return; }
	 * }
	 */
	// }
}
