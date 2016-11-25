package com.jobplus.utils;

import static java.lang.String.format;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUpload;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.FileUploadBase.SizeLimitExceededException;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartException;

public class CommonsMultiparResolver extends org.springframework.web.multipart.commons.CommonsMultipartResolver {
	/**
	 * 用于过滤百度编辑器上传文件时阻止 commonsMultipartResolver 对文件进行包装
	 */
	@Override
	public boolean isMultipart(javax.servlet.http.HttpServletRequest request) {
		String uri = request.getRequestURI();
		// 过滤使用百度EmEditor的URI
		if (uri.indexOf("topics/imageUp") != -1) {
			// System.out.println("commonsMultipartResolver 放行");
			return false;
		}
		// System.out.println("commonsMultipartResolver 拦截");
		return super.isMultipart(request);
	}

	/**
	 * Parse the given servlet request, resolving its multipart elements.
	 * 
	 * @param request
	 *            the request to parse
	 * @return the parsing result
	 * @throws MultipartException
	 *             if multipart resolution failed.
	 */
	@Override
	protected MultipartParsingResult parseRequest(HttpServletRequest request) throws MultipartException {
		String encoding = determineEncoding(request);
		FileUpload fileUpload = prepareFileUpload(encoding);
		try {
			List<FileItem> fileItems = ((ServletFileUpload) fileUpload).parseRequest(request);
			fileItems.forEach(o -> {
				if (o.getSize() > 20971520) {
					try {
						throw new SizeLimitExceededException(
								format("the request was rejected because its size (%s) exceeds the configured maximum (%s)",
										Long.valueOf(o.getSize()), Long.valueOf(20971520)),
								o.getSize(), 20971520);
					} catch (FileUploadBase.SizeLimitExceededException ex) {
						throw new MaxUploadSizeExceededException(fileUpload.getSizeMax(), ex);
					}
				}
			});
			return parseFileItems(fileItems, encoding);
		} catch (FileUploadBase.SizeLimitExceededException ex) {
			throw new MaxUploadSizeExceededException(fileUpload.getSizeMax(), ex);
		} catch (FileUploadException ex) {
			throw new MultipartException("Could not parse multipart servlet request", ex);
		}
	}
}