package org.rest.annotations.digest.auth;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.NTCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.AuthSchemes;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;

public class AuthExample
{

	public static void main(String[] args) throws ClientProtocolException, IOException
	{
		PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
		cm.setMaxTotal(18);
		cm.setDefaultMaxPerRoute(6);

		HttpGet httpget = new HttpGet("http://localhost:8080/employeesmanager/read/24");
		CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
		credentialsProvider.setCredentials(new AuthScope("localhost", 8080, "myrealm"),
			new NTCredentials("wicket", "wicket", "localhost", "localhost"));

		RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(30000)
			.setConnectTimeout(30000).setTargetPreferredAuthSchemes(Arrays.asList(AuthSchemes.NTLM))
			.setProxyPreferredAuthSchemes(Arrays.asList(AuthSchemes.BASIC)).build();


		HttpClient httpclient = HttpClients.custom().setConnectionManager(cm)
			.setDefaultCredentialsProvider(credentialsProvider)
			.setDefaultRequestConfig(requestConfig).build();

		HttpResponse response = httpclient.execute(httpget);
		BufferedReader rd = new BufferedReader(
			new InputStreamReader(response.getEntity().getContent()));
		String json = IOUtils.toString(rd);
		System.out.println(json);
	}

}
