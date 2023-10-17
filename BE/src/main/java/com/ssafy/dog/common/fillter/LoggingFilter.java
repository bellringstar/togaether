package com.ssafy.dog.common.fillter;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class LoggingFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
		FilterChain filterChain) throws
		ServletException, IOException {

		ContentCachingRequestWrapper requestWrapper = new ContentCachingRequestWrapper(request);
		ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper(response);
		filterChain.doFilter(requestWrapper, responseWrapper);

		log.info("=======URI: [{}], METHOD: [{}]=======", request.getRequestURI(), request.getMethod());
		log.info("Headers: {}", getHeaders(request));
		log.info("QueryString: {}", getQueryParameter(request));
		log.info("Request Body: {}", contentBody(requestWrapper.getContentAsByteArray()));
		log.info("Response Body: {}", contentBody(responseWrapper.getContentAsByteArray()));

		responseWrapper.copyBodyToResponse();
	}

	private Map<String, String> getHeaders(HttpServletRequest request) {
		Map<String, String> headerMap = new HashMap<>();

		Enumeration<String> headerArray = request.getHeaderNames();
		while (headerArray.hasMoreElements()) {
			String headerName = headerArray.nextElement();
			headerMap.put(headerName, request.getHeader(headerName));
		}
		return headerMap;
	}

	private Map<String, String> getQueryParameter(HttpServletRequest request) {
		Map<String, String> queryMap = new HashMap<>();
		request.getParameterMap()
			.forEach((key, value) -> queryMap.put(key, String.join("", value)));
		return queryMap;
	}

	private String contentBody(final byte[] contents) {
		StringBuilder sb = new StringBuilder("\n");
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(contents)));
		bufferedReader.lines().forEach(str -> sb.append(str).append("\n"));
		sb.deleteCharAt(sb.length() - 1);
		return sb.toString();
	}
}